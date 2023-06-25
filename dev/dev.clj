(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application.
  Call `(reset)` to reload modified code and (re)start the system.
  The system under development is `system`, referred from
  `com.stuartsierra.component.repl/system`.
  See also https://github.com/stuartsierra/component.repl"
  (:require
   [clojure.datafy :refer [datafy]]
   [clojure.data.json :as json]
   [clojure.edn :as edn]
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pprint pp]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir find-doc pst source]]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.tools.logging :as log]
   [clojure.tools.namespace.repl :refer [refresh refresh-all clear]]
   [com.stuartsierra.component :as com]
   [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
   [com.walmartlabs.schematic :as sc]
   [net.wikipunk.boot]
   [net.wikipunk.ext]
   [net.wikipunk.mop :as mop :refer [isa? descendants parents ancestors]]
   [net.wikipunk.rdf :as rdf :refer [doc]]
   [zprint.core :as zprint]
   [net.wikipunk.qdrant.boot :as boot]
   [net.wikipunk.qdrant :as qdrant]
   [net.wikipunk.qdrant.martian :as qdrant.martian]
   [martian.core :as martian]
   [martian.clj-http :as martian-http]
   [martian.openapi :as openapi])
  (:refer-clojure :exclude [isa? descendants parents ancestors]))

(set-init
  (fn [_]
    (if-let [r (io/resource "system.edn")]
      (-> (slurp r)
          (edn/read-string)
          (sc/assemble-system))
      (throw (ex-info "system.edn is not on classpath" {})))))

(defmacro inspect
  "Evaluate forms in an implicit do and inspect the value of the last
  expression using Reveal."
  [& body]
  `(do (@user/reveal (do ~@body))
       true))

(declare transform)

(defn transform-data
  [{:jsonschema/keys [allOf anyOf const default enum oneOf propertyName required properties exclusiveMaximum exclusiveMinimum maximum minimum multipleOf format items uniqueItems minItems maxItems minLength]
    :dcterms/keys    [description title]
    :as              data}]
  (let [rdf-type  (mop/type-of data)
        json-type (cond 
                    (isa? rdf-type :jsonschema/ArraySchema)   "array"
                    (isa? rdf-type :jsonschema/BooleanSchema) "boolean"
                    (isa? rdf-type :jsonschema/IntegerSchema) "integer"
                    (isa? rdf-type :jsonschema/NullSchema)    "null"
                    (isa? rdf-type :jsonschema/NumberSchema)  "number"
                    (isa? rdf-type :jsonschema/StringSchema)  "string"
                    (isa? rdf-type :jsonschema/ObjectSchema)  (if (identical? (mop/type-of data) :jsonschema/ObjectSchema)
                                                                "object"
                                                                (mop/type-of data))
                    :else                                     "object")]
    (cond-> (cond
              (qualified-keyword? json-type)
              (transform rdf-type)

              (= rdf-type :owl/Class)
              (if (isa? (:db/ident data) :jsonschema/NullSchema)
                {:type "null"}
                {:type "object"})
              
              :else {:type json-type})
      description      (assoc :description description)
      title            (assoc :title title)
      allOf            (assoc :allOf (mapv transform allOf))
      anyOf            (assoc :anyOf (mapv transform anyOf))
      const            (assoc :const (first (vals const)))
      default          (assoc :default (first (vals default)))
      enum             (assoc :enum enum)
      oneOf            (assoc :oneOf (mapv transform oneOf))
      required         (assoc :required required)
      propertyName     (assoc :propertyName propertyName)
      properties       (assoc :properties (update-vals (group-by (comp keyword :propertyName) (mapv transform (if (sequential? properties) properties [properties]))) #(dissoc (first %) :propertyName)))
      exclusiveMaximum (assoc :exclusiveMaximum (case json-type "integer" (long exclusiveMaximum) exclusiveMaximum))
      exclusiveMinimum (assoc :exclusiveMinimum (case json-type "integer" (long exclusiveMinimum) exclusiveMinimum))
      maximum          (assoc :maximum (case json-type "integer" (long maximum) maximum))
      minimum          (assoc :minimum (case json-type "integer" (long minimum) minimum))
      minItems         (assoc :minItems (case json-type "integer" (long minItems) minItems))
      maxItems         (assoc :maxItems (case json-type "integer" (long maxItems) maxItems))
      minLength        (assoc :minLength (case json-type "integer" (long minLength) minLength))
      multipleOf       (assoc :multipleOf (case json-type "integer" (long multipleOf) multipleOf))
      format           (assoc :format format)
      items            (assoc :items (if (sequential? items)
                                       (mapv transform items)
                                       (transform items)))
      uniqueItems      (assoc :uniqueItems uniqueItems))))

(defn transform
  [schema]
  (if (qualified-keyword? schema)
    (transform-data (datafy schema))
    (transform-data schema)))

(defn ns-metaobjects
  [ns-name]
  (for [sym (->> (ns-publics ns-name)
                 (vals)
                 (map deref)
                 (sort-by :db/ident)
                 (remove #(= (:vs/term_status %) "deprecated"))
                 (remove :owl/deprecated)
                 (remove #(and (isa? (:db/ident %) :owl/DeprecatedProperty)
                               (not= (:db/ident %) :owl/DeprecatedProperty)))
                 (remove #(and (isa? (:db/ident %) :owl/DeprecatedClass)
                               (not= (:db/ident %) :owl/DeprecatedClass)))
                 (map :db/ident))]
    sym))

(def lock (Object.))

(defn embed-ns
  [ns-name file]
  (for [sym (ns-metaobjects ns-name)]
    (future
      (try
        (binding [*print-length* nil]
          (locking lock
            (spit file
                  (prn-str [sym (qdrant/embed sym)])
                  :append true)))
        (catch Throwable ex
          (println (.getMessage ex) "Could not embed:" sym))))))

(defn create-metaobjects-collection
  [collection-name]
  (let [res1 (qdrant/create-collection :collection_name collection-name
                                       :vectors  {:size 1536 :distance "Cosine"}
                                       :optimizers_config {:memmap_threshold 20000}
                                       :hnsw_config {:on_disk      true
                                                     :m            64
                                                     :ef_construct 512})
        res2 (qdrant/create-field-index :collection_name collection-name
                                        :field_name "ident"
                                        :field_schema {:type      "text"
                                                       :tokenizer "prefix"})]
    [[:create res1] [:index res2]]))

(defn create-in-memory-metaobjects-collection
  [collection-name]
  (let [res1 (qdrant/create-collection :collection_name collection-name
                                       :vectors  {:size 1536 :distance "Cosine"}
                                       :optimizers_config {:memmap_threshold 20000})
        res2 (qdrant/create-field-index :collection_name collection-name
                                        :field_name "ident"
                                        :field_schema {:type      "text"
                                                       :tokenizer "prefix"})]
    [[:create res1] [:index res2]]))

(defn reset-metaobjects-collection
  [collection-name]
  (qdrant/delete-collection :collection_name collection-name)
  (create-metaobjects-collection collection-name))

(defn upsert-file
  ([file]
   (upsert-file file qdrant/*collection*))
  ([file collection]
   (upsert-file file collection (map identity)))
  ([file collection xf]
   (with-open [r (java.io.PushbackReader. (io/reader file))]
     (transduce (comp
                  (take-while (complement nil?))
                  xf
                  (map (fn [[k v]]
                         {:id      (qdrant/ident-uuid collection k)
                          :vector  v
                          :payload {:ident (str k)}}))
                  (partition-all 1024))
                (completing
                  (fn [acc points]
                    (Thread/sleep 250)
                    (try (conj acc (qdrant/upsert-points :collection_name collection :points points))
                         (catch Throwable ex
                           (log/error ex)
                           (reduced points)))))
                []
                (repeatedly #(edn/read {:eof nil} r))))))

(defn validate-file
  "ugly"
  [file]
  (with-open [in (io/reader file)
              r  (java.io.PushbackReader. in)]
    (binding [*in* in]
      (into []
            (comp
              (take-while (complement nil?))
              (map (fn [[k v]]
                     {:id      (random-uuid)
                      :vector  v
                      :payload {:ident (str k)}})))
            (repeatedly #(let [x (edn/read {:eof nil} r)]
                           (when x
                             (prn (first x))
                             (assert (= (count (second x)) 1536) x))                           
                           x))))))

(defn upsert-point
  [collection k]
  (qdrant/upsert-points :collection_name collection :points [{:id      (qdrant/ident-uuid collection k)
                                                              :vector  (qdrant/embed k)
                                                              :payload {:ident (str k)}}]))
