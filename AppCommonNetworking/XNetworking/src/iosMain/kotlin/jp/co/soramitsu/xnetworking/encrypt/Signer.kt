package jp.co.soramitsu.xnetworking.encrypt

import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.substrate.Sr25519Keypair

actual class Signer : BaseSigner() {

    actual override fun signSr25519(message: ByteArray, keypair: Sr25519Keypair): SignatureWrapper {
        TODO()
    }

    actual override fun verifySr25519(
        message: ByteArray,
        signature: ByteArray,
        publicKeyBytes: ByteArray
    ): Boolean {
        TODO()
    }

    actual override fun signEd25519(message: ByteArray, keypair: Keypair): SignatureWrapper {
        TODO()
    }

    actual override fun verifyEd25519(
        message: ByteArray,
        signature: ByteArray,
        publicKeyBytes: ByteArray
    ): Boolean {
        TODO()
    }

    actual override fun signEcdsa(
        message: ByteArray,
        keypair: Keypair,
        hasher: (ByteArray) -> ByteArray
    ): SignatureWrapper {
        TODO()
    }
}