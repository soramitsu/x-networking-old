package jp.co.soramitsu.xnetworking.encrypt

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

actual fun ByteArray.hmacSHA256(secret: ByteArray): ByteArray {
    return hmac(secret, "HmacSHA256")
}

actual fun ByteArray.hmacSHA512(secret: ByteArray): ByteArray {
    return hmac(secret, "HmacSHA512")
}

private fun ByteArray.hmac(secret: ByteArray, shaAlgorithm: String): ByteArray {
    val chiper: Mac = Mac.getInstance(shaAlgorithm)
    val secretKeySpec = SecretKeySpec(secret, shaAlgorithm)
    chiper.init(secretKeySpec)

    return chiper.doFinal(this)
}
