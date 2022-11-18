package jp.co.soramitsu.xnetworking.extensions

import org.bouncycastle.util.encoders.Hex

actual fun ByteArray.toHexString(withPrefix: Boolean): String {
    val encoded = Hex.toHexString(this)

    return if (withPrefix) {
        HEX_PREFIX + encoded
    } else {
        encoded
    }
}

actual fun String.fromHex(): ByteArray {
    return Hex.decode(removePrefix(HEX_PREFIX))
}
