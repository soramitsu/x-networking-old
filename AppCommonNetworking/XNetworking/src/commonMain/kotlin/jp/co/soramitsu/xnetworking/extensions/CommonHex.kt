package jp.co.soramitsu.xnetworking.extensions

const val HEX_PREFIX = "0x"

fun String.requirePrefix(prefix: String): String {
    return if (startsWith(prefix)) this else prefix + this
}

fun String.requireHexPrefix(): String {
    return requirePrefix(HEX_PREFIX)
}

fun Byte.toHex(withPrefix: Boolean): String {
    return byteArrayOf(this).toHexString(withPrefix)
}

expect fun ByteArray.toHexString(withPrefix: Boolean = false): String

expect fun String.fromHex(): ByteArray