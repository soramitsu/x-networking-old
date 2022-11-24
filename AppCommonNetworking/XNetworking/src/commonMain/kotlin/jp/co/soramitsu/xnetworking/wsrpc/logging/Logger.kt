package jp.co.soramitsu.xnetworking.wsrpc.logging

interface Logger {
    fun log(message: String?)

    fun log(throwable: Throwable?)
}
