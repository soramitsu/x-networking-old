package jp.co.soramitsu.xnetworking.encrypt.xsalsa20poly1305

actual class SecretBox actual constructor(secretKey: ByteArray) {

    actual constructor(
        publicKey: ByteArray?,
        privateKey: ByteArray?
    ) : this(
        sharedSecret(
            publicKey,
            privateKey
        )
    )

    actual fun seal(nonce: ByteArray?, plaintext: ByteArray): ByteArray {
        TODO()
    }

    actual fun open(
        nonce: ByteArray?,
        ciphertext: ByteArray
    ): ByteArray {
        TODO()
    }

    actual fun nonce(): ByteArray {
        TODO()
    }

    actual fun nonce(message: ByteArray): ByteArray {
        TODO()
    }
}
