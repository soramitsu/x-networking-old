package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.westend

import com.apollographql.apollo3.api.Optional
import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.core.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.fearlesswallet.GetFearlessHistoryElementsQuery
import jp.co.soramitsu.xnetworking.fearlesswallet.type.HistoryElementsOrderBy

class WestendHistoryInfoRemoteLoader(
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
            "Url for WestEnd blockExplorer on chain with id - $chainId - is null."
        }

        val response = requireNotNull(
            apolloClientStore.query(
                requestUrl,
                GetFearlessHistoryElementsQuery(
                    pageCount = pageCount,
                    cursor = cursor.orEmpty(),
                    address = signAddress,
                    orderBy = Optional.present(listOf(HistoryElementsOrderBy.TIMESTAMP_DESC)),
                )
            ).historyElements
        ) { "GetHistoryElementsQuery response is null" }

        // Unparsing JSON scalar and normally typed contents
        val items =
            response.nodes.filterNotNull().map {
                val rewards = it.reward?.asJsonObjectNullable?.let { jsonObject ->
                        listOf(
                            TxHistoryItemParam(
                                "amount",
                                jsonObject.getPrimitiveContentOrEmpty("amount")
                            ),
                            TxHistoryItemParam(
                                "era",
                                jsonObject.getPrimitiveContentOrEmpty("era")
                            ),
                            TxHistoryItemParam(
                                "isReward",
                                jsonObject.getPrimitiveContentOrEmpty("isReward")
                            ),
                            TxHistoryItemParam(
                                "validator",
                                jsonObject.getPrimitiveContentOrEmpty("validator")
                            ),
                        )
                    }

                val transfers = it.transfer?.asJsonObjectNullable?.let { jsonObject ->
                        listOf(
                            TxHistoryItemParam(
                                "block",
                                jsonObject.getPrimitiveContentOrEmpty("block")
                            ),
                            TxHistoryItemParam(
                                "amount",
                                jsonObject.getPrimitiveContentOrEmpty("amount")
                            ),
                            TxHistoryItemParam(
                                "fee",
                                jsonObject.getPrimitiveContentOrEmpty("fee")
                            ),
                            TxHistoryItemParam(
                                "to",
                                jsonObject.getPrimitiveContentOrEmpty("to")
                            ),
                            TxHistoryItemParam(
                                "from",
                                jsonObject.getPrimitiveContentOrEmpty("from")
                            ),
                            TxHistoryItemParam(
                                "extrinsicHash",
                                jsonObject.getPrimitiveContentOrEmpty("extrinsicHash")
                            ),
                            TxHistoryItemParam(
                                "success",
                                jsonObject.getPrimitiveContentOrEmpty("success")
                            )
                        )
                    }

                val extrinsics = it.extrinsic?.asJsonObjectNullable?.let { jsonObject ->
                        listOf(
                            TxHistoryItemParam(
                                "call",
                                jsonObject.getPrimitiveContentOrEmpty("call")
                            ),
                            TxHistoryItemParam(
                                "fee",
                                jsonObject.getPrimitiveContentOrEmpty("fee")
                            ),
                            TxHistoryItemParam(
                                "hash",
                                jsonObject.getPrimitiveContentOrEmpty("hash")
                            ),
                            TxHistoryItemParam(
                                "module",
                                jsonObject.getPrimitiveContentOrEmpty("module")
                            ),
                            TxHistoryItemParam(
                                "success",
                                jsonObject.getPrimitiveContentOrEmpty("success")
                            ),
                        )
                    }

                TxHistoryItem(
                    id = it.id,
                    blockHash = "",
                    module = when {
                        rewards != null -> "reward"
                        transfers != null -> "transfer"
                        extrinsics != null -> "extrinsic"
                        else -> ""
                    },
                    method = "",
                    timestamp = it.timestamp,
                    networkFee = "",
                    success = true,
                    nestedData = null,
                    data = when {
                        rewards != null -> rewards
                        transfers != null -> transfers
                        extrinsics != null -> extrinsics
                        else -> null
                    },
                )
            }

        return TxHistoryInfo(
            endCursor = response.pageInfo.endCursor,
            endReached = response.pageInfo.hasNextPage,
            items = items
        )
    }

}