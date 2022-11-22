package jp.co.soramitsu.xnetworking.encrypt.junction

import com.ditchoom.buffer.ByteOrder
import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.wrap
import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.hash.blake2b128
import jp.co.soramitsu.xnetworking.scale.dataType.stringScale

private const val CHAINCODE_LENGTH = 32

object SubstrateJunctionDecoder : JunctionDecoder() {

    override fun decodeJunction(rawJunction: String, type: JunctionType): Junction {
        val chainCode = normalize(serialize(rawJunction))

        return Junction(type, chainCode)
    }

    private fun serialize(rawJunction: String): ByteArray {
        rawJunction.toLongOrNull()?.let {
            val bytes = ByteArray(8)
            PlatformBuffer.wrap(bytes, byteOrder = ByteOrder.LITTLE_ENDIAN).write(it)
            return bytes
        }

        return runCatching {
            rawJunction.fromHex()
        }.getOrElse {
            stringScale.encode(rawJunction)
        }
    }

    private fun normalize(bytes: ByteArray): ByteArray = when {
        bytes.size < CHAINCODE_LENGTH -> ByteArray(CHAINCODE_LENGTH).apply {
            bytes.copyInto(this)
        }
        bytes.size > CHAINCODE_LENGTH -> bytes.blake2b128()
        else -> bytes
    }
}
