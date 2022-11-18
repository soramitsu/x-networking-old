package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.Sr25519

actual class Sr25519SubstrateKeypairFactory: BaseSr25519SubstrateKeypairFactory() {

    actual override fun keypairFromSeed(seed: ByteArray): ByteArray {
        return Sr25519.keypairFromSeed(seed)
    }

    actual override fun deriveKeypairSoftBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray {
        return Sr25519.deriveKeypairSoft(keypair, chaincode)
    }

    actual override fun deriveKeypairHardBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray {
        return Sr25519.deriveKeypairHard(keypair, chaincode)
    }
}