package jp.co.soramitsu.xnetworking.scale

actual interface ScaleReader<T> {
    actual fun read(reader: ScaleCodecReader): T
}