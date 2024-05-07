package jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api

import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.core.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

interface ChainsConfigFetcher {
    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    suspend fun loadConfigOrGetCached(): Map<String, ChainsConfig>
}

