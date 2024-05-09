package jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api

import com.apollographql.apollo3.exception.ApolloException
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.FiatDataResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.ReferrerRewardResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.core.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

interface BlockExplorerRepository {

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    suspend fun getAssetsInfo(
        chainId: String,
        tokenIds: List<String>,
        timeStamp: Int
    ): List<AssetsInfoResponse>

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    suspend fun getFiat(
        chainId: String
    ): List<FiatDataResponse>

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    suspend fun getReferrerRewards(
        chainId: String,
        address: String
    ): List<ReferrerRewardResponse>

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    suspend fun getSbApyInfo(
        chainId: String
    ): List<SbApyInfoResponse>

}