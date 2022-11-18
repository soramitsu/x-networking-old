package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.keypair.derivePublicKeyEcdsa

actual class ECDSASubstrateKeypairFactory : BaseECDSASubstrateKeypairFactory() {

    actual override fun derivePublicKey(privateKeyOrSeed: ByteArray): ByteArray {
        return derivePublicKeyEcdsa(privateKeyOrSeed)
    }
}