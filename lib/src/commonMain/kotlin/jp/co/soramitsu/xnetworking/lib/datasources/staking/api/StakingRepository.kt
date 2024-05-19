package jp.co.soramitsu.xnetworking.lib.datasources.staking.api

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

abstract class StakingRepository {

    @Throws(
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    abstract suspend fun getUnbondingsList(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding>

    @Throws(
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    abstract suspend fun getValidatorsList(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String>

    @Throws(
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    abstract suspend fun getApy(
        chainId: String,
        selectedCandidates: List<String>? = null
    ): Map<String, String?>

}