package jp.co.soramitsu.xnetworking.encrypt.json.coders.type.keyGenerator

actual fun generateSCrypt(
    password: ByteArray,
    salt: ByteArray,
    N: Int,
    r: Int,
    p: Int,
    scryptKeyLength: Int
): ByteArray {
    TODO()
}