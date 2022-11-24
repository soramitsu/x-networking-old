package jp.co.soramitsu.xnetworking.wsrpc.request.runtime.author

import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.RuntimeRequest

private const val METHOD = "author_pendingExtrinsics"

class PendingExtrinsicsRequest : RuntimeRequest(METHOD, listOf())
