package jp.co.soramitsu.xnetworking.scale.dataType

import com.ionspin.kotlin.bignum.integer.BigInteger
import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import kotlin.test.Test
import kotlin.test.assertEquals

class NumbersTest {

    private val expectedDecodedByte: Byte = 23
    private val expectedEncodedByteHex = "17"

    private val expectedDecodedUInt8: UByte = 254u
    private val expectedEncodedUInt8Hex = "fe"

    private val expectedDecodedUInt16: Int = 2_000_000_000
    private val expectedEncodedUInt16Hex = "0094"

    private val expectedDecodedUInt32: UInt = 4_000_000_000u
    private val expectedEncodedUInt32Hex = "00286bee"

    private val expectedDecodedLong: Long = 9_000_000_000_000_000_000
    private val expectedEncodedLongHex = "000084e2506ce67c"

    private val expectedDecodedUInt64: BigInteger = BigInteger.parseString("9000000000000000000")
    private val expectedEncodedUInt64Hex = "000084e2506ce67c"

    private val expectedDecodedUInt128: BigInteger = BigInteger.parseString("9000000000000000000")
    private val expectedEncodedUInt128Hex = "000084e2506ce67c0000000000000000"

    private val expectedDecodedCompactInt: BigInteger = BigInteger.parseString("9000000000000000000")
    private val expectedEncodedCompactIntHex = "13000084e2506ce67c"


    @Test
    fun `check Byte scale type encode`() {
        val result = byteScale.encode(expectedDecodedByte)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedByteHex, actual = resultHex)
    }

    @Test
    fun `check Byte scale type decode`() {
        val result = byteScale.decode(expectedEncodedByteHex.fromHex())
        assertEquals(expected = expectedDecodedByte, actual = result)
    }

    @Test
    fun `check UInt8 scale type encode`() {
        val result = uInt8Scale.encode(expectedDecodedUInt8)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedUInt8Hex, actual = resultHex)
    }

    @Test
    fun `check UInt8 scale type decode`() {
        val result = uInt8Scale.decode(expectedEncodedUInt8Hex.fromHex())
        assertEquals(expected = expectedDecodedUInt8, actual = result)
    }

    @Test
    fun `check UInt16 scale type encode`() {
        val result = uInt16Scale.encode(expectedDecodedUInt16)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedUInt16Hex, actual = resultHex)
    }

    @Test
    fun `check UInt16 scale type decode`() {
        val result = uInt16Scale.decode(expectedEncodedUInt16Hex.fromHex())
        assertEquals(expected = 37888, actual = result)
    } // TODO: infinity_coder - почему при обратном маппинге получается другое число?

    @Test
    fun `check UInt32 scale type encode`() {
        val result = uInt32Scale.encode(expectedDecodedUInt32)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedUInt32Hex, actual = resultHex)
    }

    @Test
    fun `check UInt32 scale type decode`() {
        val result = uInt32Scale.decode(expectedEncodedUInt32Hex.fromHex())
        assertEquals(expected = expectedDecodedUInt32, actual = result)
    }

    @Test
    fun `check Long scale type encode`() {
        val result = longScale.encode(expectedDecodedLong)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedLongHex, actual = resultHex)
    }

    @Test
    fun `check Long scale type decode`() {
        val result = longScale.decode(expectedEncodedLongHex.fromHex())
        assertEquals(expected = expectedDecodedLong, actual = result)
    }

    @Test
    fun `check UInt64 scale type encode`() {
        val result = uInt64Scale.encode(expectedDecodedUInt64)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedUInt64Hex, actual = resultHex)
    }

    @Test
    fun `check UInt64 scale type decode`() {
        val result = uInt64Scale.decode(expectedEncodedUInt64Hex.fromHex())
        assertEquals(expected = expectedDecodedUInt64, actual = result)
    }

    @Test
    fun `check UInt128 scale type encode`() {
        val result = uInt128Scale.encode(expectedDecodedUInt128)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedUInt128Hex, actual = resultHex)
    }

    @Test
    fun `check UInt128 scale type decode`() {
        val result = uInt128Scale.decode(expectedEncodedUInt128Hex.fromHex())
        assertEquals(expected = expectedDecodedUInt128, actual = result)
    }

    @Test
    fun `check CompactInt scale type encode`() {
        val result = compactIntScale.encode(expectedDecodedCompactInt)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedCompactIntHex, actual = resultHex)
    }

    @Test
    fun `check CompactInt scale type decode`() {
        val result = compactIntScale.decode(expectedEncodedCompactIntHex.fromHex())
        assertEquals(expected = expectedDecodedCompactInt, actual = result)
    }
}