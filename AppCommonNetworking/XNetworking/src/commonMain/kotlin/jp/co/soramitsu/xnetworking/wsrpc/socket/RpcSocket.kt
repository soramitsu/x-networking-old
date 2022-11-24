package jp.co.soramitsu.xnetworking.wsrpc.socket

import io.ktor.client.plugins.websocket.WebSocketException
import jp.co.soramitsu.xnetworking.wsrpc.logging.Logger
import jp.co.soramitsu.xnetworking.wsrpc.request.base.RpcRequest
import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import jp.co.soramitsu.xnetworking.wsrpc.subscription.response.SubscriptionChange
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

interface RpcSocketListener {
    fun onResponse(rpcResponse: RpcResponse)

    fun onResponse(subscriptionChange: SubscriptionChange)

    fun onStateChanged(newState: WebSocketState)

    fun onConnected()
}

private const val PING_INTERVAL_SECONDS = 30

class RpcSocket(
    private val url: String,
    listener: RpcSocketListener,
    private val logger: Logger? = null,
    factory: WebSocketFactory,
    private val json: Json
) {
    val ws = factory.createSocket(url)

    init {
        setupListener(listener)

        ws.pingInterval = PING_INTERVAL_SECONDS.seconds.inWholeMilliseconds
    }

    fun connectAsync() {
        log("Connecting", url)

        ws.connectAsynchronously()
    }

    fun clearListeners() {
        ws.clearListeners()
    }

    fun disconnect() {
        ws.disconnect()

        log("Disconnected", url)
    }

    fun sendRpcRequest(rpcRequest: RpcRequest) {
        val text = json.encodeToString(rpcRequest)

        log("Sending", text)

        ws.sendText(text)
    }

    private fun setupListener(listener: RpcSocketListener) {
        ws.addListener(object : WebSocketAdapter() {

            override fun onTextMessage(websocket: WebSocket, text: String) {
                log("Received", text)

                if (isSubscriptionChange(text)) {
                    listener.onResponse(json.decodeFromString<SubscriptionChange>(text))
                } else {
                    listener.onResponse(json.decodeFromString<RpcResponse>(text))
                }
            }

            override fun onPongFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
                log("Received", "Pong")
            }

            override fun onError(websocket: WebSocket, cause: WebSocketException) {
                log("Error", cause.message)
            }

            override fun onConnectError(websocket: WebSocket, exception: WebSocketException) {
                log("Failed to connect", exception.message)
            }

            override fun onStateChanged(websocket: WebSocket, newState: WebSocketState) {
                log("State", newState)

                listener.onStateChanged(newState)
            }

            override fun onConnected(
                websocket: WebSocket?,
                headers: MutableMap<String, MutableList<String>>?
            ) {
                log("Connected", url)

                listener.onConnected()
            }
        })
    }

    private fun log(topic: String, message: Any?) {
        logger?.log("\t[SOCKET][${topic.toUpperCase()}] $message")
    }

    private fun isSubscriptionChange(string: String): Boolean {
        return string.contains("\"subscription\":")
    }
}
