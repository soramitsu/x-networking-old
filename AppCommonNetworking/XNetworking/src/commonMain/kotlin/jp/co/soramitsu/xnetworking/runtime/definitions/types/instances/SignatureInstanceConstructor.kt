package jp.co.soramitsu.xnetworking.runtime.definitions.types.instances

import com.ionspin.kotlin.bignum.integer.toBigInteger
import jp.co.soramitsu.xnetworking.encrypt.SignatureWrapper
import jp.co.soramitsu.xnetworking.encrypt.vByte
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.TypeRegistry
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.getOrThrow
import jp.co.soramitsu.xnetworking.runtime.definitions.types.Type
import jp.co.soramitsu.xnetworking.runtime.definitions.types.composite.DictEnum
import jp.co.soramitsu.xnetworking.runtime.definitions.types.composite.Struct
import jp.co.soramitsu.xnetworking.runtime.definitions.types.generics.MultiSignature
import jp.co.soramitsu.xnetworking.runtime.definitions.types.generics.prepareForEncoding

private const val EXTRINSIC_SIGNATURE_TYPE = "ExtrinsicSignature"

object SignatureInstanceConstructor : Type.InstanceConstructor<SignatureWrapper> {

    override fun constructInstance(typeRegistry: TypeRegistry, value: SignatureWrapper): Any {
        return when (val type = typeRegistry.getOrThrow(EXTRINSIC_SIGNATURE_TYPE)) {

            is DictEnum -> { // MultiSignature
                MultiSignature(value.encryptionType, value.signature).prepareForEncoding()
            }
            is Struct -> { // EthereumSignature
                require(value is SignatureWrapper.Ecdsa) {
                    "Cannot construct extrinsic signature from ${value::class.simpleName}"
                }

                val fields = mapOf(
                    "r" to value.r,
                    "s" to value.s,
                    "v" to value.vByte.toInt().toBigInteger()
                )

                Struct.Instance(fields)
            }
            else -> throw UnsupportedOperationException("Unknown signature type: ${type.name}")
        }
    }
}
