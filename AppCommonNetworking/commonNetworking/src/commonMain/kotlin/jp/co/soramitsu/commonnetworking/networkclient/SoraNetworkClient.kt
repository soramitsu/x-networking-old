package jp.co.soramitsu.commonnetworking.networkclient

import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.serialization.SerializationException
import kotlin.coroutines.cancellation.CancellationException

interface SoraNetworkClient {

    @Throws(SoraNetworkException::class, CancellationException::class)
    suspend fun get(url: String): String
}

@Throws(SoraNetworkException::class, CancellationException::class)
suspend inline fun <reified Value : Any> SoraNetworkClient.createJsonRequest(
    path: String,
    methodType: HttpMethod = HttpMethod.Get,
    body: Any = EmptyContent
): Value =
    createRequest(path, methodType, body, ContentType.Application.Json)

@Throws(SoraNetworkException::class, CancellationException::class)
suspend inline fun <reified Value : Any> SoraNetworkClient.createJsonRequest(
    path: String,
    methodType: HttpMethod = HttpMethod.Get,
    body: Any = EmptyContent,
    headers: List<Pair<String, String>>,
): Value =
    createRequest(path, methodType, body, ContentType.Application.Json, headers)

@Throws(SoraNetworkException::class, CancellationException::class)
suspend inline fun <reified Value : Any> SoraNetworkClient.createRequest(
    path: String,
    methodType: HttpMethod = HttpMethod.Get,
    body: Any = EmptyContent,
    contentType: ContentType? = null,
    headersList: List<Pair<String, String>>? = null
): Value {
    @Suppress("SwallowedException")
    return wrapInExceptionHandler {
        this as SoraNetworkClientImpl
        httpClient.request {
            if (!headersList.isNullOrEmpty()) {
                headers {
                    headersList.forEach { pair ->
                        append(pair.first, pair.second)
                    }
                }
            }
            method = methodType
            url(path)
            if (contentType != null) contentType(contentType)
            this.body = body
        }
    }
}

@Throws(SoraNetworkException::class)
inline fun <reified Type : Any> wrapInExceptionHandler(block: () -> Type): Type {
    return try {
        block.invoke()
    } catch (e: ResponseException) {
        val code: Int = when (e) {
            is RedirectResponseException -> 3
            is ClientRequestException -> 4
            is ServerResponseException -> 5
            else -> 0
        }
        throw CodeNetworkException(code, e.message.orEmpty(), e.cause)
    } catch (e: SerializationException) {
        throw SerializationNetworkException(e.message.orEmpty(), e.cause)
    } catch (e: Throwable) {
        throw GeneralNetworkException(e.message.orEmpty(), e.cause)
    }
}
