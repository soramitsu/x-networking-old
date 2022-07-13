package jp.co.soramitsu.xnetworking

import jp.co.soramitsu.xnetworking.tbd.sha
import org.junit.Assert.assertEquals
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertEquals("", sha(byteArrayOf()))
    }
}