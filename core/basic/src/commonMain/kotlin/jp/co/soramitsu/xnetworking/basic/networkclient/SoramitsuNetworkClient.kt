package jp.co.soramitsu.xnetworking.basic.networkclient

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class SoramitsuNetworkClient(
    timeout: Long = 10000,
    logging: Boolean = false,
    provider: SoramitsuHttpClientProvider = SoramitsuHttpClientProviderImpl()
) {
    val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    val httpClient: HttpClient = provider.provide(
        NetworkClientConfig(
            logging = logging,
            requestTimeoutMillis = timeout,
            connectTimeoutMillis = timeout,
            socketTimeoutMillis = timeout,
            json = json,
            webSocketClientConfig = null
        )
    )

    suspend fun get(url: String): String {
        return wrapInExceptionHandler {
            httpClient.get(url).body()
        }
    }

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend inline fun <reified Value : Any> createJsonRequest(
        path: String,
        methodType: HttpMethod = HttpMethod.Get,
        body: Any = EmptyContent,
        headers: List<Pair<String, String>>? = null,
    ): Value =
        createRequest(path, methodType, body, ContentType.Application.Json, headers)

    @Throws(SoramitsuNetworkException::class, CancellationException::class)
    suspend inline fun <reified Value : Any> createRequest(
        path: String,
        methodType: HttpMethod = HttpMethod.Get,
        body: Any = EmptyContent,
        contentType: ContentType? = null,
        headersList: List<Pair<String, String>>? = null
    ): Value {
        @Suppress("SwallowedException")
        return wrapInExceptionHandler {
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
                setBody(body)
            }.body()
        }
    }

    @Throws(SoramitsuNetworkException::class)
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
            throw SoramitsuNetworkException(e.message.orEmpty(), e.cause, CodeNetworkException(code))
        } catch (e: SerializationException) {
            throw SoramitsuNetworkException(e.message.orEmpty(), e.cause, SerializationNetworkException())
        } catch (e: Throwable) {
            throw SoramitsuNetworkException(e.message.orEmpty(), e.cause, GeneralNetworkException())
        }
    }
}

operator fun Headers.plus(other: Headers): Headers = when {
    this.isEmpty() -> other
    other.isEmpty() -> this
    else -> Headers.build {
        appendAll(this@plus)
        appendAll(other)
    }
}
