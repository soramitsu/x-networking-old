package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.sora

import com.apollographql.apollo3.api.Optional
import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItemNested
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.core.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.asJsonArrayNullable
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.asJsonPrimitiveNullable
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.getContentOrEmpty
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.sorawallet.GetSoraHistoryElementsQuery
import jp.co.soramitsu.xnetworking.sorawallet.type.HistoryElementFilter
import jp.co.soramitsu.xnetworking.sorawallet.type.HistoryElementsOrderBy
import jp.co.soramitsu.xnetworking.sorawallet.type.JSONFilter
import jp.co.soramitsu.xnetworking.sorawallet.type.StringFilter
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class SoraHistoryInfoRemoteLoader(
    private val apolloClientStore: ApolloClientStore,
    private val chainsConfigFetcher: ChainsConfigFetcher
): HistoryInfoRemoteLoader {

    override suspend fun loadHistoryInfo(
        pageCount: Int,
        cursor: String?,
        signAddress: String,
        chainId: String,
        assetId: String,
        filters: Set<TxFilter>
    ): TxHistoryInfo {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val requestUrl = requireNotNull(config?.externalApi?.history?.url) {
            "Url for Sora blockExplorer on chain with id - $chainId - is null."
        }

        val response = requireNotNull(
            apolloClientStore.query(
                requestUrl,
                GetSoraHistoryElementsQuery(
                    pageCount = Optional.present(pageCount),
                    cursor = Optional.present(cursor),
                    orderBy = Optional.present(listOf(HistoryElementsOrderBy.TIMESTAMP_DESC)),
                    filter = Optional.present(createHistoryElementsFilter(signAddress))
                )
            ).historyElements
        ) { "GetHistoryElementsQuery response is null" }

        // Unparsing JSON scalar and normally typed contents
        val items =
            response.nodes.filterNotNull().map {
                val wasOperationSuccessful = it.execution.asJsonObjectNullable
                    ?.getPrimitiveContentOrEmpty("success").toBoolean()

                val txHistoryItemParams = it.data?.asJsonObjectNullable?.map { mapItem ->
                    TxHistoryItemParam(
                        paramName = mapItem.key,
                        paramValue = mapItem.value.asJsonPrimitiveNullable.getContentOrEmpty()
                    )
                }

                val nestedData = it.data?.asJsonArrayNullable?.mapNotNull { element ->
                    element.asJsonObjectNullable
                }?.map { json ->
                    TxHistoryItemNested(
                        hash = json.getPrimitiveContentOrEmpty("hash"),
                        module = json.getPrimitiveContentOrEmpty("module"),
                        method = json.getPrimitiveContentOrEmpty("method"),
                        data = json["data"]?.asJsonObjectNullable
                            ?.get("args")?.asJsonObjectNullable?.map { mapItem ->
                                TxHistoryItemParam(
                                    paramName = mapItem.key,
                                    paramValue = mapItem.value.asJsonPrimitiveNullable.getContentOrEmpty()
                                )
                            } ?: emptyList()
                    )
                }

                TxHistoryItem(
                    id = it.id,
                    blockHash = it.blockHash,
                    module = it.module,
                    method = it.method,
                    timestamp = it.timestamp.toString(),
                    networkFee = it.networkFee,
                    success = wasOperationSuccessful,
                    data = txHistoryItemParams,
                    nestedData = nestedData,
                )
            }

        return TxHistoryInfo(
            endCursor = response.pageInfo.endCursor,
            endReached = response.pageInfo.hasNextPage,
            items = items
        )
    }

    private fun createHistoryElementsFilter(signAddress: String) =
        HistoryElementFilter(
            or = Optional.present(
                value = listOf(
                    HistoryElementFilter(
                        address = Optional.present(
                            value = StringFilter(
                                equalTo = Optional.present(value = signAddress)
                            )
                        ),
                        or = Optional.present(
                            value = listOf(
                                HistoryElementFilter(
                                    module = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "assets")
                                        )
                                    ),
                                    method = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "transfer")
                                        )
                                    )
                                ),
                                HistoryElementFilter(
                                    module = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "liquidityProxy")
                                        )
                                    ),
                                    method = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "swap")
                                        )
                                    )
                                ),
                                HistoryElementFilter(
                                    module = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "poolXYK")
                                        )
                                    ),
                                    method = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "depositLiquidity")
                                        )
                                    )
                                ),
                                HistoryElementFilter(
                                    data = Optional.present(
                                        value = JSONFilter(
                                            contains = Optional.present(
                                                value = JsonObject(
                                                    content = mapOf("method" to JsonPrimitive("depositLiquidity"))
                                                )
                                            )
                                        )
                                    )
                                ),
                                HistoryElementFilter(
                                    module = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "poolXYK")
                                        )
                                    ),
                                    method = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "withdrawLiquidity")
                                        )
                                    )
                                ),
                                HistoryElementFilter(
                                    data = Optional.present(
                                        value = JSONFilter(
                                            contains = Optional.present(
                                                value = JsonObject(
                                                    content = mapOf("method" to JsonPrimitive("withdrawLiquidity"))
                                                )
                                            )
                                        )
                                    )
                                ),
                                HistoryElementFilter(
                                    module = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "referrals")
                                        )
                                    ),
                                ),
                                HistoryElementFilter(
                                    module = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "ethBridge")
                                        )
                                    ),
                                    method = Optional.present(
                                        value = StringFilter(
                                            equalTo = Optional.present(value = "transferToSidechain")
                                        )
                                    )
                                ),
                            )
                        )
                    ),
                    HistoryElementFilter(
                        data = Optional.present(
                            value = JSONFilter(
                                contains = Optional.present(
                                    value = JsonObject(
                                        content = mapOf("to" to JsonPrimitive(signAddress))
                                    )
                                )
                            )
                        ),
                        module = Optional.present(
                            value = StringFilter(
                                equalTo = Optional.present(value = "assets")
                            )
                        ),
                        method = Optional.present(
                            value = StringFilter(
                                equalTo = Optional.present(value = "transfer")
                            )
                        ),
                        execution = Optional.present(
                            value = JSONFilter(
                                contains = Optional.present(
                                    value = JsonObject(
                                        content = mapOf("success" to JsonPrimitive(true))
                                    )
                                )
                            )
                        )
                    ),
                    HistoryElementFilter(
                        data = Optional.present(
                            value = JSONFilter(
                                contains = Optional.present(
                                    value = JsonObject(
                                        content = mapOf("to" to JsonPrimitive(signAddress))
                                    )
                                )
                            )
                        ),
                        module = Optional.present(
                            value = StringFilter(
                                equalTo = Optional.present(value = "referrals")
                            )
                        ),
                        method = Optional.present(
                            value = StringFilter(
                                equalTo = Optional.present(value = "setReferrer")
                            )
                        ),
                        execution = Optional.present(
                            value = JSONFilter(
                                contains = Optional.present(
                                    value = JsonObject(
                                        content = mapOf("success" to JsonPrimitive(true))
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )

}