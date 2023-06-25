(ns net.wikipunk.qdrant-test
  "Tests for the Qdrant API client."
  (:require
   [clojure.test :refer :all]
   [clojure.test.check :as tc]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [clojure.test.check.clojure-test :refer [defspec]]
   [net.wikipunk.rdf :as rdf]
   [net.wikipunk.mop :as mop :refer [isa? descendants ancestors parents]]
   [net.wikipunk.qdrant :as qdrant])
  (:refer-clojure :exclude [isa? descendants ancestors parents]))

;; TODO: write tests!

;; Inspiration: https://github.com/wkok/openai-clojure/tree/main/test/wkok/openai_clojure
