package jp.co.soramitsu.xnetworking.runtime.definitions.types

import jp.co.soramitsu.xnetworking.common.exceptions.IOException

actual abstract class OutputStream {

    @Throws(IOException::class)
    actual fun write(byteArray: ByteArray) {
        TODO()
    }
}