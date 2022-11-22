package jp.co.soramitsu.xnetworking.hash

import net.jpountz.xxhash.XXHashFactory
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.jcajce.provider.digest.Keccak

object Hasher {
    private val blake2bLock = Any()

    private val blake2b256 = Blake2b.Blake2b256()

    private val blake2b128 = Blake2b128()

    private val blake2b512 = Blake2b.Blake2b512()

    val xxHash64 = XXHashFactory.safeInstance().hash64()
    val xxHash128 = XXHash(128, xxHash64)
    val xxHash256 = XXHash(256, xxHash64)

    fun ByteArray.xxHash64() = xxHash64.hash(this)
    fun ByteArray.xxHash128() = xxHash128.hash(this)
    fun ByteArray.xxHash256() = xxHash256.hash(this)

    fun blake2b128(bytes: ByteArray) = withBlake2bLock { blake2b128.digest(bytes) }
    fun blake2b256(bytes: ByteArray) = withBlake2bLock { blake2b256.digest(bytes) }
    fun blake2b512(bytes: ByteArray) = withBlake2bLock { blake2b512.digest(bytes) }

    fun keccak256(bytes: ByteArray): ByteArray {
        val digest = Keccak.Digest256()

        return digest.digest(this)
    }

    fun ByteArray.blake2b128Concat() = withBlake2bLock { blake2b128.hashConcat(this) }

    private inline fun <T> withBlake2bLock(action: () -> T) = synchronized(blake2bLock, action)
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