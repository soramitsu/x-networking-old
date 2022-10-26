package jp.co.soramitsu.xnetworking.txhistory.client.sorawallet

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.xnetworking.txhistory.HistoryDatabaseProvider
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryInfo
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryResult
import jp.co.soramitsu.xnetworking.txhistory.client.SubQueryClient
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.soraHistoryGraphQLRequest
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.sora.SoraSubQueryResponse
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.sora.SoraWalletSubQueryHistoryMapper
import kotlin.coroutines.cancellation.CancellationException

class SubQueryClientForSoraWallet(
    networkClient: SoramitsuNetworkClient,
    baseUrl: String,
    pageSize: Int,
    historyDatabaseProvider: HistoryDatabaseProvider,
) {

    private val client: SubQueryClient<SoraSubQueryResponse, TxHistoryItem> =
        SubQueryClient(
            networkClient = networkClient,
            baseUrl = baseUrl,
            pageSize = pageSize,
            deserializationStrategy = SoraSubQueryResponse.serializer(),
            jsonToHistoryInfo = { response -> SoraWalletSubQueryHistoryMapper.map(response) },
            historyInfoToResult = { it },
            historyRequest = soraHistoryGraphQLRequest(),
            historyDatabaseProvider = historyDatabaseProvider,
        )

    fun clearAllData() = client.clearAllData()

    fun clearData(address: String, networkName: String) =
        client.clearData(address, networkName)

    fun getTransactionPeers(query: String, networkName: String): List<String> =
        client.getTransactionPeers(query, networkName)

    fun getTransactionHistoryCached(
        address: String,
        networkName: String,
        count: Int,
        filter: ((TxHistoryItem) -> Boolean)? = null,
    ): List<TxHistoryItem> = client.getTransactionHistoryCached(address, networkName, count, filter)

    fun getTransactionCached(
        address: String,
        networkName: String,
        txHash: String,
    ): TxHistoryInfo = client.getTransactionCached(address, networkName, txHash)

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getTransactionHistoryPaged(
        address: String,
        networkName: String,
        page: Long,
        url: String? = null,
        filter: ((TxHistoryItem) -> Boolean)? = null
    ): TxHistoryResult<TxHistoryItem> = client.getTransactionHistoryPaged(
        address, networkName, page, url, filter
    )
}
