package jp.co.soramitsu.xnetworking.scale

expect interface ScaleWriter<T> {
    fun write(scaleWriter: ScaleCodecWriter, value: T)
}