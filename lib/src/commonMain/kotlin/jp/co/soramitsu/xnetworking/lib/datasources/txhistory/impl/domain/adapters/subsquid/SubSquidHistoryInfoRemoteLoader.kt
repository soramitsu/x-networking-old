package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.subsquid

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class SubSquidHistoryInfoRemoteLoader(
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
        val requestUrl = requireNotNull(config?.externalApi?.history?.url) {
            "Url for SubSquid blockExplorer on chain with id - $chainId - is null."
        }

        val connection = restClient.post(
            request = SubSquidRequest(
                url = requestUrl,
                address = signAddress,
                limit = pageCount,
                cursor = cursor
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                SubSquidResponse.serializer()
            )
        ).data.historyElementsConnection

        return TxHistoryInfo(
            endCursor = connection.pageInfo.endCursor,
            endReached = connection.pageInfo.hasNextPage?.not() ?: true,
            items = connection.edges.map { it.node }.mapNotNull { node ->
                node.transfer?.let {
                    TxHistoryItem(
                        id = node.extrinsicIdx ?: "",
                        blockHash = node.extrinsicHash ?: "",
                        module = "",
                        method = "",
                        timestamp = node.timestamp.toString(),
                        nestedData = emptyList(),
                        networkFee = it.fee ?: "0",
                        success = node.success,
                        data = listOf(
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

                node.reward?.let {
                    TxHistoryItem(
                        id = node.extrinsicIdx ?: "",
                        blockHash = node.extrinsicHash ?: "",
                        module = "",
                        method = "",
                        timestamp = node.timestamp.toString(),
                        nestedData = emptyList(),
                        networkFee = "0",
                        success = node.success,
                        data = listOf(
                            TxHistoryItemParam(
                                "amount",
                                it.amount
                            ),
                            TxHistoryItemParam(
                                "era",
                                it.era.toString()
                            ),
                            TxHistoryItemParam(
                                "stash",
                                it.stash ?: ""
                            ),
                        )
                    )
                }?.run { return@mapNotNull this }

                return@mapNotNull null
            }
        )
    }

}