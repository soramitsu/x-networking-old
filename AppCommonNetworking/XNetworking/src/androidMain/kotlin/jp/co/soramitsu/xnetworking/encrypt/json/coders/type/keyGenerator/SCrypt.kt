package jp.co.soramitsu.xnetworking.encrypt.json.coders.type.keyGenerator

import org.bouncycastle.crypto.generators.SCrypt

actual fun generateSCrypt(
    password: ByteArray,
    salt: ByteArray,
    N: Int,
    r: Int,
    p: Int,
    scryptKeyLength: Int
): ByteArray {
    return SCrypt.generate(password, salt, N, r, p, scryptKeyLength)
}