package jp.co.soramitsu.xnetworking.scale

actual interface ScaleWriter<T> {
    actual fun write(scaleWriter: ScaleCodecWriter, value: T)
}