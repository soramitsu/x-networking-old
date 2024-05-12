package jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

fun interface ValidatorsFetcher {

    @Throws(
        RestClientException::class,
        CancellationException::class,
        IllegalArgumentException::class,
        IllegalStateException::class,
        NullPointerException::class
    )
    suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String>

}