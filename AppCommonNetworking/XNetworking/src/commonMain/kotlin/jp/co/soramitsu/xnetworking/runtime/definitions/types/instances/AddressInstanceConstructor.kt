package jp.co.soramitsu.xnetworking.runtime.definitions.types.instances

import jp.co.soramitsu.xnetworking.runtime.definitions.types.Type
import jp.co.soramitsu.xnetworking.runtime.AccountId
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.TypeRegistry
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.getOrThrow
import jp.co.soramitsu.xnetworking.runtime.definitions.types.composite.DictEnum
import jp.co.soramitsu.xnetworking.runtime.definitions.types.primitives.FixedByteArray
import jp.co.soramitsu.xnetworking.runtime.definitions.types.generics.MULTI_ADDRESS_ID

private const val ADDRESS_TYPE = "Address"

object AddressInstanceConstructor : Type.InstanceConstructor<AccountId> {

    override fun constructInstance(typeRegistry: TypeRegistry, value: AccountId): Any {
        return when (val addressType = typeRegistry.getOrThrow(ADDRESS_TYPE)) {
            is DictEnum -> { // MultiAddress
                DictEnum.Entry(MULTI_ADDRESS_ID, value)
            }
            is FixedByteArray -> { // GenericAccountId or similar
                value
            }
            else -> throw UnsupportedOperationException("Unknown address type: ${addressType.name}")
        }
    }
}
