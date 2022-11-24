package jp.co.soramitsu.xnetworking.scale

import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader

expect interface ScaleReader<T> {
    fun read(reader: ScaleCodecReader): T
}