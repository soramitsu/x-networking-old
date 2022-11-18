package jp.co.soramitsu.xnetworking.encrypt.keypair

import com.ionspin.kotlin.bignum.integer.BigInteger

expect fun compressedPublicKeyFromPrivateEcdsa(privKey: BigInteger): ByteArray

expect fun decompressedAsIntEcdsa(compressedKey: ByteArray): BigInteger

expect fun decompressedEcdsa(compressedKey: ByteArray): ByteArray

expect fun derivePublicKeyEcdsa(privateKeyOrSeed: ByteArray): ByteArray
