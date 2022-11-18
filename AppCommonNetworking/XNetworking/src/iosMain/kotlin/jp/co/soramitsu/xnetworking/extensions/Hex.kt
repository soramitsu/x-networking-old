package jp.co.soramitsu.xnetworking.extensions

actual fun ByteArray.toHexString(withPrefix: Boolean): String {
    return ""
}

actual fun String.fromHex(): ByteArray {
    return ByteArray(0)
}