package jp.co.soramitsu.xnetworking.wsrpc.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class RpcResponse(
    @SerialName("jsonrpc")
    val jsonrpc: String,
    @SerialName("result")
    val result: JsonObject? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("error")
    val error: RpcError? = null
) {
    override fun toString(): String = "RpcResponse($id)"
}

@Serializable
class RpcError(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)
