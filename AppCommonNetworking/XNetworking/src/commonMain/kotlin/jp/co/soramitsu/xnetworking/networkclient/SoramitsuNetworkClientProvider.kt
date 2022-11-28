package jp.co.soramitsu.xnetworking.networkclient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface SoramitsuNetworkClientProvider {
    fun provide(
        logging: Boolean,
        requestTimeoutMillis: Long,
        connectTimeoutMillis: Long,
        socketTimeoutMillis: Long,
        json: Json
    ): HttpClient
}

class SoramitsuNetworkClientProviderImpl : SoramitsuNetworkClientProvider {
    override fun provide(
        logging: Boolean,
        requestTimeoutMillis: Long,
        connectTimeoutMillis: Long,
        socketTimeoutMillis: Long,
        json: Json
    ): HttpClient {
        return HttpClient(HttpEngineFactory().createEngine()) {
            if (logging) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.SIMPLE
                }
            }
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    json,
                    contentType = ContentType.Any
                )
            }
            engine {
                configure()
            }
            install(HttpTimeout) {
                this.requestTimeoutMillis = requestTimeoutMillis
                this.connectTimeoutMillis = connectTimeoutMillis
                this.socketTimeoutMillis = socketTimeoutMillis
            }
            install(WebSockets) {
                pingInterval = SoramitsuNetworkClient.WEB_SOCKET_PING_INTERVAL
            }
        }
    }
}