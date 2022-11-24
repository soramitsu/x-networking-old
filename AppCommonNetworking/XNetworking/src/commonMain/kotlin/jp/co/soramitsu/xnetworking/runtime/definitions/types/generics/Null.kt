package jp.co.soramitsu.xnetworking.runtime.definitions.types.generics

import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.runtime.definitions.types.primitives.Primitive
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

object Null : Primitive<Any?>("Null") {

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): Any? {
        return null
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: Any?) {
        // pass
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance == null
    }
}
