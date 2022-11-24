package jp.co.soramitsu.xnetworking.scale.dataType

import com.ionspin.kotlin.bignum.integer.BigInteger
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

actual class ByteScaleType: ScaleTransformer<Byte>() {

    actual override fun read(reader: ScaleCodecReader): Byte {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Byte) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UInt8ScaleType: ScaleTransformer<UByte>() {

    actual override fun read(reader: ScaleCodecReader): UByte {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: UByte) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UInt16ScaleType: ScaleTransformer<Int>() {

    actual override fun read(reader: ScaleCodecReader): Int {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Int) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UInt32ScaleType: ScaleTransformer<UInt>() {

    actual override fun read(reader: ScaleCodecReader): UInt {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: UInt) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class LongScaleType: ScaleTransformer<Long>() {

    actual override fun read(reader: ScaleCodecReader): Long {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Long) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual open class UIntScaleType actual constructor(
    private val size: Int
): ScaleTransformer<BigInteger>() {

    actual override fun read(reader: ScaleCodecReader): BigInteger {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: BigInteger) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class CompactIntScaleType : ScaleTransformer<BigInteger>() {

    actual override fun read(reader: ScaleCodecReader): BigInteger {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: BigInteger) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}