package jp.co.soramitsu.xnetworking.encrypt.keypair.ethereum

import jp.co.soramitsu.xnetworking.encrypt.junction.Junction
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.derivePublicKeyEcdsa
import jp.co.soramitsu.xnetworking.encrypt.keypair.generate
import jp.co.soramitsu.xnetworking.encrypt.keypair.substrate.KeypairWithSeed

object EthereumKeypairFactory {

    fun generate(seed: ByteArray, junctions: List<Junction>): Keypair {
        return Bip32KeypairFactory.generate(seed, junctions)
    }

    fun createWithPrivateKey(privateKeyBytes: ByteArray): Keypair {
        return KeypairWithSeed(
            seed = privateKeyBytes,
            privateKey = privateKeyBytes,
            publicKey = derivePublicKeyEcdsa(privateKeyOrSeed = privateKeyBytes)
        )
    }
}
