package jp.co.soramitsu.commonnetworking.networkclient

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.Headers
import kotlinx.serialization.json.Json

class SoraNetworkClientImpl(
    private val timeout: Long = 10000,
    private val logging: Boolean = false
) : SoraNetworkClient {

    val httpClient = HttpClient(HttpEngineFactory().createEngine()) {
        if (logging) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.SIMPLE
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
            acceptContentTypes = acceptContentTypes + ContentType.Any
        }
        install(HttpTimeout) {
            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun get(url: String): String {
        return wrapInExceptionHandler {
            httpClient.get(url)
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
