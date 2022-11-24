package jp.co.soramitsu.xnetworking.runtime.definitions.types.generics

import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.runtime.definitions.types.Type
import jp.co.soramitsu.xnetworking.runtime.definitions.types.fromByteArray
import jp.co.soramitsu.xnetworking.runtime.definitions.types.toByteArray

object OpaqueCall : Type<GenericCall.Instance>("OpaqueCall") {

    override val isFullyResolved = true

    override fun decode(
        scaleCodecReader: ScaleCodecReader,
        runtime: RuntimeSnapshot
    ): GenericCall.Instance {
        val bytes = Bytes.decode(scaleCodecReader, runtime)

        return GenericCall.fromByteArray(runtime, bytes)
    }

    override fun encode(
        scaleCodecWriter: ScaleCodecWriter,
        runtime: RuntimeSnapshot,
        value: GenericCall.Instance
    ) {
        val callEncoded = GenericCall.toByteArray(runtime, value)

        return Bytes.encode(scaleCodecWriter, runtime, callEncoded)
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance is ByteArray
    }
}
