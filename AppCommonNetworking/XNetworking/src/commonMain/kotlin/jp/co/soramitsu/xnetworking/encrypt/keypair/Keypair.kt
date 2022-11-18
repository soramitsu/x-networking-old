package jp.co.soramitsu.xnetworking.encrypt.keypair

interface Keypair {
    val privateKey: ByteArray
    val publicKey: ByteArray
}

class BaseKeypair(
    override val privateKey: ByteArray,
    override val publicKey: ByteArray
) : Keypair
