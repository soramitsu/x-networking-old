package jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions

import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.DynamicTypeExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.TypeProvider
import jp.co.soramitsu.xnetworking.runtime.definitions.types.Type
import jp.co.soramitsu.xnetworking.runtime.definitions.types.TypeReference

abstract class WrapperExtension : DynamicTypeExtension {

    abstract val wrapperName: String

    abstract fun createWrapper(name: String, innerTypeRef: TypeReference): Type<*>?

    override fun createType(name: String, typeDef: String, typeProvider: TypeProvider): Type<*>? {
        if (!typeDef.startsWith("$wrapperName<")) return null

        val innerTypeDef = typeDef.removeSurrounding("$wrapperName<", ">")

        val innerTypeRef = typeProvider(innerTypeDef)

        return createWrapper(name, innerTypeRef)
    }
}
