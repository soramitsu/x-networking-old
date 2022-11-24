package jp.co.soramitsu.xnetworking.wsrpc.request.runtime.system

import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.RuntimeRequest

private const val METHOD = "system_chain"

class NodeNetworkTypeRequest : RuntimeRequest(METHOD, listOf())
