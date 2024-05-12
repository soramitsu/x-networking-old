package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api

import com.apollographql.apollo3.exception.ApolloException
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.wrappers.TxHistoryResult
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

interface TxHistoryRepository {

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun getTransactionPeers(
        query: String,
        chainId: String
    ): List<String>

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun getTransactionHistoryCached(
        count: Int,
        address: String,
        chainId: String,
    ): List<TxHistoryItem>

    @Throws(
        CancellationException::class,
        IllegalArgumentException::class
    )
    fun getTransactionCached(
        txHash: String,
        address: String,
        chainId: String,
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
        pageCount: Int,
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
        chainId: String
    )

    fun clearAllData()

}