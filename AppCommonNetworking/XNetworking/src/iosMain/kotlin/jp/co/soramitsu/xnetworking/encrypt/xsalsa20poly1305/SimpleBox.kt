package jp.co.soramitsu.xnetworking.encrypt.xsalsa20poly1305

actual class SimpleBox {
    private val box: SecretBox

    constructor(secretKey: ByteArray) {
        box = SecretBox(secretKey)
    }

    constructor(publicKey: ByteArray?, privateKey: ByteArray?) {
        box = SecretBox(publicKey, privateKey)
    }

    actual fun seal(plaintext: ByteArray): ByteArray {
        TODO()
    }

    actual fun open(ciphertext: ByteArray): ByteArray {
        TODO()
    }
}
