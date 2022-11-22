package jp.co.soramitsu.xnetworking.encrypt.keypair

import com.ionspin.kotlin.bignum.integer.BigInteger

actual fun compressedPublicKeyFromPrivateEcdsa(privKey: BigInteger): ByteArray {
    TODO()
}

actual fun decompressedAsIntEcdsa(compressedKey: ByteArray): BigInteger {
    TODO()
}

actual fun decompressedEcdsa(compressedKey: ByteArray): ByteArray {
    TODO()
}

actual fun derivePublicKeyEcdsa(privateKeyOrSeed: ByteArray): ByteArray {
    TODO()
}
