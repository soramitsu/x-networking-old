package jp.co.soramitsu.xnetworking.encrypt.json.coders

import io.ktor.util.encodeBase64
import jp.co.soramitsu.xnetworking.encrypt.json.coders.type.TypeCoderFactory
import jp.co.soramitsu.xnetworking.encrypt.json.coders.type.encode
import jp.co.soramitsu.xnetworking.encrypt.model.JsonAccountData
import jp.co.soramitsu.xnetworking.encrypt.MultiChainEncryption
import jp.co.soramitsu.xnetworking.encrypt.json.coders.content.ContentCoderFactory
import jp.co.soramitsu.xnetworking.encrypt.json.coders.content.encode
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("EXPERIMENTAL_API_USAGE")
class JsonSeedEncoder(
    private val jsonFactory: Json,
) {
    fun generate(
        keypair: Keypair,
        seed: ByteArray?,
        password: String,
        name: String,
        multiChainEncryption: MultiChainEncryption,
        genesisHash: String,
        address: String
    ): String {
        val encoding = when (multiChainEncryption) {
            is MultiChainEncryption.Substrate -> {
                JsonAccountData.Encoding.substrate(multiChainEncryption.encryptionType)
            }
            MultiChainEncryption.Ethereum -> {
                JsonAccountData.Encoding.ethereum()
            }
        }
        val encoded = formEncodedField(keypair, seed, password, encoding)

        val importData = JsonAccountData(
            encoded = encoded,
            address = address,
            encoding = encoding,
            meta = JsonAccountData.Meta(
                name = name,
                whenCreated =  Clock.System.now().toEpochMilliseconds(),
                genesisHash = genesisHash
            )
        )

        return jsonFactory.encodeToString(importData)
    }

    private fun formEncodedField(
        keypair: Keypair,
        seed: ByteArray?,
        password: String,
        encoding: JsonAccountData.Encoding
    ): String {
        val contentEncoder = ContentCoderFactory.getEncoder(encoding.content)!!
        val typeEncoder = TypeCoderFactory.getEncoder(encoding.type)!!

        val encodedContent = contentEncoder.encode(keypair, seed)
        val encryptedContent = typeEncoder.encode(encodedContent, password.encodeToByteArray())

        return encryptedContent.encodeBase64()
    }
}
