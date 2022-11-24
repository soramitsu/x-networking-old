package jp.co.soramitsu.xnetworking.wsrpc.request.runtime.account

import jp.co.soramitsu.xnetworking.runtime.Module
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.storage.GetStorageRequest

class AccountInfoRequest(publicKey: ByteArray) : GetStorageRequest(
    listOf(
        Module.System.Account.storageKey(publicKey)
    )
)
