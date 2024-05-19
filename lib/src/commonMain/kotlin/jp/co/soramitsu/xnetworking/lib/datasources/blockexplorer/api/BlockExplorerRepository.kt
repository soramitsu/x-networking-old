package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api

import com.apollographql.apollo3.exception.ApolloException
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.FiatDataResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.ReferrerRewardResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiDAOException
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

abstract class BlockExplorerRepository {

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        ExternalApiDAOException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    abstract suspend fun getAssetsInfo(
        chainId: String,
        tokenIds: List<String>,
        timeStamp: Int
    ): List<AssetsInfoResponse>

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        ExternalApiDAOException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    abstract suspend fun getFiat(
        chainId: String
    ): List<FiatDataResponse>

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        ExternalApiDAOException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    abstract suspend fun getReferrerRewards(
        chainId: String,
        address: String
    ): List<ReferrerRewardResponse>

    @Throws(
        ApolloException::class,
        RestClientException::class,
        CancellationException::class,
        ExternalApiDAOException::class,
        IllegalArgumentException::class,
        IllegalStateException::class
    )
    abstract suspend fun getSbApyInfo(
        chainId: String
    ): List<SbApyInfoResponse>

}