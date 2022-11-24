package jp.co.soramitsu.xnetworking.wsrpc.socket

import io.ktor.client.plugins.websocket.WebSocketException
import jp.co.soramitsu.xnetworking.common.exceptions.IOException

// TODO: Replace this mock to objects for working with WS in KMM

enum class WebSocketState {
    CREATED,
    CONNECTING,
    OPEN,
    CLOSING,
    CLOSED
}


class WebSocketFactory {

    @Throws(IOException::class)
    fun createSocket(uri: String): WebSocket {
        return WebSocket()
    }
}

class WebSocket {

    var pingInterval: Long = 0

    fun connectAsynchronously() {
    }

    fun clearListeners() {
    }

    fun disconnect() {
    }

    fun sendText(text: String) {
    }

    fun addListener(adapter: WebSocketAdapter) {
    }
}

class WebSocketFrame

open class WebSocketAdapter {

    open fun onTextMessage(websocket: WebSocket, text: String) {

    }

    open fun onPongFrame(websocket: WebSocket?, frame: WebSocketFrame?) {

    }

    open fun onError(websocket: WebSocket, cause: WebSocketException) {

    }

    open fun onConnectError(websocket: WebSocket, exception: WebSocketException) {

    }

    open fun onStateChanged(websocket: WebSocket, newState: WebSocketState) {

    }

    open fun onConnected(
        websocket: WebSocket?,
        headers: MutableMap<String, MutableList<String>>?
    ) {

    }
}