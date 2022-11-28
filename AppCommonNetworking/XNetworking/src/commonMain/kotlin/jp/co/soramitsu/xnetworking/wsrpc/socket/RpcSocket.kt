package jp.co.soramitsu.xnetworking.wsrpc.socket

import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClientProvider
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClientProviderImpl
import jp.co.soramitsu.xnetworking.wsrpc.logging.Logger
import jp.co.soramitsu.xnetworking.wsrpc.request.base.RpcRequest
import jp.co.soramitsu.xnetworking.wsrpc.request.base.encodeToString
import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import jp.co.soramitsu.xnetworking.wsrpc.subscription.response.SubscriptionChange
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface RpcSocketListener {
    suspend fun onResponse(rpcResponse: RpcResponse)

    suspend fun onResponse(subscriptionChange: SubscriptionChange)

    fun onSocketClosed()

    fun onConnected()
}

class RpcSocket(
    private val url: String,
    private var listener: RpcSocketListener,
    private val logger: Logger? = null,
    private val json: Json,
    connectTimeoutMillis: Long = 10_000,
    logging: Boolean = false,
    provider: SoramitsuNetworkClientProvider = SoramitsuNetworkClientProviderImpl()
) {
    private var socketSession: DefaultClientWebSocketSession? = null
    val networkClient = provider.provide(
        logging = logging,
        requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS,
        connectTimeoutMillis = connectTimeoutMillis,
        socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS,
        json = json
    )

    suspend fun connectAsync() {
        log("Connecting", url)

        networkClient.webSocket(url) {
            try {
                socketSession = this
                listener.onConnected()

                listenIncomingMessages()
            } catch (e: Exception) {
                println("Error while connecting")
            } finally {
                listener.onSocketClosed()
            }
        }
    }

    private suspend fun DefaultClientWebSocketSession.listenIncomingMessages() {
        try {
            while (true) {
                for (message in incoming) {
                    message as? Frame.Text ?: continue
                    val text = message.readText()

                    if (isSubscriptionChange(text)) {
                        this@RpcSocket.listener.onResponse(json.decodeFromString<SubscriptionChange>(text))
                    } else {
                        this@RpcSocket.listener.onResponse(json.decodeFromString<RpcResponse>(text))
                    }
                }
                delay(100)
            }
        } catch (e: Exception) {
            println("Error while receiving: ${e.message}")
        }
    }

    suspend fun disconnect() {
        socketSession?.close()
        log("Disconnected", url)
    }

    suspend fun sendRpcRequest(rpcRequest: RpcRequest) {
        try {
            val text = json.encodeToString(rpcRequest)

            log("Sending", text)

            socketSession?.send(Frame.Text(text))
        } catch (e: Exception) {
            println("Error while sending: ${e.message}")
        }
    }

    private fun log(topic: String, message: Any?) {
        logger?.log("\t[SOCKET][${topic.toUpperCase()}] $message")
    }

    private fun isSubscriptionChange(string: String): Boolean {
        return string.contains("\"subscription\":")
    }
}
