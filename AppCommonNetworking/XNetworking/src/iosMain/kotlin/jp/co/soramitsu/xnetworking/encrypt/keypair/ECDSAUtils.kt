package jp.co.soramitsu.xnetworking.encrypt.keypair

import com.ionspin.kotlin.bignum.integer.BigInteger

actual fun compressedPublicKeyFromPrivateEcdsa(privKey: BigInteger): ByteArray {
    return ByteArray(0)
}

actual fun decompressedAsIntEcdsa(compressedKey: ByteArray): BigInteger {
    return BigInteger.ZERO
}

actual fun decompressedEcdsa(compressedKey: ByteArray): ByteArray {
    return ByteArray(0)
}

actual fun derivePublicKeyEcdsa(privateKeyOrSeed: ByteArray): ByteArray {
    return ByteArray(0)
}
