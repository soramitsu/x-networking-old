package jp.co.soramitsu.xnetworking.common.exceptions

expect open class IOException(message: String?, cause: Throwable?) : Exception {
    constructor(message: String? = null)
}