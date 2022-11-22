package jp.co.soramitsu.xnetworking.encrypt.xsalsa20poly1305


expect class SecretBox(secretKey: ByteArray) {

    constructor(
        publicKey: ByteArray?,
        privateKey: ByteArray?
    )

    fun seal(nonce: ByteArray?, plaintext: ByteArray): ByteArray

    fun open(
        nonce: ByteArray?,
        ciphertext: ByteArray
    ): ByteArray

    fun nonce(): ByteArray

    fun nonce(message: ByteArray): ByteArray
}
