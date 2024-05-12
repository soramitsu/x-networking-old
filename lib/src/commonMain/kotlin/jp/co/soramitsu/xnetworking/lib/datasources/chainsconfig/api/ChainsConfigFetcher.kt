package jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlin.coroutines.cancellation.CancellationException

interface ChainsConfigFetcher {
    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    suspend fun loadConfigOrGetCached(): Map<String, ChainsConfig>
}

