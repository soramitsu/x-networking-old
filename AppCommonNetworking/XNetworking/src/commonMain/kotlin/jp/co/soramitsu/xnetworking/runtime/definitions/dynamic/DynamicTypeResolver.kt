package jp.co.soramitsu.xnetworking.runtime.definitions.dynamic

import jp.co.soramitsu.xnetworking.runtime.definitions.types.Type
import jp.co.soramitsu.xnetworking.extensions.tryFindNonNull
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.BoxExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.CompactExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.FixedArrayExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.HashMapExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.OptionExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.ResultTypeExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.TupleExtension
import jp.co.soramitsu.xnetworking.runtime.definitions.dynamic.extentsions.VectorExtension

class DynamicTypeResolver(
    val extensions: List<DynamicTypeExtension>
) {
    constructor(vararg extensions: DynamicTypeExtension) : this(extensions.toList())

    companion object {
        fun defaultCompoundResolver(): DynamicTypeResolver {
            return DynamicTypeResolver(DEFAULT_COMPOUND_EXTENSIONS)
        }

        val DEFAULT_COMPOUND_EXTENSIONS = listOf(
            VectorExtension,
            CompactExtension,
            OptionExtension,
            BoxExtension,
            TupleExtension,
            FixedArrayExtension,
            HashMapExtension,
            ResultTypeExtension
        )
    }

    fun createDynamicType(
        name: String,
        typeDef: String,
        innerTypeProvider: TypeProvider
    ): Type<*>? {
        return extensions.tryFindNonNull { it.createType(name, typeDef, innerTypeProvider) }
    }
}
