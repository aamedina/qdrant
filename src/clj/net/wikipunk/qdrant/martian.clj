(ns net.wikipunk.qdrant.martian
  {:rdfs/seeAlso "https://github.com/wkok/openai-clojure/blob/main/src/wkok/openai_clojure/openai.clj"}
  (:require
   [clojure.data.json :as json]
   [clojure.java.io :as io]
   [martian.core :as martian]
   [martian.clj-http :as martian-http]
   [martian.encoders :as encoders]
   [martian.interceptors]
   [martian.openapi :as openapi]
   [reitit.http.interceptors.multipart :as multipart]
   [ring.middleware.multipart-params :as mp]
   [clj-commons.byte-streams :as bs]))

(def ^:dynamic *openapi* nil)

(def ^:dynamic *api-key*
  (System/getenv "QDRANT_API_KEY"))

(def ^:dynamic *base-url*
  (when-some [url (System/getenv "QDRANT_URL")]
    (str (System/getenv "QDRANT_URL") ":" (or (System/getenv "QDRANT_PORT") "6333"))))

(defn- multipart-form-data?
  [handler]
  (-> handler :openapi-definition :requestBody :content :multipart/form-data))

(defn- param->multipart-entry
  [[param content]]
  {:name    (name param)
   :content (if (or (instance? java.io.File content)
                    (instance? java.io.InputStream content)
                    (bytes? content))
              content
              (str content))})

(def multipart-form-data
  {:name  ::multipart-form-data
   :enter (fn [{:keys [handler params] :as ctx}]
            (if (multipart-form-data? handler)
              (-> (assoc-in ctx [:request :multipart]
                            (map param->multipart-entry params))
                  (update-in [:request :headers] dissoc "Content-Type")
                  (update :request dissoc :body))
              ctx))})

(defn bootstrap-openapi
  "Given the OpenAPI spec for the Qdrant API uses Martian to bootstrap
  a client that is configured with a base-url and api-key for Qdrant
  Cloud."
  ([]
   (bootstrap-openapi (io/resource "openapi.json")))
  ([spec]
   (bootstrap-openapi spec *base-url*))
  ([spec base-url]
   (bootstrap-openapi spec base-url *api-key* nil))
  ([spec base-url api-key]
   (bootstrap-openapi spec base-url api-key nil))
  ([spec base-url api-key opts]
   (let [definition (json/read-str (slurp spec))
         encoders   (assoc (select-keys (encoders/default-encoders) ["application/json"])
                           "application/octet-stream" nil
                           ;; TODO: figure out how to make this work
                           #_{:encode bs/to-byte-array
                              :decode slurp
                              :as     :byte-array}
                           "text/plain"               nil
                           "multipart/form-data"      nil)
         opts       (merge {:interceptors
                            (into martian/default-interceptors
                                  [{:name  ::auth
                                    :enter (fn [ctx]
                                             (assoc-in ctx [:request :headers "api-key"] api-key))}
                                   (martian.interceptors/encode-body encoders)
                                   (martian.interceptors/coerce-response encoders)
                                   martian.clj-http/perform-request])}
                           opts)]
     (martian/bootstrap-openapi base-url definition opts))))
