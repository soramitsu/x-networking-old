package jp.co.soramitsu.xnetworking.runtime.definitions.types

import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

fun ScaleCodecWriter.directWrite(byteArray: ByteArray) {
    directWrite(byteArray, 0, byteArray.size)
}