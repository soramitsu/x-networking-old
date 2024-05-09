package jp.co.soramitsu.xnetworking.core.datasources.txhistory.api

import com.apollographql.apollo3.exception.ApolloException
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.wrappers.TxHistoryResult
import jp.co.soramitsu.xnetworking.core.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

interface TxHistoryRepository {

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun getTransactionPeers(
        query: String,
        networkName: String
    ): List<String>

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun getTransactionHistoryCached(
        count: Int,
        address: String,
        networkName: String,
    ): List<TxHistoryItem>

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun getTransactionCached(
        txHash: String,
        address: String,
        networkName: String,
    ): TxHistoryInfo

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    suspend fun getTransactionHistoryPaged(
        address: String,
        page: Long,
        networkName: String,
        chainId: String,
        assetId: String,
        filters: Set<TxFilter>
    ): TxHistoryResult<TxHistoryItem>

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun clearData(
        address: String,
        networkName: String
    )

    fun clearAllData()

}