package jp.co.soramitsu.xnetworking.hash

import net.jpountz.xxhash.XXHashFactory
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.jcajce.provider.digest.Keccak

object Hasher {

    val blake2bLock = Any()

    private val blake2b256 = Blake2b.Blake2b256()

    val blake2b128 = Blake2b128()

    private val blake2b512 = Blake2b.Blake2b512()

    val xxHash64 = XXHashFactory.safeInstance().hash64()
    val xxHash128 = XXHash(128, xxHash64)
    val xxHash256 = XXHash(256, xxHash64)

    fun xxHash64(bytes: ByteArray) = xxHash64.hash(bytes)
    fun xxHash128(bytes: ByteArray) = xxHash128.hash(bytes)
    fun xxHash256(bytes: ByteArray) = xxHash256.hash(bytes)

    fun xxHash64Concat(bytes: ByteArray) = xxHash64.hashConcat(bytes)

    fun blake2b128(bytes: ByteArray) = withBlake2bLock { blake2b128.digest(bytes) }
    fun blake2b256(bytes: ByteArray) = withBlake2bLock { blake2b256.digest(bytes) }
    fun blake2b512(bytes: ByteArray) = withBlake2bLock { blake2b512.digest(bytes) }

    fun keccak256(bytes: ByteArray): ByteArray {
        val digest = Keccak.Digest256()

        return digest.digest(bytes)
    }

    fun ByteArray.blake2b128Concat() = withBlake2bLock { blake2b128.hashConcat(this) }

    inline fun <T> withBlake2bLock(action: () -> T) = synchronized(blake2bLock, action)
}

actual fun ByteArray.blake2b128(): ByteArray {
    return Hasher.blake2b128(this)
}

actual fun ByteArray.blake2b256(): ByteArray {
    return Hasher.blake2b256(this)
}

actual fun ByteArray.blake2b512(): ByteArray {
    return Hasher.blake2b512(this)
}

actual fun ByteArray.keccak256(): ByteArray {
    return Hasher.keccak256(this)
}

actual fun ByteArray.xxHash64(): Long {
    return Hasher.xxHash64(this)
}
actual fun ByteArray.xxHash128(): ByteArray {
    return Hasher.xxHash128(this)
}
actual fun ByteArray.xxHash256(): ByteArray {
    return Hasher.xxHash256(this)
}

actual fun ByteArray.xxHash64Concat(): ByteArray {
    return Hasher.xxHash64Concat(this)
}

actual fun ByteArray.blake2b128Concat(): ByteArray {
    return Hasher.withBlake2bLock { Hasher.blake2b128.hashConcat(this) }
}