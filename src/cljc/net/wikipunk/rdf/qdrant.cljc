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
   :rdfs/label        "Qdrant Ontology"})

(def AbortTransferOperation
  {:db/ident :qdrant/AbortTransferOperation,
   :jsonschema/properties [{:jsonschema/propertyName "abort_transfer",
                            :rdf/type :qdrant/MoveShard}],
   :jsonschema/required ["abort_transfer"],
   :rdf/type :jsonschema/ObjectSchema})

(def AliasDescription
  {:db/ident :qdrant/AliasDescription,
   :jsonschema/properties [{:jsonschema/propertyName "collection_name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "alias_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["alias_name" "collection_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def AliasOperations
  "Group of all the possible operations related to collection aliases"
  {:db/ident         :qdrant/AliasOperations,
   :dcterms/description
   "Group of all the possible operations related to collection aliases",
   :jsonschema/anyOf [:qdrant/CreateAliasOperation
                      :qdrant/DeleteAliasOperation
                      :qdrant/RenameAliasOperation],
   :rdf/type         :jsonschema/DataSchema})

(def AnyVariants
  {:db/ident         :qdrant/AnyVariants,
   :jsonschema/anyOf {:jsonschema/items [{:jsonschema/items
                                          {:jsonschema/format "int64",
                                           :rdf/type :jsonschema/IntegerSchema},
                                          :rdf/type :jsonschema/ArraySchema}
                                         {:rdf/type "string"}],
                      :rdf/type         :jsonschema/ArraySchema},
   :rdf/type         :jsonschema/DataSchema})

(def AppBuildTelemetry
  {:db/ident :qdrant/AppBuildTelemetry,
   :jsonschema/properties [{:jsonschema/format "date-time",
                            :jsonschema/propertyName "startup",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/anyOf
                            [:qdrant/RunningEnvironmentTelemetry
                             :jsonschema/NullSchema],
                            :jsonschema/propertyName "system"}
                           {:jsonschema/anyOf [:qdrant/AppFeaturesTelemetry
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName "features"}
                           {:jsonschema/propertyName "version",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["name" "startup" "version"],
   :rdf/type :jsonschema/ObjectSchema})

(def AppFeaturesTelemetry
  {:db/ident :qdrant/AppFeaturesTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "web_feature",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "service_debug_feature",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "debug",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required ["debug" "service_debug_feature" "web_feature"],
   :rdf/type :jsonschema/ObjectSchema})

(def Batch
  {:db/ident :qdrant/Batch,
   :jsonschema/properties [{:jsonschema/items {:jsonschema/anyOf
                                               [:qdrant/Payload
                                                :jsonschema/NullSchema]},
                            :jsonschema/propertyName "payloads",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName "vectors",
                            :rdf/type :qdrant/BatchVectorStruct}
                           {:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName "ids",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["ids" "vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def BatchVectorStruct
  {:db/ident         :qdrant/BatchVectorStruct,
   :jsonschema/anyOf [{:jsonschema/properties {:jsonschema/items
                                               {:jsonschema/items
                                                {:jsonschema/format "float",
                                                 :rdf/type
                                                 :jsonschema/NumberSchema},
                                                :rdf/type
                                                :jsonschema/ArraySchema},
                                               :rdf/type
                                               :jsonschema/ArraySchema},
                       :rdf/type :jsonschema/ObjectSchema}
                      {:jsonschema/items {:jsonschema/items
                                          {:jsonschema/format "float",
                                           :rdf/type :jsonschema/NumberSchema},
                                          :rdf/type :jsonschema/ArraySchema},
                       :rdf/type         :jsonschema/ArraySchema}],
   :rdf/type         :jsonschema/DataSchema})

(def ChangeAliasesOperation
  "Operation for performing changes of collection aliases. Alias changes are atomic, meaning that no collection modifications can happen between alias operations."
  {:db/ident :qdrant/ChangeAliasesOperation,
   :dcterms/description
   "Operation for performing changes of collection aliases. Alias changes are atomic, meaning that no collection modifications can happen between alias operations.",
   :jsonschema/properties [{:jsonschema/items :qdrant/AliasOperations,
                            :jsonschema/propertyName "actions",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["actions"],
   :rdf/type :jsonschema/ObjectSchema})

(def ClusterConfigTelemetry
  {:db/ident :qdrant/ClusterConfigTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "consensus",
                            :rdf/type :qdrant/ConsensusConfigTelemetry}
                           {:jsonschema/propertyName "p2p",
                            :rdf/type :qdrant/P2pConfigTelemetry}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "grpc_timeout_ms",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["consensus" "grpc_timeout_ms" "p2p"],
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
   "Information about current cluster status and structure",
   :jsonschema/oneOf
   [{:jsonschema/properties {:jsonschema/enum ["disabled"],
                             :jsonschema/propertyName "status",
                             :rdf/type        :jsonschema/StringSchema},
     :jsonschema/required ["status"],
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Description of enabled cluster",
     :jsonschema/properties
     [{:dcterms/description
       "Consequent failures of message send operations in consensus by peer address. On the first success to send to that peer - entry is removed from this hashmap.",
       :jsonschema/properties :qdrant/MessageSendErrors,
       :jsonschema/propertyName "message_send_failures",
       :rdf/type :jsonschema/ObjectSchema}
      {:jsonschema/propertyName "consensus_thread_status",
       :rdf/type :qdrant/ConsensusThreadStatus}
      {:jsonschema/propertyName "raft_info",
       :rdf/type :qdrant/RaftInfo}
      {:dcterms/description
       "Peers composition of the cluster with main information",
       :jsonschema/properties :qdrant/PeerInfo,
       :jsonschema/propertyName "peers",
       :rdf/type :jsonschema/ObjectSchema}
      {:dcterms/description "ID of this peer",
       :jsonschema/format   "uint64",
       :jsonschema/minimum  0,
       :jsonschema/propertyName "peer_id",
       :rdf/type            :jsonschema/IntegerSchema}
      {:jsonschema/enum ["enabled"],
       :jsonschema/propertyName "status",
       :rdf/type        :jsonschema/StringSchema}],
     :jsonschema/required ["consensus_thread_status"
                           "message_send_failures"
                           "peer_id"
                           "peers"
                           "raft_info"
                           "status"],
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/DataSchema})

(def ClusterStatusTelemetry
  {:db/ident :qdrant/ClusterStatusTelemetry,
   :jsonschema/properties [{:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "commit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "number_of_peers",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "consensus_thread_status",
                            :rdf/type :qdrant/ConsensusThreadStatus}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "pending_operations",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "term",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "is_voter",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "role",
                            :rdf/type :qdrant/StateRole}],
   :jsonschema/required ["commit"
                         "consensus_thread_status"
                         "is_voter"
                         "number_of_peers"
                         "pending_operations"
                         "term"],
   :rdf/type :jsonschema/ObjectSchema})

(def ClusterTelemetry
  {:db/ident :qdrant/ClusterTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "config",
                            :rdf/type :qdrant/ClusterConfigTelemetry}
                           {:jsonschema/propertyName "status",
                            :rdf/type :qdrant/ClusterStatusTelemetry}
                           {:jsonschema/propertyName "enabled",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required ["enabled"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionClusterInfo
  "Current clustering distribution for the collection"
  {:db/ident :qdrant/CollectionClusterInfo,
   :dcterms/description "Current clustering distribution for the collection",
   :jsonschema/properties [{:dcterms/description "Shard transfers",
                            :jsonschema/items :qdrant/ShardTransferInfo,
                            :jsonschema/propertyName "shard_transfers",
                            :rdf/type :jsonschema/ArraySchema}
                           {:dcterms/description "Remote shards",
                            :jsonschema/items :qdrant/RemoteShardInfo,
                            :jsonschema/propertyName "remote_shards",
                            :rdf/type :jsonschema/ArraySchema}
                           {:dcterms/description "Local shards",
                            :jsonschema/items :qdrant/LocalShardInfo,
                            :jsonschema/propertyName "local_shards",
                            :rdf/type :jsonschema/ArraySchema}
                           {:dcterms/description "Total number of shards",
                            :jsonschema/format   "uint",
                            :jsonschema/minimum  0,
                            :jsonschema/propertyName "shard_count",
                            :rdf/type            :jsonschema/IntegerSchema}
                           {:dcterms/description "ID of this peer",
                            :jsonschema/format   "uint64",
                            :jsonschema/minimum  0,
                            :jsonschema/propertyName "peer_id",
                            :rdf/type            :jsonschema/IntegerSchema}],
   :jsonschema/required
   ["local_shards" "peer_id" "remote_shards" "shard_count" "shard_transfers"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionConfig
  {:db/ident :qdrant/CollectionConfig,
   :jsonschema/properties [{:jsonschema/propertyName "quantization_config",
                            :rdf/type :qdrant/QuantizationConfig}
                           {:jsonschema/propertyName "wal_config",
                            :rdf/type :qdrant/WalConfig}
                           {:jsonschema/propertyName "optimizer_config",
                            :rdf/type :qdrant/OptimizersConfig}
                           {:jsonschema/propertyName "hnsw_config",
                            :rdf/type :qdrant/HnswConfig}
                           {:jsonschema/propertyName "params",
                            :rdf/type :qdrant/CollectionParams}],
   :jsonschema/required
   ["hnsw_config" "optimizer_config" "params" "wal_config"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionDescription
  {:db/ident :qdrant/CollectionDescription,
   :jsonschema/properties [{:jsonschema/propertyName "name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["name"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionInfo
  "Current statistics and configuration of the collection"
  {:db/ident :qdrant/CollectionInfo,
   :dcterms/description
   "Current statistics and configuration of the collection",
   :jsonschema/properties
   [{:dcterms/description
     "Number of segments in collection. Each segment has independent vector as payload indexes",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "segments_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName "status",
     :rdf/type :qdrant/CollectionStatus}
    {:dcterms/description
     "Number of points (vectors + payloads) in collection Each point could be accessed by unique id",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "points_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description "Types of stored payload",
     :jsonschema/propertyName "payload_schema",
     :rdf/type :qdrant/PayloadIndexInfo}
    {:jsonschema/propertyName "config",
     :rdf/type :qdrant/CollectionConfig}
    {:jsonschema/propertyName "optimizer_status",
     :rdf/type :qdrant/OptimizersStatus}
    {:dcterms/description
     "Number of vectors in collection All vectors in collection are available for querying Calculated as `points_count x vectors_per_point` Where `vectors_per_point` is a number of named vectors in schema",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "vectors_count",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Number of indexed vectors in the collection. Indexed vectors in large segments are faster to query, as it is stored in vector index (HNSW)",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "indexed_vectors_count",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["config"
                         "indexed_vectors_count"
                         "optimizer_status"
                         "payload_schema"
                         "points_count"
                         "segments_count"
                         "status"
                         "vectors_count"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionParams
  {:db/ident :qdrant/CollectionParams,
   :jsonschema/properties
   [{:dcterms/description
     "If true - point's payload will not be stored in memory. It will be read from the disk every time it is requested. This setting saves RAM by (slightly) increasing the response time. Note: those payload values that are involved in filtering and are indexed - remain in RAM.",
     :jsonschema/default false,
     :jsonschema/propertyName "on_disk_payload",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     "Defines how many replicas should apply the operation for us to consider it successful. Increasing this number will make the collection more resilient to inconsistencies, but will also make it fail if not enough replicas are available. Does not have any performance impact.",
     :jsonschema/default 1,
     :jsonschema/format "uint32",
     :jsonschema/minimum 1,
     :jsonschema/propertyName "write_consistency_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description "Number of replicas for each shard",
     :jsonschema/default  1,
     :jsonschema/format   "uint32",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "replication_factor",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description "Number of shards the collection has",
     :jsonschema/default  1,
     :jsonschema/format   "uint32",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "shard_number",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/propertyName "vectors",
     :rdf/type :qdrant/VectorsConfig}],
   :jsonschema/required ["vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionParamsDiff
  {:db/ident :qdrant/CollectionParamsDiff,
   :jsonschema/properties
   [{:dcterms/description
     "Minimal number successful responses from replicas to consider operation successful",
     :jsonschema/format "uint32",
     :jsonschema/minimum 1,
     :jsonschema/propertyName "write_consistency_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description "Number of replicas for each shard",
     :jsonschema/format   "uint32",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "replication_factor",
     :rdf/type            :jsonschema/IntegerSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionStatus
  "Current state of the collection. `Green` - all good. `Yellow` - optimization is running, `Red` - some operations failed and was not recovered"
  {:db/ident        :qdrant/CollectionStatus,
   :dcterms/description
   "Current state of the collection. `Green` - all good. `Yellow` - optimization is running, `Red` - some operations failed and was not recovered",
   :jsonschema/enum ["green" "yellow" "red"],
   :rdf/type        :jsonschema/StringSchema})

(def CollectionTelemetry
  {:db/ident :qdrant/CollectionTelemetry,
   :jsonschema/properties [{:jsonschema/items :qdrant/ShardTransferInfo,
                            :jsonschema/propertyName "transfers",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/items :qdrant/ReplicaSetTelemetry,
                            :jsonschema/propertyName "shards",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName "config",
                            :rdf/type :qdrant/CollectionConfig}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "init_time_ms",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "id",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["config" "id" "init_time_ms" "shards" "transfers"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionTelemetryEnum
  {:db/ident         :qdrant/CollectionTelemetryEnum,
   :jsonschema/anyOf [:qdrant/CollectionTelemetry
                      :qdrant/CollectionsAggregatedTelemetry],
   :rdf/type         :jsonschema/DataSchema})

(def CollectionsAggregatedTelemetry
  {:db/ident :qdrant/CollectionsAggregatedTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "params",
                            :rdf/type :qdrant/CollectionParams}
                           {:jsonschema/propertyName "optimizers_status",
                            :rdf/type :qdrant/OptimizersStatus}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "vectors",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["optimizers_status" "params" "vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionsAliasesResponse
  {:db/ident :qdrant/CollectionsAliasesResponse,
   :jsonschema/properties [{:jsonschema/items :qdrant/AliasDescription,
                            :jsonschema/propertyName "aliases",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["aliases"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionsResponse
  {:db/ident :qdrant/CollectionsResponse,
   :jsonschema/properties [{:jsonschema/items :qdrant/CollectionDescription,
                            :jsonschema/propertyName "collections",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["collections"],
   :rdf/type :jsonschema/ObjectSchema})

(def CollectionsTelemetry
  {:db/ident :qdrant/CollectionsTelemetry,
   :jsonschema/properties [{:jsonschema/items :qdrant/CollectionTelemetryEnum,
                            :jsonschema/propertyName "collections",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "number_of_collections",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["number_of_collections"],
   :rdf/type :jsonschema/ObjectSchema})

(def CompressionRatio
  {:db/ident        :qdrant/CompressionRatio,
   :jsonschema/enum ["x4" "x8" "x16" "x32" "x64"],
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
   :jsonschema/properties [{:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "bootstrap_timeout_sec",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "tick_period_ms",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "max_message_queue_size",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required
   ["bootstrap_timeout_sec" "max_message_queue_size" "tick_period_ms"],
   :rdf/type :jsonschema/ObjectSchema})

(def ConsensusThreadStatus
  "Information about current consensus thread status"
  {:db/ident :qdrant/ConsensusThreadStatus,
   :dcterms/description "Information about current consensus thread status",
   :jsonschema/oneOf
   [{:jsonschema/properties [{:jsonschema/format "date-time",
                              :jsonschema/propertyName "last_update",
                              :rdf/type :jsonschema/StringSchema}
                             {:jsonschema/enum ["working"],
                              :jsonschema/propertyName
                              "consensus_thread_status",
                              :rdf/type        :jsonschema/StringSchema}],
     :jsonschema/required ["consensus_thread_status" "last_update"],
     :rdf/type :jsonschema/ObjectSchema}
    {:jsonschema/properties {:jsonschema/enum ["stopped"],
                             :jsonschema/propertyName "consensus_thread_status",
                             :rdf/type        :jsonschema/StringSchema},
     :jsonschema/required ["consensus_thread_status"],
     :rdf/type :jsonschema/ObjectSchema}
    {:jsonschema/properties [{:jsonschema/propertyName "err",
                              :rdf/type :jsonschema/StringSchema}
                             {:jsonschema/enum ["stopped_with_err"],
                              :jsonschema/propertyName
                              "consensus_thread_status",
                              :rdf/type        :jsonschema/StringSchema}],
     :jsonschema/required ["consensus_thread_status" "err"],
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/DataSchema})

(def CountRequest
  "Count Request Counts the number of points which satisfy the given filter. If filter is not provided, the count of all points in the collection will be returned."
  {:db/ident :qdrant/CountRequest,
   :dcterms/description
   "Count Request Counts the number of points which satisfy the given filter. If filter is not provided, the count of all points in the collection will be returned.",
   :jsonschema/properties
   [{:dcterms/description
     "If true, count exact number of points. If false, count approximate number of points faster. Approximate count might be unreliable during the indexing process. Default: true",
     :jsonschema/default true,
     :jsonschema/propertyName "exact",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     "Look only for points which satisfies this conditions",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName "filter"}],
   :rdf/type :jsonschema/ObjectSchema})

(def CountResult
  {:db/ident :qdrant/CountResult,
   :jsonschema/properties [{:dcterms/description
                            "Number of points which satisfy the conditions",
                            :jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "count",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["count"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateAlias
  "Create alternative name for a collection. Collection will be available under both names for search, retrieve,"
  {:db/ident :qdrant/CreateAlias,
   :dcterms/description
   "Create alternative name for a collection. Collection will be available under both names for search, retrieve,",
   :jsonschema/properties [{:jsonschema/propertyName "alias_name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "collection_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["alias_name" "collection_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateAliasOperation
  {:db/ident :qdrant/CreateAliasOperation,
   :jsonschema/properties [{:jsonschema/propertyName "create_alias",
                            :rdf/type :qdrant/CreateAlias}],
   :jsonschema/required ["create_alias"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateCollection
  "Operation for creating new collection and (optionally) specify index params"
  {:db/ident :qdrant/CreateCollection,
   :dcterms/description
   "Operation for creating new collection and (optionally) specify index params",
   :jsonschema/properties
   [{:dcterms/description
     "Custom params for HNSW index. If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/HnswConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName "hnsw_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description
     "Defines how many replicas should apply the operation for us to consider it successful. Increasing this number will make the collection more resilient to inconsistencies, but will also make it fail if not enough replicas are available. Does not have any performance impact.",
     :jsonschema/format "uint32",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "write_consistency_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:jsonschema/propertyName "vectors",
     :rdf/type :qdrant/VectorsConfig}
    {:dcterms/description
     "Number of shards in collection. Default is 1 for standalone, otherwise equal to the number of nodes Minimum is 1",
     :jsonschema/format "uint32",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "shard_number",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Custom params for WAL. If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/WalConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName "wal_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Specify other collection to copy data from.",
     :jsonschema/anyOf [:qdrant/InitFrom :jsonschema/NullSchema],
     :jsonschema/propertyName "init_from",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description
     "Custom params for Optimizers.  If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/OptimizersConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName "optimizers_config",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description
     "If true - point's payload will not be stored in memory. It will be read from the disk every time it is requested. This setting saves RAM by (slightly) increasing the response time. Note: those payload values that are involved in filtering and are indexed - remain in RAM.",
     :jsonschema/propertyName "on_disk_payload",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     "Number of shards replicas. Default is 1 Minimum is 1",
     :jsonschema/format "uint32",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "replication_factor",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Quantization parameters. If none - quantization is disabled.",
     :jsonschema/anyOf [:qdrant/QuantizationConfig :jsonschema/NullSchema],
     :jsonschema/propertyName "quantization_config",
     :rdf/type :jsonschema/DataSchema}],
   :jsonschema/required ["vectors"],
   :rdf/type :jsonschema/ObjectSchema})

(def CreateFieldIndex
  "Operation for creating new field index"
  {:db/ident :qdrant/CreateFieldIndex,
   :dcterms/description "Operation for creating new field index",
   :jsonschema/properties
   [{:dcterms/description "Field schema. If none - default schema is used.",
     :jsonschema/anyOf [:qdrant/PayloadFieldSchema :jsonschema/NullSchema],
     :jsonschema/propertyName "field_schema",
     :rdf/type :jsonschema/DataSchema}
    {:jsonschema/propertyName "field_name",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["field_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeleteAlias
  "Delete alias if exists"
  {:db/ident :qdrant/DeleteAlias,
   :dcterms/description "Delete alias if exists",
   :jsonschema/properties [{:jsonschema/propertyName "alias_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["alias_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeleteAliasOperation
  "Delete alias if exists"
  {:db/ident :qdrant/DeleteAliasOperation,
   :dcterms/description "Delete alias if exists",
   :jsonschema/properties [{:jsonschema/propertyName "delete_alias",
                            :rdf/type :qdrant/DeleteAlias}],
   :jsonschema/required ["delete_alias"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeletePayload
  "Deletes payload values from points"
  {:db/ident :qdrant/DeletePayload,
   :dcterms/description "Deletes payload values from points",
   :jsonschema/properties
   [{:dcterms/description
     "Deletes values from points that satisfy this filter condition",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName "filter",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Deletes values from each point in this list",
     :jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName "points",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description "List of payload keys to remove from payload",
     :jsonschema/items :jsonschema/StringSchema,
     :jsonschema/propertyName "keys",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["keys"],
   :rdf/type :jsonschema/ObjectSchema})

(def DeleteVectors
  "Deletes vectors from points"
  {:db/ident :qdrant/DeleteVectors,
   :dcterms/description "Deletes vectors from points",
   :jsonschema/properties
   [{:dcterms/description "Vector names",
     :jsonschema/items :jsonschema/StringSchema,
     :jsonschema/minItems 1,
     :jsonschema/propertyName "vector",
     :jsonschema/uniqueItems true,
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description
     "Deletes values from points that satisfy this filter condition",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName "filter",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Deletes values from each point in this list",
     :jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName "points",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def Distance
  "Type of internal tags, build from payload Distance function types used to compare vectors"
  {:db/ident        :qdrant/Distance,
   :dcterms/description
   "Type of internal tags, build from payload Distance function types used to compare vectors",
   :jsonschema/enum ["Cosine" "Euclid" "Dot"],
   :rdf/type        :jsonschema/StringSchema})

(def DropReplicaOperation
  "Drop replica operation"
  {:db/ident :qdrant/DropReplicaOperation,
   :dcterms/description "Drop replica operation",
   :jsonschema/properties [{:jsonschema/propertyName "drop_replica",
                            :rdf/type :qdrant/Replica}],
   :jsonschema/required ["drop_replica"],
   :rdf/type :jsonschema/ObjectSchema})

(def ErrorResponse
  "Error response"
  {:db/ident :qdrant/ErrorResponse,
   :dcterms/description "Error response",
   :jsonschema/properties
   [{:jsonschema/propertyName "result",
     :rdf/type :jsonschema/ObjectSchema}
    {:jsonschema/properties {:dcterms/description
                             "Description of the occurred error.",
                             :jsonschema/propertyName "error",
                             :rdf/type :jsonschema/StringSchema},
     :jsonschema/propertyName "status",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Time spent to process this request",
     :jsonschema/format "float",
     :jsonschema/propertyName "time",
     :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def ExtendedPointId
  "Type, used for specifying point ID in user interface"
  {:db/ident         :qdrant/ExtendedPointId,
   :dcterms/description "Type, used for specifying point ID in user interface",
   :jsonschema/anyOf [{:jsonschema/format "uint64",
                       :jsonschema/minimum 0,
                       :rdf/type :jsonschema/IntegerSchema}
                      {:jsonschema/format "uuid",
                       :rdf/type :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def FieldCondition
  "All possible payload filtering conditions"
  {:db/ident :qdrant/FieldCondition,
   :dcterms/description "All possible payload filtering conditions",
   :jsonschema/properties
   [{:dcterms/description "Check number of values of the field",
     :jsonschema/anyOf [:qdrant/ValuesCount :jsonschema/NullSchema],
     :jsonschema/propertyName "values_count",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Check if geo point is within a given radius",
     :jsonschema/anyOf [:qdrant/GeoRadius :jsonschema/NullSchema],
     :jsonschema/propertyName "geo_radius",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Check if points geo location lies in a given area",
     :jsonschema/anyOf [:qdrant/GeoBoundingBox :jsonschema/NullSchema],
     :jsonschema/propertyName "geo_bounding_box",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Check if points value lies in a given range",
     :jsonschema/anyOf [:qdrant/Range :jsonschema/NullSchema],
     :jsonschema/propertyName "range",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Check if point has field with a given value",
     :jsonschema/anyOf [:qdrant/Match :jsonschema/NullSchema],
     :jsonschema/propertyName "match",
     :rdf/type :jsonschema/DataSchema}
    {:dcterms/description "Payload key",
     :jsonschema/propertyName "key",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["key"],
   :rdf/type :jsonschema/ObjectSchema})

(def Filter
  {:db/ident :qdrant/Filter,
   :jsonschema/properties
   [{:dcterms/description "All conditions must NOT match",
     :jsonschema/items :qdrant/Condition,
     :jsonschema/propertyName "must_not",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description "All conditions must match",
     :jsonschema/items :qdrant/Condition,
     :jsonschema/propertyName "must",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description "At least one of those conditions should match",
     :jsonschema/items :qdrant/Condition,
     :jsonschema/propertyName "should",
     :rdf/type :jsonschema/ArraySchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def FilterSelector
  {:db/ident :qdrant/FilterSelector,
   :jsonschema/properties [{:jsonschema/propertyName "filter",
                            :rdf/type :qdrant/Filter}],
   :jsonschema/required ["filter"],
   :rdf/type :jsonschema/ObjectSchema})

(def GeoBoundingBox
  "Geo filter request Matches coordinates inside the rectangle, described by coordinates of lop-left and bottom-right edges"
  {:db/ident :qdrant/GeoBoundingBox,
   :dcterms/description
   "Geo filter request\n\nMatches coordinates inside the rectangle, described by coordinates of lop-left and bottom-right edges",
   :jsonschema/properties [{:dcterms/description "Geo point payload schema",
                            :jsonschema/propertyName "bottom_right",
                            :rdf/type :qdrant/GeoPoint}
                           {:dcterms/description "Geo point payload schema",
                            :jsonschema/propertyName "top_left",
                            :rdf/type :qdrant/GeoPoint}],
   :jsonschema/required ["bottom_right" "top_left"],
   :rdf/type :jsonschema/ObjectSchema})

(def GeoPoint
  "Geo point payload schema"
  {:db/ident :qdrant/GeoPoint,
   :dcterms/description "Geo point payload schema",
   :jsonschema/properties [{:jsonschema/propertyName "lat",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/propertyName "lon",
                            :rdf/type :jsonschema/NumberSchema}],
   :jsonschema/required ["lat" "lon"],
   :rdf/type :jsonschema/ObjectSchema})

(def GeoRadius
  "Geo filter request Matches coordinates inside the circle of `radius` and center with coordinates `center`"
  {:db/ident :qdrant/GeoRadius,
   :dcterms/description
   "Geo filter request\n\nMatches coordinates inside the circle of `radius` and center with coordinates `center`",
   :jsonschema/properties [{:dcterms/description "Radius of the area in meters",
                            :jsonschema/propertyName "radius",
                            :rdf/type :jsonschema/NumberSchema}
                           {:dcterms/description "Geo point payload schema",
                            :jsonschema/propertyName "center",
                            :rdf/type :qdrant/GeoPoint}],
   :jsonschema/required ["center" "radius"],
   :rdf/type :jsonschema/ObjectSchema})

(def GroupId
  {:db/ident         :qdrant/GroupId,
   :jsonschema/anyOf [{:jsonschema/format "int64",
                       :rdf/type :jsonschema/IntegerSchema}
                      {:jsonschema/format "uint64",
                       :jsonschema/minimum 0,
                       :rdf/type :jsonschema/IntegerSchema}
                      {:rdf/type :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def GroupsResult
  {:db/ident :qdrant/GroupsResult,
   :jsonschema/properties [{:jsonschema/items :qdrant/PointGroup,
                            :jsonschema/propertyName "groups",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["groups"],
   :rdf/type :jsonschema/ObjectSchema})

(def GrpcTelemetry
  {:db/ident :qdrant/GrpcTelemetry,
   :jsonschema/properties [{:jsonschema/properties
                            :qdrant/OperationDurationStatistics,
                            :jsonschema/propertyName "responses",
                            :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required ["responses"],
   :rdf/type :jsonschema/ObjectSchema})

(def HasIdCondition
  "ID-based filtering condition"
  {:db/ident :qdrant/HasIdCondition,
   :dcterms/description "ID-based filtering condition",
   :jsonschema/properties [{:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName "has_id",
                            :jsonschema/uniqueItems true,
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["has_id"],
   :rdf/type :jsonschema/ObjectSchema})

(def HnswConfig
  "Config of HNSW index"
  {:db/ident :qdrant/HnswConfig,
   :dcterms/description "Config of HNSW index",
   :jsonschema/properties
   [{:dcterms/description
     "Custom M param for hnsw graph built for payload index . If not set, default M will be used.",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "payload_m",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Store HNSW index on disk. If set to false, index will be stored in RAM. Default: false",
     :jsonschema/propertyName "on_disk",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     "Number of parallel threads used for background index building. If 0 - auto selection.",
     :jsonschema/default 0,
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "max_indexing_threads",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Minimal size (in KiloBytes) of vectors for additional payload-based indexing. If payload chunk is smaller than `full_scan_threshold_kb` additional indexing won't be used - in this case full-scan search should be preferred by query planner and additional indexing is not required. Note: 1Kb = 1 vector of size 256",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "full_scan_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Number of neighbours to consider during the index building. Larger the value - more accurate the search, more time required to build index.",
     :jsonschema/format "uint",
     :jsonschema/minimum 4,
     :jsonschema/propertyName "ef_construct",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Number of edges per node in the index graph. Larger the value - more accurate the search, more space required.",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "m",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["ef_construct" "full_scan_threshold" "m"],
   :rdf/type :jsonschema/ObjectSchema})

(def HnswConfigDiff
  {:db/ident :qdrant/HnswConfigDiff,
   :jsonschema/properties
   [{:dcterms/description
     "Number of edges per node in the index graph. Larger the value - more accurate the search, more space required.",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "m",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Number of neighbours to consider during the index building. Larger the value - more accurate the search, more time required to build the index.",
     :jsonschema/format "uint",
     :jsonschema/minimum 4,
     :jsonschema/propertyName "ef_construct",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Minimal size (in kilobytes) of vectors for additional payload-based indexing. If payload chunk is smaller than `full_scan_threshold_kb` additional indexing won't be used - in this case full-scan search should be preferred by query planner and additional indexing is not required. Note: 1Kb = 1 vector of size 256",
     :jsonschema/format "uint",
     :jsonschema/minimum 1000,
     :jsonschema/propertyName "full_scan_threshold",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Number of parallel threads used for background index building. If 0 - auto selection.",
     :jsonschema/format "uint",
     :jsonschema/minimum 1000,
     :jsonschema/propertyName "max_indexing_threads",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Store HNSW index on disk. If set to false, the index will be stored in RAM. Default: false",
     :jsonschema/propertyName "on_disk",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     "Custom M param for additional payload-aware HNSW links. If not set, default M will be used.",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "payload_m",
     :rdf/type :jsonschema/IntegerSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def Indexes
  "Vector index configuration"
  {:db/ident :qdrant/Indexes,
   :dcterms/description "Vector index configuration",
   :jsonschema/oneOf
   [{:dcterms/description
     "Do not use any index, scan the whole vector collection during search. Guarantee 100% precision, but may be time-consuming on large collections.",
     :jsonschema/properties [{:jsonschema/enum ["plain"],
                              :jsonschema/propertyName "type",
                              :rdf/type        :jsonschema/StringSchema}
                             {:jsonschema/propertyName "options",
                              :rdf/type :jsonschema/ObjectSchema}],
     :jsonschema/required ["options" "type"],
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Use filterable HNSW index for approximate search. Is very fast even on a very huge collections, but require additional space to store index and additional time to build it.",
     :jsonschema/properties [{:jsonschema/enum ["hnsw"],
                              :jsonschema/propertyName "type",
                              :rdf/type        :jsonschema/StringSchema}
                             {:jsonschema/propertyName "options",
                              :jsonschema/ref :qdrant/HnswConfig,
                              :rdf/type       :jsonschema/ObjectSchema}],
     :jsonschema/required ["options" "type"],
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def InitFrom
  "Operation for creating new collection and (optionally) specify index params"
  {:db/ident :qdrant/InitFrom,
   :dcterms/description
   "Operation for creating new collection and (optionally) specify index params",
   :jsonschema/properties [{:jsonschema/propertyName "collection",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required "collection",
   :rdf/type :jsonschema/ObjectSchema})

(def IsEmptyCondition
  "Select points with empty payload for a specified field"
  {:db/ident :qdrant/IsEmptyCondition,
   :dcterms/description
   "Select points with empty payload for a specified field",
   :jsonschema/properties [{:jsonschema/propertyName "is_empty",
                            :rdf/type :qdrant/PayloadField}],
   :jsonschema/required "is_empty",
   :rdf/type :jsonschema/ObjectSchema})

(def IsNullCondition
  "Select points with null payload for a specified field"
  {:db/ident :qdrant/IsNullCondition,
   :dcterms/description "Select points with null payload for a specified field",
   :jsonschema/properties [{:jsonschema/propertyName "is_null",
                            :rdf/type :qdrant/PayloadField}],
   :jsonschema/required "is_null",
   :rdf/type :jsonschema/ObjectSchema})

(def LocalShardInfo
  {:db/ident :qdrant/LocalShardInfo,
   :jsonschema/properties [{:jsonschema/propertyName "state",
                            :rdf/type :qdrant/ReplicaState}
                           {:dcterms/description
                            "Number of points in the shard",
                            :jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "points_count",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:dcterms/description "Local shard id",
                            :jsonschema/format   "uint32",
                            :jsonschema/minimum  0,
                            :jsonschema/propertyName "shard_id",
                            :rdf/type            :jsonschema/IntegerSchema}],
   :jsonschema/required ["points_count" "shard_id" "state"],
   :rdf/type :jsonschema/ObjectSchema})

(def LocalShardTelemetry
  {:db/ident :qdrant/LocalShardTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "optimizations",
                            :rdf/type :qdrant/OptimizerTelemetry}
                           {:jsonschema/items :qdrant/SegmentTelemetry,
                            :jsonschema/propertyName "segments",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName "variant_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["optimizations" "segments"],
   :rdf/type :jsonschema/ObjectSchema})

(def LocksOption
  {:db/ident :qdrant/LocksOption,
   :jsonschema/properties [{:jsonschema/propertyName "write",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "error_message",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["write"],
   :rdf/type :jsonschema/ObjectSchema})

(def LookupLocation
  {:db/ident :qdrant/LookupLocation,
   :jsonschema/properties
   [{:dcterms/description
     "Optional name of the vector field within the collection. If not provided, the default vector field will be used.",
     :jsonschema/propertyName "vector",
     :rdf/type :jsonschema/StringSchema}
    {:dcterms/description "Name of the collection used for lookup",
     :jsonschema/propertyName "collection",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["collection"],
   :rdf/type :jsonschema/ObjectSchema})

(def Match
  "Match filter request"
  {:db/ident         :qdrant/Match,
   :dcterms/description "Match filter request",
   :jsonschema/anyOf [:qdrant/MatchValue
                      :qdrant/MatchText
                      :qdrant/MatchAny
                      :qdrant/MatchExcept],
   :rdf/type         :jsonschema/DataSchema})

(def MatchAny
  {:db/ident :qdrant/MatchAny,
   :jsonschema/properties [{:jsonschema/propertyName "any",
                            :rdf/type :qdrant/AnyVariants}],
   :jsonschema/required ["any"],
   :rdf/type :jsonschema/ObjectSchema})

(def MatchExcept
  {:db/ident :qdrant/MatchExcept,
   :jsonschema/properties [{:jsonschema/propertyName "except",
                            :rdf/type :qdrant/AnyVariants}],
   :jsonschema/required ["except"],
   :rdf/type :jsonschema/ObjectSchema})

(def MatchText
  {:db/ident :qdrant/MatchText,
   :jsonschema/properties [{:jsonschema/propertyName "text",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["text"],
   :rdf/type :jsonschema/ObjectSchema})

(def MatchValue
  {:db/ident :qdrant/MatchValue,
   :jsonschema/properties [{:jsonschema/propertyName "value",
                            :rdf/type :qdrant/ValueVariants}],
   :jsonschema/required ["value"],
   :rdf/type :jsonschema/ObjectSchema})

(def MessageSendErrors
  {:db/ident :qdrant/MessageSendErrors,
   :jsonschema/properties [{:jsonschema/propertyName "latest_error",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "count",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["count"],
   :rdf/type :jsonschema/ObjectSchema})

(def MoveShard
  {:db/ident :qdrant/MoveShard,
   :jsonschema/properties [{:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "from_peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "to_peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint32",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["from_peer_id" "shard_id" "to_peer_id"],
   :rdf/type :jsonschema/ObjectSchema})

(def MoveShardOperation
  {:db/ident :qdrant/MoveShardOperation,
   :jsonschema/properties [{:jsonschema/propertyName "move_shard",
                            :rdf/type :qdrant/MoveShard}],
   :jsonschema/required ["move_shard"],
   :rdf/type :jsonschema/ObjectSchema})

(def NamedVector
  {:db/ident :qdrant/NamedVector,
   :jsonschema/properties [{:jsonschema/items {:jsonschema/format "float",
                                               :rdf/type
                                               :jsonschema/NumberSchema},
                            :jsonschema/propertyName "vector",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName "name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["name" "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def NamedVectorStruct
  {:db/ident         :qdrant/NamedVectorStruct,
   :jsonschema/anyOf [{:jsonschema/items {:jsonschema/format "float",
                                          :rdf/type :jsonschema/NumberSchema},
                       :rdf/type         :jsonschema/ArraySchema}
                      :qdrant/NamedVector],
   :rdf/type         :jsonschema/DataSchema})

(def Nested
  {:db/ident :qdrant/Nested,
   :jsonschema/properties [{:jsonschema/propertyName "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/propertyName "key",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["filter" "key"],
   :rdf/type :jsonschema/ObjectSchema})

(def NestedCondition
  {:db/ident :qdrant/NestedCondition,
   :jsonschema/properties [{:jsonschema/propertyName "nested",
                            :rdf/type :qdrant/Nested}],
   :jsonschema/required ["nested"],
   :rdf/type :jsonschema/ObjectSchema})

(def OperationDurationStatistics
  {:db/ident :qdrant/OperationDurationStatistics,
   :jsonschema/properties [{:jsonschema/format "date-time",
                            :jsonschema/propertyName "last_responded",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format "float",
                            :jsonschema/propertyName "max_duration_micros",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/format "float",
                            :jsonschema/propertyName "min_duration_micros",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/format "float",
                            :jsonschema/propertyName "avg_duration_micros",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "fail_count",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "count",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["count"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizerTelemetry
  {:db/ident :qdrant/OptimizerTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "optimizations",
                            :rdf/type :qdrant/OperationDurationStatistics}
                           {:jsonschema/propertyName "status",
                            :rdf/type :qdrant/OptimizersStatus}],
   :jsonschema/required ["optimizations" "status"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizersConfig
  {:db/ident :qdrant/OptimizersConfig,
   :jsonschema/properties [{:jsonschema/format "uint",
                            :jsonschema/minimum 100,
                            :jsonschema/propertyName "vacuum_min_vector_number",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "indexing_threshold",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format  "float",
                            :jsonschema/maximum 1,
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "deleted_threshold",
                            :rdf/type           :jsonschema/NumberSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "max_optimization_threads",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "max_segment_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "memmap_threshold",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "default_segment_number",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "flush_interval_sec",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["default_segment_number"
                         "deleted_threshold"
                         "flush_interval_sec"
                         "max_optimization_threads"
                         "vacuum_min_vector_number"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizersConfigDiff
  {:db/ident :qdrant/OptimizersConfigDiff,
   :jsonschema/properties [{:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "max_optimization_threads",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "indexing_threshold",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "flush_interval_sec",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format  "float",
                            :jsonschema/maximum 1,
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "deleted_threshold",
                            :rdf/type           :jsonschema/NumberSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "max_segment_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "memmap_threshold",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "default_segment_number",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 100,
                            :jsonschema/propertyName "vacuum_min_vector_number",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["default_segment_number"
                         "deleted_threshold"
                         "flush_interval_sec"
                         "max_optimization_threads"
                         "vacuum_min_vector_number"],
   :rdf/type :jsonschema/ObjectSchema})

(def OptimizersStatus
  "Current state of the collection"
  {:db/ident         :qdrant/OptimizersStatus,
   :dcterms/description "Current state of the collection",
   :jsonschema/oneOf [{:dcterms/description
                       "Something wrong happened with optimizers",
                       :jsonschema/properties {:jsonschema/propertyName "error",
                                               :rdf/type
                                               :jsonschema/StringSchema},
                       :jsonschema/required ["error"],
                       :rdf/type :jsonschema/ObjectSchema}
                      {:dcterms/description
                       "Optimizers are reporting as expected",
                       :jsonschema/enum ["ok"],
                       :rdf/type        :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/ObjectSchema})

(def P2pConfigTelemetry
  {:db/ident :qdrant/P2pConfigTelemetry,
   :jsonschema/properties [{:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "connection_pool_size",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["connection_pool_size"],
   :rdf/type :jsonschema/ObjectSchema})

(def Payload
  {:db/ident :qdrant/Payload,
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadField
  "Payload field"
  {:db/ident :qdrant/PayloadField,
   :dcterms/description "Payload field",
   :jsonschema/properties [{:dcterms/description "Payload field name",
                            :jsonschema/propertyName "key",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["key"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadFieldSchema
  {:db/ident         :qdrant/PayloadFieldSchema,
   :jsonschema/anyOf [:qdrant/PayloadSchemaType :qdrant/PayloadSchemaParams],
   :rdf/type         :jsonschema/DataSchema})

(def PayloadIndexInfo
  "Display payload field type & index information"
  {:db/ident :qdrant/PayloadIndexInfo,
   :dcterms/description "Display payload field type & index information",
   :jsonschema/properties [{:dcterms/description
                            "Number of points indexed with this index",
                            :jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "points",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/anyOf [:qdrant/PayloadSchemaParams
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName "params"}
                           {:jsonschema/propertyName "data_type",
                            :rdf/type :qdrant/PayloadSchemaType}],
   :jsonschema/required ["data_type" "points"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadIndexTelemetry
  {:db/ident :qdrant/PayloadIndexTelemetry,
   :jsonschema/properties [{:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "histogram_bucket_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "points_count",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "points_values_count",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "field_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["points_count" "points_values_count"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadSchemaParams
  "Payload type with parameters"
  {:db/ident         :qdrant/PayloadSchemaParams,
   :dcterms/description "Payload type with parameters",
   :jsonschema/anyOf [:qdrant/TextIndexParams],
   :rdf/type         :jsonschema/DataSchema})

(def PayloadSchemaType
  "All possible names of payload types"
  {:db/ident        :qdrant/PayloadSchemaType,
   :dcterms/description "All possible names of payload types",
   :jsonschema/enum ["keyword" "integer" "float" "geo" "text"],
   :rdf/type        :jsonschema/StringSchema})

(def PayloadSelector
  "Specifies how to treat payload selector"
  {:db/ident         :qdrant/PayloadSelector,
   :dcterms/description "Specifies how to treat payload selector",
   :jsonschema/anyOf [:qdrant/PayloadSelectorInclude
                      :qdrant/PayloadSelectorExclude],
   :rdf/type         :jsonschema/DataSchema})

(def PayloadSelectorExclude
  {:db/ident :qdrant/PayloadSelectorExclude,
   :jsonschema/properties [{:dcterms/description
                            "Exclude this fields from returning payload",
                            :jsonschema/items :jsonschema/StringSchema,
                            :jsonschema/propertyName "exclude",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["exclude"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadSelectorInclude
  {:db/ident :qdrant/PayloadSelectorInclude,
   :jsonschema/properties [{:dcterms/description
                            "Only include this payload keys",
                            :jsonschema/items :jsonschema/StringSchema,
                            :jsonschema/propertyName "include",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["include"],
   :rdf/type :jsonschema/ObjectSchema})

(def PayloadStorageType
  "Type of payload storage"
  {:db/ident         :qdrant/PayloadStorageType,
   :dcterms/description "Type of payload storage",
   :jsonschema/oneOf [{:jsonschema/enum ["on_disk"],
                       :jsonschema/propertyName "type",
                       :rdf/type        :jsonschema/StringSchema}
                      {:jsonschema/enum ["in_memory"],
                       :jsonschema/propertyName "type",
                       :rdf/type        :jsonschema/StringSchema}],
   :rdf/type         :jsonschema/ObjectSchema})

(def PeerInfo
  "Information of a peer in the cluster"
  {:db/ident :qdrant/PeerInfo,
   :dcterms/description "Information of a peer in the cluster",
   :jsonschema/properties [{:jsonschema/propertyName "uri",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["uri"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointGroup
  {:db/ident :qdrant/PointGroup,
   :jsonschema/properties
   [{:jsonschema/propertyName "id",
     :rdf/type :jsonschema/StringSchema}
    {:dcterms/description
     "Scored points that have the same value of the group_by key",
     :jsonschema/items :qdrant/ScoredPoint,
     :jsonschema/propertyName "hits",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["hits" "id"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointIdsList
  {:db/ident :qdrant/PointIdsList,
   :jsonschema/properties [{:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName "points",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["points"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointInsertOperations
  {:db/ident         :qdrant/PointInsertOperations,
   :jsonschema/oneOf [:qdrant/PointsBatch :qdrant/PointsList],
   :rdf/type         :jsonschema/ObjectSchema})

(def PointRequest
  {:db/ident :qdrant/PointRequest,
   :jsonschema/properties
   [{:jsonschema/propertyName "with_vector",
     :rdf/type :qdrant/WithVector}
    {:dcterms/description
     "Select which payload to return with the response. Default: All",
     :jsonschema/anyOf [:qdrant/WithPayloadInterface :jsonschema/NullSchema],
     :jsonschema/propertyName "with_payload"}
    {:dcterms/description "Look for points with ids",
     :jsonschema/items :qdrant/ExtendedPointId,
     :jsonschema/propertyName "ids",
     :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["ids"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointStruct
  {:db/ident :qdrant/PointStruct,
   :jsonschema/properties [{:dcterms/description "Payload values (optional)",
                            :jsonschema/anyOf [:qdrant/Payload
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName "payload"}
                           {:jsonschema/propertyName "vector",
                            :rdf/type :qdrant/VectorStruct}
                           {:jsonschema/propertyName "id",
                            :rdf/type :qdrant/ExtendedPointId}],
   :jsonschema/required ["id" "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointVectors
  {:db/ident :qdrant/PointVectors,
   :jsonschema/properties [{:jsonschema/propertyName "vector",
                            :rdf/type :qdrant/VectorStruct}
                           {:jsonschema/propertyName "id",
                            :rdf/type :qdrant/ExtendedPointId}],
   :jsonschema/required ["id" "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointsBatch
  {:db/ident :qdrant/PointsBatch,
   :jsonschema/properties [{:jsonschema/propertyName "batch",
                            :rdf/type :qdrant/Batch}],
   :jsonschema/required ["batch"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointsList
  {:db/ident :qdrant/PointsList,
   :jsonschema/properties [{:jsonschema/items :qdrant/PointStruct,
                            :jsonschema/propertyName "points",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["points"],
   :rdf/type :jsonschema/ObjectSchema})

(def PointsSelector
  {:db/ident         :qdrant/PointsSelector,
   :jsonschema/anyOf [:qdrant/PointIdsList :qdrant/FilterSelector],
   :rdf/type         :jsonschema/DataSchema})

(def ProductQuantization
  {:db/ident :qdrant/ProductQuantization,
   :jsonschema/properties [{:jsonschema/propertyName "product",
                            :rdf/type :qdrant/ProductQuantizationConfig}],
   :jsonschema/required ["product"],
   :rdf/type :jsonschema/ObjectSchema})

(def ProductQuantizationConfig
  {:db/ident :qdrant/ProductQuantizationConfig,
   :jsonschema/properties [{:jsonschema/propertyName "always_ram",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "compression",
                            :rdf/type :qdrant/CompressionRatio}],
   :jsonschema/required ["compression"],
   :rdf/type :jsonschema/ObjectSchema})

(def QuantizationConfig
  {:db/ident         :qdrant/QuantizationConfig,
   :jsonschema/anyOf [:qdrant/ScalarQuantization :qdrant/ProductQuantization],
   :rdf/type         :jsonschema/DataSchema})

(def QuantizationSearchParams
  "Additional parameters of the search"
  {:db/ident :qdrant/QuantizationSearchParams,
   :dcterms/description "Additional parameters of the search",
   :jsonschema/properties
   [{:dcterms/description
     "If true, use original vectors to re-score top-k results. Might require more time in case if original vectors are stored on disk. Default is false.",
     :jsonschema/default false,
     :jsonschema/propertyName "rescore",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description
     "If true, quantized vectors are ignored. Default is false.",
     :jsonschema/default false,
     :jsonschema/propertyName "ignore",
     :rdf/type :jsonschema/BooleanSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def RaftInfo
  "Summary information about the current raft state"
  {:db/ident :qdrant/RaftInfo,
   :dcterms/description "Summary information about the current raft state",
   :jsonschema/properties
   [{:dcterms/description "Is this peer a voter or a learner",
     :jsonschema/propertyName "is_voter",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description     "Role of this peer in the current term",
     :jsonschema/anyOf        [:qdrant/StateRole],
     :jsonschema/propertyName "role"}
    {:dcterms/description "Leader of the current term",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "leader",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Number of consensus operations pending to be applied on this peer",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "pending_operations",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "The index of the latest committed (finalized) operation that this peer is aware of.",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "commit",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description
     "Raft divides time into terms of arbitrary length, each beginning with an election. If a candidate wins the election, it remains the leader for the rest of the term. The term number increases monotonically. Each server stores the current term number which is also exchanged in every communication.",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "term",
     :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["commit" "is_voter" "pending_operations" "term"],
   :rdf/type :jsonschema/ObjectSchema})

(def Range
  "Range filter request"
  {:db/ident :qdrant/Range,
   :dcterms/description "Range filter request",
   :jsonschema/properties [{:dcterms/description "point.key <= range.lte",
                            :jsonschema/format "double",
                            :jsonschema/propertyName "lte",
                            :rdf/type :jsonschema/NumberSchema}
                           {:dcterms/description "point.key >= range.gte",
                            :jsonschema/format "double",
                            :jsonschema/propertyName "gte",
                            :rdf/type :jsonschema/NumberSchema}
                           {:dcterms/description "point.key > range.gt",
                            :jsonschema/format "double",
                            :jsonschema/propertyName "gt",
                            :rdf/type :jsonschema/NumberSchema}
                           {:dcterms/description "point.key < range.lt",
                            :jsonschema/format "double",
                            :jsonschema/propertyName "lt",
                            :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def ReadConsistency
  "Read consistency parameter. Defines how many replicas should be queried to get the result. `N` - send N random request and return points, which present on all of them. `majority` - send N/2+1 random request and return points, which present on all of them. `quorum` - send requests to all nodes and return points which present on majority of them. `all` - send requests to all nodes and return points which present on all of them. Default value is `Factor(1)`"
  {:db/ident         :qdrant/ReadConsistency,
   :dcterms/description
   "Read consistency parameter. Defines how many replicas should be queried to get the result. `N` - send N random request and return points, which present on all of them. `majority` - send N/2+1 random request and return points, which present on all of them. `quorum` - send requests to all nodes and return points which present on majority of them. `all` - send requests to all nodes and return points which present on all of them. Default value is `Factor(1)`",
   :jsonschema/anyOf [{:jsonschema/format "uint",
                       :jsonschema/minimum 0,
                       :rdf/type :jsonschema/IntegerSchema}
                      :qdrant/ReadConsistencyType],
   :rdf/type         :jsonschema/ObjectSchema})

(def ReadConsistencyType
  "majority - send N/2+1 random request and return points, which present on all of them. quorum - send requests to all nodes and return points which present on majority of nodes. all - send requests to all nodes and return points which present on all nodes"
  {:db/ident        :qdrant/ReadConsistencyType,
   :dcterms/description
   "majority - send N/2+1 random request and return points, which present on all of them. quorum - send requests to all nodes and return points which present on majority of nodes. all - send requests to all nodes and return points which present on all nodes",
   :jsonschema/enum ["majority" "quorum" "all"],
   :rdf/type        :jsonschema/StringSchema})

(def RecommendGroupsRequest
  "Recommendation request. Provides positive and negative examples of the vectors, which are already stored in the collection. Service should look for the points which are closer to positive examples and at the same time further to negative examples. The concrete way of how to compare negative and positive distances is up to implementation in `segment` crate."
  {:db/ident :qdrant/RecommendGroupsRequest,
   :dcterms/description
   "Recommendation request. Provides positive and negative examples of the vectors, which are already stored in the collection. Service should look for the points which are closer to positive examples and at the same time further to negative examples. The concrete way of how to compare negative and positive distances is up to implementation in `segment` crate.",
   :jsonschema/properties
   [{:dcterms/description
     "Define which vector to use for recommendation, if not specified - try to use default vector",
     :jsonschema/anyOf [:qdrant/UsingVector :jsonschema/NullSchema],
     :jsonschema/propertyName "using",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Look for vectors closest to those",
     :jsonschema/items [:qdrant/ExtendedPointId],
     :jsonschema/propertyName "positive",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description "Additional search params",
     :jsonschema/anyOf [:qdrant/SearchParams :jsonschema/NullSchema],
     :jsonschema/propertyName "params",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "The location used to lookup vectors. If not specified - use current collection. Note: the other collection should have the same vector size as the current collection",
     :jsonschema/anyOf [:qdrant/LookupLocation :jsonschema/NullSchema],
     :jsonschema/propertyName "lookup_from",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Try to avoid vectors like this",
     :jsonschema/default  "[]",
     :jsonschema/items    [:qdrant/ExtendedPointId],
     :jsonschema/propertyName "negative",
     :rdf/type            :jsonschema/ArraySchema}
    {:dcterms/description "Maximum amount of groups to return",
     :jsonschema/format   "uint32",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "limit",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description
     "Select which payload to return with the response. Default: None",
     :jsonschema/anyOf [:qdrant/WithPayloadInterface :jsonschema/NullSchema],
     :jsonschema/propertyName "with_payload",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Look only for points which satisfies this conditions",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/propertyName "filter",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Maximum amount of points to return per group",
     :jsonschema/format   "uint32",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "group_size",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description
     "Define a minimal score threshold for the result. If defined, less similar results will not be returned. Score of the returned result might be higher or smaller than the threshold depending on the Distance function used. E.g. for cosine similarity only higher scores will be returned.",
     :jsonschema/format "float",
     :jsonschema/propertyName "score_threshold",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description "Whether to return the point vector with the result?",
     :jsonschema/anyOf [:qdrant/WithVector :jsonschema/NullSchema],
     :jsonschema/propertyName "with_vector",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Payload field to group by, must be a string or number field. If the field contains more than 1 value, all values will be used for grouping. One point can be in multiple groups.",
     :jsonschema/minLength 1,
     :jsonschema/propertyName "group_by",
     :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["group_by" "group_size" "limit" "positive"],
   :rdf/type :jsonschema/ObjectSchema})

(def RecommendRequest
  {:db/ident :qdrant/RecommendRequest,
   :jsonschema/properties
   [{:dcterms/description
     "Define which vector to use for recommendation, if not specified - try to use default vector",
     :jsonschema/anyOf [:qdrant/UsingVector :jsonschema/NullSchema],
     :jsonschema/default "null",
     :jsonschema/propertyName "using",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Look for vectors closest to those",
     :jsonschema/items [:qdrant/ExtendedPointId],
     :jsonschema/propertyName "positive",
     :rdf/type :jsonschema/ArraySchema}
    {:dcterms/description
     "Offset of the first result to return. May be used to paginate results. Note: large offset values may cause performance issues.",
     :jsonschema/default 0,
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "offset",
     :rdf/type :jsonschema/IntegerSchema}
    {:dcterms/description "Additional search params",
     :jsonschema/anyOf [:qdrant/SearchParams :jsonschema/NullSchema],
     :jsonschema/propertyName "params",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "The location used to lookup vectors. If not specified - use current collection. Note: the other collection should have the same vector size as the current collection",
     :jsonschema/anyOf [:qdrant/LookupLocation :jsonschema/NullSchema],
     :jsonschema/default "null",
     :jsonschema/propertyName "lookup_from",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Try to avoid vectors like this",
     :jsonschema/default  "[]",
     :jsonschema/items    [:qdrant/ExtendedPointId],
     :jsonschema/propertyName "negative",
     :rdf/type            :jsonschema/ArraySchema}
    {:dcterms/description "Max number of result to return",
     :jsonschema/format   "uint",
     :jsonschema/minimum  0,
     :jsonschema/propertyName "limit",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description
     "Select which payload to return with the response. Default: None",
     :jsonschema/anyOf [:qdrant/WithPayloadInterface :jsonschema/NullSchema],
     :jsonschema/default "null",
     :jsonschema/propertyName "with_payload",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Look only for points which satisfies this conditions",
     :jsonschema/anyOf [:qdrant/Filter :jsonschema/NullSchema],
     :jsonschema/default "null",
     :jsonschema/propertyName "filter",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Define a minimal score threshold for the result. If defined, less similar results will not be returned. Score of the returned result might be higher or smaller than the threshold depending on the Distance function used. E.g. for cosine similarity only higher scores will be returned.",
     :jsonschema/default "null",
     :jsonschema/exclusiveMinimum true,
     :jsonschema/format "float",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "score_threshold",
     :rdf/type :jsonschema/NumberSchema}
    {:dcterms/description "Whether to return the point vector with the result?",
     :jsonschema/anyOf    [:qdrant/WithVector :jsonschema/NullSchema],
     :jsonschema/default  "null",
     :jsonschema/propertyName "with_vector",
     :rdf/type            :jsonschema/ObjectSchema}],
   :jsonschema/required ["limit" "positive"],
   :rdf/type :jsonschema/ObjectSchema})

(def RecommendRequestBatch
  {:db/ident :qdrant/RecommendRequestBatch,
   :jsonschema/properties [{:dcterms/description
                            "List of recommendation requests",
                            :jsonschema/items [:qdrant/RecommendRequest],
                            :jsonschema/propertyName "searches",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["searches"],
   :rdf/type :jsonschema/ObjectSchema})

(def Record
  {:db/ident :qdrant/Record,
   :jsonschema/properties
   [{:dcterms/description "Point id",
     :jsonschema/format   "uint64",
     :jsonschema/minimum  0,
     :jsonschema/propertyName "id",
     :rdf/type            :jsonschema/IntegerSchema}
    {:dcterms/description "Payload - values assigned to the point",
     :jsonschema/anyOf [:qdrant/Payload :jsonschema/NullSchema],
     :jsonschema/propertyName "payload",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description "Vector of the point",
     :jsonschema/anyOf [:qdrant/VectorStruct :jsonschema/NullSchema],
     :jsonschema/propertyName "vector",
     :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required ["id"],
   :rdf/type :jsonschema/ObjectSchema})

(def RemoteShardInfo
  {:db/ident :qdrant/RemoteShardInfo,
   :jsonschema/properties [{:dcterms/description "Remote shard id",
                            :jsonschema/format   "uint32",
                            :jsonschema/minimum  0,
                            :jsonschema/propertyName "shard_id",
                            :rdf/type            :jsonschema/IntegerSchema}
                           {:dcterms/description "Remote peer id",
                            :jsonschema/format   "uint64",
                            :jsonschema/minimum  0,
                            :jsonschema/propertyName "peer_id",
                            :rdf/type            :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "state",
                            :rdf/type :qdrant/ReplicaState}],
   :jsonschema/required ["peer_id" "shard_id" "state"],
   :rdf/type :jsonschema/ObjectSchema})

(def RemoteShardTelemetry
  {:db/ident :qdrant/RemoteShardTelemetry,
   :jsonschema/properties [{:jsonschema/format "uint32",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "peer_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "searches",
                            :rdf/type :qdrant/OperationDurationStatistics}
                           {:jsonschema/propertyName "updates",
                            :rdf/type :qdrant/OperationDurationStatistics}],
   :jsonschema/required ["searches" "shard_id" "updates"],
   :rdf/type :jsonschema/ObjectSchema})

(def RenameAlias
  "Change alias to a new one"
  {:db/ident :qdrant/RenameAlias,
   :dcterms/description "Change alias to a new one",
   :jsonschema/properties [{:jsonschema/propertyName "old_alias_name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "new_alias_name",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["new_alias_name" "old_alias_name"],
   :rdf/type :jsonschema/ObjectSchema})

(def RenameAliasOperation
  "Change alias to a new one"
  {:db/ident :qdrant/RenameAliasOperation,
   :dcterms/description "Change alias to a new one",
   :jsonschema/properties [{:jsonschema/propertyName "rename_alias",
                            :rdf/type :qdrant/RenameAlias}],
   :jsonschema/required ["rename_alias"],
   :rdf/type :jsonschema/ObjectSchema})

(def Replica
  {:db/ident :qdrant/Replica,
   :jsonschema/properties [{:jsonschema/format "uint32",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "peer_id",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["peer_id" "shard_id"],
   :rdf/type :jsonschema/ObjectSchema})

(def ReplicaSetTelemetry
  {:db/ident :qdrant/ReplicaSetTelemetry,
   :jsonschema/properties [{:jsonschema/format "uint32",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/anyOf [:qdrant/LocalShardTelemetry
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName "local",
                            :rdf/type         :jsonschema/ObjectSchema}
                           {:jsonschema/items :qdrant/RemoteShardTelemetry,
                            :jsonschema/propertyName "remote",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName "replicate_states",
                            :rdf/type :qdrant/ReplicaState}],
   :jsonschema/required ["id" "remote" "replicate_states"],
   :rdf/type :jsonschema/ObjectSchema})

(def ReplicaState
  "State of the single shard within a replica set."
  {:db/ident        :qdrant/ReplicaState,
   :dcterms/description "State of the single shard within a replica set.",
   :jsonschema/enum ["Active" "Dead" "Partial" "Initializing" "Listener"],
   :rdf/type        :jsonschema/StringSchema})

(def ReplicateShardOperation
  {:db/ident :qdrant/ReplicateShardOperation,
   :jsonschema/properties [{:jsonschema/propertyName "replicate_shard",
                            :rdf/type :qdrant/MoveShard}],
   :jsonschema/required ["replicate_shard"],
   :rdf/type :jsonschema/ObjectSchema})

(def RequestsTelemetry
  {:db/ident :qdrant/RequestsTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "rest",
                            :rdf/type :qdrant/WebApiTelemetry}
                           {:jsonschema/propertyName "grpc",
                            :rdf/type :qdrant/GrpcTelemetry}],
   :jsonschema/required ["grpc" "rest"],
   :rdf/type :jsonschema/ObjectSchema})

(def RunningEnvironmentTelemetry
  {:db/ident :qdrant/RunningEnvironmentTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "distribution",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "distribution_version",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "is_docker",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "cores",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "ram_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "disk_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "cpu_flags",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["cpu_flags" "is_docker"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScalarQuantization
  {:db/ident :qdrant/ScalarQuantization,
   :jsonschema/properties [{:jsonschema/propertyName "scalar",
                            :rdf/type :qdrant/ScalarQuantizationConfig}],
   :jsonschema/required ["scalar"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScalarQuantizationConfig
  {:db/ident :qdrant/ScalarQuantizationConfig,
   :jsonschema/properties [{:jsonschema/propertyName "type",
                            :rdf/type :qdrant/ScalarType}
                           {:jsonschema/format  "float",
                            :jsonschema/maximum 1,
                            :jsonschema/minimum 0.5M,
                            :jsonschema/propertyName "quantile",
                            :rdf/type           :jsonschema/NumberSchema}
                           {:jsonschema/propertyName "always_ram",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required ["type"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScalarType
  {:db/ident        :qdrant/ScalarType,
   :jsonschema/enum ["int8"],
   :rdf/type        :jsonschema/StringSchema})

(def ScoredPoint
  {:db/ident :qdrant/ScoredPoint,
   :jsonschema/properties [{:jsonschema/propertyName "id",
                            :rdf/type :qdrant/ExtendedPointId}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "version",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "float",
                            :jsonschema/propertyName "score",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/propertyName "payload",
                            :rdf/type :qdrant/Payload}
                           {:jsonschema/propertyName "vector",
                            :rdf/type :qdrant/VectorStruct}],
   :jsonschema/required ["id" "score" "version"],
   :rdf/type :jsonschema/ObjectSchema})

(def ScrollRequest
  {:db/ident :qdrant/ScrollRequest,
   :jsonschema/properties [{:jsonschema/propertyName "offset",
                            :rdf/type :qdrant/ExtendedPointId}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 1,
                            :jsonschema/propertyName "limit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/propertyName "with_payload",
                            :rdf/type :qdrant/WithPayloadInterface}
                           {:jsonschema/propertyName "with_vector",
                            :rdf/type :qdrant/WithVector}],
   :rdf/type :jsonschema/ObjectSchema})

(def ScrollResult
  {:db/ident :qdrant/ScrollResult,
   :jsonschema/properties [{:jsonschema/items :qdrant/Record,
                            :jsonschema/propertyName "points",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/propertyName "next_page_offset",
                            :rdf/type :qdrant/ExtendedPointId}],
   :jsonschema/required ["points"],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchGroupsRequest
  {:db/ident :qdrant/SearchGroupsRequest,
   :jsonschema/properties [{:jsonschema/propertyName "params",
                            :rdf/type :qdrant/SearchParams}
                           {:jsonschema/format "uint32",
                            :jsonschema/minimum 1,
                            :jsonschema/propertyName "limit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "vector",
                            :rdf/type :qdrant/NamedVectorStruct}
                           {:jsonschema/propertyName "with_payload",
                            :rdf/type :qdrant/WithPayloadInterface}
                           {:jsonschema/propertyName "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/format "uint32",
                            :jsonschema/minimum 1,
                            :jsonschema/propertyName "group_size",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "float",
                            :jsonschema/propertyName "score_threshold",
                            :rdf/type :jsonschema/NumberSchema}
                           {:jsonschema/propertyName "with_vector",
                            :rdf/type :qdrant/WithVector}
                           {:jsonschema/minLength 1,
                            :jsonschema/propertyName "group_by",
                            :rdf/type :jsonschema/StringSchema}],
   :jsonschema/required ["group_by" "group_size" "limit" "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchParams
  {:db/ident :qdrant/SearchParams,
   :jsonschema/properties [{:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "hnsw_ef",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "exact",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "quantization",
                            :rdf/type :qdrant/QuantizationSearchParams}],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchRequest
  {:db/ident :qdrant/SearchRequest,
   :jsonschema/properties [{:jsonschema/propertyName "vector",
                            :rdf/type :qdrant/NamedVectorStruct}
                           {:jsonschema/propertyName "filter",
                            :rdf/type :qdrant/Filter}
                           {:jsonschema/propertyName "params",
                            :rdf/type :qdrant/SearchParams}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "limit",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "offset",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "with_payload",
                            :rdf/type :qdrant/WithPayloadInterface}
                           {:jsonschema/propertyName "with_vector",
                            :rdf/type :qdrant/WithVector}
                           {:jsonschema/format "float",
                            :jsonschema/propertyName "score_threshold",
                            :rdf/type :jsonschema/NumberSchema}],
   :jsonschema/required ["limit" "vector"],
   :rdf/type :jsonschema/ObjectSchema})

(def SearchRequestBatch
  {:db/ident :qdrant/SearchRequestBatch,
   :jsonschema/properties [{:jsonschema/items :qdrant/SearchRequest,
                            :jsonschema/propertyName "searches",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required ["searches"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentConfig
  {:db/ident :qdrant/SegmentConfig,
   :jsonschema/properties [{:jsonschema/propertyName "vector_data",
                            :rdf/type :qdrant/VectorDataConfig}
                           {:jsonschema/propertyName "payload_storage_type",
                            :rdf/type :qdrant/PayloadStorageType}],
   :jsonschema/required ["payload_storage_type" "vector_data"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentInfo
  {:db/ident :qdrant/SegmentInfo,
   :jsonschema/properties [{:jsonschema/propertyName "segment_type",
                            :rdf/type :qdrant/SegmentType}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "num_vectors",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "num_points",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "num_deleted_vectors",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "ram_usage_bytes",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "disk_usage_bytes",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "is_appendable",
                            :rdf/type :jsonschema/BooleanSchema}
                           {:jsonschema/propertyName "index_schema",
                            :rdf/type :qdrant/PayloadIndexInfo}],
   :jsonschema/required ["disk_usage_bytes"
                         "index_schema"
                         "is_appendable"
                         "num_deleted_vectors"
                         "num_points"
                         "num_vectors"
                         "ram_usage_bytes"
                         "segment_type"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentTelemetry
  {:db/ident :qdrant/SegmentTelemetry,
   :jsonschema/properties [{:jsonschema/propertyName "info",
                            :rdf/type :qdrant/SegmentInfo}
                           {:jsonschema/propertyName "config",
                            :rdf/type :qdrant/SegmentConfig}
                           {:jsonschema/items
                            :qdrant/VectorIndexSearchesTelemetry,
                            :jsonschema/propertyName "vector_index_searches",
                            :rdf/type :jsonschema/ArraySchema}
                           {:jsonschema/items :qdrant/PayloadIndexTelemetry,
                            :jsonschema/propertyName "payload_field_indices",
                            :rdf/type         :jsonschema/ArraySchema}],
   :jsonschema/required
   ["config" "info" "payload_field_indices" "vector_index_searches"],
   :rdf/type :jsonschema/ObjectSchema})

(def SegmentType
  {:db/ident        :qdrant/SegmentType,
   :jsonschema/enum ["plain" "indexed" "special"],
   :rdf/type        :jsonschema/StringSchema})

(def SetPayload
  {:db/ident :qdrant/SetPayload,
   :jsonschema/properties [{:jsonschema/propertyName "payload",
                            :rdf/type :qdrant/Payload}
                           {:jsonschema/items :qdrant/ExtendedPointId,
                            :jsonschema/propertyName "points",
                            :rdf/type         :jsonschema/ArraySchema}
                           {:jsonschema/anyOf [:qdrant/Filter
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName "filter",
                            :rdf/type         :jsonschema/ObjectSchema}],
   :jsonschema/required ["payload"],
   :rdf/type :jsonschema/ObjectSchema})

(def ShardTransferInfo
  {:db/ident :qdrant/ShardTransferInfo,
   :jsonschema/properties [{:jsonschema/format "uint32",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "shard_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "from",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "to",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "sync",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required ["from" "shard_id" "sync" "to"],
   :rdf/type :jsonschema/ObjectSchema})

(def SnapshotDescription
  {:db/ident :qdrant/SnapshotDescription,
   :jsonschema/properties [{:jsonschema/propertyName "name",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format "partial-date-time",
                            :jsonschema/propertyName "creation_time",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "size",
                            :rdf/type :jsonschema/IntegerSchema}],
   :jsonschema/required ["name" "size"],
   :rdf/type :jsonschema/ObjectSchema})

(def SnapshotPriority
  {:db/ident        :qdrant/SnapshotPriority,
   :jsonschema/enum ["snapshot" "replica"],
   :rdf/type        :jsonschema/StringSchema})

(def SnapshotRecover
  {:db/ident :qdrant/SnapshotRecover,
   :jsonschema/properties [{:jsonschema/format "uri",
                            :jsonschema/propertyName "location",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/anyOf [:qdrant/SnapshotPriority
                                               :jsonschema/NullSchema],
                            :jsonschema/propertyName "priority",
                            :rdf/type         :jsonschema/ObjectSchema}],
   :jsonschema/required ["location"],
   :rdf/type :jsonschema/ObjectSchema})

(def StateRole
  {:db/ident        :qdrant/StateRole,
   :jsonschema/enum ["Follower" "Candidate" "Leader" "PreCandidate"],
   :rdf/type        :jsonschema/StringSchema})

(def SuccessfulResponse
  "Successful operation"
  {:db/ident :qdrant/SuccessfulResponse,
   :dcterms/description "Successful operation",
   :jsonschema/properties [{:jsonschema/propertyName "result",
                            :rdf/type :jsonschema/ObjectSchema}
                           {:jsonschema/enum ["ok"],
                            :jsonschema/propertyName "status",
                            :rdf/type        :jsonschema/StringSchema}
                           {:dcterms/description
                            "Time spent to process this request",
                            :jsonschema/format "float",
                            :jsonschema/propertyName "time",
                            :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def TelemetryData
  {:db/ident :qdrant/TelemetryData,
   :jsonschema/properties [{:jsonschema/propertyName "id",
                            :rdf/type :jsonschema/StringSchema}
                           {:jsonschema/propertyName "app",
                            :rdf/type :qdrant/AppBuildTelemetry}
                           {:jsonschema/propertyName "collections",
                            :rdf/type :qdrant/CollectionsTelemetry}
                           {:jsonschema/propertyName "cluster",
                            :rdf/type :qdrant/ClusterTelemetry}
                           {:jsonschema/propertyName "requests",
                            :rdf/type :qdrant/RequestsTelemetry}],
   :jsonschema/required ["app" "cluster" "collections" "id" "requests"],
   :rdf/type :jsonschema/ObjectSchema})

(def TextIndexParams
  {:db/ident :qdrant/TextIndexParams,
   :jsonschema/properties [{:jsonschema/propertyName "type",
                            :rdf/type :qdrant/TextIndexType}
                           {:jsonschema/propertyName "tokenizer",
                            :rdf/type :qdrant/TokenizerType}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "min_token_len",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "max_token_len",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:dcterms/description
                            "If true, lowercase all tokens. Default: true",
                            :jsonschema/propertyName "lowercase",
                            :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required ["type"],
   :rdf/type :jsonschema/ObjectSchema})

(def TextIndexType
  {:db/ident        :qdrant/TextIndexType,
   :jsonschema/enum ["text"],
   :rdf/type        :jsonschema/StringSchema})

(def TokenizerType
  {:db/ident        :qdrant/TokenizerType,
   :jsonschema/enum ["prefix" "whitespace" "word"],
   :rdf/type        :jsonschema/StringSchema})

(def UpdateCollection
  "Operation for updating parameters of the existing collection"
  {:db/ident :qdrant/UpdateCollection,
   :dcterms/description
   "Operation for updating parameters of the existing collection",
   :jsonschema/properties
   [{:dcterms/description
     "Custom params for Optimizers.  If none - values from service configuration file are used. This operation is blocking, it will only proceed ones all current optimizations are complete",
     :jsonschema/anyOf [:qdrant/OptimizersConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName "optimizers_config",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Collection base params.  If none - values from service configuration file are used.",
     :jsonschema/anyOf [:qdrant/CollectionParamsDiff :jsonschema/NullSchema],
     :jsonschema/propertyName "params",
     :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def UpdateResult
  {:db/ident :qdrant/UpdateResult,
   :jsonschema/properties [{:jsonschema/format "uint64",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "operation_id",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/propertyName "status",
                            :rdf/type :qdrant/UpdateStatus}],
   :jsonschema/required ["operation_id" "status"],
   :rdf/type :jsonschema/ObjectSchema})

(def UpdateStatus
  "`Acknowledged` - Request is saved to WAL and will be process in a queue. `Completed` - Request is completed, changes are actual."
  {:db/ident        :qdrant/UpdateStatus,
   :dcterms/description
   "`Acknowledged` - Request is saved to WAL and will be process in a queue. `Completed` - Request is completed, changes are actual.",
   :jsonschema/enum ["acknowledged" "completed"],
   :rdf/type        :jsonschema/StringSchema})

(def UpdateVectors
  {:db/ident :qdrant/UpdateVectors,
   :jsonschema/properties [{:dcterms/description "Points with named vectors",
                            :jsonschema/items :qdrant/PointVectors,
                            :jsonschema/minItems 1,
                            :jsonschema/propertyName "points",
                            :rdf/type :jsonschema/ArraySchema}],
   :jsonschema/required ["points"],
   :rdf/type :jsonschema/ObjectSchema})

(def UsingVector
  {:db/ident         :qdrant/UsingVector,
   :jsonschema/anyOf [:jsonschema/StringSchema],
   :rdf/type         :jsonschema/DataSchema})

(def ValueVariants
  {:db/ident         :qdrant/ValueVariants,
   :jsonschema/anyOf [:jsonschema/StringSchema
                      {:jsonschema/format "int64",
                       :rdf/type :jsonschema/IntegerSchema}
                      :jsonschema/BooleanSchema],
   :rdf/type         :jsonschema/DataSchema})

(def ValuesCount
  {:db/ident :qdrant/ValuesCount,
   :jsonschema/properties [{:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "lt",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "gt",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "gte",
                            :rdf/type :jsonschema/IntegerSchema}
                           {:jsonschema/format "uint",
                            :jsonschema/minimum 0,
                            :jsonschema/propertyName "lte",
                            :rdf/type :jsonschema/IntegerSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorDataConfig
  "Config of single vector data storage"
  {:db/ident :qdrant/VectorDataConfig,
   :dcterms/description "Config of single vector data storage",
   :jsonschema/properties
   [{:dcterms/description "Size/dimensionality of the vectors used",
     :jsonschema/format   "uint",
     :jsonschema/minimum  0,
     :jsonschema/propertyName "size",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/propertyName "distance",
     :rdf/type :qdrant/Distance}
    {:jsonschema/propertyName "storage_type",
     :rdf/type :qdrant/VectorStorageType}
    {:jsonschema/propertyName "index",
     :rdf/type :qdrant/Indexes}
    {:dcterms/description
     "Vector specific quantization config that overrides collection config",
     :jsonschema/anyOf [:qdrant/QuantizationConfig :jsonschema/NullSchema],
     :jsonschema/propertyName "quantization_config",
     :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required ["distance" "index" "size" "storage_type"],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorIndexSearchesTelemetry
  {:db/ident :qdrant/VectorIndexSearchesTelemetry,
   :jsonschema/properties
   [{:jsonschema/propertyName "index_name",
     :rdf/type :jsonschema/StringSchema}
    {:jsonschema/propertyName "unfiltered_plain",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName "unfiltered_hnsw",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName "filtered_plain",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName "filtered_small_cardinality",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName "filtered_large_cardinality",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName "filtered_exact",
     :rdf/type :qdrant/OperationDurationStatistics}
    {:jsonschema/propertyName "unfiltered_exact",
     :rdf/type :qdrant/OperationDurationStatistics}],
   :jsonschema/required ["filtered_exact"
                         "filtered_large_cardinality"
                         "filtered_plain"
                         "filtered_small_cardinality"
                         "unfiltered_exact"
                         "unfiltered_hnsw"
                         "unfiltered_plain"],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorParams
  "Params of single vector data storage"
  {:db/ident :qdrant/VectorParams,
   :dcterms/description "Params of single vector data storage",
   :jsonschema/properties
   [{:dcterms/description "Size of a vectors used",
     :jsonschema/format   "uint64",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "size",
     :rdf/type            :jsonschema/IntegerSchema}
    {:jsonschema/propertyName "distance",
     :rdf/type :qdrant/Distance}
    {:dcterms/description
     "Custom params for HNSW index. If none - values from collection configuration are used.",
     :jsonschema/anyOf [:qdrant/HnswConfigDiff :jsonschema/NullSchema],
     :jsonschema/propertyName "hnsw_config",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "Custom params for quantization. If none - values from collection configuration are used.",
     :jsonschema/anyOf [:qdrant/QuantizationConfig :jsonschema/NullSchema],
     :jsonschema/propertyName "quantization_config",
     :rdf/type :jsonschema/ObjectSchema}
    {:dcterms/description
     "If true, vectors are served from disk, improving RAM usage at the cost of latency Default: false",
     :jsonschema/propertyName "on_disk",
     :rdf/type :jsonschema/BooleanSchema}],
   :jsonschema/required ["distance" "size"],
   :rdf/type :jsonschema/ObjectSchema})

(def VectorStorageType
  "Storage types for vectors"
  {:db/ident :qdrant/VectorStorageType,
   :dcterms/description "Storage types for vectors",
   :jsonschema/oneOf
   [{:dcterms/description
     "Storage in memory (RAM). Will be very fast at the cost of consuming a lot of memory.",
     :jsonschema/enum "Memory",
     :rdf/type        :jsonschema/StringSchema}
    {:dcterms/description
     "Storage in mmap file, not appendable. Search performance is defined by disk speed and the fraction of vectors that fit in memory.",
     :jsonschema/enum "Mmap",
     :rdf/type        :jsonschema/StringSchema}
    {:dcterms/description
     "Storage in chunked mmap files, appendable. Search performance is defined by disk speed and the fraction of vectors that fit in memory.",
     :jsonschema/enum "ChunkedMmap",
     :rdf/type        :jsonschema/StringSchema}],
   :rdf/type :jsonschema/StringSchema})

(def VectorStruct
  "Full vector data per point separator with single and multiple vector modes"
  {:db/ident         :qdrant/VectorStruct,
   :dcterms/description
   "Full vector data per point separator with single and multiple vector modes",
   :jsonschema/anyOf [{:jsonschema/items [{:jsonschema/format "float",
                                           :rdf/type :jsonschema/NumberSchema}],
                       :rdf/type         :jsonschema/ArraySchema}
                      {:jsonschema/properties [{:jsonschema/items
                                                [{:jsonschema/format "float",
                                                  :rdf/type
                                                  :jsonschema/NumberSchema}],
                                                :rdf/type
                                                :jsonschema/ArraySchema}],
                       :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def VectorsConfig
  "Vector params separator for single and multiple vector modes Single mode: { \"size\": 128, \"distance\": \"Cosine\" } or multiple mode: { \"default\": { \"size\": 128, \"distance\": \"Cosine\" } }"
  {:db/ident         :qdrant/VectorsConfig,
   :dcterms/description
   "Vector params separator for single and multiple vector modes Single mode:\n\n{ \"size\": 128, \"distance\": \"Cosine\" }\n\nor multiple mode:\n\n{ \"default\": { \"size\": 128, \"distance\": \"Cosine\" } }",
   :jsonschema/anyOf [{:rdf/type :qdrant/VectorParams}
                      {:jsonschema/properties [{:rdf/type
                                                :qdrant/VectorParams}],
                       :rdf/type :jsonschema/ObjectSchema}],
   :rdf/type         :jsonschema/DataSchema})

(def WalConfig
  {:db/ident :qdrant/WalConfig,
   :jsonschema/properties
   [{:dcterms/description "Size of a single WAL segment in MB",
     :jsonschema/format   "uint",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "wal_capacity_mb",
     :rdf/type            :jsonschema/NumberSchema}
    {:dcterms/description
     "Number of WAL segments to create ahead of actually used ones",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "wal_segments_ahead",
     :rdf/type :jsonschema/NumberSchema}],
   :jsonschema/required ["wal_capacity_mb" "wal_segments_ahead"],
   :rdf/type :jsonschema/ObjectSchema})

(def WalConfigDiff
  {:db/ident :qdrant/WalConfigDiff,
   :jsonschema/properties
   [{:dcterms/description "Size of a single WAL segment in MB",
     :jsonschema/format   "uint",
     :jsonschema/minimum  1,
     :jsonschema/propertyName "wal_capacity_mb",
     :rdf/type            :jsonschema/NumberSchema}
    {:dcterms/description
     "Number of WAL segments to create ahead of actually used ones",
     :jsonschema/format "uint",
     :jsonschema/minimum 0,
     :jsonschema/propertyName "wal_segments_ahead",
     :rdf/type :jsonschema/NumberSchema}],
   :rdf/type :jsonschema/ObjectSchema})

(def WebApiTelemetry
  {:db/ident :qdrant/WebApiTelemetry,
   :jsonschema/properties [{:jsonschema/properties
                            {:rdf/type :qdrant/OperationDurationStatistics},
                            :jsonschema/propertyName "responses",
                            :rdf/type :jsonschema/ObjectSchema}],
   :jsonschema/required ["responses"],
   :rdf/type :jsonschema/ObjectSchema})

(def WithPayloadInterface
  "Options for specifying which payload to include or not"
  {:db/ident :qdrant/WithPayloadInterface,
   :dcterms/description
   "Options for specifying which payload to include or not",
   :jsonschema/anyOf
   [{:dcterms/description
     "If `true` - return all payload, If `false` - do not return payload",
     :jsonschema/propertyName "include_payload",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description "Specify which fields to return",
     :jsonschema/items [{:rdf/type :jsonschema/StringSchema}],
     :jsonschema/propertyName "fields",
     :rdf/type :jsonschema/ArraySchema}
    :qdrant/PayloadSelector],
   :rdf/type :jsonschema/DataSchema})

(def WithVector
  "Options for specifying which vector to include"
  {:db/ident :qdrant/WithVector,
   :dcterms/description "Options for specifying which vector to include",
   :jsonschema/anyOf
   [{:dcterms/description
     "If `true` - return all vector, If `false` - do not return vector",
     :jsonschema/propertyName "include_vector",
     :rdf/type :jsonschema/BooleanSchema}
    {:dcterms/description "Specify which vector to return",
     :jsonschema/items [{:rdf/type :jsonschema/StringSchema}],
     :jsonschema/propertyName "fields",
     :rdf/type :jsonschema/ArraySchema}],
   :rdf/type :jsonschema/DataSchema})

(def WriteOrdering
  "Defines write ordering guarantees for collection operations * `weak` - write operations may be reordered, works faster, default * `medium` - write operations go through dynamically selected leader, may be inconsistent for a short period of time in case of leader change * `strong` - Write operations go through the permanent leader, consistent, but may be unavailable if leader is down"
  {:db/ident        :qdrant/WriteOrdering,
   :dcterms/description
   "Defines write ordering guarantees for collection operations\n\n* `weak` - write operations may be reordered, works faster, default\n\n* `medium` - write operations go through dynamically selected leader, may be inconsistent for a short period of time in case of leader change\n\n* `strong` - Write operations go through the permanent leader, consistent, but may be unavailable if leader is down",
   :jsonschema/enum ["weak" "medium" "strong"],
   :rdf/type        :jsonschema/StringSchema})