(ns net.wikipunk.qdrant.boot
  {:rdf/type :jsonld/Context})

(def qdrant
  {:rdf/type    :rdfa/PrefixMapping
   :rdfa/uri    "https://wikipunk.net/qdrant/"
   :rdfa/prefix "qdrant"
   :dcat/downloadURL "resources/qdrant.ttl"})
