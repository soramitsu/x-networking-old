package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

actual class ECDSASubstrateKeypairFactory : BaseECDSASubstrateKeypairFactory() {
    
    actual override fun derivePublicKey(privateKeyOrSeed: ByteArray): ByteArray {
        TODO()
    }
}