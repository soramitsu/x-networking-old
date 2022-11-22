package jp.co.soramitsu.xnetworking.extensions

import com.soywiz.krypto.encoding.hex
import kotlin.test.Test
import kotlin.test.assertEquals

class HexTest {

    private val byteArrayDecoded = byteArrayOf(1, 2, 3, 4, 127)
    private val byteArrayEncodedHex = "010203047f"
    private val byteArrayEncodedHexWithPrefix = "0x010203047f"

    @Test
    fun `check toHexString encode`() {
        val actual = byteArrayDecoded.toHexString(withPrefix = false)
        assertEquals(expected = byteArrayEncodedHex, actual = actual)
    }

    @Test
    fun `check toHexString with prefix encode`() {
        val actual = byteArrayDecoded.toHexString(withPrefix = true)
        assertEquals(expected = byteArrayEncodedHexWithPrefix, actual = actual)
    }

    @Test
    fun `check fromHex decode`() {
        val actual = byteArrayEncodedHex.fromHex()
        assertEquals(expected = byteArrayDecoded.hex, actual = actual.hex)
    }

    @Test
    fun `check fromHex withPrefix decode`() {
        val actual = byteArrayEncodedHexWithPrefix.fromHex()
        assertEquals(expected = byteArrayDecoded.hex, actual = actual.hex)
    }
}