package jp.co.soramitsu.xnetworking.scale.dataType.utils

import com.ionspin.kotlin.bignum.integer.BigInteger as SharedBigIngeger

fun SharedBigIngeger.toUnsignedBytes(): ByteArray {
    var bytes = toByteArray()

    if (bytes.first() == 0.toByte() && bytes.size > 1) {
        bytes = bytes.drop(1).toByteArray()
    }

    return bytes
}