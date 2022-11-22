package jp.co.soramitsu.xnetworking.encrypt

import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.substrate.Sr25519Keypair
import jp.co.soramitsu.xnetworking.hash.blake2b256
import jp.co.soramitsu.xnetworking.hash.keccak256

private val signer by lazy { Signer() }

abstract class BaseSigner {

    private enum class MessageHashing(val hasher: (ByteArray) -> ByteArray) {
        SUBSTRATE(hasher = { it.blake2b256() }),
        ETHEREUM(hasher = { it.keccak256() })
    }

    fun sign(
        multiChainEncryption: MultiChainEncryption,
        message: ByteArray,
        keypair: Keypair
    ): SignatureWrapper {
        return when (multiChainEncryption) {
            is MultiChainEncryption.Ethereum -> {
                signEcdsa(message, keypair, MessageHashing.ETHEREUM.hasher)
            }
            is MultiChainEncryption.Substrate -> {
                when (multiChainEncryption.encryptionType) {

                    EncryptionType.SR25519 -> {
                        require(keypair is Sr25519Keypair) {
                            "Sr25519Keypair is needed to sign with SR25519"
                        }

                        signSr25519(message, keypair)
                    }

                    EncryptionType.ED25519 -> signEd25519(message, keypair)

                    EncryptionType.ECDSA -> signEcdsa(
                        message,
                        keypair,
                        MessageHashing.SUBSTRATE.hasher
                    )
                }
            }
        }
    }

    protected abstract fun signSr25519(
        message: ByteArray,
        keypair: Sr25519Keypair
    ): SignatureWrapper

    abstract fun verifySr25519(
        message: ByteArray,
        signature: ByteArray,
        publicKeyBytes: ByteArray
    ): Boolean

    protected abstract fun signEd25519(
        message: ByteArray,
        keypair: Keypair
    ): SignatureWrapper

    abstract fun verifyEd25519(
        message: ByteArray,
        signature: ByteArray,
        publicKeyBytes: ByteArray
    ): Boolean

    protected abstract fun signEcdsa(
        message: ByteArray,
        keypair: Keypair,
        hasher: (ByteArray) -> ByteArray
    ): SignatureWrapper
}

expect class Signer(): BaseSigner {

    override fun signSr25519(message: ByteArray, keypair: Sr25519Keypair): SignatureWrapper

    override fun verifySr25519(
        message: ByteArray,
        signature: ByteArray,
        publicKeyBytes: ByteArray
    ): Boolean

    override fun signEd25519(message: ByteArray, keypair: Keypair): SignatureWrapper

    override fun verifyEd25519(
        message: ByteArray,
        signature: ByteArray,
        publicKeyBytes: ByteArray
    ): Boolean

    override fun signEcdsa(
        message: ByteArray,
        keypair: Keypair,
        hasher: (ByteArray) -> ByteArray
    ): SignatureWrapper
}
