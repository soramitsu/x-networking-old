package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.EncryptionType
import jp.co.soramitsu.xnetworking.encrypt.junction.Junction
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.generate

object SubstrateKeypairFactory {

    private val sr25519SubstrateKeypairFactory by lazy { Sr25519SubstrateKeypairFactory() }
    private val ed25519SubstrateKeypairFactory by lazy { Ed25519SubstrateKeypairFactory() }
    private val ecdsaSubstrateKeypairFactory by lazy { ECDSASubstrateKeypairFactory() }

    fun generate(
        encryptionType: EncryptionType,
        seed: ByteArray,
        junctions: List<Junction> = emptyList()
    ): Keypair = when (encryptionType) {
        EncryptionType.SR25519 -> sr25519SubstrateKeypairFactory.generate(seed, junctions)
        EncryptionType.ED25519 -> ed25519SubstrateKeypairFactory.generate(seed, junctions)
        EncryptionType.ECDSA -> ecdsaSubstrateKeypairFactory.generate(seed, junctions)
    }
}
