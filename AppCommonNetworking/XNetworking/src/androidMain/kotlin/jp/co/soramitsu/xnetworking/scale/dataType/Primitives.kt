package jp.co.soramitsu.xnetworking.scale.dataType

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.writer.StringWriter
import java.io.ByteArrayOutputStream

abstract class BaseStringScaleTransformer<T>: StringScaleTransformer<T> {

    abstract fun read(reader: ScaleCodecReader): T

    abstract fun write(writer: ScaleCodecWriter, value: T)

    override fun encode(value: T): ByteArray {
        val stream = ByteArrayOutputStream()
        val writer = ScaleCodecWriter(stream)
        write(writer, value)
        return stream.toByteArray()
    }

    override fun decode(bytes: ByteArray): T {
        return read(ScaleCodecReader(bytes))
    }
}

actual class StringScaleType: BaseStringScaleTransformer<String>(), StringScaleTransformer<String> {

    actual override fun conformsType(value: Any?): Boolean {
        return value is String
    }

    override fun read(reader: ScaleCodecReader): String {
        return reader.readString()
    }

    override fun write(writer: ScaleCodecWriter, value: String) {
        return writer.write(StringWriter(), value)
    }
}

/*
object boolean : DataType<Boolean>() {
    override fun read(reader: ScaleCodecReader): Boolean {
        return reader.readBoolean()
    }

    override fun write(writer: ScaleCodecWriter, value: Boolean) = writer.write(BoolWriter(), value)

    override fun conformsType(value: Any?) = value is Boolean
}

object byteArray : DataType<ByteArray>() {
    override fun read(reader: ScaleCodecReader): ByteArray {
        val readByteArray = reader.readByteArray()
        return readByteArray
    }

    override fun write(writer: ScaleCodecWriter, value: ByteArray) {
        writer.writeByteArray(value)
    }

    override fun conformsType(value: Any?) = value is ByteArray
}

class byteArraySized(private val length: Int) : DataType<ByteArray>() {
    override fun read(reader: ScaleCodecReader): ByteArray {
        val readByteArray = reader.readByteArray(length)
        return readByteArray
    }

    override fun write(writer: ScaleCodecWriter, value: ByteArray) = writer.directWrite(value, 0, length)

    override fun conformsType(value: Any?) = value is ByteArray
}
*/
