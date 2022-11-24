package jp.co.soramitsu.xnetworking.runtime.definitions.types

import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.TypeRegistry
import jp.co.soramitsu.xnetworking.runtime.definitions.types.errors.EncodeDecodeException
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

class TypeReference(var value: Type<*>?) {
    private var resolutionInProgress: Boolean = false

    fun requireValue() = value ?: throw IllegalArgumentException("TypeReference is null")

    fun isResolved(): Boolean {
        if (isInRecursion()) {
            return true
        }

        resolutionInProgress = true

        val resolutionResult = resolveRecursive()

        resolutionInProgress = false

        return resolutionResult
    }

    private fun resolveRecursive() = value?.isFullyResolved ?: false

    private fun isInRecursion() = resolutionInProgress
}

abstract class Type<InstanceType>(val name: String) {

    interface InstanceConstructor<I> {

        fun constructInstance(typeRegistry: TypeRegistry, value: I): Any?
    }

    abstract val isFullyResolved: Boolean

    /**
     * @throws EncodeDecodeException
     */
    abstract fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): InstanceType

    /**
     * @throws EncodeDecodeException
     */
    abstract fun encode(
        scaleCodecWriter: ScaleCodecWriter,
        runtime: RuntimeSnapshot,
        value: InstanceType
    )

    abstract fun isValidInstance(instance: Any?): Boolean

    /**
     * @throws EncodeDecodeException
     */
    @Suppress("UNCHECKED_CAST")
    fun encodeUnsafe(scaleWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: Any?) {
        if (!isValidInstance(value)) {
            val valueTypeName = value?.let { it::class.simpleName }
            val message = """
                |$value ($valueTypeName) is not a valid instance of ${this.name}
                | (${this::class.simpleName})""".trimMargin()
            throw EncodeDecodeException(message)
        }

        encode(scaleWriter, runtime, value as InstanceType)
    }
}
