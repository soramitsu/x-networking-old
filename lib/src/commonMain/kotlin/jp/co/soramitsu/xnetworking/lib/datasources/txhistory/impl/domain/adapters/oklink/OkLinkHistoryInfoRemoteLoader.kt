package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.oklink

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

class OkLinkHistoryInfoRemoteLoader(
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

        val assetSymbol = requireNotNull(config?.assets?.find { it.id == assetId }?.symbol) {
            "Symbol for asset with id - $assetId - is null."
        }

        val requestUrl = requireNotNull(config?.externalApi?.history?.url) {
            "Url for OkLink blockExplorer on chain with id - $chainId - is null."
        }

        val response = restClient.get(
            request = OkLinkRequest(
                url = requestUrl,
                address = signAddress,
                symbol = assetSymbol,
                apiKey = apiKeys[chainId]!!,
            ),
            kSerializer = OkLinkResponse.serializer()
        )

        check(response.code == 0) {
            "OkLink exception: code - ${response.code}, message - ${response.msg}."
        }

        val historyItems = response.data.firstOrNull()?.items ?: emptyList()

        return TxHistoryInfo(
            endCursor = null,
            endReached = true,
            items = historyItems.map {
                TxHistoryItem(
                    id = it.txId,
                    blockHash = it.blockHash,
                    module = "",
                    method = it.methodId,
                    timestamp = it.transactionTime.toString(),
                    nestedData = emptyList(),
                    networkFee = it.txFee,
                    success = it.state == "success",
                    data = listOf(
                        TxHistoryItemParam(
                            "height",
                            it.height
                        ),
                        TxHistoryItemParam(
                            "tokenId",
                            it.tokenId
                        ),
                        TxHistoryItemParam(
                            "amount",
                            it.amount
                        ),
                        TxHistoryItemParam(
                            "to",
                            it.to
                        ),
                        TxHistoryItemParam(
                            "isToContract",
                            it.isToContract.toString()
                        ),
                        TxHistoryItemParam(
                            "from",
                            it.from
                        ),
                        TxHistoryItemParam(
                            "isFromContract",
                            it.isFromContract.toString()
                        ),
                        TxHistoryItemParam(
                            "originHash",
                            it.l1OriginHash
                        ),
                        TxHistoryItemParam(
                            "transactionSymbol",
                            it.transactionSymbol
                        ),
                        TxHistoryItemParam(
                            "contractAddress",
                            it.tokenContractAddress
                        ),
                        TxHistoryItemParam(
                            "state",
                            it.state
                        )
                    )
                )
            }
        )
    }

}

