package jp.co.soramitsu.xnetworking.encrypt.json.coders.content.secretCoder

import jp.co.soramitsu.xnetworking.encrypt.EncryptionType
import jp.co.soramitsu.xnetworking.encrypt.MultiChainEncryption
import jp.co.soramitsu.xnetworking.encrypt.convert_Ed25519_to_Sr25519_Bytes
import jp.co.soramitsu.xnetworking.encrypt.convert_Sr25519_to_Ed25519_Bytes
import jp.co.soramitsu.xnetworking.encrypt.json.coders.content.JsonContentDecoder
import jp.co.soramitsu.xnetworking.encrypt.json.coders.content.JsonSecretCoder
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.substrate.Sr25519Keypair

object Sr25519JsonSecretCoder : JsonSecretCoder {

    override fun encode(keypair: Keypair, seed: ByteArray?): List<ByteArray> {
        require(keypair is Sr25519Keypair)

        val ed25519BytesSecret = convert_Sr25519_to_Ed25519_Bytes(keypair.privateKey + keypair.nonce)

        return listOf(ed25519BytesSecret, keypair.publicKey)
    }

    override fun decode(data: List<ByteArray>): JsonContentDecoder.SecretDecoder.DecodedSecret {
        require(data.size == 2) { "Unknown secret format. Size: ${data.size}." }

        val (privateKeyCompressed, publicKey) = data

        val privateAndNonce = convert_Ed25519_to_Sr25519_Bytes(privateKeyCompressed)

        val keypair = Sr25519Keypair(
            privateAndNonce.copyOfRange(0, 32),
            publicKey,
            privateAndNonce.copyOfRange(32, 64)
        )

        return JsonContentDecoder.SecretDecoder.DecodedSecret(
            seed = null,
            multiChainEncryption = MultiChainEncryption.Substrate(EncryptionType.SR25519),
            keypair = keypair
        )
    }
}