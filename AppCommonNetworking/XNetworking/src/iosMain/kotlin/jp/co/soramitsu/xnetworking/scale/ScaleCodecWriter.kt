package jp.co.soramitsu.xnetworking.scale

import jp.co.soramitsu.xnetworking.runtime.definitions.types.OutputStream

actual class ScaleCodecWriter actual constructor(outputStream: OutputStream) {

    actual fun <T> write(writer: ScaleWriter<T>, value: T) {
        TODO()
    }

    actual fun directWrite(bytes: ByteArray, off: Int, len: Int) {
        TODO()
    }

    actual fun writeByte(byte: Int) {
        TODO()
    }

    actual fun <T> writeOptional(scaleWriter: ScaleWriter<T>, value: T) {
        TODO()
    }
}