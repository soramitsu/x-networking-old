package jp.co.soramitsu.xnetworking.extensions

import com.ditchoom.buffer.ByteOrder
import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.allocate
import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.Sign

inline fun <T, R> Iterable<T>.tryFindNonNull(transform: (T) -> R?): R? {
    for (item in this) {
        val transformed = transform(item)

        if (transformed != null) return transformed
    }

    return null
}

private const val UNSIGNED_SIGNUM = 1

fun ByteArray.fromUnsignedBytes(byteOrder: ByteOrder = ByteOrder.BIG_ENDIAN): BigInteger {
    // Big Integer accepts big endian numbers
    val ordered = if (byteOrder == ByteOrder.LITTLE_ENDIAN) reversedArray() else this
    return BigInteger.fromByteArray(ordered, Sign.POSITIVE)
}

fun UInt.toUnsignedBytes(order: ByteOrder = ByteOrder.BIG_ENDIAN): ByteArray {
    return PlatformBuffer.allocate(Int.SIZE_BYTES, byteOrder = order).also {
        it.write(this.toInt())
    }.readByteArray(Int.SIZE_BYTES)
}

fun ByteArray.split(divider: ByteArray): List<ByteArray> {

    var elementStart = 0

    val dividerSize = divider.size
    var dividerIndex = 0

    val results = mutableListOf<ByteArray>()

    forEachIndexed { index, byte ->
        if (byte == divider[dividerIndex]) {
            dividerIndex += 1

            if (dividerIndex == dividerSize) {
                val elementEnd = index - dividerSize + 1

                if (elementStart < elementEnd) {
                    results.add(copyOfRange(elementStart, elementEnd))
                } else {
                    results.add(byteArrayOf())
                }

                dividerIndex = 0
                elementStart = index + 1
            }
        } else {
            dividerIndex = 0
        }
    }

    if (elementStart < size) {
        results.add(copyOfRange(elementStart, size))
    }

    return results
}

internal fun String.snakeCaseToCamelCase(): String {
    return split("_").mapIndexed { index, segment ->
        if (index > 0) { // do not capitalize first segment
            segment.capitalize()
        } else {
            segment
        }
    }.joinToString(separator = "")
}
