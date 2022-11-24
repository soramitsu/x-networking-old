package jp.co.soramitsu.xnetworking.scale

import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

expect interface ScaleWriter<T> {
    fun write(scaleWriter: ScaleCodecWriter, value: T)
}