package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.junction.Junction
import jp.co.soramitsu.xnetworking.encrypt.junction.JunctionType
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.KeypairFactory

class Sr25519Keypair(
    override val privateKey: ByteArray,
    override val publicKey: ByteArray,
    val nonce: ByteArray
) : Keypair

abstract class BaseSr25519SubstrateKeypairFactory: KeypairFactory<Sr25519Keypair> {

    protected abstract fun keypairFromSeed(seed: ByteArray): ByteArray

    protected abstract fun deriveKeypairSoftBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray

    protected abstract fun deriveKeypairHardBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray

    override fun deriveFromSeed(seed: ByteArray): Sr25519Keypair {
        val keypairBytes = keypairFromSeed(seed)
        return decodeSr25519Keypair(keypairBytes)
    }

    override fun deriveChild(parent: Sr25519Keypair, junction: Junction): Sr25519Keypair {
        return when (junction.type) {
            JunctionType.SOFT -> deriveSr25519SoftKeypair(junction.chaincode, parent)
            JunctionType.HARD -> deriveSr25519HardKeypair(junction.chaincode, parent)
        }
    }

    private fun deriveSr25519SoftKeypair(
        chaincode: ByteArray,
        previousKeypair: Sr25519Keypair
    ): Sr25519Keypair {
        val keypair = previousKeypair.privateKey + previousKeypair.nonce + previousKeypair.publicKey
        val newKeypairBytes = deriveKeypairSoftBytes(keypair, chaincode)

        return decodeSr25519Keypair(newKeypairBytes)
    }

    private fun deriveSr25519HardKeypair(
        chaincode: ByteArray,
        previousKeypair: Sr25519Keypair
    ): Sr25519Keypair {
        val keypair = previousKeypair.privateKey + previousKeypair.nonce + previousKeypair.publicKey
        val newKeypairBytes = deriveKeypairHardBytes(keypair, chaincode)

        return decodeSr25519Keypair(newKeypairBytes)
    }

    private fun decodeSr25519Keypair(bytes: ByteArray): Sr25519Keypair {
        val privateKey = bytes.copyOfRange(0, 32)
        val nonce = bytes.copyOfRange(32, 64)
        val publicKey = bytes.copyOfRange(64, bytes.size)
        return Sr25519Keypair(
            privateKey,
            publicKey,
            nonce
        )
    }
}

expect class Sr25519SubstrateKeypairFactory() : BaseSr25519SubstrateKeypairFactory {

    override fun keypairFromSeed(seed: ByteArray): ByteArray

    override fun deriveKeypairSoftBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray

    override fun deriveKeypairHardBytes(keypair: ByteArray, chaincode: ByteArray): ByteArray
}