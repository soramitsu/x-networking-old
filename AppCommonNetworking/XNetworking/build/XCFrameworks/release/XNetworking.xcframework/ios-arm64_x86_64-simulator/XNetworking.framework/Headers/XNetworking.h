#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class XNetworkingDatabaseDriverFactory, XNetworkingHistoryMapper, XNetworkingTxHistoryItemNested, XNetworkingExtrinsics, XNetworkingExtrinsicParam, XNetworkingTxHistoryItem, XNetworkingTxHistoryInfo, XNetworkingTxHistoryItemParam, XNetworkingTxHistoryResult<R>, XNetworkingSubQueryClient<T, R>, XNetworkingSoramitsuNetworkClient, XNetworkingSubQueryClientForFearless, XNetworkingFearlessSubQueryResponse, XNetworkingHistoryDatabaseProvider, XNetworkingSubQueryClientForFearlessWallet, XNetworkingSubQueryClientForSoraWallet, XNetworkingSubQueryRequestCompanion, XNetworkingSubQueryRequest, XNetworkingFearlessExtrinsicItemCompanion, XNetworkingFearlessExtrinsicItem, XNetworkingFearlessHistoryResponseItem, XNetworkingFearlessHistoryResponsePageInfo, XNetworkingFearlessHistoryResponseDataElementsCompanion, XNetworkingFearlessHistoryResponseDataElements, XNetworkingFearlessRewardItem, XNetworkingFearlessTransferItem, XNetworkingFearlessHistoryResponseItemCompanion, XNetworkingFearlessHistoryResponsePageInfoCompanion, XNetworkingFearlessRewardItemCompanion, XNetworkingFearlessSubQueryResponseData, XNetworkingFearlessSubQueryResponseCompanion, XNetworkingFearlessSubQueryResponseDataCompanion, XNetworkingFearlessTransferItemCompanion, XNetworkingErrorCompanion, XNetworkingError, XNetworkingExecutionResultCompanion, XNetworkingExecutionResult, XNetworkingHistoryResponseItem, XNetworkingHistoryResponsePageInfo, XNetworkingHistoryResponseDataElementsCompanion, XNetworkingHistoryResponseDataElements, XNetworkingKotlinx_serialization_jsonJsonElement, XNetworkingHistoryResponseItemCompanion, XNetworkingHistoryResponsePageInfoCompanion, XNetworkingSoraSubQueryResponseData, XNetworkingSoraSubQueryResponseCompanion, XNetworkingSoraSubQueryResponse, XNetworkingSoraSubQueryResponseDataCompanion, XNetworkingSignerInfo, XNetworkingSoraHistoryDatabaseCompanion, XNetworkingRuntimeQuery<__covariant RowType>, XNetworkingKotlinThrowable, XNetworkingKotlinArray<T>, XNetworkingChainModelCompanion, XNetworkingChainModel, XNetworkingChainResponseCompanion, XNetworkingChainResponse, XNetworkingKotlinPair<__covariant A, __covariant B>, XNetworkingResultChainInfo, XNetworkingChainBuilderException, XNetworkingSoramitsuNetworkException, XNetworkingKtor_client_coreHttpClient, XNetworkingKotlinx_serialization_jsonJson, XNetworkingKtor_httpHttpMethod, XNetworkingKtor_httpContentType, XNetworkingNodeInfoCompanion, XNetworkingNodeInfo, XNetworkingSoraEnvCompanion, XNetworkingSoraEnv, XNetworkingReferrerRewardsInfo, XNetworkingSbApyInfo, XNetworkingReferrerReward, XNetworkingSoraWalletReferrerCase0ResponseData, XNetworkingSoraWalletReferrerCase0ResponseCompanion, XNetworkingSoraWalletReferrerCase0Response, XNetworkingSoraWalletReferrerCase0ResponseDataRewards, XNetworkingSoraWalletReferrerCase0ResponseDataCompanion, XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup, XNetworkingSoraWalletReferrerCase0ResponseDataRewardsCompanion, XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum, XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupCompanion, XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSumCompanion, XNetworkingSoraWalletReferrerCase1ResponseData, XNetworkingSoraWalletReferrerCase1ResponseCompanion, XNetworkingSoraWalletReferrerCase1Response, XNetworkingSoraWalletReferrerCase1ResponseDataRewards, XNetworkingSoraWalletReferrerCase1ResponseDataCompanion, XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo, XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode, XNetworkingSoraWalletReferrerCase1ResponseDataRewardsCompanion, XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNodeCompanion, XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfoCompanion, XNetworkingSoraWalletSbApyCase0ResponseData, XNetworkingSoraWalletSbApyCase0ResponseCompanion, XNetworkingSoraWalletSbApyCase0Response, XNetworkingSoraWalletSbApyCase0ResponseDataEntities, XNetworkingSoraWalletSbApyCase0ResponseDataCompanion, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesCompanion, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodeCompanion, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsCompanion, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeCompanion, XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNodeCompanion, XNetworkingSoraWalletSbApyCase1ResponseData, XNetworkingSoraWalletSbApyCase1ResponseCompanion, XNetworkingSoraWalletSbApyCase1Response, XNetworkingSoraWalletSbApyCase1ResponseDataEntities, XNetworkingSoraWalletSbApyCase1ResponseDataCompanion, XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode, XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesCompanion, XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNodeCompanion, XNetworkingKotlinByteArray, XNetworkingKotlinException, XNetworkingKotlinRuntimeException, XNetworkingKotlinIllegalStateException, XNetworkingKotlinx_serialization_jsonJsonElementCompanion, XNetworkingRuntimeTransacterTransaction, XNetworkingKtor_client_coreHttpClientEngineConfig, XNetworkingKtor_client_coreHttpClientConfig<T>, XNetworkingKtor_eventsEvents, XNetworkingKtor_client_coreHttpReceivePipeline, XNetworkingKtor_client_coreHttpRequestPipeline, XNetworkingKtor_client_coreHttpResponsePipeline, XNetworkingKtor_client_coreHttpSendPipeline, XNetworkingKotlinx_serialization_coreSerializersModule, XNetworkingKotlinx_serialization_jsonJsonDefault, XNetworkingKotlinx_serialization_jsonJsonConfiguration, XNetworkingKtor_httpHttpMethodCompanion, XNetworkingKtor_httpHeaderValueParam, XNetworkingKtor_httpHeaderValueWithParametersCompanion, XNetworkingKtor_httpHeaderValueWithParameters, XNetworkingKtor_httpContentTypeCompanion, XNetworkingKotlinByteIterator, XNetworkingKotlinNothing, XNetworkingKotlinx_serialization_coreSerialKind, XNetworkingKtor_client_coreHttpRequestData, XNetworkingKtor_client_coreHttpResponseData, XNetworkingKotlinx_coroutines_coreCoroutineDispatcher, XNetworkingKtor_client_coreProxyConfig, XNetworkingKtor_utilsAttributeKey<T>, XNetworkingKtor_eventsEventDefinition<T>, XNetworkingKtor_utilsPipelinePhase, XNetworkingKtor_utilsPipeline<TSubject, TContext>, XNetworkingKtor_client_coreHttpReceivePipelinePhases, XNetworkingKtor_client_coreHttpResponse, XNetworkingKotlinUnit, XNetworkingKtor_client_coreHttpRequestPipelinePhases, XNetworkingKtor_client_coreHttpRequestBuilder, XNetworkingKtor_client_coreHttpResponsePipelinePhases, XNetworkingKtor_client_coreHttpResponseContainer, XNetworkingKtor_client_coreHttpClientCall, XNetworkingKtor_client_coreHttpSendPipelinePhases, XNetworkingKtor_httpUrl, XNetworkingKtor_httpOutgoingContent, XNetworkingKtor_httpHttpStatusCode, XNetworkingKtor_utilsGMTDate, XNetworkingKtor_httpHttpProtocolVersion, XNetworkingKotlinAbstractCoroutineContextElement, XNetworkingKotlinx_coroutines_coreCoroutineDispatcherKey, XNetworkingKtor_httpHeadersBuilder, XNetworkingKtor_client_coreHttpRequestBuilderCompanion, XNetworkingKtor_httpURLBuilder, XNetworkingKtor_utilsTypeInfo, XNetworkingKtor_client_coreHttpClientCallCompanion, XNetworkingKtor_httpUrlCompanion, XNetworkingKtor_httpURLProtocol, XNetworkingKotlinCancellationException, XNetworkingKtor_httpHttpStatusCodeCompanion, XNetworkingKtor_utilsGMTDateCompanion, XNetworkingKtor_utilsWeekDay, XNetworkingKtor_utilsMonth, XNetworkingKtor_httpHttpProtocolVersionCompanion, XNetworkingKotlinAbstractCoroutineContextKey<B, E>, XNetworkingKtor_ioMemory, XNetworkingKtor_ioChunkBuffer, XNetworkingKtor_ioBuffer, XNetworkingKtor_ioByteReadPacket, XNetworkingKtor_utilsStringValuesBuilderImpl, XNetworkingKtor_httpURLBuilderCompanion, XNetworkingKtor_httpURLProtocolCompanion, XNetworkingKotlinEnumCompanion, XNetworkingKotlinEnum<E>, XNetworkingKtor_utilsWeekDayCompanion, XNetworkingKtor_utilsMonthCompanion, XNetworkingKtor_ioMemoryCompanion, XNetworkingKtor_ioBufferCompanion, XNetworkingKtor_ioChunkBufferCompanion, XNetworkingKtor_ioInputCompanion, XNetworkingKtor_ioInput, XNetworkingKtor_ioByteReadPacketCompanion, XNetworkingKotlinKTypeProjection, XNetworkingKotlinx_coroutines_coreAtomicDesc, XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp, XNetworkingKotlinKVariance, XNetworkingKotlinKTypeProjectionCompanion, XNetworkingKotlinx_coroutines_coreAtomicOp<__contravariant T>, XNetworkingKotlinx_coroutines_coreOpDescriptor, XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode, XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc, XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<T>, XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<T>;

@protocol XNetworkingKotlinx_serialization_coreDeserializationStrategy, XNetworkingKotlinx_serialization_coreKSerializer, XNetworkingSoraHistoryDatabaseQueries, XNetworkingRuntimeTransactionWithoutReturn, XNetworkingRuntimeTransactionWithReturn, XNetworkingRuntimeTransacter, XNetworkingSoraHistoryDatabase, XNetworkingRuntimeSqlDriver, XNetworkingRuntimeSqlDriverSchema, XNetworkingKtor_client_coreHttpClientEngineFactory, XNetworkingSoramitsuHttpClientProvider, XNetworkingKotlinx_coroutines_coreFlow, XNetworkingKtor_httpHeaders, XNetworkingKotlinx_serialization_coreDecoder, XNetworkingKotlinx_serialization_coreSerialDescriptor, XNetworkingKotlinx_serialization_coreEncoder, XNetworkingKotlinx_serialization_coreSerializationStrategy, XNetworkingRuntimeTransactionCallbacks, XNetworkingRuntimeSqlPreparedStatement, XNetworkingRuntimeSqlCursor, XNetworkingRuntimeCloseable, XNetworkingRuntimeQueryListener, XNetworkingKotlinIterator, XNetworkingKtor_client_coreHttpClientEngine, XNetworkingKotlinCoroutineContext, XNetworkingKotlinx_coroutines_coreCoroutineScope, XNetworkingKtor_ioCloseable, XNetworkingKtor_client_coreHttpClientEngineCapability, XNetworkingKtor_utilsAttributes, XNetworkingKotlinx_serialization_coreSerialFormat, XNetworkingKotlinx_serialization_coreStringFormat, XNetworkingKotlinx_coroutines_coreFlowCollector, XNetworkingKotlinMapEntry, XNetworkingKtor_utilsStringValues, XNetworkingKotlinx_serialization_coreCompositeDecoder, XNetworkingKotlinAnnotation, XNetworkingKotlinx_serialization_coreCompositeEncoder, XNetworkingKotlinCoroutineContextElement, XNetworkingKotlinCoroutineContextKey, XNetworkingKtor_client_coreHttpClientPlugin, XNetworkingKotlinx_coroutines_coreDisposableHandle, XNetworkingKotlinSuspendFunction2, XNetworkingKotlinx_serialization_coreSerializersModuleCollector, XNetworkingKotlinKClass, XNetworkingKotlinx_coroutines_coreJob, XNetworkingKotlinContinuation, XNetworkingKotlinContinuationInterceptor, XNetworkingKotlinx_coroutines_coreRunnable, XNetworkingKotlinFunction, XNetworkingKtor_httpHttpMessage, XNetworkingKtor_ioByteReadChannel, XNetworkingKtor_httpHttpMessageBuilder, XNetworkingKtor_client_coreHttpRequest, XNetworkingKotlinKDeclarationContainer, XNetworkingKotlinKAnnotatedElement, XNetworkingKotlinKClassifier, XNetworkingKtor_httpParameters, XNetworkingKotlinx_coroutines_coreChildHandle, XNetworkingKotlinx_coroutines_coreChildJob, XNetworkingKotlinSequence, XNetworkingKotlinx_coroutines_coreSelectClause0, XNetworkingKotlinComparable, XNetworkingKtor_ioReadSession, XNetworkingKotlinSuspendFunction1, XNetworkingKotlinAppendable, XNetworkingKtor_utilsStringValuesBuilder, XNetworkingKtor_httpParametersBuilder, XNetworkingKotlinKType, XNetworkingKotlinx_coroutines_coreParentJob, XNetworkingKotlinx_coroutines_coreSelectInstance, XNetworkingKotlinSuspendFunction0, XNetworkingKtor_ioObjectPool;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface XNetworkingBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface XNetworkingBase (XNetworkingBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface XNetworkingMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface XNetworkingMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorXNetworkingKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface XNetworkingNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface XNetworkingByte : XNetworkingNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface XNetworkingUByte : XNetworkingNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface XNetworkingShort : XNetworkingNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface XNetworkingUShort : XNetworkingNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface XNetworkingInt : XNetworkingNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface XNetworkingUInt : XNetworkingNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface XNetworkingLong : XNetworkingNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface XNetworkingULong : XNetworkingNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface XNetworkingFloat : XNetworkingNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface XNetworkingDouble : XNetworkingNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface XNetworkingBoolean : XNetworkingNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryDatabaseProvider")))
@interface XNetworkingHistoryDatabaseProvider : XNetworkingBase
- (instancetype)initWithDatabaseDriverFactory:(XNetworkingDatabaseDriverFactory *)databaseDriverFactory __attribute__((swift_name("init(databaseDriverFactory:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryMapper")))
@interface XNetworkingHistoryMapper : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)historyMapper __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingHistoryMapper *shared __attribute__((swift_name("shared")));
- (XNetworkingTxHistoryItemNested *)mapItemNestedExtrinsic:(XNetworkingExtrinsics *)extrinsic params:(NSArray<XNetworkingExtrinsicParam *> *)params __attribute__((swift_name("mapItemNested(extrinsic:params:)")));
- (XNetworkingTxHistoryItem *)mapItemsExtrinsic:(XNetworkingExtrinsics *)extrinsic params:(NSArray<XNetworkingTxHistoryItemNested *> *)params __attribute__((swift_name("mapItems(extrinsic:params:)")));
- (XNetworkingTxHistoryItem *)mapParamsExtrinsic:(XNetworkingExtrinsics *)extrinsic params:(NSArray<XNetworkingExtrinsicParam *> *)params __attribute__((swift_name("mapParams(extrinsic:params:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TxHistoryInfo")))
@interface XNetworkingTxHistoryInfo : XNetworkingBase
- (instancetype)initWithEndCursor:(NSString * _Nullable)endCursor endReached:(BOOL)endReached items:(NSArray<XNetworkingTxHistoryItem *> *)items __attribute__((swift_name("init(endCursor:endReached:items:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<XNetworkingTxHistoryItem *> *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingTxHistoryInfo *)doCopyEndCursor:(NSString * _Nullable)endCursor endReached:(BOOL)endReached items:(NSArray<XNetworkingTxHistoryItem *> *)items __attribute__((swift_name("doCopy(endCursor:endReached:items:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable endCursor __attribute__((swift_name("endCursor")));
@property (readonly) BOOL endReached __attribute__((swift_name("endReached")));
@property (readonly) NSArray<XNetworkingTxHistoryItem *> *items __attribute__((swift_name("items")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TxHistoryItem")))
@interface XNetworkingTxHistoryItem : XNetworkingBase
- (instancetype)initWithId:(NSString *)id blockHash:(NSString *)blockHash module:(NSString *)module method:(NSString *)method timestamp:(NSString *)timestamp networkFee:(NSString *)networkFee success:(BOOL)success data:(NSArray<XNetworkingTxHistoryItemParam *> * _Nullable)data nestedData:(NSArray<XNetworkingTxHistoryItemNested *> * _Nullable)nestedData __attribute__((swift_name("init(id:blockHash:module:method:timestamp:networkFee:success:data:nestedData:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component7 __attribute__((swift_name("component7()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<XNetworkingTxHistoryItemParam *> * _Nullable)component8 __attribute__((swift_name("component8()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<XNetworkingTxHistoryItemNested *> * _Nullable)component9 __attribute__((swift_name("component9()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingTxHistoryItem *)doCopyId:(NSString *)id blockHash:(NSString *)blockHash module:(NSString *)module method:(NSString *)method timestamp:(NSString *)timestamp networkFee:(NSString *)networkFee success:(BOOL)success data:(NSArray<XNetworkingTxHistoryItemParam *> * _Nullable)data nestedData:(NSArray<XNetworkingTxHistoryItemNested *> * _Nullable)nestedData __attribute__((swift_name("doCopy(id:blockHash:module:method:timestamp:networkFee:success:data:nestedData:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *blockHash __attribute__((swift_name("blockHash")));
@property (readonly) NSArray<XNetworkingTxHistoryItemParam *> * _Nullable data __attribute__((swift_name("data")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *method __attribute__((swift_name("method")));
@property (readonly) NSString *module __attribute__((swift_name("module")));
@property (readonly) NSArray<XNetworkingTxHistoryItemNested *> * _Nullable nestedData __attribute__((swift_name("nestedData")));
@property (readonly) NSString *networkFee __attribute__((swift_name("networkFee")));
@property (readonly) BOOL success __attribute__((swift_name("success")));
@property (readonly) NSString *timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TxHistoryItemNested")))
@interface XNetworkingTxHistoryItemNested : XNetworkingBase
- (instancetype)initWithModule:(NSString *)module method:(NSString *)method hash:(NSString *)hash data:(NSArray<XNetworkingTxHistoryItemParam *> *)data __attribute__((swift_name("init(module:method:hash:data:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<XNetworkingTxHistoryItemParam *> *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingTxHistoryItemNested *)doCopyModule:(NSString *)module method:(NSString *)method hash:(NSString *)hash data:(NSArray<XNetworkingTxHistoryItemParam *> *)data __attribute__((swift_name("doCopy(module:method:hash:data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingTxHistoryItemParam *> *data __attribute__((swift_name("data")));
@property (readonly, getter=hash_) NSString *hash __attribute__((swift_name("hash")));
@property (readonly) NSString *method __attribute__((swift_name("method")));
@property (readonly) NSString *module __attribute__((swift_name("module")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TxHistoryItemParam")))
@interface XNetworkingTxHistoryItemParam : XNetworkingBase
- (instancetype)initWithParamName:(NSString *)paramName paramValue:(NSString *)paramValue __attribute__((swift_name("init(paramName:paramValue:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingTxHistoryItemParam *)doCopyParamName:(NSString *)paramName paramValue:(NSString *)paramValue __attribute__((swift_name("doCopy(paramName:paramValue:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *paramName __attribute__((swift_name("paramName")));
@property (readonly) NSString *paramValue __attribute__((swift_name("paramValue")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TxHistoryResult")))
@interface XNetworkingTxHistoryResult<R> : XNetworkingBase
- (instancetype)initWithEndCursor:(NSString * _Nullable)endCursor endReached:(BOOL)endReached page:(int64_t)page items:(NSArray<id> *)items __attribute__((swift_name("init(endCursor:endReached:page:items:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString * _Nullable endCursor __attribute__((swift_name("endCursor")));
@property (readonly) BOOL endReached __attribute__((swift_name("endReached")));
@property (readonly) NSArray<id> *items __attribute__((swift_name("items")));
@property (readonly) int64_t page __attribute__((swift_name("page")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClient")))
@interface XNetworkingSubQueryClient<T, R> : XNetworkingBase
- (void)clearAllData __attribute__((swift_name("clearAllData()")));
- (void)clearDataAddress:(NSString *)address networkName:(NSString *)networkName __attribute__((swift_name("clearData(address:networkName:)")));
- (XNetworkingTxHistoryInfo *)getTransactionCachedAddress:(NSString *)address networkName:(NSString *)networkName txHash:(NSString *)txHash __attribute__((swift_name("getTransactionCached(address:networkName:txHash:)")));
- (XNetworkingTxHistoryInfo *)getTransactionHistoryCachedAddress:(NSString *)address networkName:(NSString *)networkName __attribute__((swift_name("getTransactionHistoryCached(address:networkName:)")));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException, IllegalArgumentException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTransactionHistoryPagedAddress:(NSString *)address networkName:(NSString *)networkName page:(int64_t)page url:(NSString * _Nullable)url filter:(XNetworkingBoolean *(^ _Nullable)(R _Nullable))filter completionHandler:(void (^)(XNetworkingTxHistoryResult<R> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTransactionHistoryPaged(address:networkName:page:url:filter:completionHandler:)")));
- (NSArray<NSString *> *)getTransactionPeersQuery:(NSString *)query networkName:(NSString *)networkName __attribute__((swift_name("getTransactionPeers(query:networkName:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClientFactory")))
@interface XNetworkingSubQueryClientFactory<T, R> : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (XNetworkingSubQueryClient<T, R> *)createSoramitsuNetworkClient:(XNetworkingSoramitsuNetworkClient *)soramitsuNetworkClient baseUrl:(NSString *)baseUrl pageSize:(int32_t)pageSize deserializationStrategy:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializationStrategy jsonToHistoryInfo:(XNetworkingTxHistoryInfo *(^)(T _Nullable))jsonToHistoryInfo historyIntoToResult:(R _Nullable (^)(XNetworkingTxHistoryItem *))historyIntoToResult historyRequest:(NSString *)historyRequest __attribute__((swift_name("create(soramitsuNetworkClient:baseUrl:pageSize:deserializationStrategy:jsonToHistoryInfo:historyIntoToResult:historyRequest:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClientForFearless")))
@interface XNetworkingSubQueryClientForFearless : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)subQueryClientForFearless __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSubQueryClientForFearless *shared __attribute__((swift_name("shared")));
- (XNetworkingSubQueryClient<XNetworkingFearlessSubQueryResponse *, XNetworkingTxHistoryItem *> *)buildSoramitsuNetworkClient:(XNetworkingSoramitsuNetworkClient *)soramitsuNetworkClient baseUrl:(NSString *)baseUrl pageSize:(int32_t)pageSize __attribute__((swift_name("build(soramitsuNetworkClient:baseUrl:pageSize:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClientForFearlessWallet")))
@interface XNetworkingSubQueryClientForFearlessWallet : XNetworkingBase
- (instancetype)initWithNetworkClient:(XNetworkingSoramitsuNetworkClient *)networkClient baseUrl:(NSString *)baseUrl pageSize:(int32_t)pageSize historyDatabaseProvider:(XNetworkingHistoryDatabaseProvider *)historyDatabaseProvider __attribute__((swift_name("init(networkClient:baseUrl:pageSize:historyDatabaseProvider:)"))) __attribute__((objc_designated_initializer));
- (void)clearAllData __attribute__((swift_name("clearAllData()")));
- (void)clearDataAddress:(NSString *)address networkName:(NSString *)networkName __attribute__((swift_name("clearData(address:networkName:)")));
- (XNetworkingTxHistoryInfo *)getTransactionCachedAddress:(NSString *)address networkName:(NSString *)networkName txHash:(NSString *)txHash __attribute__((swift_name("getTransactionCached(address:networkName:txHash:)")));
- (XNetworkingTxHistoryInfo *)getTransactionHistoryCachedAddress:(NSString *)address networkName:(NSString *)networkName __attribute__((swift_name("getTransactionHistoryCached(address:networkName:)")));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException, IllegalArgumentException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTransactionHistoryPagedAddress:(NSString *)address networkName:(NSString *)networkName page:(int64_t)page url:(NSString * _Nullable)url filter:(XNetworkingBoolean *(^ _Nullable)(XNetworkingTxHistoryItem *))filter completionHandler:(void (^)(XNetworkingTxHistoryResult<XNetworkingTxHistoryItem *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTransactionHistoryPaged(address:networkName:page:url:filter:completionHandler:)")));
- (NSArray<NSString *> *)getTransactionPeersQuery:(NSString *)query networkName:(NSString *)networkName __attribute__((swift_name("getTransactionPeers(query:networkName:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClientForFearlessWalletFactory")))
@interface XNetworkingSubQueryClientForFearlessWalletFactory : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (XNetworkingSubQueryClientForFearlessWallet *)createSoramitsuNetworkClient:(XNetworkingSoramitsuNetworkClient *)soramitsuNetworkClient baseUrl:(NSString *)baseUrl pageSize:(int32_t)pageSize __attribute__((swift_name("create(soramitsuNetworkClient:baseUrl:pageSize:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClientForSoraWallet")))
@interface XNetworkingSubQueryClientForSoraWallet : XNetworkingBase
- (instancetype)initWithNetworkClient:(XNetworkingSoramitsuNetworkClient *)networkClient baseUrl:(NSString *)baseUrl pageSize:(int32_t)pageSize historyDatabaseProvider:(XNetworkingHistoryDatabaseProvider *)historyDatabaseProvider __attribute__((swift_name("init(networkClient:baseUrl:pageSize:historyDatabaseProvider:)"))) __attribute__((objc_designated_initializer));
- (void)clearAllData __attribute__((swift_name("clearAllData()")));
- (void)clearDataAddress:(NSString *)address networkName:(NSString *)networkName __attribute__((swift_name("clearData(address:networkName:)")));
- (XNetworkingTxHistoryInfo *)getTransactionCachedAddress:(NSString *)address networkName:(NSString *)networkName txHash:(NSString *)txHash __attribute__((swift_name("getTransactionCached(address:networkName:txHash:)")));
- (XNetworkingTxHistoryInfo *)getTransactionHistoryCachedAddress:(NSString *)address networkName:(NSString *)networkName __attribute__((swift_name("getTransactionHistoryCached(address:networkName:)")));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException, IllegalArgumentException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getTransactionHistoryPagedAddress:(NSString *)address networkName:(NSString *)networkName page:(int64_t)page url:(NSString * _Nullable)url filter:(XNetworkingBoolean *(^ _Nullable)(XNetworkingTxHistoryItem *))filter completionHandler:(void (^)(XNetworkingTxHistoryResult<XNetworkingTxHistoryItem *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getTransactionHistoryPaged(address:networkName:page:url:filter:completionHandler:)")));
- (NSArray<NSString *> *)getTransactionPeersQuery:(NSString *)query networkName:(NSString *)networkName __attribute__((swift_name("getTransactionPeers(query:networkName:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryClientForSoraWalletFactory")))
@interface XNetworkingSubQueryClientForSoraWalletFactory : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (XNetworkingSubQueryClientForSoraWallet *)createSoramitsuNetworkClient:(XNetworkingSoramitsuNetworkClient *)soramitsuNetworkClient baseUrl:(NSString *)baseUrl pageSize:(int32_t)pageSize __attribute__((swift_name("create(soramitsuNetworkClient:baseUrl:pageSize:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryRequest")))
@interface XNetworkingSubQueryRequest : XNetworkingBase
- (instancetype)initWithQuery:(NSString *)query variables:(NSString * _Nullable)variables __attribute__((swift_name("init(query:variables:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSubQueryRequestCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSubQueryRequest *)doCopyQuery:(NSString *)query variables:(NSString * _Nullable)variables __attribute__((swift_name("doCopy(query:variables:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *query __attribute__((swift_name("query")));
@property (readonly) NSString * _Nullable variables __attribute__((swift_name("variables")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SubQueryRequest.Companion")))
@interface XNetworkingSubQueryRequestCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSubQueryRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessExtrinsicItem")))
@interface XNetworkingFearlessExtrinsicItem : XNetworkingBase
- (instancetype)initWithHash:(NSString *)hash module:(NSString *)module call:(NSString *)call fee:(NSString *)fee success:(BOOL)success __attribute__((swift_name("init(hash:module:call:fee:success:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessExtrinsicItemCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessExtrinsicItem *)doCopyHash:(NSString *)hash module:(NSString *)module call:(NSString *)call fee:(NSString *)fee success:(BOOL)success __attribute__((swift_name("doCopy(hash:module:call:fee:success:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *call __attribute__((swift_name("call")));
@property (readonly) NSString *fee __attribute__((swift_name("fee")));
@property (readonly, getter=hash_) NSString *hash __attribute__((swift_name("hash")));
@property (readonly) NSString *module __attribute__((swift_name("module")));
@property (readonly) BOOL success __attribute__((swift_name("success")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessExtrinsicItem.Companion")))
@interface XNetworkingFearlessExtrinsicItemCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessExtrinsicItemCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessHistoryResponseDataElements")))
@interface XNetworkingFearlessHistoryResponseDataElements : XNetworkingBase
- (instancetype)initWithNodes:(NSArray<XNetworkingFearlessHistoryResponseItem *> *)nodes pageInfo:(XNetworkingFearlessHistoryResponsePageInfo *)pageInfo __attribute__((swift_name("init(nodes:pageInfo:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessHistoryResponseDataElementsCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingFearlessHistoryResponseItem *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessHistoryResponsePageInfo *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessHistoryResponseDataElements *)doCopyNodes:(NSArray<XNetworkingFearlessHistoryResponseItem *> *)nodes pageInfo:(XNetworkingFearlessHistoryResponsePageInfo *)pageInfo __attribute__((swift_name("doCopy(nodes:pageInfo:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingFearlessHistoryResponseItem *> *nodes __attribute__((swift_name("nodes")));
@property (readonly) XNetworkingFearlessHistoryResponsePageInfo *pageInfo __attribute__((swift_name("pageInfo")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessHistoryResponseDataElements.Companion")))
@interface XNetworkingFearlessHistoryResponseDataElementsCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessHistoryResponseDataElementsCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessHistoryResponseItem")))
@interface XNetworkingFearlessHistoryResponseItem : XNetworkingBase
- (instancetype)initWithId:(NSString *)id timestamp:(NSString *)timestamp address:(NSString *)address reward:(XNetworkingFearlessRewardItem * _Nullable)reward transfer:(XNetworkingFearlessTransferItem * _Nullable)transfer extrinsic:(XNetworkingFearlessExtrinsicItem * _Nullable)extrinsic __attribute__((swift_name("init(id:timestamp:address:reward:transfer:extrinsic:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessHistoryResponseItemCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessRewardItem * _Nullable)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessTransferItem * _Nullable)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessExtrinsicItem * _Nullable)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessHistoryResponseItem *)doCopyId:(NSString *)id timestamp:(NSString *)timestamp address:(NSString *)address reward:(XNetworkingFearlessRewardItem * _Nullable)reward transfer:(XNetworkingFearlessTransferItem * _Nullable)transfer extrinsic:(XNetworkingFearlessExtrinsicItem * _Nullable)extrinsic __attribute__((swift_name("doCopy(id:timestamp:address:reward:transfer:extrinsic:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *address __attribute__((swift_name("address")));
@property (readonly) XNetworkingFearlessExtrinsicItem * _Nullable extrinsic __attribute__((swift_name("extrinsic")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) XNetworkingFearlessRewardItem * _Nullable reward __attribute__((swift_name("reward")));
@property (readonly) NSString *timestamp __attribute__((swift_name("timestamp")));
@property (readonly) XNetworkingFearlessTransferItem * _Nullable transfer __attribute__((swift_name("transfer")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessHistoryResponseItem.Companion")))
@interface XNetworkingFearlessHistoryResponseItemCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessHistoryResponseItemCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessHistoryResponsePageInfo")))
@interface XNetworkingFearlessHistoryResponsePageInfo : XNetworkingBase
- (instancetype)initWithEndCursor:(NSString * _Nullable)endCursor hasNextPage:(BOOL)hasNextPage __attribute__((swift_name("init(endCursor:hasNextPage:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessHistoryResponsePageInfoCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessHistoryResponsePageInfo *)doCopyEndCursor:(NSString * _Nullable)endCursor hasNextPage:(BOOL)hasNextPage __attribute__((swift_name("doCopy(endCursor:hasNextPage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable endCursor __attribute__((swift_name("endCursor")));
@property (readonly) BOOL hasNextPage __attribute__((swift_name("hasNextPage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessHistoryResponsePageInfo.Companion")))
@interface XNetworkingFearlessHistoryResponsePageInfoCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessHistoryResponsePageInfoCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessRewardItem")))
@interface XNetworkingFearlessRewardItem : XNetworkingBase
- (instancetype)initWithEra:(int32_t)era amount:(NSString *)amount isReward:(BOOL)isReward validator:(NSString *)validator __attribute__((swift_name("init(era:amount:isReward:validator:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessRewardItemCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessRewardItem *)doCopyEra:(int32_t)era amount:(NSString *)amount isReward:(BOOL)isReward validator:(NSString *)validator __attribute__((swift_name("doCopy(era:amount:isReward:validator:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *amount __attribute__((swift_name("amount")));
@property (readonly) int32_t era __attribute__((swift_name("era")));
@property (readonly) BOOL isReward __attribute__((swift_name("isReward")));
@property (readonly) NSString *validator __attribute__((swift_name("validator")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessRewardItem.Companion")))
@interface XNetworkingFearlessRewardItemCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessRewardItemCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessSubQueryResponse")))
@interface XNetworkingFearlessSubQueryResponse : XNetworkingBase
- (instancetype)initWithData:(XNetworkingFearlessSubQueryResponseData *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessSubQueryResponseCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingFearlessSubQueryResponseData *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessSubQueryResponse *)doCopyData:(XNetworkingFearlessSubQueryResponseData *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingFearlessSubQueryResponseData *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessSubQueryResponse.Companion")))
@interface XNetworkingFearlessSubQueryResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessSubQueryResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessSubQueryResponseData")))
@interface XNetworkingFearlessSubQueryResponseData : XNetworkingBase
- (instancetype)initWithHistoryElements:(XNetworkingFearlessHistoryResponseDataElements *)historyElements __attribute__((swift_name("init(historyElements:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessSubQueryResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingFearlessHistoryResponseDataElements *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessSubQueryResponseData *)doCopyHistoryElements:(XNetworkingFearlessHistoryResponseDataElements *)historyElements __attribute__((swift_name("doCopy(historyElements:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingFearlessHistoryResponseDataElements *historyElements __attribute__((swift_name("historyElements")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessSubQueryResponseData.Companion")))
@interface XNetworkingFearlessSubQueryResponseDataCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessSubQueryResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessTransferItem")))
@interface XNetworkingFearlessTransferItem : XNetworkingBase
- (instancetype)initWithAmount:(NSString *)amount to:(NSString *)to from:(NSString *)from fee:(NSString *)fee block:(NSString * _Nullable)block success:(BOOL)success extrinsicHash:(NSString * _Nullable)extrinsicHash __attribute__((swift_name("init(amount:to:from:fee:block:success:extrinsicHash:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingFearlessTransferItemCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component7 __attribute__((swift_name("component7()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingFearlessTransferItem *)doCopyAmount:(NSString *)amount to:(NSString *)to from:(NSString *)from fee:(NSString *)fee block:(NSString * _Nullable)block success:(BOOL)success extrinsicHash:(NSString * _Nullable)extrinsicHash __attribute__((swift_name("doCopy(amount:to:from:fee:block:success:extrinsicHash:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *amount __attribute__((swift_name("amount")));
@property (readonly) NSString * _Nullable block __attribute__((swift_name("block")));
@property (readonly) NSString * _Nullable extrinsicHash __attribute__((swift_name("extrinsicHash")));
@property (readonly) NSString *fee __attribute__((swift_name("fee")));
@property (readonly) NSString *from __attribute__((swift_name("from")));
@property (readonly) BOOL success __attribute__((swift_name("success")));
@property (readonly) NSString *to __attribute__((swift_name("to")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessTransferItem.Companion")))
@interface XNetworkingFearlessTransferItemCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingFearlessTransferItemCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Error")))
@interface XNetworkingError : XNetworkingBase
- (instancetype)initWithModuleErrorId:(NSString * _Nullable)moduleErrorId moduleErrorIndex:(XNetworkingInt * _Nullable)moduleErrorIndex nonModuleErrorMessage:(NSString * _Nullable)nonModuleErrorMessage __attribute__((swift_name("init(moduleErrorId:moduleErrorIndex:nonModuleErrorMessage:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingErrorCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingInt * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingError *)doCopyModuleErrorId:(NSString * _Nullable)moduleErrorId moduleErrorIndex:(XNetworkingInt * _Nullable)moduleErrorIndex nonModuleErrorMessage:(NSString * _Nullable)nonModuleErrorMessage __attribute__((swift_name("doCopy(moduleErrorId:moduleErrorIndex:nonModuleErrorMessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable moduleErrorId __attribute__((swift_name("moduleErrorId")));
@property (readonly) XNetworkingInt * _Nullable moduleErrorIndex __attribute__((swift_name("moduleErrorIndex")));
@property (readonly) NSString * _Nullable nonModuleErrorMessage __attribute__((swift_name("nonModuleErrorMessage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Error.Companion")))
@interface XNetworkingErrorCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingErrorCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecutionResult")))
@interface XNetworkingExecutionResult : XNetworkingBase
- (instancetype)initWithSuccess:(BOOL)success error:(XNetworkingError * _Nullable)error __attribute__((swift_name("init(success:error:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingExecutionResultCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingError * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingExecutionResult *)doCopySuccess:(BOOL)success error:(XNetworkingError * _Nullable)error __attribute__((swift_name("doCopy(success:error:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingError * _Nullable error __attribute__((swift_name("error")));
@property (readonly) BOOL success __attribute__((swift_name("success")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecutionResult.Companion")))
@interface XNetworkingExecutionResultCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingExecutionResultCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryResponseDataElements")))
@interface XNetworkingHistoryResponseDataElements : XNetworkingBase
- (instancetype)initWithNodes:(NSArray<XNetworkingHistoryResponseItem *> *)nodes pageInfo:(XNetworkingHistoryResponsePageInfo *)pageInfo __attribute__((swift_name("init(nodes:pageInfo:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingHistoryResponseDataElementsCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingHistoryResponseItem *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingHistoryResponsePageInfo *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingHistoryResponseDataElements *)doCopyNodes:(NSArray<XNetworkingHistoryResponseItem *> *)nodes pageInfo:(XNetworkingHistoryResponsePageInfo *)pageInfo __attribute__((swift_name("doCopy(nodes:pageInfo:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingHistoryResponseItem *> *nodes __attribute__((swift_name("nodes")));
@property (readonly) XNetworkingHistoryResponsePageInfo *pageInfo __attribute__((swift_name("pageInfo")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryResponseDataElements.Companion")))
@interface XNetworkingHistoryResponseDataElementsCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingHistoryResponseDataElementsCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryResponseItem")))
@interface XNetworkingHistoryResponseItem : XNetworkingBase
- (instancetype)initWithId:(NSString *)id blockHash:(NSString *)blockHash module:(NSString *)module method:(NSString *)method timestamp:(NSString *)timestamp networkFee:(NSString *)networkFee execution:(XNetworkingExecutionResult *)execution data:(XNetworkingKotlinx_serialization_jsonJsonElement *)data __attribute__((swift_name("init(id:blockHash:module:method:timestamp:networkFee:execution:data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingHistoryResponseItemCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingExecutionResult *)component7 __attribute__((swift_name("component7()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKotlinx_serialization_jsonJsonElement *)component8 __attribute__((swift_name("component8()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingHistoryResponseItem *)doCopyId:(NSString *)id blockHash:(NSString *)blockHash module:(NSString *)module method:(NSString *)method timestamp:(NSString *)timestamp networkFee:(NSString *)networkFee execution:(XNetworkingExecutionResult *)execution data:(XNetworkingKotlinx_serialization_jsonJsonElement *)data __attribute__((swift_name("doCopy(id:blockHash:module:method:timestamp:networkFee:execution:data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *blockHash __attribute__((swift_name("blockHash")));
@property (readonly) XNetworkingKotlinx_serialization_jsonJsonElement *data __attribute__((swift_name("data")));
@property (readonly) XNetworkingExecutionResult *execution __attribute__((swift_name("execution")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *method __attribute__((swift_name("method")));
@property (readonly) NSString *module __attribute__((swift_name("module")));
@property (readonly) NSString *networkFee __attribute__((swift_name("networkFee")));
@property (readonly) NSString *timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryResponseItem.Companion")))
@interface XNetworkingHistoryResponseItemCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingHistoryResponseItemCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryResponsePageInfo")))
@interface XNetworkingHistoryResponsePageInfo : XNetworkingBase
- (instancetype)initWithEndCursor:(NSString * _Nullable)endCursor hasNextPage:(BOOL)hasNextPage __attribute__((swift_name("init(endCursor:hasNextPage:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingHistoryResponsePageInfoCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingHistoryResponsePageInfo *)doCopyEndCursor:(NSString * _Nullable)endCursor hasNextPage:(BOOL)hasNextPage __attribute__((swift_name("doCopy(endCursor:hasNextPage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable endCursor __attribute__((swift_name("endCursor")));
@property (readonly) BOOL hasNextPage __attribute__((swift_name("hasNextPage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HistoryResponsePageInfo.Companion")))
@interface XNetworkingHistoryResponsePageInfoCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingHistoryResponsePageInfoCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraSubQueryResponse")))
@interface XNetworkingSoraSubQueryResponse : XNetworkingBase
- (instancetype)initWithData:(XNetworkingSoraSubQueryResponseData *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraSubQueryResponseCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraSubQueryResponseData *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraSubQueryResponse *)doCopyData:(XNetworkingSoraSubQueryResponseData *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraSubQueryResponseData *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraSubQueryResponse.Companion")))
@interface XNetworkingSoraSubQueryResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraSubQueryResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraSubQueryResponseData")))
@interface XNetworkingSoraSubQueryResponseData : XNetworkingBase
- (instancetype)initWithHistoryElements:(XNetworkingHistoryResponseDataElements *)historyElements __attribute__((swift_name("init(historyElements:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraSubQueryResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingHistoryResponseDataElements *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraSubQueryResponseData *)doCopyHistoryElements:(XNetworkingHistoryResponseDataElements *)historyElements __attribute__((swift_name("doCopy(historyElements:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingHistoryResponseDataElements *historyElements __attribute__((swift_name("historyElements")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraSubQueryResponseData.Companion")))
@interface XNetworkingSoraSubQueryResponseDataCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraSubQueryResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExtrinsicParam")))
@interface XNetworkingExtrinsicParam : XNetworkingBase
- (instancetype)initWithExtrinsicHash:(NSString *)extrinsicHash paramName:(NSString *)paramName paramValue:(NSString *)paramValue __attribute__((swift_name("init(extrinsicHash:paramName:paramValue:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingExtrinsicParam *)doCopyExtrinsicHash:(NSString *)extrinsicHash paramName:(NSString *)paramName paramValue:(NSString *)paramValue __attribute__((swift_name("doCopy(extrinsicHash:paramName:paramValue:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *extrinsicHash __attribute__((swift_name("extrinsicHash")));
@property (readonly) NSString *paramName __attribute__((swift_name("paramName")));
@property (readonly) NSString *paramValue __attribute__((swift_name("paramValue")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Extrinsics")))
@interface XNetworkingExtrinsics : XNetworkingBase
- (instancetype)initWithTxHash:(NSString *)txHash signAddress:(NSString *)signAddress blockHash:(NSString * _Nullable)blockHash module:(NSString *)module method:(NSString *)method networkFee:(NSString *)networkFee timestamp:(int64_t)timestamp success:(BOOL)success batch:(BOOL)batch parentHash:(NSString * _Nullable)parentHash networkName:(NSString *)networkName __attribute__((swift_name("init(txHash:signAddress:blockHash:module:method:networkFee:timestamp:success:batch:parentHash:networkName:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component10 __attribute__((swift_name("component10()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component11 __attribute__((swift_name("component11()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (int64_t)component7 __attribute__((swift_name("component7()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component8 __attribute__((swift_name("component8()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component9 __attribute__((swift_name("component9()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingExtrinsics *)doCopyTxHash:(NSString *)txHash signAddress:(NSString *)signAddress blockHash:(NSString * _Nullable)blockHash module:(NSString *)module method:(NSString *)method networkFee:(NSString *)networkFee timestamp:(int64_t)timestamp success:(BOOL)success batch:(BOOL)batch parentHash:(NSString * _Nullable)parentHash networkName:(NSString *)networkName __attribute__((swift_name("doCopy(txHash:signAddress:blockHash:module:method:networkFee:timestamp:success:batch:parentHash:networkName:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL batch __attribute__((swift_name("batch")));
@property (readonly) NSString * _Nullable blockHash __attribute__((swift_name("blockHash")));
@property (readonly) NSString *method __attribute__((swift_name("method")));
@property (readonly) NSString *module __attribute__((swift_name("module")));
@property (readonly) NSString *networkFee __attribute__((swift_name("networkFee")));
@property (readonly) NSString *networkName __attribute__((swift_name("networkName")));
@property (readonly) NSString * _Nullable parentHash __attribute__((swift_name("parentHash")));
@property (readonly) NSString *signAddress __attribute__((swift_name("signAddress")));
@property (readonly) BOOL success __attribute__((swift_name("success")));
@property (readonly) int64_t timestamp __attribute__((swift_name("timestamp")));
@property (readonly) NSString *txHash __attribute__((swift_name("txHash")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SignerInfo")))
@interface XNetworkingSignerInfo : XNetworkingBase
- (instancetype)initWithSignAddress:(NSString *)signAddress topTime:(int64_t)topTime oldTime:(int64_t)oldTime oldCursor:(NSString * _Nullable)oldCursor endReached:(BOOL)endReached networkName:(NSString *)networkName __attribute__((swift_name("init(signAddress:topTime:oldTime:oldCursor:endReached:networkName:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (int64_t)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (int64_t)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (BOOL)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSignerInfo *)doCopySignAddress:(NSString *)signAddress topTime:(int64_t)topTime oldTime:(int64_t)oldTime oldCursor:(NSString * _Nullable)oldCursor endReached:(BOOL)endReached networkName:(NSString *)networkName __attribute__((swift_name("doCopy(signAddress:topTime:oldTime:oldCursor:endReached:networkName:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL endReached __attribute__((swift_name("endReached")));
@property (readonly) NSString *networkName __attribute__((swift_name("networkName")));
@property (readonly) NSString * _Nullable oldCursor __attribute__((swift_name("oldCursor")));
@property (readonly) int64_t oldTime __attribute__((swift_name("oldTime")));
@property (readonly) NSString *signAddress __attribute__((swift_name("signAddress")));
@property (readonly) int64_t topTime __attribute__((swift_name("topTime")));
@end;

__attribute__((swift_name("RuntimeTransacter")))
@protocol XNetworkingRuntimeTransacter
@required
- (void)transactionNoEnclosing:(BOOL)noEnclosing body:(void (^)(id<XNetworkingRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(noEnclosing:body:)")));
- (id _Nullable)transactionWithResultNoEnclosing:(BOOL)noEnclosing bodyWithReturn:(id _Nullable (^)(id<XNetworkingRuntimeTransactionWithReturn>))bodyWithReturn __attribute__((swift_name("transactionWithResult(noEnclosing:bodyWithReturn:)")));
@end;

__attribute__((swift_name("SoraHistoryDatabase")))
@protocol XNetworkingSoraHistoryDatabase <XNetworkingRuntimeTransacter>
@required
@property (readonly) id<XNetworkingSoraHistoryDatabaseQueries> soraHistoryDatabaseQueries __attribute__((swift_name("soraHistoryDatabaseQueries")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraHistoryDatabaseCompanion")))
@interface XNetworkingSoraHistoryDatabaseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraHistoryDatabaseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingSoraHistoryDatabase>)invokeDriver:(id<XNetworkingRuntimeSqlDriver>)driver __attribute__((swift_name("invoke(driver:)")));
@property (readonly) id<XNetworkingRuntimeSqlDriverSchema> Schema __attribute__((swift_name("Schema")));
@end;

__attribute__((swift_name("SoraHistoryDatabaseQueries")))
@protocol XNetworkingSoraHistoryDatabaseQueries <XNetworkingRuntimeTransacter>
@required
- (void)insertExtrinsicTxHash:(NSString *)txHash signAddress:(NSString *)signAddress networkName:(NSString *)networkName blockHash:(NSString * _Nullable)blockHash module:(NSString *)module method:(NSString *)method networkFee:(NSString *)networkFee timestamp:(int64_t)timestamp success:(BOOL)success batch:(BOOL)batch parentHash:(NSString * _Nullable)parentHash __attribute__((swift_name("insertExtrinsic(txHash:signAddress:networkName:blockHash:module:method:networkFee:timestamp:success:batch:parentHash:)")));
- (void)insertExtrinsicParamExtrinsicHash:(NSString *)extrinsicHash paramName:(NSString *)paramName paramValue:(NSString *)paramValue __attribute__((swift_name("insertExtrinsicParam(extrinsicHash:paramName:paramValue:)")));
- (void)insertSignerInfoSignAddress:(NSString *)signAddress networkName:(NSString *)networkName topTime:(int64_t)topTime oldTime:(int64_t)oldTime oldCursor:(NSString * _Nullable)oldCursor endReached:(BOOL)endReached __attribute__((swift_name("insertSignerInfo(signAddress:networkName:topTime:oldTime:oldCursor:endReached:)")));
- (void)insertSignerInfoFullSignerInfo:(XNetworkingSignerInfo *)SignerInfo __attribute__((swift_name("insertSignerInfoFull(SignerInfo:)")));
- (void)removeAllExtrinsics __attribute__((swift_name("removeAllExtrinsics()")));
- (void)removeAllSignerInfo __attribute__((swift_name("removeAllSignerInfo()")));
- (void)removeExtrinsicsAddress:(NSString *)address network:(NSString *)network __attribute__((swift_name("removeExtrinsics(address:network:)")));
- (void)removeSignerInfoAddress:(NSString *)address network:(NSString *)network __attribute__((swift_name("removeSignerInfo(address:network:)")));
- (XNetworkingRuntimeQuery<XNetworkingExtrinsics *> *)selectExtrinsicHash:(NSString *)hash address:(NSString *)address network:(NSString *)network __attribute__((swift_name("selectExtrinsic(hash:address:network:)")));
- (XNetworkingRuntimeQuery<id> *)selectExtrinsicHash:(NSString *)hash address:(NSString *)address network:(NSString *)network mapper:(id (^)(NSString *, NSString *, NSString * _Nullable, NSString *, NSString *, NSString *, XNetworkingLong *, XNetworkingBoolean *, XNetworkingBoolean *, NSString * _Nullable, NSString *))mapper __attribute__((swift_name("selectExtrinsic(hash:address:network:mapper:)")));
- (XNetworkingRuntimeQuery<XNetworkingExtrinsicParam *> *)selectExtrinsicParamsExtrinsicHash:(NSString *)extrinsicHash __attribute__((swift_name("selectExtrinsicParams(extrinsicHash:)")));
- (XNetworkingRuntimeQuery<id> *)selectExtrinsicParamsExtrinsicHash:(NSString *)extrinsicHash mapper:(id (^)(NSString *, NSString *, NSString *))mapper __attribute__((swift_name("selectExtrinsicParams(extrinsicHash:mapper:)")));
- (XNetworkingRuntimeQuery<XNetworkingExtrinsics *> *)selectExtrinsicsNestedParentHash:(NSString * _Nullable)parentHash __attribute__((swift_name("selectExtrinsicsNested(parentHash:)")));
- (XNetworkingRuntimeQuery<id> *)selectExtrinsicsNestedParentHash:(NSString * _Nullable)parentHash mapper:(id (^)(NSString *, NSString *, NSString * _Nullable, NSString *, NSString *, NSString *, XNetworkingLong *, XNetworkingBoolean *, XNetworkingBoolean *, NSString * _Nullable, NSString *))mapper __attribute__((swift_name("selectExtrinsicsNested(parentHash:mapper:)")));
- (XNetworkingRuntimeQuery<XNetworkingExtrinsics *> *)selectExtrinsicsPagedAddress:(NSString *)address network:(NSString *)network limit:(int64_t)limit offset:(int64_t)offset __attribute__((swift_name("selectExtrinsicsPaged(address:network:limit:offset:)")));
- (XNetworkingRuntimeQuery<id> *)selectExtrinsicsPagedAddress:(NSString *)address network:(NSString *)network limit:(int64_t)limit offset:(int64_t)offset mapper:(id (^)(NSString *, NSString *, NSString * _Nullable, NSString *, NSString *, NSString *, XNetworkingLong *, XNetworkingBoolean *, XNetworkingBoolean *, NSString * _Nullable, NSString *))mapper __attribute__((swift_name("selectExtrinsicsPaged(address:network:limit:offset:mapper:)")));
- (XNetworkingRuntimeQuery<XNetworkingSignerInfo *> *)selectSignerInfoAddress:(NSString *)address network:(NSString *)network __attribute__((swift_name("selectSignerInfo(address:network:)")));
- (XNetworkingRuntimeQuery<id> *)selectSignerInfoAddress:(NSString *)address network:(NSString *)network mapper:(id (^)(NSString *, XNetworkingLong *, XNetworkingLong *, NSString * _Nullable, XNetworkingBoolean *, NSString *))mapper __attribute__((swift_name("selectSignerInfo(address:network:mapper:)")));
- (XNetworkingRuntimeQuery<NSString *> *)selectTransfersPeersNetwork:(NSString *)network query:(NSString *)query __attribute__((swift_name("selectTransfersPeers(network:query:)")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface XNetworkingKotlinThrowable : XNetworkingBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (XNetworkingKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end;

__attribute__((swift_name("ChainBuilderException")))
@interface XNetworkingChainBuilderException : XNetworkingKotlinThrowable
- (instancetype)initWithMessage:(NSString *)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChainModel")))
@interface XNetworkingChainModel : XNetworkingBase
- (instancetype)initWithChainId:(NSString *)chainId hash:(NSString *)hash content:(NSString *)content __attribute__((swift_name("init(chainId:hash:content:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingChainModelCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingChainModel *)doCopyChainId:(NSString *)chainId hash:(NSString *)hash content:(NSString *)content __attribute__((swift_name("doCopy(chainId:hash:content:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *chainId __attribute__((swift_name("chainId")));
@property (readonly) NSString *content __attribute__((swift_name("content")));
@property (readonly, getter=hash_) NSString *hash __attribute__((swift_name("hash")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChainModel.Companion")))
@interface XNetworkingChainModelCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingChainModelCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChainResponse")))
@interface XNetworkingChainResponse : XNetworkingBase
- (instancetype)initWithChain:(NSString *)chain hash:(NSString *)hash id:(NSString *)id __attribute__((swift_name("init(chain:hash:id:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingChainResponseCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingChainResponse *)doCopyChain:(NSString *)chain hash:(NSString *)hash id:(NSString *)id __attribute__((swift_name("doCopy(chain:hash:id:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *chain __attribute__((swift_name("chain")));
@property (readonly, getter=hash_) NSString *hash __attribute__((swift_name("hash")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChainResponse.Companion")))
@interface XNetworkingChainResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingChainResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FearlessChainsBuilder")))
@interface XNetworkingFearlessChainsBuilder : XNetworkingBase
- (instancetype)initWithNetworkClient:(XNetworkingSoramitsuNetworkClient *)networkClient baseUrl:(NSString *)baseUrl indexFile:(NSString *)indexFile __attribute__((swift_name("init(networkClient:baseUrl:indexFile:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of SoramitsuNetworkException, ChainBuilderException, CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getChainsVersion:(NSString *)version existedChains:(NSArray<XNetworkingKotlinPair<NSString *, NSString *> *> *)existedChains completionHandler:(void (^)(XNetworkingResultChainInfo * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getChains(version:existedChains:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ResultChainInfo")))
@interface XNetworkingResultChainInfo : XNetworkingBase
- (instancetype)initWithNewChains:(NSArray<XNetworkingChainModel *> *)newChains updatedChains:(NSArray<XNetworkingChainModel *> *)updatedChains removedChains:(NSArray<NSString *> *)removedChains __attribute__((swift_name("init(newChains:updatedChains:removedChains:)"))) __attribute__((objc_designated_initializer));
- (NSArray<XNetworkingChainModel *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<XNetworkingChainModel *> *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<NSString *> *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingResultChainInfo *)doCopyNewChains:(NSArray<XNetworkingChainModel *> *)newChains updatedChains:(NSArray<XNetworkingChainModel *> *)updatedChains removedChains:(NSArray<NSString *> *)removedChains __attribute__((swift_name("doCopy(newChains:updatedChains:removedChains:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly, getter=doNewChains) NSArray<XNetworkingChainModel *> *newChains __attribute__((swift_name("newChains")));
@property (readonly) NSArray<NSString *> *removedChains __attribute__((swift_name("removedChains")));
@property (readonly) NSArray<XNetworkingChainModel *> *updatedChains __attribute__((swift_name("updatedChains")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VersionNotFoundException")))
@interface XNetworkingVersionNotFoundException : XNetworkingChainBuilderException
- (instancetype)initWithMessage:(NSString *)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("SoramitsuNetworkException")))
@interface XNetworkingSoramitsuNetworkException : XNetworkingKotlinThrowable
- (instancetype)initWithM:(NSString *)m c:(XNetworkingKotlinThrowable * _Nullable)c __attribute__((swift_name("init(m:c:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CodeNetworkException")))
@interface XNetworkingCodeNetworkException : XNetworkingSoramitsuNetworkException
- (instancetype)initWithCode:(int32_t)code m:(NSString *)m c:(XNetworkingKotlinThrowable * _Nullable)c __attribute__((swift_name("init(code:m:c:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithM:(NSString *)m c:(XNetworkingKotlinThrowable * _Nullable)c __attribute__((swift_name("init(m:c:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (readonly) int32_t code __attribute__((swift_name("code")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GeneralNetworkException")))
@interface XNetworkingGeneralNetworkException : XNetworkingSoramitsuNetworkException
- (instancetype)initWithM:(NSString *)m c:(XNetworkingKotlinThrowable * _Nullable)c __attribute__((swift_name("init(m:c:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("HttpEngineFactory")))
@interface XNetworkingHttpEngineFactory : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<XNetworkingKtor_client_coreHttpClientEngineFactory>)createEngine __attribute__((swift_name("createEngine()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SerializationNetworkException")))
@interface XNetworkingSerializationNetworkException : XNetworkingSoramitsuNetworkException
- (instancetype)initWithM:(NSString *)m c:(XNetworkingKotlinThrowable * _Nullable)c __attribute__((swift_name("init(m:c:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("SoramitsuHttpClientProvider")))
@protocol XNetworkingSoramitsuHttpClientProvider
@required
- (XNetworkingKtor_client_coreHttpClient *)provideLogging:(BOOL)logging timeout:(int64_t)timeout json:(XNetworkingKotlinx_serialization_jsonJson *)json __attribute__((swift_name("provide(logging:timeout:json:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoramitsuHttpClientProviderImpl")))
@interface XNetworkingSoramitsuHttpClientProviderImpl : XNetworkingBase <XNetworkingSoramitsuHttpClientProvider>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (XNetworkingKtor_client_coreHttpClient *)provideLogging:(BOOL)logging timeout:(int64_t)timeout json:(XNetworkingKotlinx_serialization_jsonJson *)json __attribute__((swift_name("provide(logging:timeout:json:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoramitsuNetworkClient")))
@interface XNetworkingSoramitsuNetworkClient : XNetworkingBase
- (instancetype)initWithTimeout:(int64_t)timeout logging:(BOOL)logging provider:(id<XNetworkingSoramitsuHttpClientProvider>)provider __attribute__((swift_name("init(timeout:logging:provider:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createJsonRequestPath:(NSString *)path methodType:(XNetworkingKtor_httpHttpMethod *)methodType body:(id)body headers:(NSArray<XNetworkingKotlinPair<NSString *, NSString *> *> * _Nullable)headers completionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createJsonRequest(path:methodType:body:headers:completionHandler:)")));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRequestPath:(NSString *)path methodType:(XNetworkingKtor_httpHttpMethod *)methodType body:(id)body contentType:(XNetworkingKtor_httpContentType * _Nullable)contentType headersList:(NSArray<XNetworkingKotlinPair<NSString *, NSString *> *> * _Nullable)headersList completionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRequest(path:methodType:body:contentType:headersList:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUrl:(NSString *)url completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("get(url:completionHandler:)")));

/**
 @note This method converts instances of SoramitsuNetworkException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (id _Nullable)wrapInExceptionHandlerAndReturnError:(NSError * _Nullable * _Nullable)error block:(id (^)(void))block __attribute__((swift_name("wrapInExceptionHandler(block:)")));
@property (readonly) XNetworkingKtor_client_coreHttpClient *httpClient __attribute__((swift_name("httpClient")));
@property (readonly) XNetworkingKotlinx_serialization_jsonJson *json __attribute__((swift_name("json")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DatabaseDriverFactory")))
@interface XNetworkingDatabaseDriverFactory : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<XNetworkingRuntimeSqlDriver>)createDriver __attribute__((swift_name("createDriver()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Cryp")))
@interface XNetworkingCryp : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<XNetworkingKotlinx_coroutines_coreFlow>)doFlow __attribute__((swift_name("doFlow()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NodeInfo")))
@interface XNetworkingNodeInfo : XNetworkingBase
- (instancetype)initWithChain:(NSString *)chain name:(NSString *)name address:(NSString *)address __attribute__((swift_name("init(chain:name:address:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingNodeInfoCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingNodeInfo *)doCopyChain:(NSString *)chain name:(NSString *)name address:(NSString *)address __attribute__((swift_name("doCopy(chain:name:address:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *address __attribute__((swift_name("address")));
@property (readonly) NSString *chain __attribute__((swift_name("chain")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NodeInfo.Companion")))
@interface XNetworkingNodeInfoCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingNodeInfoCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraEnv")))
@interface XNetworkingSoraEnv : XNetworkingBase
- (instancetype)initWithNodes:(NSArray<XNetworkingNodeInfo *> *)nodes __attribute__((swift_name("init(nodes:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraEnvCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingNodeInfo *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraEnv *)doCopyNodes:(NSArray<XNetworkingNodeInfo *> *)nodes __attribute__((swift_name("doCopy(nodes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingNodeInfo *> *nodes __attribute__((swift_name("nodes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraEnv.Companion")))
@interface XNetworkingSoraEnvCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraEnvCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraEnvBuilder")))
@interface XNetworkingSoraEnvBuilder : XNetworkingBase
- (instancetype)initWithClient:(XNetworkingSoramitsuNetworkClient *)client baseUrl:(NSString *)baseUrl __attribute__((swift_name("init(client:baseUrl:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getSoraEnvWithCompletionHandler:(void (^)(XNetworkingSoraEnv * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getSoraEnv(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletBlockExplorerInfo")))
@interface XNetworkingSoraWalletBlockExplorerInfo : XNetworkingBase
- (instancetype)initWithNetworkClient:(XNetworkingSoramitsuNetworkClient *)networkClient baseUrl:(NSString *)baseUrl __attribute__((swift_name("init(networkClient:baseUrl:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException, IllegalArgumentException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getReferrerRewardsAddress:(NSString *)address caseName:(NSString *)caseName url:(NSString *)url completionHandler:(void (^)(XNetworkingReferrerRewardsInfo * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getReferrerRewards(address:caseName:url:completionHandler:)")));

/**
 @note This method converts instances of SoramitsuNetworkException, CancellationException, IllegalArgumentException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getSpApyUrl:(NSString *)url caseName:(NSString *)caseName completionHandler:(void (^)(NSArray<XNetworkingSbApyInfo *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getSpApy(url:caseName:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReferrerReward")))
@interface XNetworkingReferrerReward : XNetworkingBase
- (instancetype)initWithReferral:(NSString *)referral amount:(NSString *)amount __attribute__((swift_name("init(referral:amount:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingReferrerReward *)doCopyReferral:(NSString *)referral amount:(NSString *)amount __attribute__((swift_name("doCopy(referral:amount:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *amount __attribute__((swift_name("amount")));
@property (readonly) NSString *referral __attribute__((swift_name("referral")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReferrerRewardsInfo")))
@interface XNetworkingReferrerRewardsInfo : XNetworkingBase
- (instancetype)initWithRewards:(NSArray<XNetworkingReferrerReward *> *)rewards __attribute__((swift_name("init(rewards:)"))) __attribute__((objc_designated_initializer));
- (NSArray<XNetworkingReferrerReward *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingReferrerRewardsInfo *)doCopyRewards:(NSArray<XNetworkingReferrerReward *> *)rewards __attribute__((swift_name("doCopy(rewards:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingReferrerReward *> *rewards __attribute__((swift_name("rewards")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0Response")))
@interface XNetworkingSoraWalletReferrerCase0Response : XNetworkingBase
- (instancetype)initWithData:(XNetworkingSoraWalletReferrerCase0ResponseData *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase0ResponseCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletReferrerCase0ResponseData *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase0Response *)doCopyData:(XNetworkingSoraWalletReferrerCase0ResponseData *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletReferrerCase0ResponseData *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0Response.Companion")))
@interface XNetworkingSoraWalletReferrerCase0ResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase0ResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseData")))
@interface XNetworkingSoraWalletReferrerCase0ResponseData : XNetworkingBase
- (instancetype)initWithReferrerRewards:(XNetworkingSoraWalletReferrerCase0ResponseDataRewards *)referrerRewards __attribute__((swift_name("init(referrerRewards:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase0ResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletReferrerCase0ResponseDataRewards *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase0ResponseData *)doCopyReferrerRewards:(XNetworkingSoraWalletReferrerCase0ResponseDataRewards *)referrerRewards __attribute__((swift_name("doCopy(referrerRewards:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletReferrerCase0ResponseDataRewards *referrerRewards __attribute__((swift_name("referrerRewards")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseData.Companion")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase0ResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseDataRewards")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataRewards : XNetworkingBase
- (instancetype)initWithGroupedAggregates:(NSArray<XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup *> *)groupedAggregates __attribute__((swift_name("init(groupedAggregates:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase0ResponseDataRewards *)doCopyGroupedAggregates:(NSArray<XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup *> *)groupedAggregates __attribute__((swift_name("doCopy(groupedAggregates:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup *> *groupedAggregates __attribute__((swift_name("groupedAggregates")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseDataRewards.Companion")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataRewardsCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseDataRewardsGroup")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup : XNetworkingBase
- (instancetype)initWithKeys:(NSArray<NSString *> *)keys sum:(XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum *)sum __attribute__((swift_name("init(keys:sum:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<NSString *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroup *)doCopyKeys:(NSArray<NSString *> *)keys sum:(XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum *)sum __attribute__((swift_name("doCopy(keys:sum:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<NSString *> *keys __attribute__((swift_name("keys")));
@property (readonly) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum *sum __attribute__((swift_name("sum")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseDataRewardsGroup.Companion")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseDataRewardsGroupSum")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum : XNetworkingBase
- (instancetype)initWithAmount:(NSString *)amount __attribute__((swift_name("init(amount:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSumCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSum *)doCopyAmount:(NSString *)amount __attribute__((swift_name("doCopy(amount:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *amount __attribute__((swift_name("amount")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase0ResponseDataRewardsGroupSum.Companion")))
@interface XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSumCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase0ResponseDataRewardsGroupSumCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1Response")))
@interface XNetworkingSoraWalletReferrerCase1Response : XNetworkingBase
- (instancetype)initWithData:(XNetworkingSoraWalletReferrerCase1ResponseData *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase1ResponseCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletReferrerCase1ResponseData *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase1Response *)doCopyData:(XNetworkingSoraWalletReferrerCase1ResponseData *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletReferrerCase1ResponseData *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1Response.Companion")))
@interface XNetworkingSoraWalletReferrerCase1ResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase1ResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseData")))
@interface XNetworkingSoraWalletReferrerCase1ResponseData : XNetworkingBase
- (instancetype)initWithReferrerRewards:(XNetworkingSoraWalletReferrerCase1ResponseDataRewards *)referrerRewards __attribute__((swift_name("init(referrerRewards:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase1ResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletReferrerCase1ResponseDataRewards *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase1ResponseData *)doCopyReferrerRewards:(XNetworkingSoraWalletReferrerCase1ResponseDataRewards *)referrerRewards __attribute__((swift_name("doCopy(referrerRewards:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletReferrerCase1ResponseDataRewards *referrerRewards __attribute__((swift_name("referrerRewards")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseData.Companion")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase1ResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseDataRewards")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataRewards : XNetworkingBase
- (instancetype)initWithPageInfo:(XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo *)pageInfo nodes:(NSArray<XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode *> *)nodes __attribute__((swift_name("init(pageInfo:nodes:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSArray<XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode *> *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase1ResponseDataRewards *)doCopyPageInfo:(XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo *)pageInfo nodes:(NSArray<XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode *> *)nodes __attribute__((swift_name("doCopy(pageInfo:nodes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode *> *nodes __attribute__((swift_name("nodes")));
@property (readonly) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo *pageInfo __attribute__((swift_name("pageInfo")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseDataRewards.Companion")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataRewardsCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseDataRewardsNode")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode : XNetworkingBase
- (instancetype)initWithReferral:(NSString *)referral amount:(NSString *)amount __attribute__((swift_name("init(referral:amount:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNodeCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNode *)doCopyReferral:(NSString *)referral amount:(NSString *)amount __attribute__((swift_name("doCopy(referral:amount:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *amount __attribute__((swift_name("amount")));
@property (readonly) NSString *referral __attribute__((swift_name("referral")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseDataRewardsNode.Companion")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNodeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsNodeCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseDataRewardsPageInfo")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo : XNetworkingBase
- (instancetype)initWithHasNextPage:(BOOL)hasNextPage endCursor:(NSString * _Nullable)endCursor __attribute__((swift_name("init(hasNextPage:endCursor:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfoCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfo *)doCopyHasNextPage:(BOOL)hasNextPage endCursor:(NSString * _Nullable)endCursor __attribute__((swift_name("doCopy(hasNextPage:endCursor:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable endCursor __attribute__((swift_name("endCursor")));
@property (readonly) BOOL hasNextPage __attribute__((swift_name("hasNextPage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletReferrerCase1ResponseDataRewardsPageInfo.Companion")))
@interface XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfoCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletReferrerCase1ResponseDataRewardsPageInfoCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SbApyInfo")))
@interface XNetworkingSbApyInfo : XNetworkingBase
- (instancetype)initWithTokenId:(NSString *)tokenId priceUsd:(XNetworkingDouble * _Nullable)priceUsd sbApy:(XNetworkingDouble * _Nullable)sbApy __attribute__((swift_name("init(tokenId:priceUsd:sbApy:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingDouble * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingDouble * _Nullable)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSbApyInfo *)doCopyTokenId:(NSString *)tokenId priceUsd:(XNetworkingDouble * _Nullable)priceUsd sbApy:(XNetworkingDouble * _Nullable)sbApy __attribute__((swift_name("doCopy(tokenId:priceUsd:sbApy:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingDouble * _Nullable priceUsd __attribute__((swift_name("priceUsd")));
@property (readonly) XNetworkingDouble * _Nullable sbApy __attribute__((swift_name("sbApy")));
@property (readonly) NSString *tokenId __attribute__((swift_name("tokenId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0Response")))
@interface XNetworkingSoraWalletSbApyCase0Response : XNetworkingBase
- (instancetype)initWithData:(XNetworkingSoraWalletSbApyCase0ResponseData *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletSbApyCase0ResponseData *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0Response *)doCopyData:(XNetworkingSoraWalletSbApyCase0ResponseData *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletSbApyCase0ResponseData *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0Response.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseData")))
@interface XNetworkingSoraWalletSbApyCase0ResponseData : XNetworkingBase
- (instancetype)initWithPoolXYKEntities:(XNetworkingSoraWalletSbApyCase0ResponseDataEntities *)poolXYKEntities __attribute__((swift_name("init(poolXYKEntities:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntities *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0ResponseData *)doCopyPoolXYKEntities:(XNetworkingSoraWalletSbApyCase0ResponseDataEntities *)poolXYKEntities __attribute__((swift_name("doCopy(poolXYKEntities:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletSbApyCase0ResponseDataEntities *poolXYKEntities __attribute__((swift_name("poolXYKEntities")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseData.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntities")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntities : XNetworkingBase
- (instancetype)initWithNodes:(NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode *> *)nodes __attribute__((swift_name("init(nodes:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntities *)doCopyNodes:(NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode *> *)nodes __attribute__((swift_name("doCopy(nodes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode *> *nodes __attribute__((swift_name("nodes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntities.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNode")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode : XNetworkingBase
- (instancetype)initWithPools:(XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools *)pools __attribute__((swift_name("init(pools:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodeCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNode *)doCopyPools:(XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools *)pools __attribute__((swift_name("doCopy(pools:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools *pools __attribute__((swift_name("pools")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNode.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodeCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNodesPools")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools : XNetworkingBase
- (instancetype)initWithEdges:(NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge *> *)edges __attribute__((swift_name("init(edges:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPools *)doCopyEdges:(NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge *> *)edges __attribute__((swift_name("doCopy(edges:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge *> *edges __attribute__((swift_name("edges")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNodesPools.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge : XNetworkingBase
- (instancetype)initWithNode:(XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode *)node __attribute__((swift_name("init(node:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge *)doCopyNode:(XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode *)node __attribute__((swift_name("doCopy(node:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode *node __attribute__((swift_name("node")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdge.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode : XNetworkingBase
- (instancetype)initWithTargetAssetId:(NSString *)targetAssetId priceUSD:(NSString * _Nullable)priceUSD strategicBonusApy:(NSString * _Nullable)strategicBonusApy __attribute__((swift_name("init(targetAssetId:priceUSD:strategicBonusApy:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNodeCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode *)doCopyTargetAssetId:(NSString *)targetAssetId priceUSD:(NSString * _Nullable)priceUSD strategicBonusApy:(NSString * _Nullable)strategicBonusApy __attribute__((swift_name("doCopy(targetAssetId:priceUSD:strategicBonusApy:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable priceUSD __attribute__((swift_name("priceUSD")));
@property (readonly) NSString * _Nullable strategicBonusApy __attribute__((swift_name("strategicBonusApy")));
@property (readonly) NSString *targetAssetId __attribute__((swift_name("targetAssetId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNode.Companion")))
@interface XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNodeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase0ResponseDataEntitiesNodesPoolsEdgeNodeCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1Response")))
@interface XNetworkingSoraWalletSbApyCase1Response : XNetworkingBase
- (instancetype)initWithData:(XNetworkingSoraWalletSbApyCase1ResponseData *)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase1ResponseCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletSbApyCase1ResponseData *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase1Response *)doCopyData:(XNetworkingSoraWalletSbApyCase1ResponseData *)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletSbApyCase1ResponseData *data __attribute__((swift_name("data")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1Response.Companion")))
@interface XNetworkingSoraWalletSbApyCase1ResponseCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase1ResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1ResponseData")))
@interface XNetworkingSoraWalletSbApyCase1ResponseData : XNetworkingBase
- (instancetype)initWithPoolXYKs:(XNetworkingSoraWalletSbApyCase1ResponseDataEntities *)poolXYKs __attribute__((swift_name("init(poolXYKs:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase1ResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingSoraWalletSbApyCase1ResponseDataEntities *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase1ResponseData *)doCopyPoolXYKs:(XNetworkingSoraWalletSbApyCase1ResponseDataEntities *)poolXYKs __attribute__((swift_name("doCopy(poolXYKs:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingSoraWalletSbApyCase1ResponseDataEntities *poolXYKs __attribute__((swift_name("poolXYKs")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1ResponseData.Companion")))
@interface XNetworkingSoraWalletSbApyCase1ResponseDataCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase1ResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1ResponseDataEntities")))
@interface XNetworkingSoraWalletSbApyCase1ResponseDataEntities : XNetworkingBase
- (instancetype)initWithNodes:(NSArray<XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode *> *)nodes __attribute__((swift_name("init(nodes:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesCompanion *companion __attribute__((swift_name("companion")));
- (NSArray<XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode *> *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase1ResponseDataEntities *)doCopyNodes:(NSArray<XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode *> *)nodes __attribute__((swift_name("doCopy(nodes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode *> *nodes __attribute__((swift_name("nodes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1ResponseDataEntities.Companion")))
@interface XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1ResponseDataEntitiesNode")))
@interface XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode : XNetworkingBase
- (instancetype)initWithId:(NSString *)id priceUSD:(NSString * _Nullable)priceUSD strategicBonusApy:(NSString * _Nullable)strategicBonusApy __attribute__((swift_name("init(id:priceUSD:strategicBonusApy:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNodeCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNode *)doCopyId:(NSString *)id priceUSD:(NSString * _Nullable)priceUSD strategicBonusApy:(NSString * _Nullable)strategicBonusApy __attribute__((swift_name("doCopy(id:priceUSD:strategicBonusApy:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable priceUSD __attribute__((swift_name("priceUSD")));
@property (readonly) NSString * _Nullable strategicBonusApy __attribute__((swift_name("strategicBonusApy")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoraWalletSbApyCase1ResponseDataEntitiesNode.Companion")))
@interface XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNodeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingSoraWalletSbApyCase1ResponseDataEntitiesNodeCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SoramitsuNetworkClientKt")))
@interface XNetworkingSoramitsuNetworkClientKt : XNetworkingBase
+ (id<XNetworkingKtor_httpHeaders>)plus:(id<XNetworkingKtor_httpHeaders>)receiver other:(id<XNetworkingKtor_httpHeaders>)other __attribute__((swift_name("plus(_:other:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TbdActualInfoIosKt")))
@interface XNetworkingTbdActualInfoIosKt : XNetworkingBase
+ (NSString *)shaA:(XNetworkingKotlinByteArray *)a __attribute__((swift_name("sha(a:)")));
@end;

__attribute__((swift_name("KotlinException")))
@interface XNetworkingKotlinException : XNetworkingKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinRuntimeException")))
@interface XNetworkingKotlinRuntimeException : XNetworkingKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinIllegalStateException")))
@interface XNetworkingKotlinIllegalStateException : XNetworkingKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinCancellationException")))
@interface XNetworkingKotlinCancellationException : XNetworkingKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinIllegalArgumentException")))
@interface XNetworkingKotlinIllegalArgumentException : XNetworkingKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol XNetworkingKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<XNetworkingKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<XNetworkingKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol XNetworkingKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<XNetworkingKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<XNetworkingKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol XNetworkingKotlinx_serialization_coreKSerializer <XNetworkingKotlinx_serialization_coreSerializationStrategy, XNetworkingKotlinx_serialization_coreDeserializationStrategy>
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface XNetworkingKotlinx_serialization_jsonJsonElement : XNetworkingBase
@property (class, readonly, getter=companion) XNetworkingKotlinx_serialization_jsonJsonElementCompanion *companion __attribute__((swift_name("companion")));
@end;

__attribute__((swift_name("RuntimeTransactionCallbacks")))
@protocol XNetworkingRuntimeTransactionCallbacks
@required
- (void)afterCommitFunction:(void (^)(void))function __attribute__((swift_name("afterCommit(function:)")));
- (void)afterRollbackFunction:(void (^)(void))function __attribute__((swift_name("afterRollback(function:)")));
@end;

__attribute__((swift_name("RuntimeTransactionWithoutReturn")))
@protocol XNetworkingRuntimeTransactionWithoutReturn <XNetworkingRuntimeTransactionCallbacks>
@required
- (void)rollback __attribute__((swift_name("rollback()")));
- (void)transactionBody:(void (^)(id<XNetworkingRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(body:)")));
@end;

__attribute__((swift_name("RuntimeTransactionWithReturn")))
@protocol XNetworkingRuntimeTransactionWithReturn <XNetworkingRuntimeTransactionCallbacks>
@required
- (void)rollbackReturnValue:(id _Nullable)returnValue __attribute__((swift_name("rollback(returnValue:)")));
- (id _Nullable)transactionBody_:(id _Nullable (^)(id<XNetworkingRuntimeTransactionWithReturn>))body __attribute__((swift_name("transaction(body_:)")));
@end;

__attribute__((swift_name("RuntimeCloseable")))
@protocol XNetworkingRuntimeCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end;

__attribute__((swift_name("RuntimeSqlDriver")))
@protocol XNetworkingRuntimeSqlDriver <XNetworkingRuntimeCloseable>
@required
- (XNetworkingRuntimeTransacterTransaction * _Nullable)currentTransaction __attribute__((swift_name("currentTransaction()")));
- (void)executeIdentifier:(XNetworkingInt * _Nullable)identifier sql:(NSString *)sql parameters:(int32_t)parameters binders:(void (^ _Nullable)(id<XNetworkingRuntimeSqlPreparedStatement>))binders __attribute__((swift_name("execute(identifier:sql:parameters:binders:)")));
- (id<XNetworkingRuntimeSqlCursor>)executeQueryIdentifier:(XNetworkingInt * _Nullable)identifier sql:(NSString *)sql parameters:(int32_t)parameters binders:(void (^ _Nullable)(id<XNetworkingRuntimeSqlPreparedStatement>))binders __attribute__((swift_name("executeQuery(identifier:sql:parameters:binders:)")));
- (XNetworkingRuntimeTransacterTransaction *)doNewTransaction __attribute__((swift_name("doNewTransaction()")));
@end;

__attribute__((swift_name("RuntimeSqlDriverSchema")))
@protocol XNetworkingRuntimeSqlDriverSchema
@required
- (void)createDriver:(id<XNetworkingRuntimeSqlDriver>)driver __attribute__((swift_name("create(driver:)")));
- (void)migrateDriver:(id<XNetworkingRuntimeSqlDriver>)driver oldVersion:(int32_t)oldVersion newVersion:(int32_t)newVersion __attribute__((swift_name("migrate(driver:oldVersion:newVersion:)")));
@property (readonly) int32_t version __attribute__((swift_name("version")));
@end;

__attribute__((swift_name("RuntimeQuery")))
@interface XNetworkingRuntimeQuery<__covariant RowType> : XNetworkingBase
- (instancetype)initWithQueries:(NSMutableArray<XNetworkingRuntimeQuery<id> *> *)queries mapper:(RowType (^)(id<XNetworkingRuntimeSqlCursor>))mapper __attribute__((swift_name("init(queries:mapper:)"))) __attribute__((objc_designated_initializer));
- (void)addListenerListener:(id<XNetworkingRuntimeQueryListener>)listener __attribute__((swift_name("addListener(listener:)")));
- (id<XNetworkingRuntimeSqlCursor>)execute __attribute__((swift_name("execute()")));
- (NSArray<RowType> *)executeAsList __attribute__((swift_name("executeAsList()")));
- (RowType)executeAsOne __attribute__((swift_name("executeAsOne()")));
- (RowType _Nullable)executeAsOneOrNull __attribute__((swift_name("executeAsOneOrNull()")));
- (void)notifyDataChanged __attribute__((swift_name("notifyDataChanged()")));
- (void)removeListenerListener:(id<XNetworkingRuntimeQueryListener>)listener __attribute__((swift_name("removeListener(listener:)")));
@property (readonly) RowType (^mapper)(id<XNetworkingRuntimeSqlCursor>) __attribute__((swift_name("mapper")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface XNetworkingKotlinArray<T> : XNetworkingBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(XNetworkingInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<XNetworkingKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface XNetworkingKotlinPair<__covariant A, __covariant B> : XNetworkingBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (A _Nullable)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (B _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKotlinPair<A, B> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpClientEngineFactory")))
@protocol XNetworkingKtor_client_coreHttpClientEngineFactory
@required
- (id<XNetworkingKtor_client_coreHttpClientEngine>)createBlock:(void (^)(XNetworkingKtor_client_coreHttpClientEngineConfig *))block __attribute__((swift_name("create(block:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineScope")))
@protocol XNetworkingKotlinx_coroutines_coreCoroutineScope
@required
@property (readonly) id<XNetworkingKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end;

__attribute__((swift_name("Ktor_ioCloseable")))
@protocol XNetworkingKtor_ioCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpClient")))
@interface XNetworkingKtor_client_coreHttpClient : XNetworkingBase <XNetworkingKotlinx_coroutines_coreCoroutineScope, XNetworkingKtor_ioCloseable>
- (instancetype)initWithEngine:(id<XNetworkingKtor_client_coreHttpClientEngine>)engine userConfig:(XNetworkingKtor_client_coreHttpClientConfig<XNetworkingKtor_client_coreHttpClientEngineConfig *> *)userConfig __attribute__((swift_name("init(engine:userConfig:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (XNetworkingKtor_client_coreHttpClient *)configBlock:(void (^)(XNetworkingKtor_client_coreHttpClientConfig<id> *))block __attribute__((swift_name("config(block:)")));
- (BOOL)isSupportedCapability:(id<XNetworkingKtor_client_coreHttpClientEngineCapability>)capability __attribute__((swift_name("isSupported(capability:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<XNetworkingKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) id<XNetworkingKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property (readonly) id<XNetworkingKtor_client_coreHttpClientEngine> engine __attribute__((swift_name("engine")));
@property (readonly) XNetworkingKtor_client_coreHttpClientEngineConfig *engineConfig __attribute__((swift_name("engineConfig")));
@property (readonly) XNetworkingKtor_eventsEvents *monitor __attribute__((swift_name("monitor")));
@property (readonly) XNetworkingKtor_client_coreHttpReceivePipeline *receivePipeline __attribute__((swift_name("receivePipeline")));
@property (readonly) XNetworkingKtor_client_coreHttpRequestPipeline *requestPipeline __attribute__((swift_name("requestPipeline")));
@property (readonly) XNetworkingKtor_client_coreHttpResponsePipeline *responsePipeline __attribute__((swift_name("responsePipeline")));
@property (readonly) XNetworkingKtor_client_coreHttpSendPipeline *sendPipeline __attribute__((swift_name("sendPipeline")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialFormat")))
@protocol XNetworkingKotlinx_serialization_coreSerialFormat
@required
@property (readonly) XNetworkingKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreStringFormat")))
@protocol XNetworkingKotlinx_serialization_coreStringFormat <XNetworkingKotlinx_serialization_coreSerialFormat>
@required
- (id _Nullable)decodeFromStringDeserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (NSString *)encodeToStringSerializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_jsonJson")))
@interface XNetworkingKotlinx_serialization_jsonJson : XNetworkingBase <XNetworkingKotlinx_serialization_coreStringFormat>
@property (class, readonly, getter=companion) XNetworkingKotlinx_serialization_jsonJsonDefault *companion __attribute__((swift_name("companion")));
- (id _Nullable)decodeFromJsonElementDeserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer element:(XNetworkingKotlinx_serialization_jsonJsonElement *)element __attribute__((swift_name("decodeFromJsonElement(deserializer:element:)")));
- (id _Nullable)decodeFromStringDeserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (XNetworkingKotlinx_serialization_jsonJsonElement *)encodeToJsonElementSerializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToJsonElement(serializer:value:)")));
- (NSString *)encodeToStringSerializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
- (XNetworkingKotlinx_serialization_jsonJsonElement *)parseToJsonElementString:(NSString *)string __attribute__((swift_name("parseToJsonElement(string:)")));
@property (readonly) XNetworkingKotlinx_serialization_jsonJsonConfiguration *configuration __attribute__((swift_name("configuration")));
@property (readonly) XNetworkingKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpMethod")))
@interface XNetworkingKtor_httpHttpMethod : XNetworkingBase
- (instancetype)initWithValue:(NSString *)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_httpHttpMethodCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_httpHttpMethod *)doCopyValue:(NSString *)value __attribute__((swift_name("doCopy(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("Ktor_httpHeaderValueWithParameters")))
@interface XNetworkingKtor_httpHeaderValueWithParameters : XNetworkingBase
- (instancetype)initWithContent:(NSString *)content parameters:(NSArray<XNetworkingKtor_httpHeaderValueParam *> *)parameters __attribute__((swift_name("init(content:parameters:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_httpHeaderValueWithParametersCompanion *companion __attribute__((swift_name("companion")));
- (NSString * _Nullable)parameterName:(NSString *)name __attribute__((swift_name("parameter(name:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *content __attribute__((swift_name("content")));
@property (readonly) NSArray<XNetworkingKtor_httpHeaderValueParam *> *parameters __attribute__((swift_name("parameters")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpContentType")))
@interface XNetworkingKtor_httpContentType : XNetworkingKtor_httpHeaderValueWithParameters
- (instancetype)initWithContentType:(NSString *)contentType contentSubtype:(NSString *)contentSubtype parameters:(NSArray<XNetworkingKtor_httpHeaderValueParam *> *)parameters __attribute__((swift_name("init(contentType:contentSubtype:parameters:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithContent:(NSString *)content parameters:(NSArray<XNetworkingKtor_httpHeaderValueParam *> *)parameters __attribute__((swift_name("init(content:parameters:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_httpContentTypeCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)matchPattern:(XNetworkingKtor_httpContentType *)pattern __attribute__((swift_name("match(pattern:)")));
- (BOOL)matchPattern_:(NSString *)pattern __attribute__((swift_name("match(pattern_:)")));
- (XNetworkingKtor_httpContentType *)withParameterName:(NSString *)name value:(NSString *)value __attribute__((swift_name("withParameter(name:value:)")));
- (XNetworkingKtor_httpContentType *)withoutParameters __attribute__((swift_name("withoutParameters()")));
@property (readonly) NSString *contentSubtype __attribute__((swift_name("contentSubtype")));
@property (readonly) NSString *contentType __attribute__((swift_name("contentType")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol XNetworkingKotlinx_coroutines_coreFlow
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<XNetworkingKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end;

__attribute__((swift_name("Ktor_utilsStringValues")))
@protocol XNetworkingKtor_utilsStringValues
@required
- (BOOL)containsName:(NSString *)name __attribute__((swift_name("contains(name:)")));
- (BOOL)containsName:(NSString *)name value:(NSString *)value __attribute__((swift_name("contains(name:value:)")));
- (NSSet<id<XNetworkingKotlinMapEntry>> *)entries __attribute__((swift_name("entries()")));
- (void)forEachBody:(void (^)(NSString *, NSArray<NSString *> *))body __attribute__((swift_name("forEach(body:)")));
- (NSString * _Nullable)getName:(NSString *)name __attribute__((swift_name("get(name:)")));
- (NSArray<NSString *> * _Nullable)getAllName:(NSString *)name __attribute__((swift_name("getAll(name:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (NSSet<NSString *> *)names __attribute__((swift_name("names()")));
@property (readonly) BOOL caseInsensitiveName __attribute__((swift_name("caseInsensitiveName")));
@end;

__attribute__((swift_name("Ktor_httpHeaders")))
@protocol XNetworkingKtor_httpHeaders <XNetworkingKtor_utilsStringValues>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface XNetworkingKotlinByteArray : XNetworkingBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(XNetworkingByte *(^)(XNetworkingInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (XNetworkingKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol XNetworkingKotlinx_serialization_coreDecoder
@required
- (id<XNetworkingKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<XNetworkingKotlinx_serialization_coreDecoder>)decodeInlineInlineDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)inlineDescriptor __attribute__((swift_name("decodeInline(inlineDescriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));
- (XNetworkingKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) XNetworkingKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol XNetworkingKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<XNetworkingKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<XNetworkingKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) XNetworkingKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol XNetworkingKotlinx_serialization_coreEncoder
@required
- (id<XNetworkingKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<XNetworkingKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<XNetworkingKotlinx_serialization_coreEncoder>)encodeInlineInlineDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)inlineDescriptor __attribute__((swift_name("encodeInline(inlineDescriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));
- (void)encodeNull __attribute__((swift_name("encodeNull()")));
- (void)encodeNullableSerializableValueSerializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) XNetworkingKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement.Companion")))
@interface XNetworkingKotlinx_serialization_jsonJsonElementCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKotlinx_serialization_jsonJsonElementCompanion *shared __attribute__((swift_name("shared")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("RuntimeTransacterTransaction")))
@interface XNetworkingRuntimeTransacterTransaction : XNetworkingBase <XNetworkingRuntimeTransactionCallbacks>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)afterCommitFunction:(void (^)(void))function __attribute__((swift_name("afterCommit(function:)")));
- (void)afterRollbackFunction:(void (^)(void))function __attribute__((swift_name("afterRollback(function:)")));
- (void)endTransactionSuccessful:(BOOL)successful __attribute__((swift_name("endTransaction(successful:)")));
@property (readonly) XNetworkingRuntimeTransacterTransaction * _Nullable enclosingTransaction __attribute__((swift_name("enclosingTransaction")));
@end;

__attribute__((swift_name("RuntimeSqlPreparedStatement")))
@protocol XNetworkingRuntimeSqlPreparedStatement
@required
- (void)bindBytesIndex:(int32_t)index bytes:(XNetworkingKotlinByteArray * _Nullable)bytes __attribute__((swift_name("bindBytes(index:bytes:)")));
- (void)bindDoubleIndex:(int32_t)index double:(XNetworkingDouble * _Nullable)double_ __attribute__((swift_name("bindDouble(index:double:)")));
- (void)bindLongIndex:(int32_t)index long:(XNetworkingLong * _Nullable)long_ __attribute__((swift_name("bindLong(index:long:)")));
- (void)bindStringIndex:(int32_t)index string:(NSString * _Nullable)string __attribute__((swift_name("bindString(index:string:)")));
@end;

__attribute__((swift_name("RuntimeSqlCursor")))
@protocol XNetworkingRuntimeSqlCursor <XNetworkingRuntimeCloseable>
@required
- (XNetworkingKotlinByteArray * _Nullable)getBytesIndex:(int32_t)index __attribute__((swift_name("getBytes(index:)")));
- (XNetworkingDouble * _Nullable)getDoubleIndex:(int32_t)index __attribute__((swift_name("getDouble(index:)")));
- (XNetworkingLong * _Nullable)getLongIndex:(int32_t)index __attribute__((swift_name("getLong(index:)")));
- (NSString * _Nullable)getStringIndex:(int32_t)index __attribute__((swift_name("getString(index:)")));
- (BOOL)next __attribute__((swift_name("next()")));
@end;

__attribute__((swift_name("RuntimeQueryListener")))
@protocol XNetworkingRuntimeQueryListener
@required
- (void)queryResultsChanged __attribute__((swift_name("queryResultsChanged()")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol XNetworkingKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next_ __attribute__((swift_name("next_()")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpClientEngine")))
@protocol XNetworkingKtor_client_coreHttpClientEngine <XNetworkingKotlinx_coroutines_coreCoroutineScope, XNetworkingKtor_ioCloseable>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeData:(XNetworkingKtor_client_coreHttpRequestData *)data completionHandler:(void (^)(XNetworkingKtor_client_coreHttpResponseData * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(data:completionHandler:)")));
- (void)installClient:(XNetworkingKtor_client_coreHttpClient *)client __attribute__((swift_name("install(client:)")));
@property (readonly) XNetworkingKtor_client_coreHttpClientEngineConfig *config __attribute__((swift_name("config")));
@property (readonly) XNetworkingKotlinx_coroutines_coreCoroutineDispatcher *dispatcher __attribute__((swift_name("dispatcher")));
@property (readonly) NSSet<id<XNetworkingKtor_client_coreHttpClientEngineCapability>> *supportedCapabilities __attribute__((swift_name("supportedCapabilities")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpClientEngineConfig")))
@interface XNetworkingKtor_client_coreHttpClientEngineConfig : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property BOOL pipelining __attribute__((swift_name("pipelining")));
@property XNetworkingKtor_client_coreProxyConfig * _Nullable proxy __attribute__((swift_name("proxy")));
@property int32_t threadsCount __attribute__((swift_name("threadsCount")));
@end;

__attribute__((swift_name("KotlinCoroutineContext")))
@protocol XNetworkingKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<XNetworkingKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<XNetworkingKotlinCoroutineContextElement> _Nullable)getKey:(id<XNetworkingKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<XNetworkingKotlinCoroutineContext>)minusKeyKey:(id<XNetworkingKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<XNetworkingKotlinCoroutineContext>)plusContext:(id<XNetworkingKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpClientConfig")))
@interface XNetworkingKtor_client_coreHttpClientConfig<T> : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (XNetworkingKtor_client_coreHttpClientConfig<T> *)clone __attribute__((swift_name("clone()")));
- (void)engineBlock:(void (^)(T))block __attribute__((swift_name("engine(block:)")));
- (void)installClient:(XNetworkingKtor_client_coreHttpClient *)client __attribute__((swift_name("install(client:)")));
- (void)installPlugin:(id<XNetworkingKtor_client_coreHttpClientPlugin>)plugin configure:(void (^)(id))configure __attribute__((swift_name("install(plugin:configure:)")));
- (void)installKey:(NSString *)key block:(void (^)(XNetworkingKtor_client_coreHttpClient *))block __attribute__((swift_name("install(key:block:)")));
- (void)plusAssignOther:(XNetworkingKtor_client_coreHttpClientConfig<T> *)other __attribute__((swift_name("plusAssign(other:)")));
@property BOOL developmentMode __attribute__((swift_name("developmentMode")));
@property BOOL expectSuccess __attribute__((swift_name("expectSuccess")));
@property BOOL followRedirects __attribute__((swift_name("followRedirects")));
@property BOOL useDefaultTransformers __attribute__((swift_name("useDefaultTransformers")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpClientEngineCapability")))
@protocol XNetworkingKtor_client_coreHttpClientEngineCapability
@required
@end;

__attribute__((swift_name("Ktor_utilsAttributes")))
@protocol XNetworkingKtor_utilsAttributes
@required
- (id)computeIfAbsentKey:(XNetworkingKtor_utilsAttributeKey<id> *)key block:(id (^)(void))block __attribute__((swift_name("computeIfAbsent(key:block:)")));
- (BOOL)containsKey:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("contains(key:)")));
- (id)getKey_:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("get(key_:)")));
- (id _Nullable)getOrNullKey:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("getOrNull(key:)")));
- (void)putKey:(XNetworkingKtor_utilsAttributeKey<id> *)key value:(id)value __attribute__((swift_name("put(key:value:)")));
- (void)removeKey:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("remove(key:)")));
- (id)takeKey:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("take(key:)")));
- (id _Nullable)takeOrNullKey:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("takeOrNull(key:)")));
@property (readonly) NSArray<XNetworkingKtor_utilsAttributeKey<id> *> *allKeys __attribute__((swift_name("allKeys")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_eventsEvents")))
@interface XNetworkingKtor_eventsEvents : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)raiseDefinition:(XNetworkingKtor_eventsEventDefinition<id> *)definition value:(id _Nullable)value __attribute__((swift_name("raise(definition:value:)")));
- (id<XNetworkingKotlinx_coroutines_coreDisposableHandle>)subscribeDefinition:(XNetworkingKtor_eventsEventDefinition<id> *)definition handler:(void (^)(id _Nullable))handler __attribute__((swift_name("subscribe(definition:handler:)")));
- (void)unsubscribeDefinition:(XNetworkingKtor_eventsEventDefinition<id> *)definition handler:(void (^)(id _Nullable))handler __attribute__((swift_name("unsubscribe(definition:handler:)")));
@end;

__attribute__((swift_name("Ktor_utilsPipeline")))
@interface XNetworkingKtor_utilsPipeline<TSubject, TContext> : XNetworkingBase
- (instancetype)initWithPhase:(XNetworkingKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<XNetworkingKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhases:(XNetworkingKotlinArray<XNetworkingKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer));
- (void)addPhasePhase:(XNetworkingKtor_utilsPipelinePhase *)phase __attribute__((swift_name("addPhase(phase:)")));
- (void)afterIntercepted __attribute__((swift_name("afterIntercepted()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeContext:(TContext)context subject:(TSubject)subject completionHandler:(void (^)(TSubject _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("execute(context:subject:completionHandler:)")));
- (void)insertPhaseAfterReference:(XNetworkingKtor_utilsPipelinePhase *)reference phase:(XNetworkingKtor_utilsPipelinePhase *)phase __attribute__((swift_name("insertPhaseAfter(reference:phase:)")));
- (void)insertPhaseBeforeReference:(XNetworkingKtor_utilsPipelinePhase *)reference phase:(XNetworkingKtor_utilsPipelinePhase *)phase __attribute__((swift_name("insertPhaseBefore(reference:phase:)")));
- (void)interceptPhase:(XNetworkingKtor_utilsPipelinePhase *)phase block:(id<XNetworkingKotlinSuspendFunction2>)block __attribute__((swift_name("intercept(phase:block:)")));
- (NSArray<id<XNetworkingKotlinSuspendFunction2>> *)interceptorsForPhasePhase:(XNetworkingKtor_utilsPipelinePhase *)phase __attribute__((swift_name("interceptorsForPhase(phase:)")));
- (void)mergeFrom:(XNetworkingKtor_utilsPipeline<TSubject, TContext> *)from __attribute__((swift_name("merge(from:)")));
- (void)mergePhasesFrom:(XNetworkingKtor_utilsPipeline<TSubject, TContext> *)from __attribute__((swift_name("mergePhases(from:)")));
- (void)resetFromFrom:(XNetworkingKtor_utilsPipeline<TSubject, TContext> *)from __attribute__((swift_name("resetFrom(from:)")));
@property (readonly) id<XNetworkingKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@property (readonly, getter=isEmpty_) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) NSArray<XNetworkingKtor_utilsPipelinePhase *> *items __attribute__((swift_name("items")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpReceivePipeline")))
@interface XNetworkingKtor_client_coreHttpReceivePipeline : XNetworkingKtor_utilsPipeline<XNetworkingKtor_client_coreHttpResponse *, XNetworkingKotlinUnit *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhase:(XNetworkingKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<XNetworkingKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhases:(XNetworkingKotlinArray<XNetworkingKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_client_coreHttpReceivePipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestPipeline")))
@interface XNetworkingKtor_client_coreHttpRequestPipeline : XNetworkingKtor_utilsPipeline<id, XNetworkingKtor_client_coreHttpRequestBuilder *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhase:(XNetworkingKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<XNetworkingKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhases:(XNetworkingKotlinArray<XNetworkingKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_client_coreHttpRequestPipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponsePipeline")))
@interface XNetworkingKtor_client_coreHttpResponsePipeline : XNetworkingKtor_utilsPipeline<XNetworkingKtor_client_coreHttpResponseContainer *, XNetworkingKtor_client_coreHttpClientCall *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhase:(XNetworkingKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<XNetworkingKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhases:(XNetworkingKotlinArray<XNetworkingKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_client_coreHttpResponsePipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpSendPipeline")))
@interface XNetworkingKtor_client_coreHttpSendPipeline : XNetworkingKtor_utilsPipeline<id, XNetworkingKtor_client_coreHttpRequestBuilder *>
- (instancetype)initWithDevelopmentMode:(BOOL)developmentMode __attribute__((swift_name("init(developmentMode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithPhase:(XNetworkingKtor_utilsPipelinePhase *)phase interceptors:(NSArray<id<XNetworkingKotlinSuspendFunction2>> *)interceptors __attribute__((swift_name("init(phase:interceptors:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithPhases:(XNetworkingKotlinArray<XNetworkingKtor_utilsPipelinePhase *> *)phases __attribute__((swift_name("init(phases:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_client_coreHttpSendPipelinePhases *companion __attribute__((swift_name("companion")));
@property (readonly) BOOL developmentMode __attribute__((swift_name("developmentMode")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface XNetworkingKotlinx_serialization_coreSerializersModule : XNetworkingBase
- (void)dumpToCollector:(id<XNetworkingKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));
- (id<XNetworkingKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<XNetworkingKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<XNetworkingKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));
- (id<XNetworkingKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<XNetworkingKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));
- (id<XNetworkingKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<XNetworkingKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJson.Default")))
@interface XNetworkingKotlinx_serialization_jsonJsonDefault : XNetworkingKotlinx_serialization_jsonJson
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)default_ __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKotlinx_serialization_jsonJsonDefault *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonConfiguration")))
@interface XNetworkingKotlinx_serialization_jsonJsonConfiguration : XNetworkingBase
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL allowSpecialFloatingPointValues __attribute__((swift_name("allowSpecialFloatingPointValues")));
@property (readonly) BOOL allowStructuredMapKeys __attribute__((swift_name("allowStructuredMapKeys")));
@property (readonly) NSString *classDiscriminator __attribute__((swift_name("classDiscriminator")));
@property (readonly) BOOL coerceInputValues __attribute__((swift_name("coerceInputValues")));
@property (readonly) BOOL encodeDefaults __attribute__((swift_name("encodeDefaults")));
@property (readonly) BOOL explicitNulls __attribute__((swift_name("explicitNulls")));
@property (readonly) BOOL ignoreUnknownKeys __attribute__((swift_name("ignoreUnknownKeys")));
@property (readonly) BOOL isLenient __attribute__((swift_name("isLenient")));
@property (readonly) BOOL prettyPrint __attribute__((swift_name("prettyPrint")));
@property (readonly) NSString *prettyPrintIndent __attribute__((swift_name("prettyPrintIndent")));
@property (readonly) BOOL useAlternativeNames __attribute__((swift_name("useAlternativeNames")));
@property (readonly) BOOL useArrayPolymorphism __attribute__((swift_name("useArrayPolymorphism")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpMethod.Companion")))
@interface XNetworkingKtor_httpHttpMethodCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpHttpMethodCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_httpHttpMethod *)parseMethod:(NSString *)method __attribute__((swift_name("parse(method:)")));
@property (readonly) NSArray<XNetworkingKtor_httpHttpMethod *> *DefaultMethods __attribute__((swift_name("DefaultMethods")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Delete __attribute__((swift_name("Delete")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Get __attribute__((swift_name("Get")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Head __attribute__((swift_name("Head")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Options __attribute__((swift_name("Options")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Patch __attribute__((swift_name("Patch")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Post __attribute__((swift_name("Post")));
@property (readonly) XNetworkingKtor_httpHttpMethod *Put __attribute__((swift_name("Put")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHeaderValueParam")))
@interface XNetworkingKtor_httpHeaderValueParam : XNetworkingBase
- (instancetype)initWithName:(NSString *)name value:(NSString *)value __attribute__((swift_name("init(name:value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_httpHeaderValueParam *)doCopyName:(NSString *)name value:(NSString *)value __attribute__((swift_name("doCopy(name:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHeaderValueWithParameters.Companion")))
@interface XNetworkingKtor_httpHeaderValueWithParametersCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpHeaderValueWithParametersCompanion *shared __attribute__((swift_name("shared")));
- (id _Nullable)parseValue:(NSString *)value init:(id _Nullable (^)(NSString *, NSArray<XNetworkingKtor_httpHeaderValueParam *> *))init __attribute__((swift_name("parse(value:init:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpContentType.Companion")))
@interface XNetworkingKtor_httpContentTypeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpContentTypeCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_httpContentType *)parseValue:(NSString *)value __attribute__((swift_name("parse(value:)")));
@property (readonly) XNetworkingKtor_httpContentType *Any __attribute__((swift_name("Any")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol XNetworkingKotlinx_coroutines_coreFlowCollector
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end;

__attribute__((swift_name("KotlinMapEntry")))
@protocol XNetworkingKotlinMapEntry
@required
@property (readonly) id _Nullable key __attribute__((swift_name("key")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("KotlinByteIterator")))
@interface XNetworkingKotlinByteIterator : XNetworkingBase <XNetworkingKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (XNetworkingByte *)next_ __attribute__((swift_name("next_()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol XNetworkingKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<XNetworkingKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) XNetworkingKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface XNetworkingKotlinNothing : XNetworkingBase
@end;

__attribute__((swift_name("KotlinAnnotation")))
@protocol XNetworkingKotlinAnnotation
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface XNetworkingKotlinx_serialization_coreSerialKind : XNetworkingBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol XNetworkingKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<XNetworkingKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));
- (void)encodeNullableSerializableElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<XNetworkingKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) XNetworkingKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestData")))
@interface XNetworkingKtor_client_coreHttpRequestData : XNetworkingBase
- (instancetype)initWithUrl:(XNetworkingKtor_httpUrl *)url method:(XNetworkingKtor_httpHttpMethod *)method headers:(id<XNetworkingKtor_httpHeaders>)headers body:(XNetworkingKtor_httpOutgoingContent *)body executionContext:(id<XNetworkingKotlinx_coroutines_coreJob>)executionContext attributes:(id<XNetworkingKtor_utilsAttributes>)attributes __attribute__((swift_name("init(url:method:headers:body:executionContext:attributes:)"))) __attribute__((objc_designated_initializer));
- (id _Nullable)getCapabilityOrNullKey:(id<XNetworkingKtor_client_coreHttpClientEngineCapability>)key __attribute__((swift_name("getCapabilityOrNull(key:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<XNetworkingKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) XNetworkingKtor_httpOutgoingContent *body __attribute__((swift_name("body")));
@property (readonly) id<XNetworkingKotlinx_coroutines_coreJob> executionContext __attribute__((swift_name("executionContext")));
@property (readonly) id<XNetworkingKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@property (readonly) XNetworkingKtor_httpHttpMethod *method __attribute__((swift_name("method")));
@property (readonly) XNetworkingKtor_httpUrl *url __attribute__((swift_name("url")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponseData")))
@interface XNetworkingKtor_client_coreHttpResponseData : XNetworkingBase
- (instancetype)initWithStatusCode:(XNetworkingKtor_httpHttpStatusCode *)statusCode requestTime:(XNetworkingKtor_utilsGMTDate *)requestTime headers:(id<XNetworkingKtor_httpHeaders>)headers version:(XNetworkingKtor_httpHttpProtocolVersion *)version body:(id)body callContext:(id<XNetworkingKotlinCoroutineContext>)callContext __attribute__((swift_name("init(statusCode:requestTime:headers:version:body:callContext:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id body __attribute__((swift_name("body")));
@property (readonly) id<XNetworkingKotlinCoroutineContext> callContext __attribute__((swift_name("callContext")));
@property (readonly) id<XNetworkingKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@property (readonly) XNetworkingKtor_utilsGMTDate *requestTime __attribute__((swift_name("requestTime")));
@property (readonly) XNetworkingKtor_utilsGMTDate *responseTime __attribute__((swift_name("responseTime")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *statusCode __attribute__((swift_name("statusCode")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *version __attribute__((swift_name("version")));
@end;

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol XNetworkingKotlinCoroutineContextElement <XNetworkingKotlinCoroutineContext>
@required
@property (readonly) id<XNetworkingKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end;

__attribute__((swift_name("KotlinAbstractCoroutineContextElement")))
@interface XNetworkingKotlinAbstractCoroutineContextElement : XNetworkingBase <XNetworkingKotlinCoroutineContextElement>
- (instancetype)initWithKey:(id<XNetworkingKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<XNetworkingKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end;

__attribute__((swift_name("KotlinContinuationInterceptor")))
@protocol XNetworkingKotlinContinuationInterceptor <XNetworkingKotlinCoroutineContextElement>
@required
- (id<XNetworkingKotlinContinuation>)interceptContinuationContinuation:(id<XNetworkingKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (void)releaseInterceptedContinuationContinuation:(id<XNetworkingKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher")))
@interface XNetworkingKotlinx_coroutines_coreCoroutineDispatcher : XNetworkingKotlinAbstractCoroutineContextElement <XNetworkingKotlinContinuationInterceptor>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithKey:(id<XNetworkingKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKotlinx_coroutines_coreCoroutineDispatcherKey *companion __attribute__((swift_name("companion")));
- (void)dispatchContext:(id<XNetworkingKotlinCoroutineContext>)context block:(id<XNetworkingKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatch(context:block:)")));
- (void)dispatchYieldContext:(id<XNetworkingKotlinCoroutineContext>)context block:(id<XNetworkingKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatchYield(context:block:)")));
- (id<XNetworkingKotlinContinuation>)interceptContinuationContinuation:(id<XNetworkingKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (BOOL)isDispatchNeededContext:(id<XNetworkingKotlinCoroutineContext>)context __attribute__((swift_name("isDispatchNeeded(context:)")));
- (XNetworkingKotlinx_coroutines_coreCoroutineDispatcher *)limitedParallelismParallelism:(int32_t)parallelism __attribute__((swift_name("limitedParallelism(parallelism:)")));
- (XNetworkingKotlinx_coroutines_coreCoroutineDispatcher *)plusOther:(XNetworkingKotlinx_coroutines_coreCoroutineDispatcher *)other __attribute__((swift_name("plus(other:)"))) __attribute__((unavailable("Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.")));
- (void)releaseInterceptedContinuationContinuation:(id<XNetworkingKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreProxyConfig")))
@interface XNetworkingKtor_client_coreProxyConfig : XNetworkingBase
- (instancetype)initWithUrl:(XNetworkingKtor_httpUrl *)url __attribute__((swift_name("init(url:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingKtor_httpUrl *url __attribute__((swift_name("url")));
@end;

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol XNetworkingKotlinCoroutineContextKey
@required
@end;

__attribute__((swift_name("Ktor_client_coreHttpClientPlugin")))
@protocol XNetworkingKtor_client_coreHttpClientPlugin
@required
- (void)installPlugin:(id)plugin scope:(XNetworkingKtor_client_coreHttpClient *)scope __attribute__((swift_name("install(plugin:scope:)")));
- (id)prepareBlock:(void (^)(id))block __attribute__((swift_name("prepare(block:)")));
@property (readonly) XNetworkingKtor_utilsAttributeKey<id> *key __attribute__((swift_name("key")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsAttributeKey")))
@interface XNetworkingKtor_utilsAttributeKey<T> : XNetworkingBase
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((swift_name("Ktor_eventsEventDefinition")))
@interface XNetworkingKtor_eventsEventDefinition<T> : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreDisposableHandle")))
@protocol XNetworkingKotlinx_coroutines_coreDisposableHandle
@required
- (void)dispose __attribute__((swift_name("dispose()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsPipelinePhase")))
@interface XNetworkingKtor_utilsPipelinePhase : XNetworkingBase
- (instancetype)initWithName:(NSString *)name __attribute__((swift_name("init(name:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((swift_name("KotlinFunction")))
@protocol XNetworkingKotlinFunction
@required
@end;

__attribute__((swift_name("KotlinSuspendFunction2")))
@protocol XNetworkingKotlinSuspendFunction2 <XNetworkingKotlinFunction>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeP1:(id _Nullable)p1 p2:(id _Nullable)p2 completionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(p1:p2:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpReceivePipeline.Phases")))
@interface XNetworkingKtor_client_coreHttpReceivePipelinePhases : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_client_coreHttpReceivePipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *After __attribute__((swift_name("After")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Before __attribute__((swift_name("Before")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@end;

__attribute__((swift_name("Ktor_httpHttpMessage")))
@protocol XNetworkingKtor_httpHttpMessage
@required
@property (readonly) id<XNetworkingKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpResponse")))
@interface XNetworkingKtor_client_coreHttpResponse : XNetworkingBase <XNetworkingKtor_httpHttpMessage, XNetworkingKotlinx_coroutines_coreCoroutineScope>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingKtor_client_coreHttpClientCall *call __attribute__((swift_name("call")));
@property (readonly) id<XNetworkingKtor_ioByteReadChannel> content __attribute__((swift_name("content")));
@property (readonly) XNetworkingKtor_utilsGMTDate *requestTime __attribute__((swift_name("requestTime")));
@property (readonly) XNetworkingKtor_utilsGMTDate *responseTime __attribute__((swift_name("responseTime")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *status __attribute__((swift_name("status")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *version_ __attribute__((swift_name("version_")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface XNetworkingKotlinUnit : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKotlinUnit *shared __attribute__((swift_name("shared")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestPipeline.Phases")))
@interface XNetworkingKtor_client_coreHttpRequestPipelinePhases : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_client_coreHttpRequestPipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Before __attribute__((swift_name("Before")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Render __attribute__((swift_name("Render")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Send __attribute__((swift_name("Send")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Transform __attribute__((swift_name("Transform")));
@end;

__attribute__((swift_name("Ktor_httpHttpMessageBuilder")))
@protocol XNetworkingKtor_httpHttpMessageBuilder
@required
@property (readonly) XNetworkingKtor_httpHeadersBuilder *headers __attribute__((swift_name("headers")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestBuilder")))
@interface XNetworkingKtor_client_coreHttpRequestBuilder : XNetworkingBase <XNetworkingKtor_httpHttpMessageBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) XNetworkingKtor_client_coreHttpRequestBuilderCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingKtor_client_coreHttpRequestData *)build __attribute__((swift_name("build()")));
- (id _Nullable)getCapabilityOrNullKey:(id<XNetworkingKtor_client_coreHttpClientEngineCapability>)key __attribute__((swift_name("getCapabilityOrNull(key:)")));
- (void)setAttributesBlock:(void (^)(id<XNetworkingKtor_utilsAttributes>))block __attribute__((swift_name("setAttributes(block:)")));
- (void)setCapabilityKey:(id<XNetworkingKtor_client_coreHttpClientEngineCapability>)key capability:(id)capability __attribute__((swift_name("setCapability(key:capability:)")));
- (XNetworkingKtor_client_coreHttpRequestBuilder *)takeFromBuilder:(XNetworkingKtor_client_coreHttpRequestBuilder *)builder __attribute__((swift_name("takeFrom(builder:)")));
- (XNetworkingKtor_client_coreHttpRequestBuilder *)takeFromWithExecutionContextBuilder:(XNetworkingKtor_client_coreHttpRequestBuilder *)builder __attribute__((swift_name("takeFromWithExecutionContext(builder:)")));
- (void)urlBlock:(void (^)(XNetworkingKtor_httpURLBuilder *, XNetworkingKtor_httpURLBuilder *))block __attribute__((swift_name("url(block:)")));
@property (readonly) id<XNetworkingKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property id body __attribute__((swift_name("body")));
@property XNetworkingKtor_utilsTypeInfo * _Nullable bodyType __attribute__((swift_name("bodyType")));
@property (readonly) id<XNetworkingKotlinx_coroutines_coreJob> executionContext __attribute__((swift_name("executionContext")));
@property (readonly) XNetworkingKtor_httpHeadersBuilder *headers __attribute__((swift_name("headers")));
@property XNetworkingKtor_httpHttpMethod *method __attribute__((swift_name("method")));
@property (readonly) XNetworkingKtor_httpURLBuilder *url __attribute__((swift_name("url")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponsePipeline.Phases")))
@interface XNetworkingKtor_client_coreHttpResponsePipelinePhases : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_client_coreHttpResponsePipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *After __attribute__((swift_name("After")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Parse __attribute__((swift_name("Parse")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Receive __attribute__((swift_name("Receive")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Transform __attribute__((swift_name("Transform")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpResponseContainer")))
@interface XNetworkingKtor_client_coreHttpResponseContainer : XNetworkingBase
- (instancetype)initWithExpectedType:(XNetworkingKtor_utilsTypeInfo *)expectedType response:(id)response __attribute__((swift_name("init(expectedType:response:)"))) __attribute__((objc_designated_initializer));
- (XNetworkingKtor_utilsTypeInfo *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (id)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_client_coreHttpResponseContainer *)doCopyExpectedType:(XNetworkingKtor_utilsTypeInfo *)expectedType response:(id)response __attribute__((swift_name("doCopy(expectedType:response:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingKtor_utilsTypeInfo *expectedType __attribute__((swift_name("expectedType")));
@property (readonly) id response __attribute__((swift_name("response")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpClientCall")))
@interface XNetworkingKtor_client_coreHttpClientCall : XNetworkingBase <XNetworkingKotlinx_coroutines_coreCoroutineScope>
- (instancetype)initWithClient:(XNetworkingKtor_client_coreHttpClient *)client requestData:(XNetworkingKtor_client_coreHttpRequestData *)requestData responseData:(XNetworkingKtor_client_coreHttpResponseData *)responseData __attribute__((swift_name("init(client:requestData:responseData:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithClient:(XNetworkingKtor_client_coreHttpClient *)client __attribute__((swift_name("init(client:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_client_coreHttpClientCallCompanion *companion __attribute__((swift_name("companion")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)bodyInfo:(XNetworkingKtor_utilsTypeInfo *)info completionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("body(info:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getResponseContentWithCompletionHandler:(void (^)(id<XNetworkingKtor_ioByteReadChannel> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getResponseContent(completionHandler:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL allowDoubleReceive __attribute__((swift_name("allowDoubleReceive")));
@property (readonly) id<XNetworkingKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) XNetworkingKtor_client_coreHttpClient *client __attribute__((swift_name("client")));
@property (readonly) id<XNetworkingKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@property id<XNetworkingKtor_client_coreHttpRequest> request __attribute__((swift_name("request")));
@property XNetworkingKtor_client_coreHttpResponse *response __attribute__((swift_name("response")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpSendPipeline.Phases")))
@interface XNetworkingKtor_client_coreHttpSendPipelinePhases : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)phases __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_client_coreHttpSendPipelinePhases *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Before __attribute__((swift_name("Before")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Engine __attribute__((swift_name("Engine")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Monitoring __attribute__((swift_name("Monitoring")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *Receive __attribute__((swift_name("Receive")));
@property (readonly) XNetworkingKtor_utilsPipelinePhase *State __attribute__((swift_name("State")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol XNetworkingKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<XNetworkingKotlinKClass>)kClass provider:(id<XNetworkingKotlinx_serialization_coreKSerializer> (^)(NSArray<id<XNetworkingKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<XNetworkingKotlinKClass>)kClass serializer:(id<XNetworkingKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<XNetworkingKotlinKClass>)baseClass actualClass:(id<XNetworkingKotlinKClass>)actualClass actualSerializer:(id<XNetworkingKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<XNetworkingKotlinKClass>)baseClass defaultDeserializerProvider:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<XNetworkingKotlinKClass>)baseClass defaultDeserializerProvider:(id<XNetworkingKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<XNetworkingKotlinKClass>)baseClass defaultSerializerProvider:(id<XNetworkingKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol XNetworkingKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol XNetworkingKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol XNetworkingKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol XNetworkingKotlinKClass <XNetworkingKotlinKDeclarationContainer, XNetworkingKotlinKAnnotatedElement, XNetworkingKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpUrl")))
@interface XNetworkingKtor_httpUrl : XNetworkingBase
@property (class, readonly, getter=companion) XNetworkingKtor_httpUrlCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *encodedFragment __attribute__((swift_name("encodedFragment")));
@property (readonly) NSString * _Nullable encodedPassword __attribute__((swift_name("encodedPassword")));
@property (readonly) NSString *encodedPath __attribute__((swift_name("encodedPath")));
@property (readonly) NSString *encodedPathAndQuery __attribute__((swift_name("encodedPathAndQuery")));
@property (readonly) NSString *encodedQuery __attribute__((swift_name("encodedQuery")));
@property (readonly) NSString * _Nullable encodedUser __attribute__((swift_name("encodedUser")));
@property (readonly) NSString *fragment __attribute__((swift_name("fragment")));
@property (readonly) NSString *host __attribute__((swift_name("host")));
@property (readonly) id<XNetworkingKtor_httpParameters> parameters __attribute__((swift_name("parameters")));
@property (readonly) NSString * _Nullable password __attribute__((swift_name("password")));
@property (readonly) NSArray<NSString *> *pathSegments __attribute__((swift_name("pathSegments")));
@property (readonly) int32_t port __attribute__((swift_name("port")));
@property (readonly) XNetworkingKtor_httpURLProtocol *protocol __attribute__((swift_name("protocol")));
@property (readonly) int32_t specifiedPort __attribute__((swift_name("specifiedPort")));
@property (readonly) BOOL trailingQuery __attribute__((swift_name("trailingQuery")));
@property (readonly) NSString * _Nullable user __attribute__((swift_name("user")));
@end;

__attribute__((swift_name("Ktor_httpOutgoingContent")))
@interface XNetworkingKtor_httpOutgoingContent : XNetworkingBase
- (id _Nullable)getPropertyKey:(XNetworkingKtor_utilsAttributeKey<id> *)key __attribute__((swift_name("getProperty(key:)")));
- (void)setPropertyKey:(XNetworkingKtor_utilsAttributeKey<id> *)key value:(id _Nullable)value __attribute__((swift_name("setProperty(key:value:)")));
- (id<XNetworkingKtor_httpHeaders> _Nullable)trailers __attribute__((swift_name("trailers()")));
@property (readonly) XNetworkingLong * _Nullable contentLength __attribute__((swift_name("contentLength")));
@property (readonly) XNetworkingKtor_httpContentType * _Nullable contentType __attribute__((swift_name("contentType")));
@property (readonly) id<XNetworkingKtor_httpHeaders> headers __attribute__((swift_name("headers")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode * _Nullable status __attribute__((swift_name("status")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreJob")))
@protocol XNetworkingKotlinx_coroutines_coreJob <XNetworkingKotlinCoroutineContextElement>
@required
- (id<XNetworkingKotlinx_coroutines_coreChildHandle>)attachChildChild:(id<XNetworkingKotlinx_coroutines_coreChildJob>)child __attribute__((swift_name("attachChild(child:)")));
- (void)cancelCause:(XNetworkingKotlinCancellationException * _Nullable)cause __attribute__((swift_name("cancel(cause:)")));
- (XNetworkingKotlinCancellationException *)getCancellationException __attribute__((swift_name("getCancellationException()")));
- (id<XNetworkingKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionOnCancelling:(BOOL)onCancelling invokeImmediately:(BOOL)invokeImmediately handler:(void (^)(XNetworkingKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(onCancelling:invokeImmediately:handler:)")));
- (id<XNetworkingKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionHandler:(void (^)(XNetworkingKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(handler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("join(completionHandler:)")));
- (id<XNetworkingKotlinx_coroutines_coreJob>)plusOther_:(id<XNetworkingKotlinx_coroutines_coreJob>)other __attribute__((swift_name("plus(other_:)"))) __attribute__((unavailable("Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")));
- (BOOL)start __attribute__((swift_name("start()")));
@property (readonly) id<XNetworkingKotlinSequence> children __attribute__((swift_name("children")));
@property (readonly) BOOL isActive __attribute__((swift_name("isActive")));
@property (readonly) BOOL isCancelled __attribute__((swift_name("isCancelled")));
@property (readonly) BOOL isCompleted __attribute__((swift_name("isCompleted")));
@property (readonly) id<XNetworkingKotlinx_coroutines_coreSelectClause0> onJoin __attribute__((swift_name("onJoin")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpStatusCode")))
@interface XNetworkingKtor_httpHttpStatusCode : XNetworkingBase
- (instancetype)initWithValue:(int32_t)value description:(NSString *)description __attribute__((swift_name("init(value:description:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_httpHttpStatusCodeCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (NSString *)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_httpHttpStatusCode *)doCopyValue:(int32_t)value description:(NSString *)description __attribute__((swift_name("doCopy(value:description:)")));
- (XNetworkingKtor_httpHttpStatusCode *)descriptionValue:(NSString *)value __attribute__((swift_name("description(value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *description_ __attribute__((swift_name("description_")));
@property (readonly) int32_t value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol XNetworkingKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsGMTDate")))
@interface XNetworkingKtor_utilsGMTDate : XNetworkingBase <XNetworkingKotlinComparable>
@property (class, readonly, getter=companion) XNetworkingKtor_utilsGMTDateCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(XNetworkingKtor_utilsGMTDate *)other __attribute__((swift_name("compareTo(other:)")));
- (int32_t)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_utilsWeekDay *)component4 __attribute__((swift_name("component4()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component5 __attribute__((swift_name("component5()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component6 __attribute__((swift_name("component6()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_utilsMonth *)component7 __attribute__((swift_name("component7()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component8 __attribute__((swift_name("component8()"))) __attribute__((deprecated("use corresponding property instead")));
- (int64_t)component9 __attribute__((swift_name("component9()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_utilsGMTDate *)doCopySeconds:(int32_t)seconds minutes:(int32_t)minutes hours:(int32_t)hours dayOfWeek:(XNetworkingKtor_utilsWeekDay *)dayOfWeek dayOfMonth:(int32_t)dayOfMonth dayOfYear:(int32_t)dayOfYear month:(XNetworkingKtor_utilsMonth *)month year:(int32_t)year timestamp:(int64_t)timestamp __attribute__((swift_name("doCopy(seconds:minutes:hours:dayOfWeek:dayOfMonth:dayOfYear:month:year:timestamp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t dayOfMonth __attribute__((swift_name("dayOfMonth")));
@property (readonly) XNetworkingKtor_utilsWeekDay *dayOfWeek __attribute__((swift_name("dayOfWeek")));
@property (readonly) int32_t dayOfYear __attribute__((swift_name("dayOfYear")));
@property (readonly) int32_t hours __attribute__((swift_name("hours")));
@property (readonly) int32_t minutes __attribute__((swift_name("minutes")));
@property (readonly) XNetworkingKtor_utilsMonth *month __attribute__((swift_name("month")));
@property (readonly) int32_t seconds __attribute__((swift_name("seconds")));
@property (readonly) int64_t timestamp __attribute__((swift_name("timestamp")));
@property (readonly) int32_t year __attribute__((swift_name("year")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpProtocolVersion")))
@interface XNetworkingKtor_httpHttpProtocolVersion : XNetworkingBase
- (instancetype)initWithName:(NSString *)name major:(int32_t)major minor:(int32_t)minor __attribute__((swift_name("init(name:major:minor:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_httpHttpProtocolVersionCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_httpHttpProtocolVersion *)doCopyName:(NSString *)name major:(int32_t)major minor:(int32_t)minor __attribute__((swift_name("doCopy(name:major:minor:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t major __attribute__((swift_name("major")));
@property (readonly) int32_t minor __attribute__((swift_name("minor")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((swift_name("KotlinContinuation")))
@protocol XNetworkingKotlinContinuation
@required
- (void)resumeWithResult:(id _Nullable)result __attribute__((swift_name("resumeWith(result:)")));
@property (readonly) id<XNetworkingKotlinCoroutineContext> context __attribute__((swift_name("context")));
@end;

__attribute__((swift_name("KotlinAbstractCoroutineContextKey")))
@interface XNetworkingKotlinAbstractCoroutineContextKey<B, E> : XNetworkingBase <XNetworkingKotlinCoroutineContextKey>
- (instancetype)initWithBaseKey:(id<XNetworkingKotlinCoroutineContextKey>)baseKey safeCast:(E _Nullable (^)(id<XNetworkingKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher.Key")))
@interface XNetworkingKotlinx_coroutines_coreCoroutineDispatcherKey : XNetworkingKotlinAbstractCoroutineContextKey<id<XNetworkingKotlinContinuationInterceptor>, XNetworkingKotlinx_coroutines_coreCoroutineDispatcher *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithBaseKey:(id<XNetworkingKotlinCoroutineContextKey>)baseKey safeCast:(id<XNetworkingKotlinCoroutineContextElement> _Nullable (^)(id<XNetworkingKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)key __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKotlinx_coroutines_coreCoroutineDispatcherKey *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreRunnable")))
@protocol XNetworkingKotlinx_coroutines_coreRunnable
@required
- (void)run __attribute__((swift_name("run()")));
@end;

__attribute__((swift_name("Ktor_ioByteReadChannel")))
@protocol XNetworkingKtor_ioByteReadChannel
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)awaitContentWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("awaitContent(completionHandler:)")));
- (BOOL)cancelCause_:(XNetworkingKotlinThrowable * _Nullable)cause __attribute__((swift_name("cancel(cause_:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)discardMax:(int64_t)max completionHandler:(void (^)(XNetworkingLong * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("discard(max:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)peekToDestination:(XNetworkingKtor_ioMemory *)destination destinationOffset:(int64_t)destinationOffset offset:(int64_t)offset min:(int64_t)min max:(int64_t)max completionHandler:(void (^)(XNetworkingLong * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("peekTo(destination:destinationOffset:offset:min:max:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readAvailableDst:(XNetworkingKtor_ioChunkBuffer *)dst completionHandler:(void (^)(XNetworkingInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readAvailable(dst:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readAvailableDst:(XNetworkingKotlinByteArray *)dst offset:(int32_t)offset length:(int32_t)length completionHandler:(void (^)(XNetworkingInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readAvailable(dst:offset:length:completionHandler:)")));
- (int32_t)readAvailableMin:(int32_t)min block:(void (^)(XNetworkingKtor_ioBuffer *))block __attribute__((swift_name("readAvailable(min:block:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readAvailableDst:(void *)dst offset:(int32_t)offset length:(int32_t)length completionHandler_:(void (^)(XNetworkingInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readAvailable(dst:offset:length:completionHandler_:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readAvailableDst:(void *)dst offset:(int64_t)offset length:(int64_t)length completionHandler__:(void (^)(XNetworkingInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readAvailable(dst:offset:length:completionHandler__:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readBooleanWithCompletionHandler:(void (^)(XNetworkingBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readBoolean(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readByteWithCompletionHandler:(void (^)(XNetworkingByte * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readByte(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readDoubleWithCompletionHandler:(void (^)(XNetworkingDouble * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readDouble(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readFloatWithCompletionHandler:(void (^)(XNetworkingFloat * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readFloat(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readFullyDst:(XNetworkingKtor_ioChunkBuffer *)dst n:(int32_t)n completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("readFully(dst:n:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readFullyDst:(XNetworkingKotlinByteArray *)dst offset:(int32_t)offset length:(int32_t)length completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("readFully(dst:offset:length:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readFullyDst:(void *)dst offset:(int32_t)offset length:(int32_t)length completionHandler_:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("readFully(dst:offset:length:completionHandler_:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readFullyDst:(void *)dst offset:(int64_t)offset length:(int64_t)length completionHandler__:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("readFully(dst:offset:length:completionHandler__:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readIntWithCompletionHandler:(void (^)(XNetworkingInt * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readInt(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readLongWithCompletionHandler:(void (^)(XNetworkingLong * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readLong(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readPacketSize:(int32_t)size completionHandler:(void (^)(XNetworkingKtor_ioByteReadPacket * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readPacket(size:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readRemainingLimit:(int64_t)limit completionHandler:(void (^)(XNetworkingKtor_ioByteReadPacket * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readRemaining(limit:completionHandler:)")));
- (void)readSessionConsumer:(void (^)(id<XNetworkingKtor_ioReadSession>))consumer __attribute__((swift_name("readSession(consumer:)"))) __attribute__((deprecated("Use read { } instead.")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readShortWithCompletionHandler:(void (^)(XNetworkingShort * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readShort(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readSuspendableSessionConsumer:(id<XNetworkingKotlinSuspendFunction1>)consumer completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("readSuspendableSession(consumer:completionHandler:)"))) __attribute__((deprecated("Use read { } instead.")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readUTF8LineLimit:(int32_t)limit completionHandler:(void (^)(NSString * _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("readUTF8Line(limit:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)readUTF8LineToOut:(id<XNetworkingKotlinAppendable>)out limit:(int32_t)limit completionHandler:(void (^)(XNetworkingBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("readUTF8LineTo(out:limit:completionHandler:)")));
@property (readonly) int32_t availableForRead __attribute__((swift_name("availableForRead")));
@property (readonly) XNetworkingKotlinThrowable * _Nullable closedCause __attribute__((swift_name("closedCause")));
@property (readonly) BOOL isClosedForRead __attribute__((swift_name("isClosedForRead")));
@property (readonly) BOOL isClosedForWrite __attribute__((swift_name("isClosedForWrite")));
@property (readonly) int64_t totalBytesRead __attribute__((swift_name("totalBytesRead")));
@end;

__attribute__((swift_name("Ktor_utilsStringValuesBuilder")))
@protocol XNetworkingKtor_utilsStringValuesBuilder
@required
- (void)appendName:(NSString *)name value:(NSString *)value __attribute__((swift_name("append(name:value:)")));
- (void)appendAllStringValues:(id<XNetworkingKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendAll(stringValues:)")));
- (void)appendAllName:(NSString *)name values:(id)values __attribute__((swift_name("appendAll(name:values:)")));
- (void)appendMissingStringValues:(id<XNetworkingKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendMissing(stringValues:)")));
- (void)appendMissingName:(NSString *)name values:(id)values __attribute__((swift_name("appendMissing(name:values:)")));
- (id<XNetworkingKtor_utilsStringValues>)build __attribute__((swift_name("build()")));
- (void)clear __attribute__((swift_name("clear()")));
- (BOOL)containsName:(NSString *)name __attribute__((swift_name("contains(name:)")));
- (BOOL)containsName:(NSString *)name value:(NSString *)value __attribute__((swift_name("contains(name:value:)")));
- (NSSet<id<XNetworkingKotlinMapEntry>> *)entries __attribute__((swift_name("entries()")));
- (NSString * _Nullable)getName:(NSString *)name __attribute__((swift_name("get(name:)")));
- (NSArray<NSString *> * _Nullable)getAllName:(NSString *)name __attribute__((swift_name("getAll(name:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (NSSet<NSString *> *)names __attribute__((swift_name("names()")));
- (void)removeName:(NSString *)name __attribute__((swift_name("remove(name:)")));
- (BOOL)removeName:(NSString *)name value:(NSString *)value __attribute__((swift_name("remove(name:value:)")));
- (void)removeKeysWithNoEntries __attribute__((swift_name("removeKeysWithNoEntries()")));
- (void)setName:(NSString *)name value:(NSString *)value __attribute__((swift_name("set(name:value:)")));
@property (readonly) BOOL caseInsensitiveName __attribute__((swift_name("caseInsensitiveName")));
@end;

__attribute__((swift_name("Ktor_utilsStringValuesBuilderImpl")))
@interface XNetworkingKtor_utilsStringValuesBuilderImpl : XNetworkingBase <XNetworkingKtor_utilsStringValuesBuilder>
- (instancetype)initWithCaseInsensitiveName:(BOOL)caseInsensitiveName size:(int32_t)size __attribute__((swift_name("init(caseInsensitiveName:size:)"))) __attribute__((objc_designated_initializer));
- (void)appendName:(NSString *)name value:(NSString *)value __attribute__((swift_name("append(name:value:)")));
- (void)appendAllStringValues:(id<XNetworkingKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendAll(stringValues:)")));
- (void)appendAllName:(NSString *)name values:(id)values __attribute__((swift_name("appendAll(name:values:)")));
- (void)appendMissingStringValues:(id<XNetworkingKtor_utilsStringValues>)stringValues __attribute__((swift_name("appendMissing(stringValues:)")));
- (void)appendMissingName:(NSString *)name values:(id)values __attribute__((swift_name("appendMissing(name:values:)")));
- (id<XNetworkingKtor_utilsStringValues>)build __attribute__((swift_name("build()")));
- (void)clear __attribute__((swift_name("clear()")));
- (BOOL)containsName:(NSString *)name __attribute__((swift_name("contains(name:)")));
- (BOOL)containsName:(NSString *)name value:(NSString *)value __attribute__((swift_name("contains(name:value:)")));
- (NSSet<id<XNetworkingKotlinMapEntry>> *)entries __attribute__((swift_name("entries()")));
- (NSString * _Nullable)getName:(NSString *)name __attribute__((swift_name("get(name:)")));
- (NSArray<NSString *> * _Nullable)getAllName:(NSString *)name __attribute__((swift_name("getAll(name:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (NSSet<NSString *> *)names __attribute__((swift_name("names()")));
- (void)removeName:(NSString *)name __attribute__((swift_name("remove(name:)")));
- (BOOL)removeName:(NSString *)name value:(NSString *)value __attribute__((swift_name("remove(name:value:)")));
- (void)removeKeysWithNoEntries __attribute__((swift_name("removeKeysWithNoEntries()")));
- (void)setName:(NSString *)name value:(NSString *)value __attribute__((swift_name("set(name:value:)")));
- (void)validateNameName:(NSString *)name __attribute__((swift_name("validateName(name:)")));
- (void)validateValueValue:(NSString *)value __attribute__((swift_name("validateValue(value:)")));
@property (readonly) BOOL caseInsensitiveName __attribute__((swift_name("caseInsensitiveName")));
@property (readonly) XNetworkingMutableDictionary<NSString *, NSMutableArray<NSString *> *> *values __attribute__((swift_name("values")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHeadersBuilder")))
@interface XNetworkingKtor_httpHeadersBuilder : XNetworkingKtor_utilsStringValuesBuilderImpl
- (instancetype)initWithSize:(int32_t)size __attribute__((swift_name("init(size:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCaseInsensitiveName:(BOOL)caseInsensitiveName size:(int32_t)size __attribute__((swift_name("init(caseInsensitiveName:size:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (id<XNetworkingKtor_httpHeaders>)build __attribute__((swift_name("build()")));
- (void)validateNameName:(NSString *)name __attribute__((swift_name("validateName(name:)")));
- (void)validateValueValue:(NSString *)value __attribute__((swift_name("validateValue(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpRequestBuilder.Companion")))
@interface XNetworkingKtor_client_coreHttpRequestBuilderCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_client_coreHttpRequestBuilderCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLBuilder")))
@interface XNetworkingKtor_httpURLBuilder : XNetworkingBase
- (instancetype)initWithProtocol:(XNetworkingKtor_httpURLProtocol *)protocol host:(NSString *)host port:(int32_t)port user:(NSString * _Nullable)user password:(NSString * _Nullable)password pathSegments:(NSArray<NSString *> *)pathSegments parameters:(id<XNetworkingKtor_httpParameters>)parameters fragment:(NSString *)fragment trailingQuery:(BOOL)trailingQuery __attribute__((swift_name("init(protocol:host:port:user:password:pathSegments:parameters:fragment:trailingQuery:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_httpURLBuilderCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingKtor_httpUrl *)build __attribute__((swift_name("build()")));
- (NSString *)buildString __attribute__((swift_name("buildString()")));
@property NSString *encodedFragment __attribute__((swift_name("encodedFragment")));
@property id<XNetworkingKtor_httpParametersBuilder> encodedParameters __attribute__((swift_name("encodedParameters")));
@property NSString * _Nullable encodedPassword __attribute__((swift_name("encodedPassword")));
@property NSArray<NSString *> *encodedPathSegments __attribute__((swift_name("encodedPathSegments")));
@property NSString * _Nullable encodedUser __attribute__((swift_name("encodedUser")));
@property NSString *fragment __attribute__((swift_name("fragment")));
@property NSString *host __attribute__((swift_name("host")));
@property (readonly) id<XNetworkingKtor_httpParametersBuilder> parameters __attribute__((swift_name("parameters")));
@property NSString * _Nullable password __attribute__((swift_name("password")));
@property NSArray<NSString *> *pathSegments __attribute__((swift_name("pathSegments")));
@property int32_t port __attribute__((swift_name("port")));
@property XNetworkingKtor_httpURLProtocol *protocol __attribute__((swift_name("protocol")));
@property BOOL trailingQuery __attribute__((swift_name("trailingQuery")));
@property NSString * _Nullable user __attribute__((swift_name("user")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsTypeInfo")))
@interface XNetworkingKtor_utilsTypeInfo : XNetworkingBase
- (instancetype)initWithType:(id<XNetworkingKotlinKClass>)type reifiedType:(id<XNetworkingKotlinKType>)reifiedType kotlinType:(id<XNetworkingKotlinKType> _Nullable)kotlinType __attribute__((swift_name("init(type:reifiedType:kotlinType:)"))) __attribute__((objc_designated_initializer));
- (id<XNetworkingKotlinKClass>)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (id<XNetworkingKotlinKType>)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (id<XNetworkingKotlinKType> _Nullable)component3 __attribute__((swift_name("component3()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_utilsTypeInfo *)doCopyType:(id<XNetworkingKotlinKClass>)type reifiedType:(id<XNetworkingKotlinKType>)reifiedType kotlinType:(id<XNetworkingKotlinKType> _Nullable)kotlinType __attribute__((swift_name("doCopy(type:reifiedType:kotlinType:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<XNetworkingKotlinKType> _Nullable kotlinType __attribute__((swift_name("kotlinType")));
@property (readonly) id<XNetworkingKotlinKType> reifiedType __attribute__((swift_name("reifiedType")));
@property (readonly) id<XNetworkingKotlinKClass> type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_client_coreHttpClientCall.Companion")))
@interface XNetworkingKtor_client_coreHttpClientCallCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_client_coreHttpClientCallCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_utilsAttributeKey<id> *CustomResponse __attribute__((swift_name("CustomResponse"))) __attribute__((unavailable("This is going to be removed. Please file a ticket with clarification why and what for do you need it.")));
@end;

__attribute__((swift_name("Ktor_client_coreHttpRequest")))
@protocol XNetworkingKtor_client_coreHttpRequest <XNetworkingKtor_httpHttpMessage, XNetworkingKotlinx_coroutines_coreCoroutineScope>
@required
@property (readonly) id<XNetworkingKtor_utilsAttributes> attributes __attribute__((swift_name("attributes")));
@property (readonly) XNetworkingKtor_client_coreHttpClientCall *call __attribute__((swift_name("call")));
@property (readonly) XNetworkingKtor_httpOutgoingContent *content __attribute__((swift_name("content")));
@property (readonly) XNetworkingKtor_httpHttpMethod *method __attribute__((swift_name("method")));
@property (readonly) XNetworkingKtor_httpUrl *url __attribute__((swift_name("url")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpUrl.Companion")))
@interface XNetworkingKtor_httpUrlCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpUrlCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("Ktor_httpParameters")))
@protocol XNetworkingKtor_httpParameters <XNetworkingKtor_utilsStringValues>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLProtocol")))
@interface XNetworkingKtor_httpURLProtocol : XNetworkingBase
- (instancetype)initWithName:(NSString *)name defaultPort:(int32_t)defaultPort __attribute__((swift_name("init(name:defaultPort:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_httpURLProtocolCompanion *companion __attribute__((swift_name("companion")));
- (NSString *)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (int32_t)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKtor_httpURLProtocol *)doCopyName:(NSString *)name defaultPort:(int32_t)defaultPort __attribute__((swift_name("doCopy(name:defaultPort:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t defaultPort __attribute__((swift_name("defaultPort")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChildHandle")))
@protocol XNetworkingKotlinx_coroutines_coreChildHandle <XNetworkingKotlinx_coroutines_coreDisposableHandle>
@required
- (BOOL)childCancelledCause:(XNetworkingKotlinThrowable *)cause __attribute__((swift_name("childCancelled(cause:)")));
@property (readonly) id<XNetworkingKotlinx_coroutines_coreJob> _Nullable parent __attribute__((swift_name("parent")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChildJob")))
@protocol XNetworkingKotlinx_coroutines_coreChildJob <XNetworkingKotlinx_coroutines_coreJob>
@required
- (void)parentCancelledParentJob:(id<XNetworkingKotlinx_coroutines_coreParentJob>)parentJob __attribute__((swift_name("parentCancelled(parentJob:)")));
@end;

__attribute__((swift_name("KotlinSequence")))
@protocol XNetworkingKotlinSequence
@required
- (id<XNetworkingKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause0")))
@protocol XNetworkingKotlinx_coroutines_coreSelectClause0
@required
- (void)registerSelectClause0Select:(id<XNetworkingKotlinx_coroutines_coreSelectInstance>)select block:(id<XNetworkingKotlinSuspendFunction0>)block __attribute__((swift_name("registerSelectClause0(select:block:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpStatusCode.Companion")))
@interface XNetworkingKtor_httpHttpStatusCodeCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpHttpStatusCodeCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_httpHttpStatusCode *)fromValueValue:(int32_t)value __attribute__((swift_name("fromValue(value:)")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Accepted __attribute__((swift_name("Accepted")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *BadGateway __attribute__((swift_name("BadGateway")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *BadRequest __attribute__((swift_name("BadRequest")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Conflict __attribute__((swift_name("Conflict")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Continue __attribute__((swift_name("Continue")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Created __attribute__((swift_name("Created")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *ExpectationFailed __attribute__((swift_name("ExpectationFailed")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *FailedDependency __attribute__((swift_name("FailedDependency")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Forbidden __attribute__((swift_name("Forbidden")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Found __attribute__((swift_name("Found")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *GatewayTimeout __attribute__((swift_name("GatewayTimeout")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Gone __attribute__((swift_name("Gone")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *InsufficientStorage __attribute__((swift_name("InsufficientStorage")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *InternalServerError __attribute__((swift_name("InternalServerError")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *LengthRequired __attribute__((swift_name("LengthRequired")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Locked __attribute__((swift_name("Locked")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *MethodNotAllowed __attribute__((swift_name("MethodNotAllowed")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *MovedPermanently __attribute__((swift_name("MovedPermanently")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *MultiStatus __attribute__((swift_name("MultiStatus")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *MultipleChoices __attribute__((swift_name("MultipleChoices")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *NoContent __attribute__((swift_name("NoContent")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *NonAuthoritativeInformation __attribute__((swift_name("NonAuthoritativeInformation")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *NotAcceptable __attribute__((swift_name("NotAcceptable")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *NotFound __attribute__((swift_name("NotFound")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *NotImplemented __attribute__((swift_name("NotImplemented")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *NotModified __attribute__((swift_name("NotModified")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *OK __attribute__((swift_name("OK")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *PartialContent __attribute__((swift_name("PartialContent")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *PayloadTooLarge __attribute__((swift_name("PayloadTooLarge")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *PaymentRequired __attribute__((swift_name("PaymentRequired")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *PermanentRedirect __attribute__((swift_name("PermanentRedirect")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *PreconditionFailed __attribute__((swift_name("PreconditionFailed")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Processing __attribute__((swift_name("Processing")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *ProxyAuthenticationRequired __attribute__((swift_name("ProxyAuthenticationRequired")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *RequestHeaderFieldTooLarge __attribute__((swift_name("RequestHeaderFieldTooLarge")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *RequestTimeout __attribute__((swift_name("RequestTimeout")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *RequestURITooLong __attribute__((swift_name("RequestURITooLong")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *RequestedRangeNotSatisfiable __attribute__((swift_name("RequestedRangeNotSatisfiable")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *ResetContent __attribute__((swift_name("ResetContent")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *SeeOther __attribute__((swift_name("SeeOther")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *ServiceUnavailable __attribute__((swift_name("ServiceUnavailable")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *SwitchProxy __attribute__((swift_name("SwitchProxy")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *SwitchingProtocols __attribute__((swift_name("SwitchingProtocols")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *TemporaryRedirect __attribute__((swift_name("TemporaryRedirect")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *TooManyRequests __attribute__((swift_name("TooManyRequests")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *Unauthorized __attribute__((swift_name("Unauthorized")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *UnprocessableEntity __attribute__((swift_name("UnprocessableEntity")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *UnsupportedMediaType __attribute__((swift_name("UnsupportedMediaType")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *UpgradeRequired __attribute__((swift_name("UpgradeRequired")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *UseProxy __attribute__((swift_name("UseProxy")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *VariantAlsoNegotiates __attribute__((swift_name("VariantAlsoNegotiates")));
@property (readonly) XNetworkingKtor_httpHttpStatusCode *VersionNotSupported __attribute__((swift_name("VersionNotSupported")));
@property (readonly) NSArray<XNetworkingKtor_httpHttpStatusCode *> *allStatusCodes __attribute__((swift_name("allStatusCodes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsGMTDate.Companion")))
@interface XNetworkingKtor_utilsGMTDateCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_utilsGMTDateCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_utilsGMTDate *START __attribute__((swift_name("START")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface XNetworkingKotlinEnum<E> : XNetworkingBase <XNetworkingKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsWeekDay")))
@interface XNetworkingKtor_utilsWeekDay : XNetworkingKotlinEnum<XNetworkingKtor_utilsWeekDay *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_utilsWeekDayCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *monday __attribute__((swift_name("monday")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *tuesday __attribute__((swift_name("tuesday")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *wednesday __attribute__((swift_name("wednesday")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *thursday __attribute__((swift_name("thursday")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *friday __attribute__((swift_name("friday")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *saturday __attribute__((swift_name("saturday")));
@property (class, readonly) XNetworkingKtor_utilsWeekDay *sunday __attribute__((swift_name("sunday")));
+ (XNetworkingKotlinArray<XNetworkingKtor_utilsWeekDay *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsMonth")))
@interface XNetworkingKtor_utilsMonth : XNetworkingKotlinEnum<XNetworkingKtor_utilsMonth *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_utilsMonthCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) XNetworkingKtor_utilsMonth *january __attribute__((swift_name("january")));
@property (class, readonly) XNetworkingKtor_utilsMonth *february __attribute__((swift_name("february")));
@property (class, readonly) XNetworkingKtor_utilsMonth *march __attribute__((swift_name("march")));
@property (class, readonly) XNetworkingKtor_utilsMonth *april __attribute__((swift_name("april")));
@property (class, readonly) XNetworkingKtor_utilsMonth *may __attribute__((swift_name("may")));
@property (class, readonly) XNetworkingKtor_utilsMonth *june __attribute__((swift_name("june")));
@property (class, readonly) XNetworkingKtor_utilsMonth *july __attribute__((swift_name("july")));
@property (class, readonly) XNetworkingKtor_utilsMonth *august __attribute__((swift_name("august")));
@property (class, readonly) XNetworkingKtor_utilsMonth *september __attribute__((swift_name("september")));
@property (class, readonly) XNetworkingKtor_utilsMonth *october __attribute__((swift_name("october")));
@property (class, readonly) XNetworkingKtor_utilsMonth *november __attribute__((swift_name("november")));
@property (class, readonly) XNetworkingKtor_utilsMonth *december __attribute__((swift_name("december")));
+ (XNetworkingKotlinArray<XNetworkingKtor_utilsMonth *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpHttpProtocolVersion.Companion")))
@interface XNetworkingKtor_httpHttpProtocolVersionCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpHttpProtocolVersionCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_httpHttpProtocolVersion *)fromValueName:(NSString *)name major:(int32_t)major minor:(int32_t)minor __attribute__((swift_name("fromValue(name:major:minor:)")));
- (XNetworkingKtor_httpHttpProtocolVersion *)parseValue:(id)value __attribute__((swift_name("parse(value:)")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *HTTP_1_0 __attribute__((swift_name("HTTP_1_0")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *HTTP_1_1 __attribute__((swift_name("HTTP_1_1")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *HTTP_2_0 __attribute__((swift_name("HTTP_2_0")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *QUIC __attribute__((swift_name("QUIC")));
@property (readonly) XNetworkingKtor_httpHttpProtocolVersion *SPDY_3 __attribute__((swift_name("SPDY_3")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioMemory")))
@interface XNetworkingKtor_ioMemory : XNetworkingBase
- (instancetype)initWithPointer:(void *)pointer size:(int64_t)size __attribute__((swift_name("init(pointer:size:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_ioMemoryCompanion *companion __attribute__((swift_name("companion")));
- (void)doCopyToDestination:(XNetworkingKtor_ioMemory *)destination offset:(int32_t)offset length:(int32_t)length destinationOffset:(int32_t)destinationOffset __attribute__((swift_name("doCopyTo(destination:offset:length:destinationOffset:)")));
- (void)doCopyToDestination:(XNetworkingKtor_ioMemory *)destination offset:(int64_t)offset length:(int64_t)length destinationOffset_:(int64_t)destinationOffset __attribute__((swift_name("doCopyTo(destination:offset:length:destinationOffset_:)")));
- (int8_t)loadAtIndex:(int32_t)index __attribute__((swift_name("loadAt(index:)")));
- (int8_t)loadAtIndex_:(int64_t)index __attribute__((swift_name("loadAt(index_:)")));
- (XNetworkingKtor_ioMemory *)sliceOffset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("slice(offset:length:)")));
- (XNetworkingKtor_ioMemory *)sliceOffset:(int64_t)offset length_:(int64_t)length __attribute__((swift_name("slice(offset:length_:)")));
- (void)storeAtIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("storeAt(index:value:)")));
- (void)storeAtIndex:(int64_t)index value_:(int8_t)value __attribute__((swift_name("storeAt(index:value_:)")));
@property (readonly) void *pointer __attribute__((swift_name("pointer")));
@property (readonly) int64_t size __attribute__((swift_name("size")));
@property (readonly) int32_t size32 __attribute__((swift_name("size32")));
@end;

__attribute__((swift_name("Ktor_ioBuffer")))
@interface XNetworkingKtor_ioBuffer : XNetworkingBase
- (instancetype)initWithMemory:(XNetworkingKtor_ioMemory *)memory __attribute__((swift_name("init(memory:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_ioBufferCompanion *companion __attribute__((swift_name("companion")));
- (void)commitWrittenCount:(int32_t)count __attribute__((swift_name("commitWritten(count:)")));
- (void)discardExactCount:(int32_t)count __attribute__((swift_name("discardExact(count:)")));
- (XNetworkingKtor_ioBuffer *)duplicate __attribute__((swift_name("duplicate()")));
- (void)duplicateToCopy:(XNetworkingKtor_ioBuffer *)copy __attribute__((swift_name("duplicateTo(copy:)")));
- (int8_t)readByte __attribute__((swift_name("readByte()")));
- (void)reserveEndGapEndGap:(int32_t)endGap __attribute__((swift_name("reserveEndGap(endGap:)")));
- (void)reserveStartGapStartGap:(int32_t)startGap __attribute__((swift_name("reserveStartGap(startGap:)")));
- (void)reset __attribute__((swift_name("reset()")));
- (void)resetForRead __attribute__((swift_name("resetForRead()")));
- (void)resetForWrite __attribute__((swift_name("resetForWrite()")));
- (void)resetForWriteLimit:(int32_t)limit __attribute__((swift_name("resetForWrite(limit:)")));
- (void)rewindCount:(int32_t)count __attribute__((swift_name("rewind(count:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (int32_t)tryPeekByte __attribute__((swift_name("tryPeekByte()")));
- (int32_t)tryReadByte __attribute__((swift_name("tryReadByte()")));
- (void)writeByteValue:(int8_t)value __attribute__((swift_name("writeByte(value:)")));
@property (readonly) int32_t capacity __attribute__((swift_name("capacity")));
@property (readonly) int32_t endGap __attribute__((swift_name("endGap")));
@property (readonly) int32_t limit __attribute__((swift_name("limit")));
@property (readonly) XNetworkingKtor_ioMemory *memory __attribute__((swift_name("memory")));
@property (readonly) int32_t readPosition __attribute__((swift_name("readPosition")));
@property (readonly) int32_t readRemaining __attribute__((swift_name("readRemaining")));
@property (readonly) int32_t startGap __attribute__((swift_name("startGap")));
@property (readonly) int32_t writePosition __attribute__((swift_name("writePosition")));
@property (readonly) int32_t writeRemaining __attribute__((swift_name("writeRemaining")));
@end;

__attribute__((swift_name("Ktor_ioChunkBuffer")))
@interface XNetworkingKtor_ioChunkBuffer : XNetworkingKtor_ioBuffer
- (instancetype)initWithMemory:(XNetworkingKtor_ioMemory *)memory origin:(XNetworkingKtor_ioChunkBuffer * _Nullable)origin parentPool:(id<XNetworkingKtor_ioObjectPool> _Nullable)parentPool __attribute__((swift_name("init(memory:origin:parentPool:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMemory:(XNetworkingKtor_ioMemory *)memory __attribute__((swift_name("init(memory:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_ioChunkBufferCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingKtor_ioChunkBuffer * _Nullable)cleanNext __attribute__((swift_name("cleanNext()")));
- (XNetworkingKtor_ioChunkBuffer *)duplicate __attribute__((swift_name("duplicate()")));
- (void)releasePool:(id<XNetworkingKtor_ioObjectPool>)pool __attribute__((swift_name("release(pool:)")));
- (void)reset __attribute__((swift_name("reset()")));
@property (getter=next__) XNetworkingKtor_ioChunkBuffer * _Nullable next __attribute__((swift_name("next")));
@property (readonly) XNetworkingKtor_ioChunkBuffer * _Nullable origin __attribute__((swift_name("origin")));
@property (readonly) int32_t referenceCount __attribute__((swift_name("referenceCount")));
@end;

__attribute__((swift_name("Ktor_ioInput")))
@interface XNetworkingKtor_ioInput : XNetworkingBase <XNetworkingKtor_ioCloseable>
- (instancetype)initWithHead:(XNetworkingKtor_ioChunkBuffer *)head remaining:(int64_t)remaining pool:(id<XNetworkingKtor_ioObjectPool>)pool __attribute__((swift_name("init(head:remaining:pool:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKtor_ioInputCompanion *companion __attribute__((swift_name("companion")));
- (BOOL)canRead __attribute__((swift_name("canRead()")));
- (void)close __attribute__((swift_name("close()")));
- (void)closeSource __attribute__((swift_name("closeSource()")));
- (int32_t)discardN:(int32_t)n __attribute__((swift_name("discard(n:)")));
- (int64_t)discardN_:(int64_t)n __attribute__((swift_name("discard(n_:)")));
- (void)discardExactN:(int32_t)n __attribute__((swift_name("discardExact(n:)")));
- (XNetworkingKtor_ioChunkBuffer * _Nullable)fill __attribute__((swift_name("fill()")));
- (int32_t)fillDestination:(XNetworkingKtor_ioMemory *)destination offset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("fill(destination:offset:length:)")));
- (BOOL)hasBytesN:(int32_t)n __attribute__((swift_name("hasBytes(n:)")));
- (void)markNoMoreChunksAvailable __attribute__((swift_name("markNoMoreChunksAvailable()")));
- (int64_t)peekToDestination:(XNetworkingKtor_ioMemory *)destination destinationOffset:(int64_t)destinationOffset offset:(int64_t)offset min:(int64_t)min max:(int64_t)max __attribute__((swift_name("peekTo(destination:destinationOffset:offset:min:max:)")));
- (int32_t)peekToBuffer:(XNetworkingKtor_ioChunkBuffer *)buffer __attribute__((swift_name("peekTo(buffer:)")));
- (int8_t)readByte __attribute__((swift_name("readByte()")));
- (NSString *)readTextMin:(int32_t)min max:(int32_t)max __attribute__((swift_name("readText(min:max:)")));
- (int32_t)readTextOut:(id<XNetworkingKotlinAppendable>)out min:(int32_t)min max:(int32_t)max __attribute__((swift_name("readText(out:min:max:)")));
- (NSString *)readTextExactExactCharacters:(int32_t)exactCharacters __attribute__((swift_name("readTextExact(exactCharacters:)")));
- (void)readTextExactOut:(id<XNetworkingKotlinAppendable>)out exactCharacters:(int32_t)exactCharacters __attribute__((swift_name("readTextExact(out:exactCharacters:)")));
- (void)release_ __attribute__((swift_name("release()")));
- (int32_t)tryPeek __attribute__((swift_name("tryPeek()")));
@property (readonly) BOOL endOfInput __attribute__((swift_name("endOfInput")));
@property (readonly) id<XNetworkingKtor_ioObjectPool> pool __attribute__((swift_name("pool")));
@property (readonly) int64_t remaining __attribute__((swift_name("remaining")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioByteReadPacket")))
@interface XNetworkingKtor_ioByteReadPacket : XNetworkingKtor_ioInput
- (instancetype)initWithHead:(XNetworkingKtor_ioChunkBuffer *)head pool:(id<XNetworkingKtor_ioObjectPool>)pool __attribute__((swift_name("init(head:pool:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithHead:(XNetworkingKtor_ioChunkBuffer *)head remaining:(int64_t)remaining pool:(id<XNetworkingKtor_ioObjectPool>)pool __attribute__((swift_name("init(head:remaining:pool:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) XNetworkingKtor_ioByteReadPacketCompanion *companion __attribute__((swift_name("companion")));
- (void)closeSource __attribute__((swift_name("closeSource()")));
- (XNetworkingKtor_ioByteReadPacket *)doCopy __attribute__((swift_name("doCopy()")));
- (XNetworkingKtor_ioChunkBuffer * _Nullable)fill __attribute__((swift_name("fill()")));
- (int32_t)fillDestination:(XNetworkingKtor_ioMemory *)destination offset:(int32_t)offset length:(int32_t)length __attribute__((swift_name("fill(destination:offset:length:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("Ktor_ioReadSession")))
@protocol XNetworkingKtor_ioReadSession
@required
- (int32_t)discardN:(int32_t)n __attribute__((swift_name("discard(n:)")));
- (XNetworkingKtor_ioChunkBuffer * _Nullable)requestAtLeast:(int32_t)atLeast __attribute__((swift_name("request(atLeast:)")));
@property (readonly) int32_t availableForRead __attribute__((swift_name("availableForRead")));
@end;

__attribute__((swift_name("KotlinSuspendFunction1")))
@protocol XNetworkingKotlinSuspendFunction1 <XNetworkingKotlinFunction>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeP1:(id _Nullable)p1 completionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(p1:completionHandler:)")));
@end;

__attribute__((swift_name("KotlinAppendable")))
@protocol XNetworkingKotlinAppendable
@required
- (id<XNetworkingKotlinAppendable>)appendValue:(unichar)value __attribute__((swift_name("append(value:)")));
- (id<XNetworkingKotlinAppendable>)appendValue_:(id _Nullable)value __attribute__((swift_name("append(value_:)")));
- (id<XNetworkingKotlinAppendable>)appendValue:(id _Nullable)value startIndex:(int32_t)startIndex endIndex:(int32_t)endIndex __attribute__((swift_name("append(value:startIndex:endIndex:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLBuilder.Companion")))
@interface XNetworkingKtor_httpURLBuilderCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpURLBuilderCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((swift_name("Ktor_httpParametersBuilder")))
@protocol XNetworkingKtor_httpParametersBuilder <XNetworkingKtor_utilsStringValuesBuilder>
@required
@end;

__attribute__((swift_name("KotlinKType")))
@protocol XNetworkingKotlinKType
@required
@property (readonly) NSArray<XNetworkingKotlinKTypeProjection *> *arguments __attribute__((swift_name("arguments")));
@property (readonly) id<XNetworkingKotlinKClassifier> _Nullable classifier __attribute__((swift_name("classifier")));
@property (readonly) BOOL isMarkedNullable __attribute__((swift_name("isMarkedNullable")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_httpURLProtocol.Companion")))
@interface XNetworkingKtor_httpURLProtocolCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_httpURLProtocolCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_httpURLProtocol *)createOrDefaultName:(NSString *)name __attribute__((swift_name("createOrDefault(name:)")));
@property (readonly) XNetworkingKtor_httpURLProtocol *HTTP __attribute__((swift_name("HTTP")));
@property (readonly) XNetworkingKtor_httpURLProtocol *HTTPS __attribute__((swift_name("HTTPS")));
@property (readonly) XNetworkingKtor_httpURLProtocol *SOCKS __attribute__((swift_name("SOCKS")));
@property (readonly) XNetworkingKtor_httpURLProtocol *WS __attribute__((swift_name("WS")));
@property (readonly) XNetworkingKtor_httpURLProtocol *WSS __attribute__((swift_name("WSS")));
@property (readonly) NSDictionary<NSString *, XNetworkingKtor_httpURLProtocol *> *byName __attribute__((swift_name("byName")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreParentJob")))
@protocol XNetworkingKotlinx_coroutines_coreParentJob <XNetworkingKotlinx_coroutines_coreJob>
@required
- (XNetworkingKotlinCancellationException *)getChildJobCancellationCause __attribute__((swift_name("getChildJobCancellationCause()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectInstance")))
@protocol XNetworkingKotlinx_coroutines_coreSelectInstance
@required
- (void)disposeOnSelectHandle:(id<XNetworkingKotlinx_coroutines_coreDisposableHandle>)handle __attribute__((swift_name("disposeOnSelect(handle:)")));
- (id _Nullable)performAtomicTrySelectDesc:(XNetworkingKotlinx_coroutines_coreAtomicDesc *)desc __attribute__((swift_name("performAtomicTrySelect(desc:)")));
- (void)resumeSelectWithExceptionException:(XNetworkingKotlinThrowable *)exception __attribute__((swift_name("resumeSelectWithException(exception:)")));
- (BOOL)trySelect __attribute__((swift_name("trySelect()")));
- (id _Nullable)trySelectOtherOtherOp:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp * _Nullable)otherOp __attribute__((swift_name("trySelectOther(otherOp:)")));
@property (readonly) id<XNetworkingKotlinContinuation> completion __attribute__((swift_name("completion")));
@property (readonly) BOOL isSelected __attribute__((swift_name("isSelected")));
@end;

__attribute__((swift_name("KotlinSuspendFunction0")))
@protocol XNetworkingKotlinSuspendFunction0 <XNetworkingKotlinFunction>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeWithCompletionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface XNetworkingKotlinEnumCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsWeekDay.Companion")))
@interface XNetworkingKtor_utilsWeekDayCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_utilsWeekDayCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_utilsWeekDay *)fromOrdinal:(int32_t)ordinal __attribute__((swift_name("from(ordinal:)")));
- (XNetworkingKtor_utilsWeekDay *)fromValue:(NSString *)value __attribute__((swift_name("from(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_utilsMonth.Companion")))
@interface XNetworkingKtor_utilsMonthCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_utilsMonthCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKtor_utilsMonth *)fromOrdinal:(int32_t)ordinal __attribute__((swift_name("from(ordinal:)")));
- (XNetworkingKtor_utilsMonth *)fromValue:(NSString *)value __attribute__((swift_name("from(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioMemory.Companion")))
@interface XNetworkingKtor_ioMemoryCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_ioMemoryCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_ioMemory *Empty __attribute__((swift_name("Empty")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioBuffer.Companion")))
@interface XNetworkingKtor_ioBufferCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_ioBufferCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_ioBuffer *Empty __attribute__((swift_name("Empty")));
@property (readonly) int32_t ReservedSize __attribute__((swift_name("ReservedSize")));
@end;

__attribute__((swift_name("Ktor_ioObjectPool")))
@protocol XNetworkingKtor_ioObjectPool <XNetworkingKtor_ioCloseable>
@required
- (id)borrow __attribute__((swift_name("borrow()")));
- (void)dispose __attribute__((swift_name("dispose()")));
- (void)recycleInstance:(id)instance __attribute__((swift_name("recycle(instance:)")));
@property (readonly) int32_t capacity __attribute__((swift_name("capacity")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioChunkBuffer.Companion")))
@interface XNetworkingKtor_ioChunkBufferCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_ioChunkBufferCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_ioChunkBuffer *Empty __attribute__((swift_name("Empty")));
@property (readonly) id<XNetworkingKtor_ioObjectPool> EmptyPool __attribute__((swift_name("EmptyPool")));
@property (readonly) id<XNetworkingKtor_ioObjectPool> Pool __attribute__((swift_name("Pool")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioInput.Companion")))
@interface XNetworkingKtor_ioInputCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_ioInputCompanion *shared __attribute__((swift_name("shared")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ktor_ioByteReadPacket.Companion")))
@interface XNetworkingKtor_ioByteReadPacketCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKtor_ioByteReadPacketCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) XNetworkingKtor_ioByteReadPacket *Empty __attribute__((swift_name("Empty")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinKTypeProjection")))
@interface XNetworkingKotlinKTypeProjection : XNetworkingBase
- (instancetype)initWithVariance:(XNetworkingKotlinKVariance * _Nullable)variance type:(id<XNetworkingKotlinKType> _Nullable)type __attribute__((swift_name("init(variance:type:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) XNetworkingKotlinKTypeProjectionCompanion *companion __attribute__((swift_name("companion")));
- (XNetworkingKotlinKVariance * _Nullable)component1 __attribute__((swift_name("component1()"))) __attribute__((deprecated("use corresponding property instead")));
- (id<XNetworkingKotlinKType> _Nullable)component2 __attribute__((swift_name("component2()"))) __attribute__((deprecated("use corresponding property instead")));
- (XNetworkingKotlinKTypeProjection *)doCopyVariance:(XNetworkingKotlinKVariance * _Nullable)variance type:(id<XNetworkingKotlinKType> _Nullable)type __attribute__((swift_name("doCopy(variance:type:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<XNetworkingKotlinKType> _Nullable type __attribute__((swift_name("type")));
@property (readonly) XNetworkingKotlinKVariance * _Nullable variance __attribute__((swift_name("variance")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreAtomicDesc")))
@interface XNetworkingKotlinx_coroutines_coreAtomicDesc : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeOp:(XNetworkingKotlinx_coroutines_coreAtomicOp<id> *)op failure:(id _Nullable)failure __attribute__((swift_name("complete(op:failure:)")));
- (id _Nullable)prepareOp:(XNetworkingKotlinx_coroutines_coreAtomicOp<id> *)op __attribute__((swift_name("prepare(op:)")));
@property XNetworkingKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreOpDescriptor")))
@interface XNetworkingKotlinx_coroutines_coreOpDescriptor : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)isEarlierThanThat:(XNetworkingKotlinx_coroutines_coreOpDescriptor *)that __attribute__((swift_name("isEarlierThan(that:)")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingKotlinx_coroutines_coreAtomicOp<id> * _Nullable atomicOp __attribute__((swift_name("atomicOp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode.PrepareOp")))
@interface XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp : XNetworkingKotlinx_coroutines_coreOpDescriptor
- (instancetype)initWithAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next desc:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc *)desc __attribute__((swift_name("init(affected:next:desc:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)finishPrepare __attribute__((swift_name("finishPrepare()")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *affected __attribute__((swift_name("affected")));
@property (readonly) XNetworkingKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc *desc __attribute__((swift_name("desc")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *next __attribute__((swift_name("next")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinKVariance")))
@interface XNetworkingKotlinKVariance : XNetworkingKotlinEnum<XNetworkingKotlinKVariance *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) XNetworkingKotlinKVariance *invariant __attribute__((swift_name("invariant")));
@property (class, readonly) XNetworkingKotlinKVariance *in __attribute__((swift_name("in")));
@property (class, readonly) XNetworkingKotlinKVariance *out __attribute__((swift_name("out")));
+ (XNetworkingKotlinArray<XNetworkingKotlinKVariance *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinKTypeProjection.Companion")))
@interface XNetworkingKotlinKTypeProjectionCompanion : XNetworkingBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) XNetworkingKotlinKTypeProjectionCompanion *shared __attribute__((swift_name("shared")));
- (XNetworkingKotlinKTypeProjection *)contravariantType:(id<XNetworkingKotlinKType>)type __attribute__((swift_name("contravariant(type:)")));
- (XNetworkingKotlinKTypeProjection *)covariantType:(id<XNetworkingKotlinKType>)type __attribute__((swift_name("covariant(type:)")));
- (XNetworkingKotlinKTypeProjection *)invariantType:(id<XNetworkingKotlinKType>)type __attribute__((swift_name("invariant(type:)")));
@property (readonly) XNetworkingKotlinKTypeProjection *STAR __attribute__((swift_name("STAR")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreAtomicOp")))
@interface XNetworkingKotlinx_coroutines_coreAtomicOp<__contravariant T> : XNetworkingKotlinx_coroutines_coreOpDescriptor
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeAffected:(T _Nullable)affected failure:(id _Nullable)failure __attribute__((swift_name("complete(affected:failure:)")));
- (id _Nullable)decideDecision:(id _Nullable)decision __attribute__((swift_name("decide(decision:)")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (id _Nullable)prepareAffected:(T _Nullable)affected __attribute__((swift_name("prepare(affected:)")));
@property (readonly) XNetworkingKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@property (readonly) id _Nullable consensus __attribute__((swift_name("consensus")));
@property (readonly) BOOL isDecided __attribute__((swift_name("isDecided")));
@property (readonly) int64_t opSequence __attribute__((swift_name("opSequence")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode")))
@interface XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode : XNetworkingBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)addLastNode:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("addLast(node:)")));
- (BOOL)addLastIfNode:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)node condition:(XNetworkingBoolean *(^)(void))condition __attribute__((swift_name("addLastIf(node:condition:)")));
- (BOOL)addLastIfPrevNode:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)node predicate:(XNetworkingBoolean *(^)(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *))predicate __attribute__((swift_name("addLastIfPrev(node:predicate:)")));
- (BOOL)addLastIfPrevAndIfNode:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)node predicate:(XNetworkingBoolean *(^)(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *))predicate condition:(XNetworkingBoolean *(^)(void))condition __attribute__((swift_name("addLastIfPrevAndIf(node:predicate:condition:)")));
- (BOOL)addOneIfEmptyNode:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("addOneIfEmpty(node:)")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *> *)describeAddLastNode:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("describeAddLast(node:)")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *> *)describeRemoveFirst __attribute__((swift_name("describeRemoveFirst()")));
- (void)helpRemove __attribute__((swift_name("helpRemove()")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)nextIfRemoved __attribute__((swift_name("nextIfRemoved()")));
- (BOOL)remove __attribute__((swift_name("remove()")));
- (id _Nullable)removeFirstIfIsInstanceOfOrPeekIfPredicate:(XNetworkingBoolean *(^)(id _Nullable))predicate __attribute__((swift_name("removeFirstIfIsInstanceOfOrPeekIf(predicate:)")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)removeFirstOrNull __attribute__((swift_name("removeFirstOrNull()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isRemoved __attribute__((swift_name("isRemoved")));
@property (readonly, getter=next__) id next __attribute__((swift_name("next")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *nextNode __attribute__((swift_name("nextNode")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *prevNode __attribute__((swift_name("prevNode")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode.AbstractAtomicDesc")))
@interface XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc : XNetworkingKotlinx_coroutines_coreAtomicDesc
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeOp:(XNetworkingKotlinx_coroutines_coreAtomicOp<id> *)op failure:(id _Nullable)failure __attribute__((swift_name("complete(op:failure:)")));
- (id _Nullable)failureAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected __attribute__((swift_name("failure(affected:)")));
- (void)finishOnSuccessAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (id _Nullable)onPreparePrepareOp:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("onPrepare(prepareOp:)")));
- (void)onRemovedAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected __attribute__((swift_name("onRemoved(affected:)")));
- (id _Nullable)prepareOp:(XNetworkingKotlinx_coroutines_coreAtomicOp<id> *)op __attribute__((swift_name("prepare(op:)")));
- (BOOL)retryAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(XNetworkingKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc")))
@interface XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<T> : XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc
- (instancetype)initWithQueue:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)queue node:(T)node __attribute__((swift_name("init(queue:node:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)finishOnSuccessAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (BOOL)retryAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(XNetworkingKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) T node __attribute__((swift_name("node")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *originalNext __attribute__((swift_name("originalNext")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *queue __attribute__((swift_name("queue")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc")))
@interface XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<T> : XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc
- (instancetype)initWithQueue:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)queue __attribute__((swift_name("init(queue:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (id _Nullable)failureAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected __attribute__((swift_name("failure(affected:)")));
- (void)finishOnSuccessAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (BOOL)retryAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(XNetworkingKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@property (readonly) XNetworkingKotlinx_coroutines_coreLockFreeLinkedListNode *queue __attribute__((swift_name("queue")));
@property (readonly) T _Nullable result __attribute__((swift_name("result")));
@end;

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
