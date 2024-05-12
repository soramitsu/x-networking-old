package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api

import com.apollographql.apollo3.exception.ApolloException
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

enum class TxFilter {
    EXTRINSIC,
    REWARD,
    TRANSFER
}

fun interface HistoryInfoRemoteLoader {

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    suspend fun loadHistoryInfo(
        pageCount: Int,
        cursor: String?,
        signAddress: String,
        chainId: String,
        assetId: String,
        filters: Set<TxFilter>
    ): TxHistoryInfo

}