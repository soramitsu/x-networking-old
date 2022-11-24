package jp.co.soramitsu.xnetworking.runtime.definitions.types

import jp.co.soramitsu.xnetworking.common.exceptions.IOException

expect abstract class OutputStream() {

    @Throws(IOException::class)
    fun write(byteArray: ByteArray)
}