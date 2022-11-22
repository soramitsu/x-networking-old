package jp.co.soramitsu.xnetworking.encrypt

@Suppress("FunctionName")
actual fun convert_Sr25519_to_Ed25519_Bytes(bytes: ByteArray): ByteArray {
    return Sr25519.toEd25519Bytes(bytes)
}

@Suppress("FunctionName")
actual fun convert_Ed25519_to_Sr25519_Bytes(bytes: ByteArray): ByteArray {
    return Sr25519.fromEd25519Bytes(bytes)
}
