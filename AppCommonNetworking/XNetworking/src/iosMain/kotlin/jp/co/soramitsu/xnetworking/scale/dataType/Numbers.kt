package jp.co.soramitsu.xnetworking.scale.dataType

import com.ionspin.kotlin.bignum.integer.BigInteger

actual class ByteScaleType: ScaleTransformer<Byte> {

    actual override fun encode(value: Byte): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): Byte {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UInt8ScaleType: ScaleTransformer<UByte> {

    actual override fun encode(value: UByte): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): UByte {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UInt16ScaleType: ScaleTransformer<Int> {

    actual override fun encode(value: Int): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): Int {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UInt32ScaleType: ScaleTransformer<UInt> {

    actual override fun encode(value: UInt): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): UInt {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class LongScaleType: ScaleTransformer<Long> {

    actual override fun encode(value: Long): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): Long {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual open class UIntScaleType actual constructor(
    private val size: Int
): ScaleTransformer<BigInteger> {

    actual override fun encode(value: BigInteger): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): BigInteger {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class CompactIntScaleType : ScaleTransformer<BigInteger> {

    actual override fun encode(value: BigInteger): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): BigInteger {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}