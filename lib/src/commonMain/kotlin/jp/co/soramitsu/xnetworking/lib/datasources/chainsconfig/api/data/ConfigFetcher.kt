package jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.data

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlinx.serialization.json.JsonObject
import kotlin.coroutines.cancellation.CancellationException

abstract class ConfigFetcher {
    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    abstract suspend fun fetch(chainId: String): JsonObject
}

