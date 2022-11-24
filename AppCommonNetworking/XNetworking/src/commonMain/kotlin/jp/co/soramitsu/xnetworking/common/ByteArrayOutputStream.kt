package jp.co.soramitsu.xnetworking.common

import jp.co.soramitsu.xnetworking.runtime.definitions.types.OutputStream

expect class ByteArrayOutputStream(): OutputStream {

    fun toByteArray(): ByteArray
}