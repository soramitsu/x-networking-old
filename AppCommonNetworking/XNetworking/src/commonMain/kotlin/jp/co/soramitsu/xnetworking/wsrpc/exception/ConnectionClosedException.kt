package jp.co.soramitsu.xnetworking.wsrpc.exception

class ConnectionClosedException : Exception() {

    override fun toString(): String = this::class.simpleName.orEmpty()
}
