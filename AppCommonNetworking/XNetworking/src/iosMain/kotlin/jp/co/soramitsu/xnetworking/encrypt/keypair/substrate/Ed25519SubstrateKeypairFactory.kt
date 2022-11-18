package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.keypair.BaseKeypair

actual class Ed25519SubstrateKeypairFactory: BaseEd25519SubstrateKeypairFactory() {

    actual override fun generateBaseKeypair(seed: ByteArray): BaseKeypair {
        return BaseKeypair(
            privateKey = ByteArray(0),
            publicKey = ByteArray(0)
        )
    }
}