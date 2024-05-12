package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.zeta

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class ZetaHistoryInfoRemoteLoader(
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
        val assetType = config?.assets?.find { it.id == assetId }?.ethereumType
        val requestUrl = requireNotNull(config?.externalApi?.history?.url) {
            "Url for Zeta blockExplorer on chain with id - $chainId - is null."
        }

        val response = restClient.get(
            request = when(assetType) {
                "normal" -> TransactionsZetaRequest(
                    url = requestUrl,
                    address = signAddress
                )
                else -> TokenTransfersZetaRequest(
                    url = requestUrl,
                    address = signAddress,
                    assetId = assetId
                )
            },
            kSerializer = ZetaResponse.serializer()
        )

        return TxHistoryInfo(
            endCursor = null,
            endReached = true,
            items = response.items.map {
                TxHistoryItem(
                    id = it.txHash ?: "",
                    blockHash = it.hash ?: "",
                    module = "",
                    method = "",
                    timestamp = it.timestamp,
                    nestedData = emptyList(),
                    networkFee = it.fee?.value ?: "0",
                    success = true,
                    data = listOf(
                        TxHistoryItemParam(
                            "amount",
                            it.total?.value ?: "0"
                        ),
                        TxHistoryItemParam(
                            "to",
                            it.to.hash
                        ),
                        TxHistoryItemParam(
                            "from",
                            it.from.hash
                        ),
                    )
                )
            }
        )
    }

}