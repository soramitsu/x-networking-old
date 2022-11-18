package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.keypair.BaseKeypair

abstract class BaseEd25519SubstrateKeypairFactory : OtherSubstrateKeypairFactory() {

    override val hardDerivationPrefix: String = "Ed25519HDKD"

    override fun deriveFromSeed(seed: ByteArray): KeypairWithSeed {
        val baseKeyPair = generateBaseKeypair(seed)
        return KeypairWithSeed(
            seed = seed,
            privateKey = baseKeyPair.privateKey,
            publicKey = baseKeyPair.publicKey
        )
    }

    protected abstract fun generateBaseKeypair(seed: ByteArray): BaseKeypair
}

expect class Ed25519SubstrateKeypairFactory() : BaseEd25519SubstrateKeypairFactory {
    override fun generateBaseKeypair(seed: ByteArray): BaseKeypair
}