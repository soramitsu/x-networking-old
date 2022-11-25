package jp.co.soramitsu.xnetworking.scale

expect interface ScaleReader<T> {
    fun read(reader: ScaleCodecReader): T
}