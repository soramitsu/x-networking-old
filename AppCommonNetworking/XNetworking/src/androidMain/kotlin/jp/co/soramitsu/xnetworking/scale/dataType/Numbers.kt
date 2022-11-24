package jp.co.soramitsu.xnetworking.scale.dataType

import com.ditchoom.buffer.ByteOrder
import com.ionspin.kotlin.bignum.integer.BigInteger
import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.reader.CompactBigIntReader
import jp.co.soramitsu.xnetworking.common.toJavaBigInteger
import jp.co.soramitsu.xnetworking.common.toSharedBigInteger
import jp.co.soramitsu.xnetworking.extensions.fromUnsignedBytes
import jp.co.soramitsu.xnetworking.scale.dataType.utils.CompactBigIntWriter

actual class ByteScaleType: ScaleTransformer<Byte>() {

    actual override fun read(reader: ScaleCodecReader): Byte {
        return reader.readByte()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Byte) {
        scaleWriter.writeByte(value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is Byte
    }
}

actual class UInt8ScaleType: ScaleTransformer<UByte>() {

    actual override fun read(reader: ScaleCodecReader): UByte {
        return reader.readUByte().toUByte()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: UByte) {
        scaleWriter.writeByte(value.toInt())
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is UByte
    }
}

actual class UInt16ScaleType: ScaleTransformer<Int>() {

    actual override fun read(reader: ScaleCodecReader): Int {
        return reader.readUint16()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Int) {
        scaleWriter.writeUint16(value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is Int
    }
}

actual class UInt32ScaleType: ScaleTransformer<UInt>() {

    actual override fun read(reader: ScaleCodecReader): UInt {
        return reader.readUint32().toUInt()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: UInt) {
        scaleWriter.writeUint32(value.toLong())
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is UInt
    }
}

actual class LongScaleType: ScaleTransformer<Long>() {

    actual override fun read(reader: ScaleCodecReader): Long {
        return reader.readLong()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Long) {
        scaleWriter.writeLong(value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is Long
    }
}

actual open class UIntScaleType actual constructor(
    private val size: Int
): ScaleTransformer<BigInteger>() {

    actual override fun read(reader: ScaleCodecReader): BigInteger {
        val bytes = reader.readByteArray(size)
        return bytes.fromUnsignedBytes(ByteOrder.LITTLE_ENDIAN)
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: BigInteger) {
        val array = value.toByteArray()
        val padded = ByteArray(size)
        val startAt = padded.size - array.size
        array.copyInto(padded, startAt)
        scaleWriter.directWrite(padded.reversedArray(), 0, size)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is BigInteger
    }
}

actual class CompactIntScaleType: ScaleTransformer<BigInteger>() {

    private val compactIntReader = CompactBigIntReader()
    private val compactIntWriter = CompactBigIntWriter()

    actual override fun read(reader: ScaleCodecReader): BigInteger {
        return compactIntReader.read(reader).toSharedBigInteger()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: BigInteger) {
        compactIntWriter.write(scaleWriter, value.toJavaBigInteger())
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is BigInteger
    }
}