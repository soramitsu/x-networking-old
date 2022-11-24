package jp.co.soramitsu.xnetworking.scale.dataType

import io.emeraldpay.polkaj.scale.writer.BoolWriter
import io.emeraldpay.polkaj.scale.writer.StringWriter
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

actual class StringScaleType: ScaleTransformer<String>() {

    actual override fun conformsType(value: Any?): Boolean {
        return value is String
    }

    actual override fun read(reader: ScaleCodecReader): String {
        return reader.readString()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: String) {
        scaleWriter.write(StringWriter(), value)
    }
}

actual class BooleanScaleType: ScaleTransformer<Boolean>() {

    actual override fun conformsType(value: Any?): Boolean {
        return value is Boolean
    }

    actual override fun read(reader: ScaleCodecReader): Boolean {
        return reader.readBoolean()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Boolean) {
        scaleWriter.write(BoolWriter(), value)
    }
}

actual class ByteArrayScaleType: ScaleTransformer<ByteArray>() {

    actual override fun conformsType(value: Any?): Boolean {
        return value is ByteArray
    }

    actual override fun read(reader: ScaleCodecReader): ByteArray {
        return reader.readByteArray()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: ByteArray) {
        scaleWriter.writeByteArray(value)
    }
}

actual class ByteArraySizedScaleType actual constructor(
    private val length: Int
): ScaleTransformer<ByteArray>() {

    actual override fun conformsType(value: Any?): Boolean {
        return value is ByteArray
    }

    actual override fun read(reader: ScaleCodecReader): ByteArray {
        return reader.readByteArray(length)
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: ByteArray) {
        scaleWriter.directWrite(value, 0, length)
    }
}
