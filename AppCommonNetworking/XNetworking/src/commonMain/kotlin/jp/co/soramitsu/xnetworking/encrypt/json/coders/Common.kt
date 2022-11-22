@file:Suppress("EXPERIMENTAL_API_USAGE")

package jp.co.soramitsu.xnetworking.encrypt.json.coders

import com.ditchoom.buffer.ByteOrder
import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.allocate
import com.ditchoom.buffer.wrap

val SCRYPT_KEY_SIZE = 32

val SALT_OFFSET = 0
val SALT_SIZE = 32

val N_OFFSET = SALT_OFFSET + SALT_SIZE
val N_SIZE = 4

val P_OFFSET = N_OFFSET + N_SIZE
val P_SIZE = 4

val R_OFFSET = P_OFFSET + P_SIZE
val R_SIZE = 4

val NONCE_OFFSET = R_OFFSET + R_SIZE
val NONCE_SIZE = 24

val DATA_OFFSET = NONCE_OFFSET + NONCE_SIZE

const val ENCODING_SCRYPT = "scrypt"
const val ENCODING_SALSA = "xsalsa20-poly1305"

val PKCS8_HEADER = intArrayOf(48, 83, 2, 1, 1, 48, 5, 6, 3, 43, 101, 112, 4, 34, 4, 32)
    .map(Int::toByte)
    .toByteArray()

val PKCS8_DIVIDER = intArrayOf(161, 35, 3, 33, 0)
    .map(Int::toByte)
    .toByteArray()

fun ByteArray.asLittleEndianInt() = PlatformBuffer.wrap(this, byteOrder = ByteOrder.LITTLE_ENDIAN)
    .readInt()

fun ByteArray.copyBytes(from: Int, size: Int) = copyOfRange(from, from + size)

fun Int.asLittleEndianBytes() = usingLittleEndian(Int.SIZE_BYTES) {
    write(this@asLittleEndianBytes)
}

fun usingLittleEndian(size: Int, builder: PlatformBuffer.() -> Unit): ByteArray {
    val buffer = PlatformBuffer.allocate(size, byteOrder = ByteOrder.LITTLE_ENDIAN)
    builder.invoke(buffer)

    return buffer.readByteArray(size)
}
