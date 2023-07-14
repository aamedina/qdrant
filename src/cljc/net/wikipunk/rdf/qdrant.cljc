(ns net.wikipunk.rdf.qdrant
  "Qdrant Ontology"
  {:dcat/downloadURL  "resources/qdrant.ttl",
   :rdf/ns-prefix-map {"as" "https://www.w3.org/ns/activitystreams#",
                       "dcterms" "http://purl.org/dc/terms/",
                       "jsonschema" "https://www.w3.org/2019/wot/json-schema#",
                       "owl" "http://www.w3.org/2002/07/owl#",
                       "qdrant" "https://wikipunk.net/qdrant/",
                       "rdf" "http://www.w3.org/1999/02/22-rdf-syntax-ns#",
                       "rdfs" "http://www.w3.org/2000/01/rdf-schema#",
                       "schema" "http://schema.org/",
                       "xsd" "http://www.w3.org/2001/XMLSchema#"},
   :rdf/type          :owl/Ontology,
   :rdfa/prefix       "qdrant",
   :rdfa/uri          "https://wikipunk.net/qdrant/",
   :rdfs/label        #xsd/string "Qdrant Ontology"})

(def AbortTransferOperation
  {:db/ident :qdrant/AbortTransferOperation,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string
                                                      "abort_transfer",
                            :rdf/type :qdrant/MoveShard}],
   :jsonschema/required [#xsd/string "abort_transfer"],
   :rdf/type :jsonschema/ObjectSchema})

(def AliasDescription
  {:db/ident :qdrant/AliasDescription,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string
                                                      "collection_name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName #xsd/string "alias_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "alias_name"
                         #xsd/string "collection_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def AliasOperations
  "Group of all the possible operations related to collection aliases"
  {:db/ident         :qdrant/AliasOperations,
   :dcterms/description
   #xsd/string
    "Group of all the possible operations related to collection aliases",
   :jsonschema/anyOf [:qdrant/CreateAliasOperation
                      :qdrant/DeleteAliasOperation
                      :qdrant/RenameAliasOperation],
   :rdf/type         :jsonschema/DataSchema})

(def AnyVariants
  {:db/ident         :qdrant/AnyVariants,
   :jsonschema/anyOf {:jsonschema/items [{:jsonschema/items
                                          {:jsonschema/format #xsd/string
                                                               "int64",
                                           :rdf/type :jsonschema/IntegerSchema},
                                          :rdf/type :jsonschema/ArraySchema}
                                         {:rdf/type :jsonschema/StringSchema}],
                      :rdf/type         :jsonschema/ArraySchema},
   :rdf/type         :jsonschema/DataSchema})

(def AppBuildTelemetry
  {:db/ident :qdrant/AppBuildTelemetry,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "date-time",
     :jsonschema/propertyName #xsd/string "startup",
     :rdf/type :jsonschema/StringSchema}
    {:jsonschema/anyOf [:qdrant/RunningEnvironmentTelemetry
                        :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "system",
     :rdf/type         :jsonschema/ObjectSchema}
    {:jsonschema/anyOf [:qdrant/AppFeaturesTelemetry :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "features",
     :rdf/type         :jsonschema/ObjectSchema}
    {:jsonschema/propertyName #xsd/string "version",
     :rdf/type :jsonschema/StringSchema}
    {:jsonschema/propertyName #xsd/string "name",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required
   [#xsd/string "name" #xsd/string "startup" #xsd/string "version"],
   :rdf/type :jsonschema/ObjectSchema})

(def AppFeaturesTelemetry
  {:db/ident :qdrant/AppFeaturesTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "web_feature",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName #xsd/string
                                                      "service_debug_feature",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName #xsd/string "debug",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required [#xsd/string "debug"
                         #xsd/string "service_debug_feature"
                         #xsd/string "web_feature"],
   :rdf/type :jsonschema/ObjectSchema})

(def Batch
  {:db/ident :qdrant/Batch,
   :jsonschema/properties
   [{:jsonschema/items {:jsonschema/anyOf [:qdrant/Payload
                                           :jsonschema/NullSchema],
                        :rdf/type         :jsonschema/ObjectSchema},
     :jsonschema/propertyName #xsd/string "payloads",
     :rdf/type         :jsonschema/ArraySchema}
    {:jsonschema/propertyName #xsd/string "vectors",
     :rdf/type :qdrant/BatchVectorStruct}
    {:jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName #xsd/string "ids",
     :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "ids" #xsd/string "vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def BatchVectorStruct
  {:db/ident         :qdrant/BatchVectorStruct,
   :jsonschema/anyOf [{:jsonschema/properties {:jsonschema/items
                                               {:jsonschema/items
                                                {:jsonschema/format #xsd/string
                                                                     "float",
                                                 :rdf/type
                                                 :jsonschema/NumberSchema},
                                                :rdf/type
                                                :jsonschema/ArraySchema},
                                               :rdf/type
                                               :jsonschema/ArraySchema},
                       :rdf/type :jsonschema/ObjectSchema}
                      {:jsonschema/items {:jsonschema/items
                                          {:jsonschema/format #xsd/string
                                                               "float",
                                           :rdf/type :jsonschema/NumberSchema},
                                          :rdf/type :jsonschema/ArraySchema},
                       :rdf/type         :jsonschema/ArraySchema}],
   :rdf/type         :jsonschema/DataSchema})

(def ChangeAliasesOperation
  "Operation for performing changes of collection aliases. Alias changes are atomic, meaning that no collection modifications can happen between alias operations."
  {:db/ident :qdrant/ChangeAliasesOperation,
   :dcterms/description
   #xsd/string
    "Operation for performing changes of collection aliases. Alias changes are atomic, meaning that no collection modifications can happen between alias operations.",
   :jsonschema/properties [{:jsonschema/items :qdrant/AliasOperations,
                            :jsonschema/propertyName #xsd/string "actions",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "actions"],
   :rdf/type :jsonschema/ObjectSchema})

(def ClusterConfigTelemetry
  {:db/ident :qdrant/ClusterConfigTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "consensus",
                            :rdf/type :qdrant/ConsensusConfigTelemetry}
                           {:jsonschema/propertyName #xsd/string "p2p",
                            :rdf/type :qdrant/P2pConfigTelemetry}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string
                                                      "grpc_timeout_ms",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required
   [#xsd/string "consensus" #xsd/string "grpc_timeout_ms" #xsd/string "p2p"],
   :rdf/type :jsonschema/ObjectSchema})

(def ClusterOperations
  {:db/ident         :qdrant/ClusterOperations,
   :jsonschema/anyOf [:qdrant/MoveShardOperation
                      :qdrant/ReplicateShardOperation
                      :qdrant/AbortTransferOperation
                      :qdrant/DropReplicaOperation],
   :rdf/type         :jsonschema/DataSchema})

(def ClusterStatus
  "Information about current cluster status and structure"
  {:db/ident :qdrant/ClusterStatus,
   :dcterms/description
   #xsd/string "Information about current cluster status and structure",
   :jsonschema/oneOf
   [{:jsonschema/properties {:jsonschema/enum [#xsd/string "disabled"],
                             :jsonschema/propertyName #xsd/string "status",
                             :rdf/type        :jsonschema/StringSchema},
     :jsonschema/required [#xsd/string "status"],
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Description of enabled cluster",
     :jsonschema/properties
     [{:dcterms/description
       #xsd/string
        "Consequent failures of message send operations in consensus by peer address. On the first success to send to that peer - entry is removed from this hashmap.",
       :jsonschema/properties :qdrant/MessageSendErrors,
       :jsonschema/propertyName #xsd/string "message_send_failures",
       :rdf/type :jsonschema/ObjectSchema}
      {:jsonschema/propertyName #xsd/string "consensus_thread_status",
       :rdf/type :qdrant/ConsensusThreadStatus}
      {:jsonschema/propertyName #xsd/string "raft_info",
       :rdf/type :qdrant/RaftInfo}
      {:dcterms/description
       #xsd/string "Peers composition of the cluster with main information",
       :jsonschema/properties :qdrant/PeerInfo,
       :jsonschema/propertyName #xsd/string "peers",
       :rdf/type :jsonschema/ObjectSchema}
      {:dcterms/description #xsd/string "ID of this peer",
       :jsonschema/format   #xsd/string "uint64",
       :jsonschema/minimum  0.0,
       :jsonschema/propertyName #xsd/string "peer_id",
       :rdf/type            :jsonschema/IntegerSchema}
      {:jsonschema/enum [#xsd/string "enabled"],
       :jsonschema/propertyName #xsd/string "status",
       :rdf/type        :jsonschema/StringSchema}],
     :jsonschema/required [#xsd/string "consensus_thread_status"
                           #xsd/string "message_send_failures"
                           #xsd/string "peer_id"
                           #xsd/string "peers"
                           #xsd/string "raft_info"
                           #xsd/string "status"],
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/DataSchema})

(def ClusterStatusTelemetry
  {:db/ident :qdrant/ClusterStatusTelemetry,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "peer_id",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "role",
     :rdf/type :qdrant/StateRole}
    {:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "term",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "pending_operations",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "commit",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "is_voter",
     :rdf/type :jsonschema/BooleanSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "number_of_peers",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "consensus_thread_status",
     :rdf/type :qdrant/ConsensusThreadStatus}],
   :jsonschema/required [#xsd/string "commit"
                         #xsd/string "consensus_thread_status"
                         #xsd/string "is_voter"
                         #xsd/string "number_of_peers"
                         #xsd/string "pending_operations"
                         #xsd/string "term"],
   :rdf/type :jsonschema/ObjectSchema})

(def ClusterTelemetry
  {:db/ident :qdrant/ClusterTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "config",
                            :rdf/type :qdrant/ClusterConfigTelemetry}
                           {:jsonschema/propertyName #xsd/string "status",
                            :rdf/type :qdrant/ClusterStatusTelemetry}
                           {:jsonschema/propertyName #xsd/string "enabled",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required [#xsd/string "enabled"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionClusterInfo
  "Current clustering distribution for the collection"
  {:db/ident :qdrant/CollectionClusterInfo,
   :dcterms/description #xsd/string
                         "Current clustering distribution for the collection",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Shard transfers",
     :jsonschema/items :qdrant/ShardTransferInfo,
     :jsonschema/propertyName #xsd/string "shard_transfers",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "Remote shards",
     :jsonschema/items :qdrant/RemoteShardInfo,
     :jsonschema/propertyName #xsd/string "remote_shards",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "Local shards",
     :jsonschema/items :qdrant/LocalShardInfo,
     :jsonschema/propertyName #xsd/string "local_shards",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "Total number of shards",
     :jsonschema/format   #xsd/string "uint",
     :jsonschema/minimum  0.0,
     :jsonschema/propertyName #xsd/string "shard_count",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "ID of this peer",
     :jsonschema/format   #xsd/string "uint64",
     :jsonschema/minimum  0.0,
     :jsonschema/propertyName #xsd/string "peer_id",
     :rdf/type            :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "local_shards"
                         #xsd/string "peer_id"
                         #xsd/string "remote_shards"
                         #xsd/string "shard_count"
                         #xsd/string "shard_transfers"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionConfig
  {:db/ident :qdrant/CollectionConfig,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "quantization_config",
     :rdf/type :qdrant/QuantizationConfig}
    {:jsonschema/propertyName #xsd/string "wal_config",
     :rdf/type :qdrant/WalConfig}
    {:jsonschema/propertyName #xsd/string "optimizer_config",
     :rdf/type :qdrant/OptimizersConfig}
    {:jsonschema/propertyName #xsd/string "hnsw_config",
     :rdf/type :qdrant/HnswConfig}
    {:jsonschema/propertyName #xsd/string "params",
     :rdf/type :qdrant/CollectionParams}],
   :jsonschema/required [#xsd/string "hnsw_config"
                         #xsd/string "optimizer_config"
                         #xsd/string "params"
                         #xsd/string "wal_config"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionDescription
  {:db/ident :qdrant/CollectionDescription,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "name"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionInfo
  "Current statistics and configuration of the collection"
  {:db/ident :qdrant/CollectionInfo,
   :dcterms/description
   #xsd/string "Current statistics and configuration of the collection",
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "optimizer_status",
     :rdf/type :qdrant/OptimizersStatus}
    {:jsonschema/propertyName #xsd/string "config",
     :rdf/type :qdrant/CollectionConfig}
    {:dcterms/description
     #xsd/string
      "Number of vectors in collection All vectors in collection are available for querying Calculated as `points_count x vectors_per_point` Where `vectors_per_point` is a number of named vectors in schema",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "vectors_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of points (vectors + payloads) in collection Each point could be accessed by unique id",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "points_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of segments in collection. Each segment has independent vector as payload indexes",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "segments_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of indexed vectors in the collection. Indexed vectors in large segments are faster to query, as it is stored in vector index (HNSW)",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "indexed_vectors_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "Types of stored payload",
     :jsonschema/propertyName #xsd/string "payload_schema",
     :rdf/type :qdrant/PayloadIndexInfo}
    {:jsonschema/propertyName #xsd/string "status",
     :rdf/type :qdrant/CollectionStatus}],
   :jsonschema/required [#xsd/string "config"
                         #xsd/string "indexed_vectors_count"
                         #xsd/string "optimizer_status"
                         #xsd/string "payload_schema"
                         #xsd/string "points_count"
                         #xsd/string "segments_count"
                         #xsd/string "status"
                         #xsd/string "vectors_count"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionParams
  {:db/ident :qdrant/CollectionParams,
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "If true - point's payload will not be stored in memory. It will be read from the disk every time it is requested. This setting saves RAM by (slightly) increasing the response time. Note: those payload values that are involved in filtering and are indexed - remain in RAM.",
     :jsonschema/default #xsd/boolean false,
     :jsonschema/propertyName #xsd/string "on_disk_payload",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     #xsd/string
      "Defines how many replicas should apply the operation for us to consider it successful. Increasing this number will make the collection more resilient to inconsistencies, but will also make it fail if not enough replicas are available. Does not have any performance impact.",
     :jsonschema/default #xsd/integer 1,
     :jsonschema/format #xsd/string "uint32",
     :jsonschema/minimum 1.0,
     :jsonschema/propertyName #xsd/string "write_consistency_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "Number of replicas for each shard",
     :jsonschema/default  #xsd/integer 1,
     :jsonschema/format   #xsd/string "uint32",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "replication_factor",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "Number of shards the collection has",
     :jsonschema/default  #xsd/integer 1,
     :jsonschema/format   #xsd/string "uint32",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "shard_number",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "vectors",
     :rdf/type :qdrant/VectorsConfig}],
   :jsonschema/required [#xsd/string "vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionParamsDiff
  {:db/ident :qdrant/CollectionParamsDiff,
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Minimal number successful responses from replicas to consider operation successful",
     :jsonschema/format #xsd/string "uint32",
     :jsonschema/minimum 1.0,
     :jsonschema/propertyName #xsd/string "write_consistency_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "Number of replicas for each shard",
     :jsonschema/format   #xsd/string "uint32",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "replication_factor",
     :rdf/type            :jsonschema/IntegerSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionStatus
  "Current state of the collection. `Green` - all good. `Yellow` - optimization is running, `Red` - some operations failed and was not recovered"
  {:db/ident        :qdrant/CollectionStatus,
   :dcterms/description
   #xsd/string
    "Current state of the collection. `Green` - all good. `Yellow` - optimization is running, `Red` - some operations failed and was not recovered",
   :jsonschema/enum [#xsd/string "green"
                     #xsd/string "yellow"
                     #xsd/string "red"],
   :rdf/type        :jsonschema/StringSchema})

(def CollectionTelemetry
  {:db/ident :qdrant/CollectionTelemetry,
   :jsonschema/properties [{:jsonschema/items :qdrant/ShardTransferInfo,
                            :jsonschema/propertyName #xsd/string "transfers",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/items :qdrant/ReplicaSetTelemetry,
                            :jsonschema/propertyName #xsd/string "shards",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName #xsd/string "config",
                            :rdf/type :qdrant/CollectionConfig}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "init_time_ms",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "id",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "config"
                         #xsd/string "id"
                         #xsd/string "init_time_ms"
                         #xsd/string "shards"
                         #xsd/string "transfers"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionTelemetryEnum
  {:db/ident         :qdrant/CollectionTelemetryEnum,
   :jsonschema/anyOf [:qdrant/CollectionTelemetry
                      :qdrant/CollectionsAggregatedTelemetry],
   :rdf/type         :jsonschema/DataSchema})

(def CollectionsAggregatedTelemetry
  {:db/ident :qdrant/CollectionsAggregatedTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "params",
                            :rdf/type :qdrant/CollectionParams}
                           {:jsonschema/propertyName #xsd/string
                                                      "optimizers_status",
                            :rdf/type :qdrant/OptimizersStatus}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "vectors",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required
   [#xsd/string "optimizers_status" #xsd/string "params" #xsd/string "vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionsAliasesResponse
  {:db/ident :qdrant/CollectionsAliasesResponse,
   :jsonschema/properties [{:jsonschema/items :qdrant/AliasDescription,
                            :jsonschema/propertyName #xsd/string "aliases",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "aliases"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionsResponse
  {:db/ident :qdrant/CollectionsResponse,
   :jsonschema/properties [{:jsonschema/items :qdrant/CollectionDescription,
                            :jsonschema/propertyName #xsd/string "collections",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "collections"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionsTelemetry
  {:db/ident :qdrant/CollectionsTelemetry,
   :jsonschema/properties [{:jsonschema/items :qdrant/CollectionTelemetryEnum,
                            :jsonschema/propertyName #xsd/string "collections",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string
                                                      "number_of_collections",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "number_of_collections"],
   :rdf/type :jsonschema/ObjectSchema})

(def CompressionRatio
  {:db/ident        :qdrant/CompressionRatio,
   :jsonschema/enum [#xsd/string "x4"
                     #xsd/string "x8"
                     #xsd/string "x16"
                     #xsd/string "x32"
                     #xsd/string "x64"],
   :rdf/type        :jsonschema/StringSchema})

(def Condition
  {:db/ident         :qdrant/Condition,
   :jsonschema/oneOf [:qdrant/FieldCondition
                      :qdrant/IsEmptyCondition
                      :qdrant/IsNullCondition
                      :qdrant/HasIdCondition
                      :qdrant/NestedCondition
                      :qdrant/Filter],
   :rdf/type         :jsonschema/DataSchema})

(def ConsensusConfigTelemetry
  {:db/ident :qdrant/ConsensusConfigTelemetry,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "bootstrap_timeout_sec",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "tick_period_ms",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_message_queue_size",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "bootstrap_timeout_sec"
                         #xsd/string "max_message_queue_size"
                         #xsd/string "tick_period_ms"],
   :rdf/type :jsonschema/ObjectSchema})

(def ConsensusThreadStatus
  "Information about current consensus thread status"
  {:db/ident :qdrant/ConsensusThreadStatus,
   :dcterms/description #xsd/string
                         "Information about current consensus thread status",
   :jsonschema/oneOf
   [{:jsonschema/properties
     [{:jsonschema/format #xsd/string "date-time",
       :jsonschema/propertyName #xsd/string "last_update",
       :rdf/type :jsonschema/StringSchema}
      {:jsonschema/enum [#xsd/string "working"],
       :jsonschema/propertyName #xsd/string "consensus_thread_status",
       :rdf/type        :jsonschema/StringSchema}],
     :jsonschema/required [#xsd/string "consensus_thread_status"
                           #xsd/string "last_update"],
     :rdf/type :jsonschema/ObjectSchema}
    {:jsonschema/properties {:jsonschema/enum [#xsd/string "stopped"],
                             :jsonschema/propertyName
                             #xsd/string "consensus_thread_status",
                             :rdf/type        :jsonschema/StringSchema},
     :jsonschema/required [#xsd/string "consensus_thread_status"],
     :rdf/type :jsonschema/ObjectSchema}
    {:jsonschema/properties [{:jsonschema/propertyName #xsd/string "err",
                              :rdf/type :jsonschema/StringSchema}
                             {:jsonschema/enum [#xsd/string "stopped_with_err"],
                              :jsonschema/propertyName
                              #xsd/string "consensus_thread_status",
                              :rdf/type        :jsonschema/StringSchema}],
     :jsonschema/required [#xsd/string "consensus_thread_status"
                           #xsd/string "err"],
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/DataSchema})

(def CountRequest
  "Count Request Counts the number of points which satisfy the given filter. If filter is not provided, the count of all points in the collection will be returned."
  {:db/ident :qdrant/CountRequest,
   :dcterms/description
   #xsd/string
    "Count Request Counts the number of points which satisfy the given filter. If filter is not provided, the count of all points in the collection will be returned.",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "If true, count exact number of points. If false, count approximate number of points faster. Approximate count might be unreliable during the indexing process. Default: true",
     :jsonschema/default #xsd/boolean true,
     :jsonschema/propertyName #xsd/string "exact",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     #xsd/string "Look only for points which satisfies this conditions",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "filter",
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def CountResult
  {:db/ident :qdrant/CountResult,
   :jsonschema/properties [{:dcterms/description
                            #xsd/string
                             "Number of points which satisfy the conditions",
                            :jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "count",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "count"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateAlias
  "Create alternative name for a collection. Collection will be available under both names for search, retrieve,"
  {:db/ident :qdrant/CreateAlias,
   :dcterms/description
   #xsd/string
    "Create alternative name for a collection. Collection will be available under both names for search, retrieve,",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "alias_name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName #xsd/string
                                                      "collection_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "alias_name"
                         #xsd/string "collection_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateAliasOperation
  {:db/ident :qdrant/CreateAliasOperation,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "create_alias",
                            :rdf/type :qdrant/CreateAlias}],
   :jsonschema/required [#xsd/string "create_alias"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateCollection
  "Operation for creating new collection and (optionally) specify index params"
  {:db/ident :qdrant/CreateCollection,
   :dcterms/description
   #xsd/string
    "Operation for creating new collection and (optionally) specify index params",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string "Number of shards replicas. Default is 1 Minimum is 1",
     :jsonschema/format #xsd/string "uint32",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "replication_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of shards in collection. Default is 1 for standalone, otherwise equal to the number of nodes Minimum is 1",
     :jsonschema/format #xsd/string "uint32",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "shard_number",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Custom params for HNSW index. If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/HnswConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "hnsw_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description
     #xsd/string
      "Custom params for WAL. If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/WalConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "wal_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Specify other collection to copy data from.",
     :jsonschema/anyOf [:qdrant/InitFrom :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "init_from",
     :rdf/type :jsonschema/DataSchema}
    {:jsonschema/propertyName #xsd/string "vectors",
     :rdf/type :qdrant/VectorsConfig}
    {:dcterms/description
     #xsd/string
      "Custom params for Optimizers.  If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/OptimizersConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "optimizers_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description
     #xsd/string
      "If true - point's payload will not be stored in memory. It will be read from the disk every time it is requested. This setting saves RAM by (slightly) increasing the response time. Note: those payload values that are involved in filtering and are indexed - remain in RAM.",
     :jsonschema/propertyName #xsd/string "on_disk_payload",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     #xsd/string "Quantization parameters. If none - quantization is disabled.",
     :jsonschema/anyOf [:qdrant/QuantizationConfig :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "quantization_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description
     #xsd/string
      "Defines how many replicas should apply the operation for us to consider it successful. Increasing this number will make the collection more resilient to inconsistencies, but will also make it fail if not enough replicas are available. Does not have any performance impact.",
     :jsonschema/format #xsd/string "uint32",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "write_consistency_factor",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateFieldIndex
  "Operation for creating new field index"
  {:db/ident :qdrant/CreateFieldIndex,
   :dcterms/description #xsd/string "Operation for creating new field index",
   :jsonschema/properties
   [{:dcterms/description #xsd/string
                           "Field schema. If none - default schema is used.",
     :jsonschema/anyOf [:qdrant/PayloadFieldSchema :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "field_schema",
     :rdf/type :jsonschema/DataSchema}
    {:jsonschema/propertyName #xsd/string "field_name",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "field_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeleteAlias
  "Delete alias if exists"
  {:db/ident :qdrant/DeleteAlias,
   :dcterms/description #xsd/string "Delete alias if exists",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "alias_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "alias_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeleteAliasOperation
  "Delete alias if exists"
  {:db/ident :qdrant/DeleteAliasOperation,
   :dcterms/description #xsd/string "Delete alias if exists",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "delete_alias",
                            :rdf/type :qdrant/DeleteAlias}],
   :jsonschema/required [#xsd/string "delete_alias"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeletePayload
  "Deletes payload values from points"
  {:db/ident :qdrant/DeletePayload,
   :dcterms/description #xsd/string "Deletes payload values from points",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Deletes values from points that satisfy this filter condition",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "filter",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Deletes values from each point in this list",
     :jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName #xsd/string "points",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string
                           "List of payload keys to remove from payload",
     :jsonschema/items :jsonschema/StringSchema,
     :jsonschema/propertyName #xsd/string "keys",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "keys"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeleteVectors
  "Deletes vectors from points"
  {:db/ident :qdrant/DeleteVectors,
   :dcterms/description #xsd/string "Deletes vectors from points",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Vector names",
     :jsonschema/items :jsonschema/StringSchema,
     :jsonschema/minItems #xsd/integer 1,
     :jsonschema/propertyName #xsd/string "vector",
     :jsonschema/uniqueItems #xsd/boolean true,
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description
     #xsd/string
      "Deletes values from points that satisfy this filter condition",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "filter",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Deletes values from each point in this list",
     :jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName #xsd/string "points",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def Distance
  "Type of internal tags, build from payload Distance function types used to compare vectors"
  {:db/ident        :qdrant/Distance,
   :dcterms/description
   #xsd/string
    "Type of internal tags, build from payload Distance function types used to compare vectors",
   :jsonschema/enum [#xsd/string "Cosine"
                     #xsd/string "Euclid"
                     #xsd/string "Dot"],
   :rdf/type        :jsonschema/StringSchema})

(def DropReplicaOperation
  "Drop replica operation"
  {:db/ident :qdrant/DropReplicaOperation,
   :dcterms/description #xsd/string "Drop replica operation",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "drop_replica",
                            :rdf/type :qdrant/Replica}],
   :jsonschema/required [#xsd/string "drop_replica"],
   :rdf/type :jsonschema/ObjectSchema})

(def ErrorResponse
  "Error response"
  {:db/ident :qdrant/ErrorResponse,
   :dcterms/description #xsd/string "Error response",
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "result",
     :rdf/type :jsonschema/ObjectSchema}
    {:jsonschema/properties {:dcterms/description
                             #xsd/string "Description of the occurred error.",
                             :jsonschema/propertyName #xsd/string "error",
                             :rdf/type :jsonschema/StringSchema},
     :jsonschema/propertyName #xsd/string "status",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Time spent to process this request",
     :jsonschema/format #xsd/string "float",
     :jsonschema/propertyName #xsd/string "time",
     :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def ExtendedPointId
  "Type, used for specifying point ID in user interface"
  {:db/ident         :qdrant/ExtendedPointId,
   :dcterms/description #xsd/string
                         "Type, used for specifying point ID in user interface",
   :jsonschema/anyOf [{:jsonschema/format #xsd/string "uint64",
                       :jsonschema/minimum 0.0,
                       :rdf/type :jsonschema/IntegerSchema}
                      {:jsonschema/format #xsd/string "uuid",
                       :rdf/type :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def FieldCondition
  "All possible payload filtering conditions"
  {:db/ident :qdrant/FieldCondition,
   :dcterms/description #xsd/string "All possible payload filtering conditions",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Check number of values of the field",
     :jsonschema/anyOf [:qdrant/ValuesCount :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "values_count",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Check if geo point is within a given radius",
     :jsonschema/anyOf [:qdrant/GeoRadius :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "geo_radius",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Check if points geo location lies in a given area",
     :jsonschema/anyOf [:qdrant/GeoBoundingBox :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "geo_bounding_box",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Check if points value lies in a given range",
     :jsonschema/anyOf [:qdrant/Range :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "range",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string
                           "Check if point has field with a given value",
     :jsonschema/anyOf [:qdrant/Match :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "match",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string "Payload key",
     :jsonschema/propertyName #xsd/string "key",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "key"],
   :rdf/type :jsonschema/ObjectSchema})

(def Filter
  {:db/ident :qdrant/Filter,
   :jsonschema/properties
   [{:dcterms/description #xsd/string "All conditions must NOT match",
     :jsonschema/items :qdrant/Condition,
     :jsonschema/propertyName #xsd/string "must_not",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "All conditions must match",
     :jsonschema/items :qdrant/Condition,
     :jsonschema/propertyName #xsd/string "must",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string
                           "At least one of those conditions should match",
     :jsonschema/items :qdrant/Condition,
     :jsonschema/propertyName #xsd/string "should",
     :rdf/type :jsonschema/ArraySchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def FilterSelector
  {:db/ident :qdrant/FilterSelector,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "filter",
                            :rdf/type :qdrant/Filter}],
   :jsonschema/required [#xsd/string "filter"],
   :rdf/type :jsonschema/ObjectSchema})

(def GeoBoundingBox
  "Geo filter request\n\nMatches coordinates inside the rectangle, described by coordinates of lop-left and bottom-right edges"
  {:db/ident :qdrant/GeoBoundingBox,
   :dcterms/description
   #xsd/string
    "Geo filter request\n\nMatches coordinates inside the rectangle, described by coordinates of lop-left and bottom-right edges",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Geo point payload schema",
     :jsonschema/propertyName #xsd/string "bottom_right",
     :rdf/type :qdrant/GeoPoint}
    {:dcterms/description #xsd/string "Geo point payload schema",
     :jsonschema/propertyName #xsd/string "top_left",
     :rdf/type :qdrant/GeoPoint}],
   :jsonschema/required [#xsd/string "bottom_right" #xsd/string "top_left"],
   :rdf/type :jsonschema/ObjectSchema})

(def GeoPoint
  "Geo point payload schema"
  {:db/ident :qdrant/GeoPoint,
   :dcterms/description #xsd/string "Geo point payload schema",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "lat",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/propertyName #xsd/string "lon",
                            :rdf/type :jsonschema/NumberSchema}],
   :jsonschema/required [#xsd/string "lat" #xsd/string "lon"],
   :rdf/type :jsonschema/ObjectSchema})

(def GeoRadius
  "Geo filter request\n\nMatches coordinates inside the circle of `radius` and center with coordinates `center`"
  {:db/ident :qdrant/GeoRadius,
   :dcterms/description
   #xsd/string
    "Geo filter request\n\nMatches coordinates inside the circle of `radius` and center with coordinates `center`",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Radius of the area in meters",
     :jsonschema/propertyName #xsd/string "radius",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description #xsd/string "Geo point payload schema",
     :jsonschema/propertyName #xsd/string "center",
     :rdf/type :qdrant/GeoPoint}],
   :jsonschema/required [#xsd/string "center" #xsd/string "radius"],
   :rdf/type :jsonschema/ObjectSchema})

(def GroupId
  {:db/ident         :qdrant/GroupId,
   :jsonschema/anyOf [{:jsonschema/format #xsd/string "int64",
                       :rdf/type :jsonschema/IntegerSchema}
                      {:jsonschema/format #xsd/string "uint64",
                       :jsonschema/minimum 0.0,
                       :rdf/type :jsonschema/IntegerSchema}
                      {:rdf/type :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def GroupsResult
  {:db/ident :qdrant/GroupsResult,
   :jsonschema/properties [{:jsonschema/items :qdrant/PointGroup,
                            :jsonschema/propertyName #xsd/string "groups",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "groups"],
   :rdf/type :jsonschema/ObjectSchema})

(def GrpcTelemetry
  {:db/ident :qdrant/GrpcTelemetry,
   :jsonschema/properties [{:jsonschema/properties
                            :qdrant/OperationDurationStatistics,
                            :jsonschema/propertyName #xsd/string "responses",
                            :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "responses"],
   :rdf/type :jsonschema/ObjectSchema})

(def HasIdCondition
  "ID-based filtering condition"
  {:db/ident :qdrant/HasIdCondition,
   :dcterms/description #xsd/string "ID-based filtering condition",
   :jsonschema/properties [{:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName #xsd/string "has_id",
                            :jsonschema/uniqueItems #xsd/boolean true,
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "has_id"],
   :rdf/type :jsonschema/ObjectSchema})

(def HnswConfig
  "Config of HNSW index"
  {:db/ident :qdrant/HnswConfig,
   :dcterms/description #xsd/string "Config of HNSW index",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Custom M param for hnsw graph built for payload index . If not set, default M will be used.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "payload_m",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Store HNSW index on disk. If set to false, index will be stored in RAM. Default: false",
     :jsonschema/propertyName #xsd/string "on_disk",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     #xsd/string
      "Number of parallel threads used for background index building. If 0 - auto selection.",
     :jsonschema/default #xsd/integer 0,
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_indexing_threads",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Minimal size (in KiloBytes) of vectors for additional payload-based indexing. If payload chunk is smaller than `full_scan_threshold_kb` additional indexing won't be used - in this case full-scan search should be preferred by query planner and additional indexing is not required. Note: 1Kb = 1 vector of size 256",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "full_scan_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of neighbours to consider during the index building. Larger the value - more accurate the search, more time required to build index.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 4.0,
     :jsonschema/propertyName #xsd/string "ef_construct",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of edges per node in the index graph. Larger the value - more accurate the search, more space required.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "m",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "ef_construct"
                         #xsd/string "full_scan_threshold"
                         #xsd/string "m"],
   :rdf/type :jsonschema/ObjectSchema})

(def HnswConfigDiff
  {:db/ident :qdrant/HnswConfigDiff,
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Number of edges per node in the index graph. Larger the value - more accurate the search, more space required.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "m",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of neighbours to consider during the index building. Larger the value - more accurate the search, more time required to build the index.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 4.0,
     :jsonschema/propertyName #xsd/string "ef_construct",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Minimal size (in kilobytes) of vectors for additional payload-based indexing. If payload chunk is smaller than `full_scan_threshold_kb` additional indexing won't be used - in this case full-scan search should be preferred by query planner and additional indexing is not required. Note: 1Kb = 1 vector of size 256",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 1000.0,
     :jsonschema/propertyName #xsd/string "full_scan_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of parallel threads used for background index building. If 0 - auto selection.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 1000.0,
     :jsonschema/propertyName #xsd/string "max_indexing_threads",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Store HNSW index on disk. If set to false, the index will be stored in RAM. Default: false",
     :jsonschema/propertyName #xsd/string "on_disk",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     #xsd/string
      "Custom M param for additional payload-aware HNSW links. If not set, default M will be used.",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "payload_m",
     :rdf/type :jsonschema/IntegerSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def Indexes
  "Vector index configuration"
  {:db/ident :qdrant/Indexes,
   :dcterms/description #xsd/string "Vector index configuration",
   :jsonschema/oneOf
   [{:dcterms/description
     #xsd/string
      "Do not use any index, scan the whole vector collection during search. Guarantee 100% precision, but may be time-consuming on large collections.",
     :jsonschema/properties [{:jsonschema/enum [#xsd/string "plain"],
                              :jsonschema/propertyName #xsd/string "type",
                              :rdf/type        :jsonschema/StringSchema}
                             {:jsonschema/propertyName #xsd/string "options",
                              :rdf/type :jsonschema/ObjectSchema}],
     :jsonschema/required [#xsd/string "options" #xsd/string "type"],
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "Use filterable HNSW index for approximate search. Is very fast even on a very huge collections, but require additional space to store index and additional time to build it.",
     :jsonschema/properties [{:jsonschema/enum [#xsd/string "hnsw"],
                              :jsonschema/propertyName #xsd/string "type",
                              :rdf/type        :jsonschema/StringSchema}
                             {:jsonschema/propertyName #xsd/string "options",
                              :rdf/type :qdrant/HnswConfig}],
     :jsonschema/required [#xsd/string "options" #xsd/string "type"],
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def InitFrom
  "Operation for creating new collection and (optionally) specify index params"
  {:db/ident :qdrant/InitFrom,
   :dcterms/description
   #xsd/string
    "Operation for creating new collection and (optionally) specify index params",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "collection",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required #xsd/string "collection",
   :rdf/type :jsonschema/ObjectSchema})

(def IsEmptyCondition
  "Select points with empty payload for a specified field"
  {:db/ident :qdrant/IsEmptyCondition,
   :dcterms/description
   #xsd/string "Select points with empty payload for a specified field",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "is_empty",
                            :rdf/type :qdrant/PayloadField}],
   :jsonschema/required #xsd/string "is_empty",
   :rdf/type :jsonschema/ObjectSchema})

(def IsNullCondition
  "Select points with null payload for a specified field"
  {:db/ident :qdrant/IsNullCondition,
   :dcterms/description
   #xsd/string "Select points with null payload for a specified field",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "is_null",
                            :rdf/type :qdrant/PayloadField}],
   :jsonschema/required #xsd/string "is_null",
   :rdf/type :jsonschema/ObjectSchema})

(def LocalShardInfo
  {:db/ident :qdrant/LocalShardInfo,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "state",
                            :rdf/type :qdrant/ReplicaState}
                           {:dcterms/description
                            #xsd/string "Number of points in the shard",
                            :jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "points_count",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:dcterms/description #xsd/string "Local shard id",
                            :jsonschema/format   #xsd/string "uint32",
                            :jsonschema/minimum  0.0,
                            :jsonschema/propertyName #xsd/string "shard_id",
                            :rdf/type            :jsonschema/IntegerSchema}],
   :jsonschema/required
   [#xsd/string "points_count" #xsd/string "shard_id" #xsd/string "state"],
   :rdf/type :jsonschema/ObjectSchema})

(def LocalShardTelemetry
  {:db/ident :qdrant/LocalShardTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string
                                                      "optimizations",
                            :rdf/type :qdrant/OptimizerTelemetry}
                           {:jsonschema/items :qdrant/SegmentTelemetry,
                            :jsonschema/propertyName #xsd/string "segments",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName #xsd/string "variant_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "optimizations" #xsd/string "segments"],
   :rdf/type :jsonschema/ObjectSchema})

(def LocksOption
  {:db/ident :qdrant/LocksOption,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "write",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName #xsd/string
                                                      "error_message",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "write"],
   :rdf/type :jsonschema/ObjectSchema})

(def LookupLocation
  {:db/ident :qdrant/LookupLocation,
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Optional name of the vector field within the collection. If not provided, the default vector field will be used.",
     :jsonschema/propertyName #xsd/string "vector",
     :rdf/type :jsonschema/StringSchema}
    {:dcterms/description #xsd/string "Name of the collection used for lookup",
     :jsonschema/propertyName #xsd/string "collection",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "collection"],
   :rdf/type :jsonschema/ObjectSchema})

(def Match
  "Match filter request"
  {:db/ident         :qdrant/Match,
   :dcterms/description #xsd/string "Match filter request",
   :jsonschema/anyOf [:qdrant/MatchValue
                      :qdrant/MatchText
                      :qdrant/MatchAny
                      :qdrant/MatchExcept],
   :rdf/type         :jsonschema/DataSchema})

(def MatchAny
  {:db/ident :qdrant/MatchAny,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "any",
                            :rdf/type :qdrant/AnyVariants}],
   :jsonschema/required [#xsd/string "any"],
   :rdf/type :jsonschema/ObjectSchema})

(def MatchExcept
  {:db/ident :qdrant/MatchExcept,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "except",
                            :rdf/type :qdrant/AnyVariants}],
   :jsonschema/required [#xsd/string "except"],
   :rdf/type :jsonschema/ObjectSchema})

(def MatchText
  {:db/ident :qdrant/MatchText,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "text",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "text"],
   :rdf/type :jsonschema/ObjectSchema})

(def MatchValue
  {:db/ident :qdrant/MatchValue,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "value",
                            :rdf/type :qdrant/ValueVariants}],
   :jsonschema/required [#xsd/string "value"],
   :rdf/type :jsonschema/ObjectSchema})

(def MessageSendErrors
  {:db/ident :qdrant/MessageSendErrors,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "latest_error",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "count",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "count"],
   :rdf/type :jsonschema/ObjectSchema})

(def MoveShard
  {:db/ident :qdrant/MoveShard,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "from_peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "to_peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint32",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required
   [#xsd/string "from_peer_id" #xsd/string "shard_id" #xsd/string "to_peer_id"],
   :rdf/type :jsonschema/ObjectSchema})

(def MoveShardOperation
  {:db/ident :qdrant/MoveShardOperation,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "move_shard",
                            :rdf/type :qdrant/MoveShard}],
   :jsonschema/required [#xsd/string "move_shard"],
   :rdf/type :jsonschema/ObjectSchema})

(def NamedVector
  {:db/ident :qdrant/NamedVector,
   :jsonschema/properties
   [{:jsonschema/items {:jsonschema/format #xsd/string "float",
                        :rdf/type :jsonschema/NumberSchema},
     :jsonschema/propertyName #xsd/string "vector",
     :rdf/type         :jsonschema/ArraySchema}
    {:jsonschema/propertyName #xsd/string "name",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "name" #xsd/string "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def NamedVectorStruct
  {:db/ident         :qdrant/NamedVectorStruct,
   :jsonschema/anyOf [{:jsonschema/items {:jsonschema/format #xsd/string
                                                              "float",
                                          :rdf/type :jsonschema/NumberSchema},
                       :rdf/type         :jsonschema/ArraySchema}
                      :qdrant/NamedVector],
   :rdf/type         :jsonschema/DataSchema})

(def Nested
  {:db/ident :qdrant/Nested,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/propertyName #xsd/string "key",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "filter" #xsd/string "key"],
   :rdf/type :jsonschema/ObjectSchema})

(def NestedCondition
  {:db/ident :qdrant/NestedCondition,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "nested",
                            :rdf/type :qdrant/Nested}],
   :jsonschema/required [#xsd/string "nested"],
   :rdf/type :jsonschema/ObjectSchema})

(def OperationDurationStatistics
  {:db/ident :qdrant/OperationDurationStatistics,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "date-time",
     :jsonschema/propertyName #xsd/string "last_responded",
     :rdf/type :jsonschema/StringSchema}
    {:jsonschema/format #xsd/string "float",
     :jsonschema/propertyName #xsd/string "max_duration_micros",
     :rdf/type :jsonschema/NumberSchema}
    {:jsonschema/format #xsd/string "float",
     :jsonschema/propertyName #xsd/string "min_duration_micros",
     :rdf/type :jsonschema/NumberSchema}
    {:jsonschema/format #xsd/string "float",
     :jsonschema/propertyName #xsd/string "avg_duration_micros",
     :rdf/type :jsonschema/NumberSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "fail_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "count",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "count"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizerTelemetry
  {:db/ident :qdrant/OptimizerTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string
                                                      "optimizations",
                            :rdf/type :qdrant/OperationDurationStatistics}
                           {:jsonschema/propertyName #xsd/string "status",
                            :rdf/type :qdrant/OptimizersStatus}],
   :jsonschema/required [#xsd/string "optimizations" #xsd/string "status"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizersConfig
  {:db/ident :qdrant/OptimizersConfig,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "flush_interval_sec",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 100.0,
     :jsonschema/propertyName #xsd/string "vacuum_min_vector_number",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format  #xsd/string "float",
     :jsonschema/maximum 1.0,
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "deleted_threshold",
     :rdf/type           :jsonschema/NumberSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_optimization_threads",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_segment_size",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "memmap_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "default_segment_number",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "indexing_threshold",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "default_segment_number"
                         #xsd/string "deleted_threshold"
                         #xsd/string "flush_interval_sec"
                         #xsd/string "max_optimization_threads"
                         #xsd/string "vacuum_min_vector_number"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizersConfigDiff
  {:db/ident :qdrant/OptimizersConfigDiff,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "indexing_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "default_segment_number",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_optimization_threads",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_segment_size",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format  #xsd/string "float",
     :jsonschema/maximum 1.0,
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "deleted_threshold",
     :rdf/type           :jsonschema/NumberSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "memmap_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 100.0,
     :jsonschema/propertyName #xsd/string "vacuum_min_vector_number",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint64",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "flush_interval_sec",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "default_segment_number"
                         #xsd/string "deleted_threshold"
                         #xsd/string "flush_interval_sec"
                         #xsd/string "max_optimization_threads"
                         #xsd/string "vacuum_min_vector_number"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizersStatus
  "Current state of the collection"
  {:db/ident         :qdrant/OptimizersStatus,
   :dcterms/description #xsd/string "Current state of the collection",
   :jsonschema/oneOf [{:dcterms/description
                       #xsd/string "Something wrong happened with optimizers",
                       :jsonschema/properties {:jsonschema/propertyName
                                               #xsd/string "error",
                                               :rdf/type
                                               :jsonschema/StringSchema},
                       :jsonschema/required [#xsd/string "error"],
                       :rdf/type :jsonschema/ObjectSchema}
                      {:dcterms/description
                       #xsd/string "Optimizers are reporting as expected",
                       :jsonschema/enum [#xsd/string "ok"],
                       :rdf/type        :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/ObjectSchema})

(def P2pConfigTelemetry
  {:db/ident :qdrant/P2pConfigTelemetry,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string
                                                      "connection_pool_size",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "connection_pool_size"],
   :rdf/type :jsonschema/ObjectSchema})

(def Payload
  {:db/ident :qdrant/Payload,
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadField
  "Payload field"
  {:db/ident :qdrant/PayloadField,
   :dcterms/description #xsd/string "Payload field",
   :jsonschema/properties [{:dcterms/description #xsd/string
                                                  "Payload field name",
                            :jsonschema/propertyName #xsd/string "key",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "key"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadFieldSchema
  {:db/ident         :qdrant/PayloadFieldSchema,
   :jsonschema/anyOf [:qdrant/PayloadSchemaType :qdrant/PayloadSchemaParams],
   :rdf/type         :jsonschema/DataSchema})

(def PayloadIndexInfo
  "Display payload field type & index information"
  {:db/ident :qdrant/PayloadIndexInfo,
   :dcterms/description #xsd/string
                         "Display payload field type & index information",
   :jsonschema/properties
   [{:dcterms/description #xsd/string
                           "Number of points indexed with this index",
     :jsonschema/format   #xsd/string "uint",
     :jsonschema/minimum  0.0,
     :jsonschema/propertyName #xsd/string "points",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/anyOf [:qdrant/PayloadSchemaParams :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "params",
     :rdf/type         :jsonschema/DataSchema}
    {:jsonschema/propertyName #xsd/string "data_type",
     :rdf/type :qdrant/PayloadSchemaType}],
   :jsonschema/required [#xsd/string "data_type" #xsd/string "points"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadIndexTelemetry
  {:db/ident :qdrant/PayloadIndexTelemetry,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "histogram_bucket_size",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "points_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "points_values_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "field_name",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "points_count"
                         #xsd/string "points_values_count"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadSchemaParams
  "Payload type with parameters"
  {:db/ident         :qdrant/PayloadSchemaParams,
   :dcterms/description #xsd/string "Payload type with parameters",
   :jsonschema/anyOf [:qdrant/TextIndexParams],
   :rdf/type         :jsonschema/DataSchema})

(def PayloadSchemaType
  "All possible names of payload types"
  {:db/ident        :qdrant/PayloadSchemaType,
   :dcterms/description #xsd/string "All possible names of payload types",
   :jsonschema/enum [#xsd/string "keyword"
                     #xsd/string "integer"
                     #xsd/string "float"
                     #xsd/string "geo"
                     #xsd/string "text"],
   :rdf/type        :jsonschema/StringSchema})

(def PayloadSelector
  "Specifies how to treat payload selector"
  {:db/ident         :qdrant/PayloadSelector,
   :dcterms/description #xsd/string "Specifies how to treat payload selector",
   :jsonschema/anyOf [:qdrant/PayloadSelectorInclude
                      :qdrant/PayloadSelectorExclude],
   :rdf/type         :jsonschema/DataSchema})

(def PayloadSelectorExclude
  {:db/ident :qdrant/PayloadSelectorExclude,
   :jsonschema/properties [{:dcterms/description
                            #xsd/string
                             "Exclude this fields from returning payload",
                            :jsonschema/items :jsonschema/StringSchema,
                            :jsonschema/propertyName #xsd/string "exclude",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "exclude"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadSelectorInclude
  {:db/ident :qdrant/PayloadSelectorInclude,
   :jsonschema/properties [{:dcterms/description
                            #xsd/string "Only include this payload keys",
                            :jsonschema/items :jsonschema/StringSchema,
                            :jsonschema/propertyName #xsd/string "include",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "include"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadStorageType
  "Type of payload storage"
  {:db/ident         :qdrant/PayloadStorageType,
   :dcterms/description #xsd/string "Type of payload storage",
   :jsonschema/oneOf [{:jsonschema/enum [#xsd/string "on_disk"],
                       :jsonschema/propertyName #xsd/string "type",
                       :rdf/type        :jsonschema/StringSchema}
                      {:jsonschema/enum [#xsd/string "in_memory"],
                       :jsonschema/propertyName #xsd/string "type",
                       :rdf/type        :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/ObjectSchema})

(def PeerInfo
  "Information of a peer in the cluster"
  {:db/ident :qdrant/PeerInfo,
   :dcterms/description #xsd/string "Information of a peer in the cluster",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "uri",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "uri"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointGroup
  {:db/ident :qdrant/PointGroup,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "id",
     :rdf/type :jsonschema/StringSchema}
    {:dcterms/description
     #xsd/string "Scored points that have the same value of the group_by key",
     :jsonschema/items :qdrant/ScoredPoint,
     :jsonschema/propertyName #xsd/string "hits",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "hits" #xsd/string "id"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointIdsList
  {:db/ident :qdrant/PointIdsList,
   :jsonschema/properties [{:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName #xsd/string "points",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "points"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointInsertOperations
  {:db/ident         :qdrant/PointInsertOperations,
   :jsonschema/oneOf [:qdrant/PointsBatch :qdrant/PointsList],
   :rdf/type         :jsonschema/ObjectSchema})

(def PointRequest
  {:db/ident :qdrant/PointRequest,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "with_vector",
     :rdf/type :qdrant/WithVector}
    {:dcterms/description
     #xsd/string
      "Select which payload to return with the response. Default: All",
     :jsonschema/anyOf [:qdrant/WithPayloadInterface :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "with_payload",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description #xsd/string "Look for points with ids",
     :jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName #xsd/string "ids",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "ids"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointStruct
  {:db/ident :qdrant/PointStruct,
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Payload values (optional)",
     :jsonschema/anyOf [:qdrant/Payload :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "payload",
     :rdf/type :jsonschema/DataSchema}
    {:jsonschema/propertyName #xsd/string "vector",
     :rdf/type :qdrant/VectorStruct}
    {:jsonschema/propertyName #xsd/string "id",
     :rdf/type :qdrant/ExtendedPointId}],
   :jsonschema/required [#xsd/string "id" #xsd/string "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointVectors
  {:db/ident :qdrant/PointVectors,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "vector",
                            :rdf/type :qdrant/VectorStruct}
                           {:jsonschema/propertyName #xsd/string "id",
                            :rdf/type :qdrant/ExtendedPointId}],
   :jsonschema/required [#xsd/string "id" #xsd/string "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointsBatch
  {:db/ident :qdrant/PointsBatch,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "batch",
                            :rdf/type :qdrant/Batch}],
   :jsonschema/required [#xsd/string "batch"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointsList
  {:db/ident :qdrant/PointsList,
   :jsonschema/properties [{:jsonschema/items :qdrant/PointStruct,
                            :jsonschema/propertyName #xsd/string "points",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "points"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointsSelector
  {:db/ident         :qdrant/PointsSelector,
   :jsonschema/anyOf [:qdrant/PointIdsList :qdrant/FilterSelector],
   :rdf/type         :jsonschema/DataSchema})

(def ProductQuantization
  {:db/ident :qdrant/ProductQuantization,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "product",
                            :rdf/type :qdrant/ProductQuantizationConfig}],
   :jsonschema/required [#xsd/string "product"],
   :rdf/type :jsonschema/ObjectSchema})

(def ProductQuantizationConfig
  {:db/ident :qdrant/ProductQuantizationConfig,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "always_ram",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName #xsd/string "compression",
                            :rdf/type :qdrant/CompressionRatio}],
   :jsonschema/required [#xsd/string "compression"],
   :rdf/type :jsonschema/ObjectSchema})

(def QuantizationConfig
  {:db/ident         :qdrant/QuantizationConfig,
   :jsonschema/anyOf [:qdrant/ScalarQuantization :qdrant/ProductQuantization],
   :rdf/type         :jsonschema/DataSchema})

(def QuantizationSearchParams
  "Additional parameters of the search"
  {:db/ident :qdrant/QuantizationSearchParams,
   :dcterms/description #xsd/string "Additional parameters of the search",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "If true, use original vectors to re-score top-k results. Might require more time in case if original vectors are stored on disk. Default is false.",
     :jsonschema/default #xsd/boolean false,
     :jsonschema/propertyName #xsd/string "rescore",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     #xsd/string "If true, quantized vectors are ignored. Default is false.",
     :jsonschema/default #xsd/boolean false,
     :jsonschema/propertyName #xsd/string "ignore",
     :rdf/type :jsonschema/BooleanSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def RaftInfo
  "Summary information about the current raft state"
  {:db/ident :qdrant/RaftInfo,
   :dcterms/description #xsd/string
                         "Summary information about the current raft state",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Is this peer a voter or a learner",
     :jsonschema/propertyName #xsd/string "is_voter",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description #xsd/string "Role of this peer in the current term",
     :jsonschema/anyOf [:qdrant/StateRole],
     :jsonschema/propertyName #xsd/string "role",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Leader of the current term",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "leader",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Number of consensus operations pending to be applied on this peer",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "pending_operations",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "The index of the latest committed (finalized) operation that this peer is aware of.",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "commit",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Raft divides time into terms of arbitrary length, each beginning with an election. If a candidate wins the election, it remains the leader for the rest of the term. The term number increases monotonically. Each server stores the current term number which is also exchanged in every communication.",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "term",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "commit"
                         #xsd/string "is_voter"
                         #xsd/string "pending_operations"
                         #xsd/string "term"],
   :rdf/type :jsonschema/ObjectSchema})

(def Range
  "Range filter request"
  {:db/ident :qdrant/Range,
   :dcterms/description #xsd/string "Range filter request",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "point.key <= range.lte",
     :jsonschema/format #xsd/string "double",
     :jsonschema/propertyName #xsd/string "lte",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description #xsd/string "point.key >= range.gte",
     :jsonschema/format #xsd/string "double",
     :jsonschema/propertyName #xsd/string "gte",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description #xsd/string "point.key > range.gt",
     :jsonschema/format #xsd/string "double",
     :jsonschema/propertyName #xsd/string "gt",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description #xsd/string "point.key < range.lt",
     :jsonschema/format #xsd/string "double",
     :jsonschema/propertyName #xsd/string "lt",
     :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def ReadConsistency
  "Read consistency parameter. Defines how many replicas should be queried to get the result. `N` - send N random request and return points, which present on all of them. `majority` - send N/2+1 random request and return points, which present on all of them. `quorum` - send requests to all nodes and return points which present on majority of them. `all` - send requests to all nodes and return points which present on all of them. Default value is `Factor(1)`"
  {:db/ident         :qdrant/ReadConsistency,
   :dcterms/description
   #xsd/string
    "Read consistency parameter. Defines how many replicas should be queried to get the result. `N` - send N random request and return points, which present on all of them. `majority` - send N/2+1 random request and return points, which present on all of them. `quorum` - send requests to all nodes and return points which present on majority of them. `all` - send requests to all nodes and return points which present on all of them. Default value is `Factor(1)`",
   :jsonschema/anyOf [{:jsonschema/format #xsd/string "uint",
                       :jsonschema/minimum 0.0,
                       :rdf/type :jsonschema/IntegerSchema}
                      :qdrant/ReadConsistencyType],
   :rdf/type         :jsonschema/ObjectSchema})

(def ReadConsistencyType
  "majority - send N/2+1 random request and return points, which present on all of them. quorum - send requests to all nodes and return points which present on majority of nodes. all - send requests to all nodes and return points which present on all nodes"
  {:db/ident        :qdrant/ReadConsistencyType,
   :dcterms/description
   #xsd/string
    "majority - send N/2+1 random request and return points, which present on all of them. quorum - send requests to all nodes and return points which present on majority of nodes. all - send requests to all nodes and return points which present on all nodes",
   :jsonschema/enum [#xsd/string "majority"
                     #xsd/string "quorum"
                     #xsd/string "all"],
   :rdf/type        :jsonschema/StringSchema})

(def RecommendGroupsRequest
  "Recommendation request. Provides positive and negative examples of the vectors, which are already stored in the collection. Service should look for the points which are closer to positive examples and at the same time further to negative examples. The concrete way of how to compare negative and positive distances is up to implementation in `segment` crate."
  {:db/ident :qdrant/RecommendGroupsRequest,
   :dcterms/description
   #xsd/string
    "Recommendation request. Provides positive and negative examples of the vectors, which are already stored in the collection. Service should look for the points which are closer to positive examples and at the same time further to negative examples. The concrete way of how to compare negative and positive distances is up to implementation in `segment` crate.",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Define which vector to use for recommendation, if not specified - try to use default vector",
     :jsonschema/anyOf [:qdrant/UsingVector :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "using",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Look for vectors closest to those",
     :jsonschema/items [:qdrant/ExtendedPointId],
     :jsonschema/propertyName #xsd/string "positive",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "Additional search params",
     :jsonschema/anyOf [:qdrant/SearchParams :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "params",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "The location used to lookup vectors. If not specified - use current collection. Note: the other collection should have the same vector size as the current collection",
     :jsonschema/anyOf [:qdrant/LookupLocation :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "lookup_from",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Try to avoid vectors like this",
     :jsonschema/default  #xsd/string "[]",
     :jsonschema/items    [:qdrant/ExtendedPointId],
     :jsonschema/propertyName #xsd/string "negative",
     :rdf/type            :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "Maximum amount of groups to return",
     :jsonschema/format   #xsd/string "uint32",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "limit",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Select which payload to return with the response. Default: None",
     :jsonschema/anyOf [:qdrant/WithPayloadInterface :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "with_payload",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string "Look only for points which satisfies this conditions",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "filter",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string
                           "Maximum amount of points to return per group",
     :jsonschema/format   #xsd/string "uint32",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "group_size",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Define a minimal score threshold for the result. If defined, less similar results will not be returned. Score of the returned result might be higher or smaller than the threshold depending on the Distance function used. E.g. for cosine similarity only higher scores will be returned.",
     :jsonschema/format #xsd/string "float",
     :jsonschema/propertyName #xsd/string "score_threshold",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description
     #xsd/string "Whether to return the point vector with the result?",
     :jsonschema/anyOf [:qdrant/WithVector :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "with_vector",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "Payload field to group by, must be a string or number field. If the field contains more than 1 value, all values will be used for grouping. One point can be in multiple groups.",
     :jsonschema/minLength #xsd/integer 1,
     :jsonschema/propertyName #xsd/string "group_by",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "group_by"
                         #xsd/string "group_size"
                         #xsd/string "limit"
                         #xsd/string "positive"],
   :rdf/type :jsonschema/ObjectSchema})

(def RecommendRequest
  {:db/ident :qdrant/RecommendRequest,
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Define which vector to use for recommendation, if not specified - try to use default vector",
     :jsonschema/anyOf [:qdrant/UsingVector :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "using",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Look for vectors closest to those",
     :jsonschema/items [:qdrant/ExtendedPointId],
     :jsonschema/propertyName #xsd/string "positive",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description
     #xsd/string
      "Offset of the first result to return. May be used to paginate results. Note: large offset values may cause performance issues.",
     :jsonschema/default #xsd/integer 0,
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "offset",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "Additional search params",
     :jsonschema/anyOf [:qdrant/SearchParams :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "params",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "The location used to lookup vectors. If not specified - use current collection. Note: the other collection should have the same vector size as the current collection",
     :jsonschema/anyOf [:qdrant/LookupLocation :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "lookup_from",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Try to avoid vectors like this",
     :jsonschema/default  #xsd/string "[]",
     :jsonschema/items    [:qdrant/ExtendedPointId],
     :jsonschema/propertyName #xsd/string "negative",
     :rdf/type            :jsonschema/ArraySchema}
    {:dcterms/description #xsd/string "Max number of result to return",
     :jsonschema/format   #xsd/string "uint",
     :jsonschema/minimum  0.0,
     :jsonschema/propertyName #xsd/string "limit",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description
     #xsd/string
      "Select which payload to return with the response. Default: None",
     :jsonschema/anyOf [:qdrant/WithPayloadInterface :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "with_payload",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string "Look only for points which satisfies this conditions",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "filter",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "Define a minimal score threshold for the result. If defined, less similar results will not be returned. Score of the returned result might be higher or smaller than the threshold depending on the Distance function used. E.g. for cosine similarity only higher scores will be returned.",
     :jsonschema/format #xsd/string "float",
     :jsonschema/propertyName #xsd/string "score_threshold",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description
     #xsd/string "Whether to return the point vector with the result?",
     :jsonschema/anyOf [:qdrant/WithVector :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "with_vector",
     :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "limit" #xsd/string "positive"],
   :rdf/type :jsonschema/ObjectSchema})

(def RecommendRequestBatch
  {:db/ident :qdrant/RecommendRequestBatch,
   :jsonschema/properties [{:dcterms/description
                            #xsd/string "List of recommendation requests",
                            :jsonschema/items [:qdrant/RecommendRequest],
                            :jsonschema/propertyName #xsd/string "searches",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "searches"],
   :rdf/type :jsonschema/ObjectSchema})

(def Record
  {:db/ident :qdrant/Record,
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Point id",
     :jsonschema/format   #xsd/string "uint64",
     :jsonschema/minimum  0.0,
     :jsonschema/propertyName #xsd/string "id",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string "Payload - values assigned to the point",
     :jsonschema/anyOf [:qdrant/Payload :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "payload",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description #xsd/string "Vector of the point",
     :jsonschema/anyOf [:qdrant/VectorStruct :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "vector",
     :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "id"],
   :rdf/type :jsonschema/ObjectSchema})

(def RemoteShardInfo
  {:db/ident :qdrant/RemoteShardInfo,
   :jsonschema/properties [{:dcterms/description #xsd/string "Remote shard id",
                            :jsonschema/format   #xsd/string "uint32",
                            :jsonschema/minimum  0.0,
                            :jsonschema/propertyName #xsd/string "shard_id",
                            :rdf/type            :jsonschema/IntegerSchema}
                           {:dcterms/description #xsd/string "Remote peer id",
                            :jsonschema/format   #xsd/string "uint64",
                            :jsonschema/minimum  0.0,
                            :jsonschema/propertyName #xsd/string "peer_id",
                            :rdf/type            :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "state",
                            :rdf/type :qdrant/ReplicaState}],
   :jsonschema/required
   [#xsd/string "peer_id" #xsd/string "shard_id" #xsd/string "state"],
   :rdf/type :jsonschema/ObjectSchema})

(def RemoteShardTelemetry
  {:db/ident :qdrant/RemoteShardTelemetry,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint32",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "searches",
                            :rdf/type :qdrant/OperationDurationStatistics}
                           {:jsonschema/propertyName #xsd/string "updates",
                            :rdf/type :qdrant/OperationDurationStatistics}],
   :jsonschema/required
   [#xsd/string "searches" #xsd/string "shard_id" #xsd/string "updates"],
   :rdf/type :jsonschema/ObjectSchema})

(def RenameAlias
  "Change alias to a new one"
  {:db/ident :qdrant/RenameAlias,
   :dcterms/description #xsd/string "Change alias to a new one",
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "old_alias_name",
     :rdf/type :jsonschema/StringSchema}
    {:jsonschema/propertyName #xsd/string "new_alias_name",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "new_alias_name"
                         #xsd/string "old_alias_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def RenameAliasOperation
  "Change alias to a new one"
  {:db/ident :qdrant/RenameAliasOperation,
   :dcterms/description #xsd/string "Change alias to a new one",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "rename_alias",
                            :rdf/type :qdrant/RenameAlias}],
   :jsonschema/required [#xsd/string "rename_alias"],
   :rdf/type :jsonschema/ObjectSchema})

(def Replica
  {:db/ident :qdrant/Replica,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint32",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "peer_id",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "peer_id" #xsd/string "shard_id"],
   :rdf/type :jsonschema/ObjectSchema})

(def ReplicaSetTelemetry
  {:db/ident :qdrant/ReplicaSetTelemetry,
   :jsonschema/properties
   [{:jsonschema/format #xsd/string "uint32",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "id",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/anyOf [:qdrant/LocalShardTelemetry :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "local",
     :rdf/type         :jsonschema/ObjectSchema}
    {:jsonschema/items :qdrant/RemoteShardTelemetry,
     :jsonschema/propertyName #xsd/string "remote",
     :rdf/type         :jsonschema/ArraySchema}
    {:jsonschema/propertyName #xsd/string "replicate_states",
     :rdf/type :qdrant/ReplicaState}],
   :jsonschema/required
   [#xsd/string "id" #xsd/string "remote" #xsd/string "replicate_states"],
   :rdf/type :jsonschema/ObjectSchema})

(def ReplicaState
  "State of the single shard within a replica set."
  {:db/ident        :qdrant/ReplicaState,
   :dcterms/description #xsd/string
                         "State of the single shard within a replica set.",
   :jsonschema/enum [#xsd/string "Active"
                     #xsd/string "Dead"
                     #xsd/string "Partial"
                     #xsd/string "Initializing"
                     #xsd/string "Listener"],
   :rdf/type        :jsonschema/StringSchema})

(def ReplicateShardOperation
  {:db/ident :qdrant/ReplicateShardOperation,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string
                                                      "replicate_shard",
                            :rdf/type :qdrant/MoveShard}],
   :jsonschema/required [#xsd/string "replicate_shard"],
   :rdf/type :jsonschema/ObjectSchema})

(def RequestsTelemetry
  {:db/ident :qdrant/RequestsTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "rest",
                            :rdf/type :qdrant/WebApiTelemetry}
                           {:jsonschema/propertyName #xsd/string "grpc",
                            :rdf/type :qdrant/GrpcTelemetry}],
   :jsonschema/required [#xsd/string "grpc" #xsd/string "rest"],
   :rdf/type :jsonschema/ObjectSchema})

(def RunningEnvironmentTelemetry
  {:db/ident :qdrant/RunningEnvironmentTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "distribution",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName #xsd/string
                                                      "distribution_version",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName #xsd/string "is_docker",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "cores",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "ram_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "disk_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "cpu_flags",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "cpu_flags" #xsd/string "is_docker"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScalarQuantization
  {:db/ident :qdrant/ScalarQuantization,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "scalar",
                            :rdf/type :qdrant/ScalarQuantizationConfig}],
   :jsonschema/required [#xsd/string "scalar"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScalarQuantizationConfig
  {:db/ident :qdrant/ScalarQuantizationConfig,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "type",
                            :rdf/type :qdrant/ScalarType}
                           {:jsonschema/format  #xsd/string "float",
                            :jsonschema/maximum 1.0,
                            :jsonschema/minimum 0.5,
                            :jsonschema/propertyName #xsd/string "quantile",
                            :rdf/type           :jsonschema/NumberSchema}
                           {:jsonschema/propertyName #xsd/string "always_ram",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required [#xsd/string "type"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScalarType
  {:db/ident        :qdrant/ScalarType,
   :jsonschema/enum [#xsd/string "int8"],
   :rdf/type        :jsonschema/StringSchema})

(def ScoredPoint
  {:db/ident :qdrant/ScoredPoint,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "id",
                            :rdf/type :qdrant/ExtendedPointId}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "version",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "float",
                            :jsonschema/propertyName #xsd/string "score",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/propertyName #xsd/string "payload",
                            :rdf/type :qdrant/Payload}
                           {:jsonschema/propertyName #xsd/string "vector",
                            :rdf/type :qdrant/VectorStruct}],
   :jsonschema/required
   [#xsd/string "id" #xsd/string "score" #xsd/string "version"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScrollRequest
  {:db/ident :qdrant/ScrollRequest,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "offset",
                            :rdf/type :qdrant/ExtendedPointId}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 1.0,
                            :jsonschema/propertyName #xsd/string "limit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/propertyName #xsd/string "with_payload",
                            :rdf/type :qdrant/WithPayloadInterface}
                           {:jsonschema/propertyName #xsd/string "with_vector",
                            :rdf/type :qdrant/WithVector}],
   :rdf/type :jsonschema/ObjectSchema})

(def ScrollResult
  {:db/ident :qdrant/ScrollResult,
   :jsonschema/properties [{:jsonschema/items :qdrant/Record,
                            :jsonschema/propertyName #xsd/string "points",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName #xsd/string
                                                      "next_page_offset",
                            :rdf/type :qdrant/ExtendedPointId}],
   :jsonschema/required [#xsd/string "points"],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchGroupsRequest
  {:db/ident :qdrant/SearchGroupsRequest,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "params",
                            :rdf/type :qdrant/SearchParams}
                           {:jsonschema/format #xsd/string "uint32",
                            :jsonschema/minimum 1.0,
                            :jsonschema/propertyName #xsd/string "limit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "vector",
                            :rdf/type :qdrant/NamedVectorStruct}
                           {:jsonschema/propertyName #xsd/string "with_payload",
                            :rdf/type :qdrant/WithPayloadInterface}
                           {:jsonschema/propertyName #xsd/string "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/format #xsd/string "uint32",
                            :jsonschema/minimum 1.0,
                            :jsonschema/propertyName #xsd/string "group_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "float",
                            :jsonschema/propertyName #xsd/string
                                                      "score_threshold",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/propertyName #xsd/string "with_vector",
                            :rdf/type :qdrant/WithVector}
                           {:jsonschema/minLength #xsd/integer 1,
                            :jsonschema/propertyName #xsd/string "group_by",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required [#xsd/string "group_by"
                         #xsd/string "group_size"
                         #xsd/string "limit"
                         #xsd/string "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchParams
  {:db/ident :qdrant/SearchParams,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "hnsw_ef",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "exact",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName #xsd/string "quantization",
                            :rdf/type :qdrant/QuantizationSearchParams}],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchRequest
  {:db/ident :qdrant/SearchRequest,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "vector",
                            :rdf/type :qdrant/NamedVectorStruct}
                           {:jsonschema/propertyName #xsd/string "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/propertyName #xsd/string "params",
                            :rdf/type :qdrant/SearchParams}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "limit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "offset",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "with_payload",
                            :rdf/type :qdrant/WithPayloadInterface}
                           {:jsonschema/propertyName #xsd/string "with_vector",
                            :rdf/type :qdrant/WithVector}
                           {:jsonschema/format #xsd/string "float",
                            :jsonschema/propertyName #xsd/string
                                                      "score_threshold",
                            :rdf/type :jsonschema/NumberSchema}],
   :jsonschema/required [#xsd/string "limit" #xsd/string "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchRequestBatch
  {:db/ident :qdrant/SearchRequestBatch,
   :jsonschema/properties [{:jsonschema/items :qdrant/SearchRequest,
                            :jsonschema/propertyName #xsd/string "searches",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "searches"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentConfig
  {:db/ident :qdrant/SegmentConfig,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "vector_data",
                            :rdf/type :qdrant/VectorDataConfig}
                           {:jsonschema/propertyName #xsd/string
                                                      "payload_storage_type",
                            :rdf/type :qdrant/PayloadStorageType}],
   :jsonschema/required [#xsd/string "payload_storage_type"
                         #xsd/string "vector_data"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentInfo
  {:db/ident :qdrant/SegmentInfo,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "segment_type",
     :rdf/type :qdrant/SegmentType}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "num_vectors",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "num_points",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "num_deleted_vectors",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "ram_usage_bytes",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "disk_usage_bytes",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "is_appendable",
     :rdf/type :jsonschema/BooleanSchema}
    {:jsonschema/propertyName #xsd/string "index_schema",
     :rdf/type :qdrant/PayloadIndexInfo}],
   :jsonschema/required [#xsd/string "disk_usage_bytes"
                         #xsd/string "index_schema"
                         #xsd/string "is_appendable"
                         #xsd/string "num_deleted_vectors"
                         #xsd/string "num_points"
                         #xsd/string "num_vectors"
                         #xsd/string "ram_usage_bytes"
                         #xsd/string "segment_type"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentTelemetry
  {:db/ident :qdrant/SegmentTelemetry,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "info",
     :rdf/type :qdrant/SegmentInfo}
    {:jsonschema/propertyName #xsd/string "config",
     :rdf/type :qdrant/SegmentConfig}
    {:jsonschema/items :qdrant/VectorIndexSearchesTelemetry,
     :jsonschema/propertyName #xsd/string "vector_index_searches",
     :rdf/type         :jsonschema/ArraySchema}
    {:jsonschema/items :qdrant/PayloadIndexTelemetry,
     :jsonschema/propertyName #xsd/string "payload_field_indices",
     :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "config"
                         #xsd/string "info"
                         #xsd/string "payload_field_indices"
                         #xsd/string "vector_index_searches"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentType
  {:db/ident        :qdrant/SegmentType,
   :jsonschema/enum [#xsd/string "plain"
                     #xsd/string "indexed"
                     #xsd/string "special"],
   :rdf/type        :jsonschema/StringSchema})

(def SetPayload
  {:db/ident :qdrant/SetPayload,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "payload",
                            :rdf/type :qdrant/Payload}
                           {:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName #xsd/string "points",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/anyOf [:qdrant/Filter
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName #xsd/string "filter",
                            :rdf/type         :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "payload"],
   :rdf/type :jsonschema/ObjectSchema})

(def ShardTransferInfo
  {:db/ident :qdrant/ShardTransferInfo,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint32",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "from",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "to",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "sync",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required [#xsd/string "from"
                         #xsd/string "shard_id"
                         #xsd/string "sync"
                         #xsd/string "to"],
   :rdf/type :jsonschema/ObjectSchema})

(def SnapshotDescription
  {:db/ident :qdrant/SnapshotDescription,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format #xsd/string "partial-date-time",
                            :jsonschema/propertyName #xsd/string
                                                      "creation_time",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "size",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required [#xsd/string "name" #xsd/string "size"],
   :rdf/type :jsonschema/ObjectSchema})

(def SnapshotPriority
  {:db/ident        :qdrant/SnapshotPriority,
   :jsonschema/enum [#xsd/string "snapshot" #xsd/string "replica"],
   :rdf/type        :jsonschema/StringSchema})

(def SnapshotRecover
  {:db/ident :qdrant/SnapshotRecover,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uri",
                            :jsonschema/propertyName #xsd/string "location",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/anyOf [:qdrant/SnapshotPriority
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName #xsd/string "priority",
                            :rdf/type         :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "location"],
   :rdf/type :jsonschema/ObjectSchema})

(def StateRole
  {:db/ident        :qdrant/StateRole,
   :jsonschema/enum [#xsd/string "Follower"
                     #xsd/string "Candidate"
                     #xsd/string "Leader"
                     #xsd/string "PreCandidate"],
   :rdf/type        :jsonschema/StringSchema})

(def SuccessfulResponse
  "Successful operation"
  {:db/ident :qdrant/SuccessfulResponse,
   :dcterms/description #xsd/string "Successful operation",
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "result",
                            :rdf/type :jsonschema/ObjectSchema}
                           {:jsonschema/enum [#xsd/string "ok"],
                            :jsonschema/propertyName #xsd/string "status",
                            :rdf/type        :jsonschema/StringSchema}
                           {:dcterms/description
                            #xsd/string "Time spent to process this request",
                            :jsonschema/format #xsd/string "float",
                            :jsonschema/propertyName #xsd/string "time",
                            :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def TelemetryData
  {:db/ident :qdrant/TelemetryData,
   :jsonschema/properties [{:jsonschema/propertyName #xsd/string "id",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName #xsd/string "app",
                            :rdf/type :qdrant/AppBuildTelemetry}
                           {:jsonschema/propertyName #xsd/string "collections",
                            :rdf/type :qdrant/CollectionsTelemetry}
                           {:jsonschema/propertyName #xsd/string "cluster",
                            :rdf/type :qdrant/ClusterTelemetry}
                           {:jsonschema/propertyName #xsd/string "requests",
                            :rdf/type :qdrant/RequestsTelemetry}],
   :jsonschema/required [#xsd/string "app"
                         #xsd/string "cluster"
                         #xsd/string "collections"
                         #xsd/string "id"
                         #xsd/string "requests"],
   :rdf/type :jsonschema/ObjectSchema})

(def TextIndexParams
  {:db/ident :qdrant/TextIndexParams,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "type",
     :rdf/type :qdrant/TextIndexType}
    {:jsonschema/propertyName #xsd/string "tokenizer",
     :rdf/type :qdrant/TokenizerType}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "min_token_len",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "max_token_len",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description #xsd/string
                           "If true, lowercase all tokens. Default: true",
     :jsonschema/propertyName #xsd/string "lowercase",
     :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required [#xsd/string "type"],
   :rdf/type :jsonschema/ObjectSchema})

(def TextIndexType
  {:db/ident        :qdrant/TextIndexType,
   :jsonschema/enum [#xsd/string "text"],
   :rdf/type        :jsonschema/StringSchema})

(def TokenizerType
  {:db/ident        :qdrant/TokenizerType,
   :jsonschema/enum [#xsd/string "prefix"
                     #xsd/string "whitespace"
                     #xsd/string "word"],
   :rdf/type        :jsonschema/StringSchema})

(def UpdateCollection
  "Operation for updating parameters of the existing collection"
  {:db/ident :qdrant/UpdateCollection,
   :dcterms/description
   #xsd/string "Operation for updating parameters of the existing collection",
   :jsonschema/properties
   [{:dcterms/description
     #xsd/string
      "Custom params for Optimizers.  If none - values from service configuration file are used. This operation is blocking, it will only proceed ones all current optimizations are complete",
     :jsonschema/anyOf [:qdrant/OptimizersConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "optimizers_config",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "Collection base params.  If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/CollectionParamsDiff :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "params",
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def UpdateResult
  {:db/ident :qdrant/UpdateResult,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint64",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "operation_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName #xsd/string "status",
                            :rdf/type :qdrant/UpdateStatus}],
   :jsonschema/required [#xsd/string "operation_id" #xsd/string "status"],
   :rdf/type :jsonschema/ObjectSchema})

(def UpdateStatus
  "`Acknowledged` - Request is saved to WAL and will be process in a queue. `Completed` - Request is completed, changes are actual."
  {:db/ident        :qdrant/UpdateStatus,
   :dcterms/description
   #xsd/string
    "`Acknowledged` - Request is saved to WAL and will be process in a queue. `Completed` - Request is completed, changes are actual.",
   :jsonschema/enum [#xsd/string "acknowledged" #xsd/string "completed"],
   :rdf/type        :jsonschema/StringSchema})

(def UpdateVectors
  {:db/ident :qdrant/UpdateVectors,
   :jsonschema/properties [{:dcterms/description #xsd/string
                                                  "Points with named vectors",
                            :jsonschema/items :qdrant/PointVectors,
                            :jsonschema/minItems #xsd/integer 1,
                            :jsonschema/propertyName #xsd/string "points",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required [#xsd/string "points"],
   :rdf/type :jsonschema/ObjectSchema})

(def UsingVector
  {:db/ident         :qdrant/UsingVector,
   :jsonschema/anyOf [:jsonschema/StringSchema],
   :rdf/type         :jsonschema/DataSchema})

(def ValueVariants
  {:db/ident         :qdrant/ValueVariants,
   :jsonschema/anyOf [:jsonschema/StringSchema
                      {:jsonschema/format #xsd/string "int64",
                       :rdf/type :jsonschema/IntegerSchema}
                      :jsonschema/BooleanSchema],
   :rdf/type         :jsonschema/DataSchema})

(def ValuesCount
  {:db/ident :qdrant/ValuesCount,
   :jsonschema/properties [{:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "lt",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "gt",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "gte",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format #xsd/string "uint",
                            :jsonschema/minimum 0.0,
                            :jsonschema/propertyName #xsd/string "lte",
                            :rdf/type :jsonschema/IntegerSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorDataConfig
  "Config of single vector data storage"
  {:db/ident :qdrant/VectorDataConfig,
   :dcterms/description #xsd/string "Config of single vector data storage",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Size/dimensionality of the vectors used",
     :jsonschema/format   #xsd/string "uint",
     :jsonschema/minimum  0.0,
     :jsonschema/propertyName #xsd/string "size",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "distance",
     :rdf/type :qdrant/Distance}
    {:jsonschema/propertyName #xsd/string "storage_type",
     :rdf/type :qdrant/VectorStorageType}
    {:jsonschema/propertyName #xsd/string "index",
     :rdf/type :qdrant/Indexes}
    {:dcterms/description
     #xsd/string
      "Vector specific quantization config that overrides collection config",
     :jsonschema/anyOf [:qdrant/QuantizationConfig :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "quantization_config",
     :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "distance"
                         #xsd/string "index"
                         #xsd/string "size"
                         #xsd/string "storage_type"],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorIndexSearchesTelemetry
  {:db/ident :qdrant/VectorIndexSearchesTelemetry,
   :jsonschema/properties
   [{:jsonschema/propertyName #xsd/string "index_name",
     :rdf/type :jsonschema/StringSchema}
    {:jsonschema/propertyName #xsd/string "unfiltered_plain",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName #xsd/string "unfiltered_hnsw",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName #xsd/string "filtered_plain",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName #xsd/string "filtered_small_cardinality",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName #xsd/string "filtered_large_cardinality",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName #xsd/string "filtered_exact",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName #xsd/string "unfiltered_exact",
     :rdf/type :qdrant/OperationDurationStatistics}],
   :jsonschema/required [#xsd/string "filtered_exact"
                         #xsd/string "filtered_large_cardinality"
                         #xsd/string "filtered_plain"
                         #xsd/string "filtered_small_cardinality"
                         #xsd/string "unfiltered_exact"
                         #xsd/string "unfiltered_hnsw"
                         #xsd/string "unfiltered_plain"],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorParams
  "Params of single vector data storage"
  {:db/ident :qdrant/VectorParams,
   :dcterms/description #xsd/string "Params of single vector data storage",
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Size of a vectors used",
     :jsonschema/format   #xsd/string "uint64",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "size",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/propertyName #xsd/string "distance",
     :rdf/type :qdrant/Distance}
    {:dcterms/description
     #xsd/string
      "Custom params for HNSW index. If none - values from collection configuration are used.",
     :jsonschema/anyOf [:qdrant/HnswConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "hnsw_config",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "Custom params for quantization. If none - values from collection configuration are used.",
     :jsonschema/anyOf [:qdrant/QuantizationConfig :jsonschema/NullSchema],
     :jsonschema/propertyName #xsd/string "quantization_config",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     #xsd/string
      "If true, vectors are served from disk, improving RAM usage at the cost of latency Default: false",
     :jsonschema/propertyName #xsd/string "on_disk",
     :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required [#xsd/string "distance" #xsd/string "size"],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorStorageType
  "Storage types for vectors"
  {:db/ident :qdrant/VectorStorageType,
   :dcterms/description #xsd/string "Storage types for vectors",
   :jsonschema/oneOf
   [{:dcterms/description
     #xsd/string
      "Storage in memory (RAM). Will be very fast at the cost of consuming a lot of memory.",
     :jsonschema/enum #xsd/string "Memory",
     :rdf/type        :jsonschema/StringSchema}
    {:dcterms/description
     #xsd/string
      "Storage in mmap file, not appendable. Search performance is defined by disk speed and the fraction of vectors that fit in memory.",
     :jsonschema/enum #xsd/string "Mmap",
     :rdf/type        :jsonschema/StringSchema}
    {:dcterms/description
     #xsd/string
      "Storage in chunked mmap files, appendable. Search performance is defined by disk speed and the fraction of vectors that fit in memory.",
     :jsonschema/enum #xsd/string "ChunkedMmap",
     :rdf/type        :jsonschema/StringSchema}],
   :rdf/type :jsonschema/StringSchema})

(def VectorStruct
  "Full vector data per point separator with single and multiple vector modes"
  {:db/ident         :qdrant/VectorStruct,
   :dcterms/description
   #xsd/string
    "Full vector data per point separator with single and multiple vector modes",
   :jsonschema/anyOf [{:jsonschema/items [{:jsonschema/format #xsd/string
                                                               "float",
                                           :rdf/type :jsonschema/NumberSchema}],
                       :rdf/type         :jsonschema/ArraySchema}
                      {:jsonschema/properties [{:jsonschema/items
                                                [{:jsonschema/format #xsd/string
                                                                      "float",
                                                  :rdf/type
                                                  :jsonschema/NumberSchema}],
                                                :rdf/type
                                                :jsonschema/ArraySchema}],
                       :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def VectorsConfig
  "Vector params separator for single and multiple vector modes Single mode:\n\n{ \"size\": 128, \"distance\": \"Cosine\" }\n\nor multiple mode:\n\n{ \"default\": { \"size\": 128, \"distance\": \"Cosine\" } }"
  {:db/ident         :qdrant/VectorsConfig,
   :dcterms/description
   #xsd/string
    "Vector params separator for single and multiple vector modes Single mode:\n\n{ \"size\": 128, \"distance\": \"Cosine\" }\n\nor multiple mode:\n\n{ \"default\": { \"size\": 128, \"distance\": \"Cosine\" } }",
   :jsonschema/anyOf [{:rdf/type :qdrant/VectorParams}
                      {:jsonschema/properties [{:rdf/type
                                                :qdrant/VectorParams}],
                       :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def WalConfig
  {:db/ident :qdrant/WalConfig,
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Size of a single WAL segment in MB",
     :jsonschema/format   #xsd/string "uint",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "wal_capacity_mb",
     :rdf/type            :jsonschema/NumberSchema}
    {:dcterms/description
     #xsd/string "Number of WAL segments to create ahead of actually used ones",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "wal_segments_ahead",
     :rdf/type :jsonschema/NumberSchema}],
   :jsonschema/required [#xsd/string "wal_capacity_mb"
                         #xsd/string "wal_segments_ahead"],
   :rdf/type :jsonschema/ObjectSchema})

(def WalConfigDiff
  {:db/ident :qdrant/WalConfigDiff,
   :jsonschema/properties
   [{:dcterms/description #xsd/string "Size of a single WAL segment in MB",
     :jsonschema/format   #xsd/string "uint",
     :jsonschema/minimum  1.0,
     :jsonschema/propertyName #xsd/string "wal_capacity_mb",
     :rdf/type            :jsonschema/NumberSchema}
    {:dcterms/description
     #xsd/string "Number of WAL segments to create ahead of actually used ones",
     :jsonschema/format #xsd/string "uint",
     :jsonschema/minimum 0.0,
     :jsonschema/propertyName #xsd/string "wal_segments_ahead",
     :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def WebApiTelemetry
  {:db/ident :qdrant/WebApiTelemetry,
   :jsonschema/properties [{:jsonschema/properties
                            {:rdf/type :qdrant/OperationDurationStatistics},
                            :jsonschema/propertyName #xsd/string "responses",
                            :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required [#xsd/string "responses"],
   :rdf/type :jsonschema/ObjectSchema})

(def WithPayloadInterface
  "Options for specifying which payload to include or not"
  {:db/ident :qdrant/WithPayloadInterface,
   :dcterms/description
   #xsd/string "Options for specifying which payload to include or not",
   :jsonschema/anyOf
   [{:dcterms/description
     #xsd/string
      "If `true` - return all payload, If `false` - do not return payload",
     :jsonschema/propertyName #xsd/string "include_payload",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description #xsd/string "Specify which fields to return",
     :jsonschema/items [{:rdf/type :jsonschema/StringSchema}],
     :jsonschema/propertyName #xsd/string "fields",
     :rdf/type :jsonschema/ArraySchema}
    :qdrant/PayloadSelector],
   :rdf/type :jsonschema/DataSchema})

(def WithVector
  "Options for specifying which vector to include"
  {:db/ident :qdrant/WithVector,
   :dcterms/description #xsd/string
                         "Options for specifying which vector to include",
   :jsonschema/anyOf
   [{:dcterms/description
     #xsd/string
      "If `true` - return all vector, If `false` - do not return vector",
     :jsonschema/propertyName #xsd/string "include_vector",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description #xsd/string "Specify which vector to return",
     :jsonschema/items [{:rdf/type :jsonschema/StringSchema}],
     :jsonschema/propertyName #xsd/string "fields",
     :rdf/type :jsonschema/ArraySchema}],
   :rdf/type :jsonschema/DataSchema})

(def WriteOrdering
  "Defines write ordering guarantees for collection operations\n\n* `weak` - write operations may be reordered, works faster, default\n\n* `medium` - write operations go through dynamically selected leader, may be inconsistent for a short period of time in case of leader change\n\n* `strong` - Write operations go through the permanent leader, consistent, but may be unavailable if leader is down"
  {:db/ident        :qdrant/WriteOrdering,
   :dcterms/description
   #xsd/string
    "Defines write ordering guarantees for collection operations\n\n* `weak` - write operations may be reordered, works faster, default\n\n* `medium` - write operations go through dynamically selected leader, may be inconsistent for a short period of time in case of leader change\n\n* `strong` - Write operations go through the permanent leader, consistent, but may be unavailable if leader is down",
   :jsonschema/enum [#xsd/string "weak"
                     #xsd/string "medium"
                     #xsd/string "strong"],
   :rdf/type        :jsonschema/StringSchema})