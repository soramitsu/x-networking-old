package jp.co.soramitsu.xnetworking.wsrpc.response

import kotlinx.serialization.SerialName

class RpcResponse(
    @SerialName("jsonrpc")
    val jsonrpc: String,
    @SerialName("result")
    val result: Any?,
    @SerialName("id")
    val id: Int,
    val error: RpcError?
) {
    override fun toString(): String = "RpcResponse($id)"
}

class RpcError(val code: Int, val message: String)
