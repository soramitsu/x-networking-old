package jp.co.soramitsu.xnetworking.encrypt.keypair

import com.ionspin.kotlin.bignum.integer.BigInteger
import org.spongycastle.jce.ECNamedCurveTable
import org.spongycastle.util.encoders.Hex
import org.web3j.crypto.Sign
import com.ionspin.kotlin.bignum.integer.Sign as BigNumSign

actual fun compressedPublicKeyFromPrivateEcdsa(privKey: BigInteger): ByteArray {
    val point = Sign.publicPointFromPrivate(privKey.toJavaBigInteger())
    return point.getEncoded(true)
}

private fun BigInteger.toJavaBigInteger(): java.math.BigInteger {
    return java.math.BigInteger(toByteArray())
}

private fun java.math.BigInteger.toSharedBigInteger(): BigInteger {
    val signum = signum()
    return BigInteger.fromByteArray(
        toByteArray(),
        sign = when {
            signum > 0 -> BigNumSign.POSITIVE
            signum == 0 -> BigNumSign.ZERO
            else -> BigNumSign.NEGATIVE
        }
    )
}

actual fun decompressedAsIntEcdsa(compressedKey: ByteArray): BigInteger {
    val decompressedArray = decompressedEcdsa(compressedKey)

    return Hex.toHexString(byteArrayOf(0x00) + decompressedArray).toBigInteger(16).toSharedBigInteger()
}

actual fun decompressedEcdsa(compressedKey: ByteArray): ByteArray {
    val spec = ECNamedCurveTable.getParameterSpec("secp256k1")
    val point = spec.curve.decodePoint(compressedKey)
    val x: ByteArray = point.xCoord.encoded
    val y: ByteArray = point.yCoord.encoded

    return x + y
}

actual fun derivePublicKeyEcdsa(privateKeyOrSeed: ByteArray): ByteArray {
    val privateKeyInt = BigInteger.parseString(Hex.toHexString(privateKeyOrSeed), 16)
    return compressedPublicKeyFromPrivateEcdsa(privateKeyInt)
}
