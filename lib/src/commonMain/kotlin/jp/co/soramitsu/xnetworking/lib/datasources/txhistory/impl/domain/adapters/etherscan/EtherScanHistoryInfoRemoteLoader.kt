package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.etherscan

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class EtherScanHistoryInfoRemoteLoader(
    private val apiKeys: Map<String, String>,
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
            "Url for EtherScan blockExplorer on chain with id - $chainId - is null."
        }

        val response = restClient.get(
            request = when(assetType) {
                "normal" -> NormalEtherScanRequest(
                    url = requestUrl,
                    address = signAddress,
                    apiKey = apiKeys[chainId]!!
                )

                "erc20", "bep20" -> ErcBepEtherScanRequest(
                    url = requestUrl,
                    contractAddress = assetId,
                    address = signAddress,
                    apiKey = apiKeys[chainId]!!
                )

                else -> error("Unknown AssetType for EtherScan BlockExplorer")
            },
            kSerializer = EtherScanResponse.serializer()
        )

        return TxHistoryInfo(
            endCursor = null,
            endReached = true,
            items = response.result.map { historyElement ->
                TxHistoryItem(
                    id = historyElement.hash,
                    blockHash = historyElement.blockHash,
                    module = "",
                    method = "",
                    timestamp = historyElement.timeStamp.toString(),
                    nestedData = emptyList(),
                    networkFee = "0", // fee for Eth network is calculated differently between transactions
                    success = response.status == 1 && historyElement.isError == 0,
                    data = listOf(
                        TxHistoryItemParam(
                            "blockNumber",
                            historyElement.blockNumber
                        ),
                        TxHistoryItemParam(
                            "nonce",
                            historyElement.nonce
                        ),
                        TxHistoryItemParam(
                            "amount",
                            historyElement.value
                        ),
                        TxHistoryItemParam(
                            "to",
                            historyElement.to
                        ),
                        TxHistoryItemParam(
                            "from",
                            historyElement.from
                        ),
                        TxHistoryItemParam(
                            "gas",
                            historyElement.gas
                        ),
                        TxHistoryItemParam(
                            "gasPrice",
                            historyElement.gasPrice
                        ),
                        TxHistoryItemParam(
                            "gasUsed",
                            historyElement.gasUsed
                        ),
                        TxHistoryItemParam(
                            "contractAddress",
                            historyElement.contractAddress
                        )
                    )
                )
            }
        )
    }

}