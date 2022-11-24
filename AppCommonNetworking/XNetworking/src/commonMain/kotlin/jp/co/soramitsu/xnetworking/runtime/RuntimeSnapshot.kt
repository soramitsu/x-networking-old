package jp.co.soramitsu.xnetworking.runtime

import jp.co.soramitsu.xnetworking.runtime.definitions.registry.TypeRegistry
import jp.co.soramitsu.xnetworking.runtime.metadata.RuntimeMetadata

typealias OverriddenConstantsMap = Map<String, Map<String, String>>

class RuntimeSnapshot(
    val typeRegistry: TypeRegistry,
    val metadata: RuntimeMetadata,
    val overrides: OverriddenConstantsMap? = null
)
