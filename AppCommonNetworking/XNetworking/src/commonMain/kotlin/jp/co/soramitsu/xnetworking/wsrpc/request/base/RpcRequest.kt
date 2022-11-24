package jp.co.soramitsu.xnetworking.wsrpc.request.base

import kotlinx.serialization.SerialName

abstract class RpcRequest(
    @SerialName("jsonrpc")
    val jsonRpc: String = "2.0"
)
