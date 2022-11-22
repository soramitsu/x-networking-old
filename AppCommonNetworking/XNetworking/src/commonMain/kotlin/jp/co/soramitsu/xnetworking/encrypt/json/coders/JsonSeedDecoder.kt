package jp.co.soramitsu.xnetworking.encrypt.json.coders

import io.ktor.util.decodeBase64Bytes
import jp.co.soramitsu.xnetworking.encrypt.json.coders.JsonSeedDecodingException.IncorrectPasswordException
import jp.co.soramitsu.xnetworking.encrypt.json.coders.JsonSeedDecodingException.InvalidJsonException
import jp.co.soramitsu.xnetworking.encrypt.json.coders.type.TypeCoderFactory
import jp.co.soramitsu.xnetworking.encrypt.json.coders.type.decode
import jp.co.soramitsu.xnetworking.encrypt.model.ImportAccountData
import jp.co.soramitsu.xnetworking.encrypt.model.ImportAccountMeta
import jp.co.soramitsu.xnetworking.encrypt.model.JsonAccountData
import jp.co.soramitsu.xnetworking.encrypt.model.NetworkTypeIdentifier
import jp.co.soramitsu.xnetworking.encrypt.EncryptionType
import jp.co.soramitsu.xnetworking.encrypt.MultiChainEncryption
import jp.co.soramitsu.xnetworking.encrypt.json.coders.content.ContentCoderFactory
import jp.co.soramitsu.xnetworking.encrypt.json.coders.content.decode
import jp.co.soramitsu.xnetworking.ss58.SS58Encoder.addressByteOrNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

sealed class JsonSeedDecodingException : Exception() {
    class InvalidJsonException : JsonSeedDecodingException()
    class IncorrectPasswordException : JsonSeedDecodingException()
    class UnsupportedEncryptionTypeException : JsonSeedDecodingException()
}

private fun MultiChainEncryption.Companion.from(name: String): MultiChainEncryption {
    return when (name) {
        "ethereum" -> MultiChainEncryption.Ethereum
        else -> {
            MultiChainEncryption.Substrate(EncryptionType.fromString(name))
        }
    }
}

class JsonSeedDecoder(private val jsonFactory: Json) {

    fun extractImportMetaData(json: String): ImportAccountMeta {
        val jsonData = decodeJson(json)

        try {
            val address = jsonData.address
            val networkType = getNetworkTypeIdentifier(address, jsonData.meta.genesisHash)

            val encryptionTypeRaw = jsonData.encoding.content[1]
            val multiChainEncryption = MultiChainEncryption.from(encryptionTypeRaw)

            val name = jsonData.meta.name

            return ImportAccountMeta(name, networkType, multiChainEncryption)
        } catch (_: Exception) {
            throw InvalidJsonException()
        }
    }

    fun decode(json: String, password: String): ImportAccountData {
        val jsonData = decodeJson(json)

        try {
            return decode(jsonData, password)
        } catch (exception: IncorrectPasswordException) {
            throw exception
        } catch (_: Exception) {
            throw InvalidJsonException()
        }
    }

    private fun decode(jsonData: JsonAccountData, password: String): ImportAccountData {
        val username = jsonData.meta.name

        val networkTypeIdentifier = getNetworkTypeIdentifier(
            jsonData.address,
            jsonData.meta.genesisHash
        )

        val byteData = jsonData.encoded.decodeBase64Bytes()

        val typeDecoder = TypeCoderFactory.getDecoder(jsonData.encoding.type)
            ?: throw InvalidJsonException()

        val secret = typeDecoder.decode(byteData, password.encodeToByteArray())
            ?: throw IncorrectPasswordException()

        val contentDecoder = ContentCoderFactory.getDecoder(jsonData.encoding.content)
            ?: throw InvalidJsonException()

        val decodedSecret = contentDecoder.decode(secret)

        return ImportAccountData(
            decodedSecret.keypair,
            decodedSecret.multiChainEncryption,
            username,
            networkTypeIdentifier,
            decodedSecret.seed
        )
    }

    private fun validatePassword(secret: ByteArray) {
        if (secret.isEmpty()) throw IncorrectPasswordException()
    }

    private fun getNetworkTypeIdentifier(
        address: String?,
        genesisHash: String?
    ): NetworkTypeIdentifier {
        val addressByte = address?.addressByteOrNull()

        return when {
            genesisHash != null -> NetworkTypeIdentifier.Genesis(genesisHash)
            addressByte != null -> NetworkTypeIdentifier.AddressByte(addressByte)
            else -> NetworkTypeIdentifier.Undefined
        }
    }

    private fun decodeJson(json: String): JsonAccountData {
        return try {
            jsonFactory.decodeFromString(json)
        } catch (exception: Exception) {
            throw InvalidJsonException()
        }
    }
}
