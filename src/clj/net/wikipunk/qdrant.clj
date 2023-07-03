(ns net.wikipunk.qdrant
  "Qdrant Cloud API"
  (:require
   [clj-uuid :as uuid]
   [clojure.core.memoize :as memo]
   [clojure.datafy :refer [datafy]]
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.data.json :as json]
   [clojure.walk :as walk]
   [clojure.tools.logging :as log]
   [clojure.spec.alpha :as s]
   [com.stuartsierra.component :as com]
   [martian.core :as martian]
   [net.wikipunk.qdrant.boot]
   [net.wikipunk.rdf.qdrant :as qdrant]
   [net.wikipunk.rdf.jsonschema]
   [net.wikipunk.rdf.dcterms]
   [net.wikipunk.rdf.qdrant]
   [net.wikipunk.rdf.rdf]
   [net.wikipunk.rdf.rdfs]
   [net.wikipunk.rdf.owl]
   [net.wikipunk.rdf.xsd]
   [net.wikipunk.rdf.dcterms]
   [net.wikipunk.rdf.as]
   [net.wikipunk.rdf.schema]
   [net.wikipunk.rdf :as rdf]
   [net.wikipunk.mop :as mop :refer [isa? parents ancestors descendants]]
   [net.wikipunk.openai :as openai]
   [net.wikipunk.qdrant.martian :as qdrant.martian])
  (:refer-clojure :exclude [isa? parents ancestors descendants]))

(defrecord Client [base-url api-key client]
  com/Lifecycle
  (start [this]
    (binding [qdrant.martian/*api-key*  (or api-key qdrant.martian/*api-key*)
              qdrant.martian/*base-url* (or base-url qdrant.martian/*base-url*)]        
      (let [client (qdrant.martian/bootstrap-openapi)]
        (when-not (thread-bound? #'qdrant.martian/*openapi*)
          (alter-var-root #'qdrant.martian/*openapi* (constantly client)))
        (assoc this
               :client client
               :base-url qdrant.martian/*base-url*
               :api-key qdrant.martian/*api-key*))))
  (stop [this]
    (when-not (thread-bound? #'qdrant.martian/*openapi*)
      (alter-var-root #'qdrant.martian/*openapi* (constantly nil)))
    (assoc this :client nil :base-url nil :api-key nil)))

(def ^:dynamic *uuid-ns* uuid/+null+)
(def ^:dynamic *collection* "metaobjects")
(def ^:dynamic *limit* 25)

(def mem-v5
  "memoized uuid/v5"
  (memo/memo uuid/v5))

(defn collection-uuid
  ([]
   (collection-uuid *collection*))
  ([collection-name]
   (collection-uuid *uuid-ns* collection-name))
  ([uuid-ns collection-name]
   (mem-v5 uuid-ns collection-name)))

(defn ident-uuid
  ([ident]
   (ident-uuid *uuid-ns* *collection* ident))
  ([collection-name ident]
   (ident-uuid *uuid-ns* collection-name ident))
  ([uuid-ns collection-name ident]
   (mem-v5 (collection-uuid uuid-ns collection-name) ident)))

(def mem-embeddings
  "memoized openai/embeddings"
  (memo/memo openai/embeddings))

(defmacro defapi []
  (let [openapi-spec (qdrant.martian/bootstrap-openapi)]
    (when-not (thread-bound? #'qdrant.martian/*openapi*)
      (alter-var-root #'qdrant.martian/*openapi* (constantly openapi-spec)))
    `(do       
       ~@(for [{:keys [description route-name parameter-aliases]}
               (:handlers openapi-spec)]
           `(defn ~(symbol route-name)
              ~(str description)
              {:arglists '([:keys [~@(map symbol (mapcat keys (mapcat vals (vals parameter-aliases))))]])}
              [& {:keys [~@(map symbol (mapcat keys (mapcat vals (vals parameter-aliases))))]
                  :as   ~'params}]
              (:body (martian/response-for qdrant.martian/*openapi* ~route-name (or ~'params {}))))))))

(defapi)

(s/def ::filter (s/coll-of string?))

(s/def ::must ::filter)
(s/def ::must_not ::filter)
(s/def ::should ::filter)
(s/def ::collection string?)
(s/def ::positive (s/coll-of qualified-keyword?))
(s/def ::negative (s/coll-of qualified-keyword?))

(defmulti embed-value
  "Uses the metaobjects hierarchy to determine the value to embed."
  (fn [x] (if (qualified-keyword? x) x mop/type-of))
  :hierarchy #'mop/*metaobjects*)

(defn embed
  "Embed a metaobject into high dimensional space."
  [x]
  (binding [*print-length* 250]
    (get-in (mem-embeddings :input (pr-str (embed-value x))
                            :model "text-embedding-ada-002")
            [:data 0 :embedding])))

(def ^:dynamic *dont-embed*
  #{:d3f/d3fend-kb-annotation-property})

(defmethod embed-value :rdfs/Resource
  [x]
  (reduce-kv (fn [m k v]
               (cond
                 (some #(isa? k %) *dont-embed*)
                 m
                 
                 :else (assoc m k v)))
             {} (datafy x)))

(defmethod embed-value :default
  [x]
  ;; by default attempt to embed the value directly
  x)


(defn search
  "Returns a vector of metaobject :db/ident to computed score in the
  qdrant collection.

  This function should only be used when using the Client component in
  the context of a RDF-aware peer for wikipunk.net."
  [query & {:keys [limit collection_name should must must_not consistency]
            :or   {limit *limit* collection_name *collection*}}]
  (when-some [res (search-points
                    (cond-> {:collection_name collection_name
                             :vector          (if (vector? query) query (embed query))
                             :limit           limit
                             :with_payload    ["ident"]}
                      should
                      (assoc-in [:filter :should]
                                (into []
                                      (map (fn [match]
                                             {:key "ident" :match {:text match}}))
                                      should))

                      must
                      (assoc-in [:filter :must]
                                (into []
                                      (map (fn [match]
                                             {:key "ident" :match {:text match}}))
                                      must))

                      must_not
                      (assoc-in [:filter :must_not]
                                (into []
                                      (map (fn [match]
                                             {:key "ident" :match {:text match}}))
                                      must_not))

                      consistency
                      (assoc :consistency consistency)))]
    (if (= (:status res) "ok")
      (mapv (fn [{:keys [id score payload] :as md}]
              (let [{:keys [ident]} payload]
                (edn/read-string ident)))
            (:result res))
      (throw (ex-info "Your search could not be processed successfully." res)))))

(defn recommend
  "Returns points closest to positive and tries to avoid vectors like negative.

  This function should only be used when using the Client component in
  the context of a RDF-aware peer for wikipunk.net."
  [positive & {:keys [negative limit collection_name should must must_not consistency]
               :or   {limit *limit* collection_name *collection*}}]
  (when-some [res (recommend-points
                    (cond-> {:collection_name collection_name
                             :positive        (mapv (partial ident-uuid collection_name) positive)
                             :limit           limit
                             :with_payload    ["ident"]}
                      negative
                      (assoc :negative (mapv (partial ident-uuid collection_name) negative))
                      
                      should
                      (assoc-in [:filter :should]
                                (into []
                                      (map (fn [match]
                                             {:key "ident" :match {:text match}}))
                                      should))

                      must
                      (assoc-in [:filter :must]
                                (into []
                                      (map (fn [match]
                                             {:key "ident" :match {:text match}}))
                                      must))

                      must_not
                      (assoc-in [:filter :must_not]
                                (into []
                                      (map (fn [match]
                                             {:key "ident" :match {:text match}}))
                                      must_not))

                      consistency
                      (assoc :consistency consistency)))]
    (if (= (:status res) "ok")
      (mapv (fn [{:keys [id score payload] :as md}]
              (let [{:keys [ident]} payload]
                (edn/read-string ident)))
            (:result res))
      (throw (ex-info "Your search could not be processed successfully." res)))))

