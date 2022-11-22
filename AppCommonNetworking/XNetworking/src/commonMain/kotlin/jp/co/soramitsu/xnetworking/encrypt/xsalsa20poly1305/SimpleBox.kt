package jp.co.soramitsu.xnetworking.encrypt.xsalsa20poly1305

expect class SimpleBox {

    fun seal(plaintext: ByteArray): ByteArray

    fun open(ciphertext: ByteArray): ByteArray
}
