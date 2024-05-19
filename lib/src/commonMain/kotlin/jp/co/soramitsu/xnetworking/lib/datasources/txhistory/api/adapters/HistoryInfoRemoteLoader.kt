package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.adapters

import com.apollographql.apollo3.exception.ApolloException
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

enum class TxFilter {
    EXTRINSIC,
    REWARD,
    TRANSFER
}

sealed interface ChainInfo {

    val chainId: String

    class Simple(
        override val chainId: String
    ): ChainInfo

    class WithEthereumType(
        override val chainId: String,
        val contractAddress: String,
        val ethereumType: String?
    ): ChainInfo

    class WithAssetSymbol(
        override val chainId: String,
        val symbol: String
    ): ChainInfo

}

abstract class HistoryInfoRemoteLoader {

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    abstract suspend fun loadHistoryInfo(
        pageCount: Int,
        cursor: String?,
        signAddress: String,
        chainInfo: ChainInfo,
        filters: Set<TxFilter>
    ): TxHistoryInfo

}