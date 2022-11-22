package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

actual class Sr25519SubstrateKeypairFactory: BaseSr25519SubstrateKeypairFactory() {

    actual override fun keypairFromSeed(seed: ByteArray): ByteArray {
        TODO()
    }

    actual override fun deriveKeypairSoftBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray {
        TODO()
    }

    actual override fun deriveKeypairHardBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray {
        TODO()
    }
}