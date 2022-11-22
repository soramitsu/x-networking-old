package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import kotlin.test.Test
import kotlin.test.assertEquals

class PrimitivesTest {

    private val expectedEncodedStringHex = "2c4564323535313948444b44"
    private val expectedDecodedString = "Ed25519HDKD"
    private val expectedEncodedBooleanHex = "01"
    private val expectedDecodedBoolean = true
    private val expectedEncodedByteArrayHex = "1074657374"
    private val expectedDecodedByteArray = "test".encodeToByteArray()
    private val expectedEncodedByteArraySizedHex = "7465"
    private val expectedDecodedByteSizedArray = "test".encodeToByteArray()

    @Test
    fun `check String scale type encode`() {
        val result = stringScale.encode(expectedDecodedString)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedStringHex, actual = resultHex)
    }

    @Test
    fun `check String scale type decode`() {
        val result = stringScale.decode(expectedEncodedStringHex.fromHex())
        assertEquals(expected = expectedDecodedString, actual = result)
    }

    @Test
    fun `check Boolean scale type encode`() {
        val result = booleanScale.encode(expectedDecodedBoolean)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedBooleanHex, actual = resultHex)
    }

    @Test
    fun `check Boolean scale type decode`() {
        val result = booleanScale.decode(expectedEncodedBooleanHex.fromHex())
        assertEquals(expected = expectedDecodedBoolean, actual = result)
    }

    @Test
    fun `check ByteArray scale type encode`() {
        val result = byteArrayScale.encode(expectedDecodedByteArray)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedByteArrayHex, actual = resultHex)
    }

    @Test
    fun `check ByteArray scale type decode`() {
        val result = byteArrayScale.decode(expectedEncodedByteArrayHex.fromHex())
        assertEquals(expected = expectedDecodedByteArray.toHexString(), actual = result.toHexString())
    }

    @Test
    fun `check ByteArraySized scale type encode`() {
        val result = byteArraySizedScale(2).encode(expectedDecodedByteSizedArray)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedByteArraySizedHex, actual = resultHex)
    }

    @Test
    fun `check ByteArraySized scale type decode`() {
        val result = byteArraySizedScale(2).decode(expectedEncodedByteArraySizedHex.fromHex())
        val expected = expectedDecodedByteSizedArray.toHexString().take(4)
        assertEquals(expected = expected, actual = result.toHexString())
    }
}