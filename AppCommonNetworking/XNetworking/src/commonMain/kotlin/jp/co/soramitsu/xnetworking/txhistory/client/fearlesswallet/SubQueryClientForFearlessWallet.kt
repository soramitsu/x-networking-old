package jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.txhistory.HistoryDatabaseProvider
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryInfo
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryResult
import jp.co.soramitsu.xnetworking.txhistory.client.SubQueryClient
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.fearlessHistoryGraphQLRequest
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.fearless.FearlessSubQueryResponse
import jp.co.soramitsu.xnetworking.txhistory.subquery.response.fearless.FearlessWalletSubQueryHistoryMapper
import kotlin.coroutines.cancellation.CancellationException

class SubQueryClientForFearlessWallet(
    networkClient: SoramitsuNetworkClient,
    pageSize: Int,
    historyDatabaseProvider: HistoryDatabaseProvider,
) {

    private val client: SubQueryClient<FearlessSubQueryResponse, TxHistoryItem> =
        SubQueryClient(
            networkClient = networkClient,
            pageSize = pageSize,
            deserializationStrategy = FearlessSubQueryResponse.serializer(),
            jsonToHistoryInfo = { response -> FearlessWalletSubQueryHistoryMapper.map(response) },
            historyInfoToResult = { it },
            historyRequest = fearlessHistoryGraphQLRequest(),
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
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getTransactionHistoryPaged(
        address: String,
        networkName: String,
        page: Long,
        url: String,
        filter: ((TxHistoryItem) -> Boolean)? = null
    ): TxHistoryResult<TxHistoryItem> = client.getTransactionHistoryPaged(
        address, networkName, page, url, filter
    )
}