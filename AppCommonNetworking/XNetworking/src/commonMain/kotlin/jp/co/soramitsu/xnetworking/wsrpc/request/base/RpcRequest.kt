package jp.co.soramitsu.xnetworking.wsrpc.request.base

import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.RuntimeRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
abstract class RpcRequest(
    @SerialName("jsonrpc")
    val jsonRpc: String = "2.0"
)

fun Json.encodeToString(rpcRequest: RpcRequest): String {
    return when (rpcRequest) {
        is RuntimeRequest -> {
            encodeToString(rpcRequest)
        }
        else -> ""
    }
}