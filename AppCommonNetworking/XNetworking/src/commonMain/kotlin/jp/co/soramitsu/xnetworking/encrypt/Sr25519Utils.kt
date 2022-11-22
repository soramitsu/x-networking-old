package jp.co.soramitsu.xnetworking.encrypt

@Suppress("FunctionName")
expect fun convert_Sr25519_to_Ed25519_Bytes(bytes: ByteArray): ByteArray

@Suppress("FunctionName")
expect fun convert_Ed25519_to_Sr25519_Bytes(bytes: ByteArray): ByteArray
