package jp.co.soramitsu.xnetworking.hash

import com.appmattus.crypto.Algorithm
import com.appmattus.crypto.Digest
import com.ditchoom.buffer.ByteOrder
import com.ditchoom.buffer.PlatformBuffer
import com.ditchoom.buffer.wrap
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okio.Buffer

private val blake2b_128 by lazy { Algorithm.Blake2b(128).createDigest() }
private val blake2b_256 by lazy { Algorithm.Blake2b(256).createDigest() }
private val blake2b_512 by lazy { Algorithm.Blake2b(512).createDigest() }
private val keccak_256 by lazy { Algorithm.Keccak256.createDigest() }
private val xxHash64 by lazy { Algorithm.XXHash64(0) }
private val mutex = Mutex()

fun ByteArray.blake2b128(): ByteArray {
    return threadSafe { blake2b_128.digest(this) }
}

fun ByteArray.blake2b128Concat(): ByteArray {
    return threadSafe { blake2b_128.hashConcatDigest(this) }
}

fun ByteArray.blake2b256(): ByteArray {
    return threadSafe { blake2b_256.digest(this) }
}

fun ByteArray.blake2b512(): ByteArray {
    return threadSafe { blake2b_512.digest(this) }
}

fun ByteArray.keccak256(): ByteArray {
    return keccak_256.digest(this)
}

fun ByteArray.xxHash64(byteOrder: ByteOrder = ByteOrder.BIG_ENDIAN): Long {
    return PlatformBuffer.wrap(xxHash64.hash(this), byteOrder).readLong()
}

fun ByteArray.xxHash128(): ByteArray {
    return xxHash(hashLengthBits = 128)
}

fun ByteArray.xxHash256(): ByteArray {
    return xxHash(hashLengthBits = 256)
}

fun ByteArray.xxHash64Concat(): ByteArray {
    val buffer = Buffer()
    buffer.writeLong(xxHash64(byteOrder = ByteOrder.LITTLE_ENDIAN))
    buffer.write(this)
    return buffer.readByteArray()
}

private inline fun <T> threadSafe(crossinline block: () -> T): T = runBlocking {
    return@runBlocking mutex.withLock {
        block.invoke()
    }
}

private fun Digest<*>.hashConcatDigest(bytes: ByteArray): ByteArray {
    return digest(bytes) + bytes
}

private fun ByteArray.xxHash(hashLengthBits: Int): ByteArray {
    require(hashLengthBits % 64 == 0)

    val timesToRepeat = hashLengthBits / 64

    val buffer = Buffer()

    (0 until timesToRepeat).map { seed ->
        val xxHashAlgorithm = Algorithm.XXHash64(seed.toLong())
        PlatformBuffer.wrap(xxHashAlgorithm.hash(this), ByteOrder.LITTLE_ENDIAN).readLong()
    }.onEach(buffer::writeLong)

    return buffer.readByteArray()
}