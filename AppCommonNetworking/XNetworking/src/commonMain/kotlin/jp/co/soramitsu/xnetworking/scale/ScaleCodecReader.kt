package jp.co.soramitsu.xnetworking.scale

expect class ScaleCodecReader(source: ByteArray) {

    fun readBoolean(): Boolean

    fun readByteArray(length: Int): ByteArray

    fun readByte(): Byte
}