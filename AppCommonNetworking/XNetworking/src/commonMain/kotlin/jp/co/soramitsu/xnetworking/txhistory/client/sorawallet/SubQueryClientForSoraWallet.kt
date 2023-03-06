package jp.co.soramitsu.xnetworking.txhistory.client.sorawallet

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.mainconfig.SoraRemoteConfigBuilder
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
    pageSize: Int,
    historyDatabaseProvider: HistoryDatabaseProvider,
    private val soraRemoteConfigBuilder: SoraRemoteConfigBuilder,
) {

    private val networkName = "SORA"

    private val client: SubQueryClient<SoraSubQueryResponse, TxHistoryItem> =
        SubQueryClient(
            networkClient = networkClient,
            pageSize = pageSize,
            deserializationStrategy = SoraSubQueryResponse.serializer(),
            jsonToHistoryInfo = { response -> SoraWalletSubQueryHistoryMapper.map(response) },
            historyInfoToResult = { it },
            historyRequest = soraHistoryGraphQLRequest(),
            historyDatabaseProvider = historyDatabaseProvider,
        )

    fun clearAllData() = client.clearAllData()

    fun clearData(address: String) =
        client.clearData(address, networkName)

    fun getTransactionPeers(query: String): List<String> =
        client.getTransactionPeers(query, networkName)

    fun getTransactionHistoryCached(
        address: String,
        count: Int,
        filter: ((TxHistoryItem) -> Boolean)? = null,
    ): List<TxHistoryItem> = client.getTransactionHistoryCached(address, networkName, count, filter)

    fun getTransactionCached(
        address: String,
        txHash: String,
    ): TxHistoryInfo = client.getTransactionCached(address, networkName, txHash)

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getTransactionHistoryPaged(
        address: String,
        page: Long,
        filter: ((TxHistoryItem) -> Boolean)? = null
    ): TxHistoryResult<TxHistoryItem>? {
        val explorerUrl = soraRemoteConfigBuilder.getConfig()?.blockExplorerUrl ?: return null
        return client.getTransactionHistoryPaged(
            address, networkName, page, explorerUrl, filter
        )
    }
}
