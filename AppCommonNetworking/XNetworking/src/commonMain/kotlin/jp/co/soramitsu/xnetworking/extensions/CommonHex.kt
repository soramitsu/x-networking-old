package jp.co.soramitsu.xnetworking.extensions

import com.soywiz.krypto.encoding.hex
import com.soywiz.krypto.encoding.unhex

private const val HEX_PREFIX = "0x"

fun String.requirePrefix(prefix: String): String {
    return if (startsWith(prefix)) this else prefix + this
}

fun String.requireHexPrefix(): String {
    return requirePrefix(HEX_PREFIX)
}

fun Byte.toHex(withPrefix: Boolean): String {
    return byteArrayOf(this).toHexString(withPrefix)
}

fun ByteArray.toHexString(withPrefix: Boolean = false): String {
    return if (withPrefix) {
        HEX_PREFIX + hex
    } else {
        hex
    }
}

fun String.fromHex(): ByteArray {
    return removePrefix(HEX_PREFIX).unhex
}