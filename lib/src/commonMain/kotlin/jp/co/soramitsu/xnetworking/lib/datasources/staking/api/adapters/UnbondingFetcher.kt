package jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

fun interface UnbondingFetcher {

    @Throws(
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    suspend fun fetch(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding>

}