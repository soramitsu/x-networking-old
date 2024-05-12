package jp.co.soramitsu.xnetworking.lib.engines.rest.api

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import kotlinx.serialization.KSerializer
import kotlin.coroutines.cancellation.CancellationException

interface RestClient {

    enum class ContentType {
        JSON, NONE
    }

    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    suspend fun <T> post(
        request: AbstractRestServerRequest.WithBody,
        kSerializer: KSerializer<T>
    ): T

    @Throws(
        RestClientException::class,
        CancellationException::class
    )
    suspend fun <T> get(
        request: AbstractRestServerRequest,
        kSerializer: KSerializer<T>
    ): T
}