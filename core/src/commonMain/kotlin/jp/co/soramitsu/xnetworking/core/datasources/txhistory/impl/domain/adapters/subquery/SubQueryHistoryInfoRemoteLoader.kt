package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.subquery

import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.core.engines.rest.api.RestClient

class SubQueryHistoryInfoRemoteLoader(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val restClient: RestClient
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
        val stakingType = config?.assets?.find { it.id == assetId }?.staking
        val requestUrl = requireNotNull(config?.externalApi?.history?.url) {
            "Url for SubQuery blockExplorer on chain with id - $chainId - is null."
        }

        val historyElements = restClient.post(
            request = SubQueryRequest(
                url = requestUrl,
                cursor = cursor,
                pageCount = pageCount,
                address = signAddress,
                filters = filters,
                requestRewards = stakingType != null
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubQueryResponse.serializer()
            )
        ).data.historyElements

        return TxHistoryInfo(
            endCursor = historyElements.pageInfo.endCursor,
            endReached = historyElements.pageInfo.endCursor == null,
            items = historyElements.nodes.mapNotNull { node ->
                node.transfer?.let {
                    TxHistoryItem(
                        id = node.id,
                        blockHash = it.block,
                        module = "",
                        method = "",
                        timestamp = node.timestamp,
                        nestedData = emptyList(),
                        networkFee = it.fee,
                        success = it.success,
                        data = listOfNotNull(
                            it.assetId?.let { tokenId ->
                                TxHistoryItemParam(
                                    "tokenId",
                                    tokenId
                                )
                            },
                            TxHistoryItemParam(
                                "amount",
                                it.amount
                            ),
                            TxHistoryItemParam(
                                "to",
                                it.to
                            ),
                            TxHistoryItemParam(
                                "from",
                                it.from
                            ),
                        )
                    )
                }?.run { return@mapNotNull this }

                node.extrinsic?.let {
                    TxHistoryItem(
                        id = node.id,
                        blockHash = it.hash,
                        module = it.module,
                        method = it.call,
                        timestamp = node.timestamp,
                        nestedData = emptyList(),
                        networkFee = it.fee,
                        success = it.success,
                        data = listOfNotNull(
                            it.assetId?.let { tokenId ->
                                TxHistoryItemParam(
                                    "tokenId",
                                    tokenId
                                )
                            }
                        )
                    )
                }?.run { return@mapNotNull this }

                node.reward?.let {
                    TxHistoryItem(
                        id = node.id,
                        blockHash = "",
                        module = "",
                        method = "",
                        timestamp = node.timestamp,
                        nestedData = emptyList(),
                        networkFee = "0",
                        success = true,
                        data = listOfNotNull(
                            it.assetId?.let { tokenId ->
                                TxHistoryItemParam(
                                    "tokenId",
                                    tokenId
                                )
                            },
                            TxHistoryItemParam(
                                "amount",
                                it.amount
                            ),
                            TxHistoryItemParam(
                                "validator",
                                it.validator
                            ),
                            TxHistoryItemParam(
                                "isReward",
                                it.isReward.toString()
                            ),
                            TxHistoryItemParam(
                                "era",
                                it.era.toString()
                            ),
                        )
                    )
                }?.run { return@mapNotNull this }

                return@mapNotNull null
            }
        )
    }

}