package jp.co.soramitsu.xnetworking.hash

import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HasherTest {

    @Test
    fun `check blake2b128`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.blake2b128()
        assertEquals(
            expected = "428cafa6a823bc9f42ae83b5c496c0d2",
            actual = result.toHexString()
        )
    }

    @Test
    fun `check blake2b256`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.blake2b256()
        assertEquals(
            expected = "a6dfd8f0eef1533c5780aec9cd7507009a469c94adc2792b429ebb429d674c47",
            actual = result.toHexString()
        )
    }

    @Test
    fun `check blake2b512`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.blake2b512()
        assertEquals(
            expected = "75e3aa0bad3c484ac89838875c750b2f022fe1f75bb5a2fb2016d795d50a83fc1a39e7d6342278fccc450ad2083fb4d1fcb87464d0e9255e1ad0460d48d5b642",
            actual = result.toHexString()
        )
    }

    @Test
    fun `check keccak256`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.keccak256()
        assertEquals(
            expected = "b607ea97c8c0dfe6ea78d56d2f1046362f9f43edf208b5b500259cf4f77d82b0",
            actual = result.toHexString()
        )
    }

    @Test
    fun `check blake2b128Concat`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.blake2b128Concat()
        assertEquals(
            expected = "428cafa6a823bc9f42ae83b5c496c0d2e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925",
            actual = result.toHexString()
        )
    }

    @Test
    fun `check xxHash64`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.xxHash64()
        assertEquals(
            expected = 5794077967867516868,
            actual = result
        )
    }

    @Test
    fun `check xxHash64Concat`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.xxHash64Concat().toHexString()
        assertEquals(
            expected = "c417a9a336b36850e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925",
            actual = result
        )
    }

    @Test
    fun `check xxHash128`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.xxHash128().toHexString()
        assertEquals(
            expected = "c417a9a336b36850cfdbc40ffff7afc5",
            actual = result
        )
    }

    @Test
    fun `check xxHash256`() {
        val byteArray = "e2db688cbc776a7711c573eb1edeeb76ead9fb26e14f78467a3c17620bf72925".fromHex()
        val result = byteArray.xxHash256().toHexString()
        assertEquals(
            expected = "c417a9a336b36850cfdbc40ffff7afc59e74cfef2f89f219d042ef241f0853e4",
            actual = result
        )
    }

    @Test
    fun `blake2b128 should be thread safe`() {
        assertHashThreadSafety { it.blake2b128() }
    }

    @Test
    fun `blake2b256 should be thread safe`() {
        assertHashThreadSafety { it.blake2b256() }
    }

    @Test
    fun `blake2b512 should be thread safe`() {
        assertHashThreadSafety { it.blake2b512() }
    }

    @Test
    fun `blake2b128Concat should be thread safe`() {
        assertHashThreadSafety { it.blake2b128Concat() }
    }


    private fun assertHashThreadSafety(block: (bytes: ByteArray) -> ByteArray) = runBlocking {
        val testData = (0..1000).map { Random.nextBytes(32) }

        val sequentialResults = testData.map { block.invoke(it) }

        val concurrentResults = testData
            .map {
                // Lazy to ensure parallel start later for more concurrency
                async(Dispatchers.Default, CoroutineStart.LAZY) { block.invoke(it) }
            }.awaitAll()

        assertTrue(
            sequentialResults
                .zip(concurrentResults)
                .all { (expected, actual) -> expected.contentEquals(actual) }
        )
    }
}