package jp.co.soramitsu.xnetworking.common.exceptions

actual open class IOException actual constructor(
    message: String?,
    cause: Throwable?
) : Exception(message, cause) {
    actual constructor(message: String?) : this(message, null)
}