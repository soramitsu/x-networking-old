package jp.co.soramitsu.xnetworking.runtime.metadata.builder

import jp.co.soramitsu.xnetworking.runtime.definitions.registry.TypeRegistry
import jp.co.soramitsu.xnetworking.runtime.metadata.RuntimeMetadata
import jp.co.soramitsu.xnetworking.runtime.metadata.RuntimeMetadataReader

interface RuntimeBuilder {

    fun buildMetadata(reader: RuntimeMetadataReader, typeRegistry: TypeRegistry): RuntimeMetadata
}

object VersionedRuntimeBuilder : RuntimeBuilder {

    override fun buildMetadata(
        reader: RuntimeMetadataReader,
        typeRegistry: TypeRegistry
    ): RuntimeMetadata {
        return when (reader.metadataVersion) {
            14 -> V14RuntimeBuilder.buildMetadata(reader, typeRegistry)
            else -> V13RuntimeBuilder.buildMetadata(reader, typeRegistry)
        }
    }
}
