package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.reef

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class ReefHistoryInfoRemoteLoader(
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
            "Url for Reef blockExplorer on chain with id - $chainId - is null."
        }

        val cursors = cursor.orEmpty().split(";")
            .associate {
                val module = it.substringBefore(delimiter = ":", missingDelimiterValue = "")
                val cursor = it.substringAfter(delimiter = ":", missingDelimiterValue = "")

                module to cursor
            }.filter { it.key.isNotBlank() && it.value.isNotBlank() }

        val items = mutableListOf<TxHistoryItem>()

        val nextCursorBuilder = StringBuilder()
        var isNextCursorAvailable = false

        if (TxFilter.TRANSFER in filters) {
            val response = restClient.post(
                request = ReefRequest(
                    url = requestUrl,
                    address = signAddress,
                    limit = pageCount,
                    cursor = cursors["transfers"],
                    txFilter = TxFilter.TRANSFER
                ),
                kSerializer = GraphQLResponseDataWrapper.serializer(
                    ReefResponse.serializer()
                )
            ).data.transfersConnection

            response?.edges?.map {
                TxHistoryItem(
                    id = it.node.id!!,
                    blockHash = it.node.extrinsicHash ?: "",
                    module = "",
                    method = "",
                    timestamp = it.node.timestamp,
                    nestedData = emptyList(),
                    networkFee = it.node.signedData?.fee?.partialFee ?: "",
                    success = it.node.success,
                    data = listOf(
                        TxHistoryItemParam(
                            "amount",
                            it.node.amount
                        ),
                        TxHistoryItemParam(
                            "to",
                            it.node.to.id
                        ),
                        TxHistoryItemParam(
                            "from",
                            it.node.from.id
                        ),
                    )
                )
            }?.run {
                items += this

                isNextCursorAvailable =
                    isNextCursorAvailable || response.pageInfo.hasNextPage

                with(nextCursorBuilder) {
                    append("transfers")
                    append(":")
                    append(response.pageInfo.endCursor)
                    append(";")
                }
            }
        }

        if (TxFilter.REWARD in filters) {
            val response = restClient.post(
                request = ReefRequest(
                    url = requestUrl,
                    address = signAddress,
                    limit = pageCount,
                    cursor = cursors["rewards"],
                    txFilter = TxFilter.REWARD
                ),
                kSerializer = GraphQLResponseDataWrapper.serializer(
                    ReefResponse.serializer()
                )
            ).data.rewardsConnection

            response?.edges?.map {
                TxHistoryItem(
                    id = it.node.id,
                    blockHash = "",
                    module = "",
                    method = "",
                    timestamp = it.node.timestamp,
                    nestedData = emptyList(),
                    networkFee = "",
                    success = true,
                    data = listOf(
                        TxHistoryItemParam(
                            "accountId",
                            it.node.signer.id
                        ),
                        TxHistoryItemParam(
                            "amount",
                            it.node.amount
                        )
                    )
                )
            }?.run {
                items += this

                isNextCursorAvailable =
                    isNextCursorAvailable || response.pageInfo.hasNextPage

                with(nextCursorBuilder) {
                    append("rewards")
                    append(":")
                    append(response.pageInfo.endCursor)
                    append(";")
                }
            }
        }

        if (TxFilter.EXTRINSIC in filters) {
            val response = restClient.post(
                request = ReefRequest(
                    url = requestUrl,
                    address = signAddress,
                    limit = pageCount,
                    cursor = cursors["extrinsics"],
                    txFilter = TxFilter.EXTRINSIC
                ),
                kSerializer = GraphQLResponseDataWrapper.serializer(
                    ReefResponse.serializer()
                )
            ).data.extrinsicsConnection

            response?.edges?.map {
                TxHistoryItem(
                    id = it.node.id,
                    blockHash = it.node.hash,
                    module = it.node.section,
                    method = it.node.method,
                    timestamp = it.node.timestamp,
                    nestedData = emptyList(),
                    networkFee = it.node.signedData?.fee?.partialFee ?: "",
                    success = true,
                    data = listOf(
                        TxHistoryItemParam(
                            "accountId",
                            it.node.signer
                        ),
                    )
                )
            }?.run {
                items += this

                isNextCursorAvailable =
                    isNextCursorAvailable || response.pageInfo.hasNextPage

                with(nextCursorBuilder) {
                    append("extrinsics")
                    append(":")
                    append(response.pageInfo.endCursor)
                }
            }
        }

        return TxHistoryInfo(
            endCursor = nextCursorBuilder.toString(),
            endReached = !isNextCursorAvailable,
            items = items
        )
    }

}