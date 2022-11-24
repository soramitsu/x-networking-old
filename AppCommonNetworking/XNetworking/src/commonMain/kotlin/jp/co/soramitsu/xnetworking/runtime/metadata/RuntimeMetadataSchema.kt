package jp.co.soramitsu.xnetworking.runtime.metadata

import jp.co.soramitsu.xnetworking.hash.blake2b128
import jp.co.soramitsu.xnetworking.hash.blake2b128Concat
import jp.co.soramitsu.xnetworking.hash.blake2b256
import jp.co.soramitsu.xnetworking.hash.xxHash128
import jp.co.soramitsu.xnetworking.hash.xxHash256
import jp.co.soramitsu.xnetworking.hash.xxHash64Concat
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import jp.co.soramitsu.xnetworking.scale.bool
import jp.co.soramitsu.xnetworking.scale.byteArray
import jp.co.soramitsu.xnetworking.scale.dataType.EnumScaleType
import jp.co.soramitsu.xnetworking.scale.dataType.scalableScale
import jp.co.soramitsu.xnetworking.scale.dataType.stringScale
import jp.co.soramitsu.xnetworking.scale.enum
import jp.co.soramitsu.xnetworking.scale.schema
import jp.co.soramitsu.xnetworking.scale.string
import jp.co.soramitsu.xnetworking.scale.uint8
import jp.co.soramitsu.xnetworking.scale.vector

object RuntimeMetadataSchema : Schema<RuntimeMetadataSchema>() {
    val modules by vector(ModuleMetadataSchema)

    val extrinsic by schema(ExtrinsicMetadataSchema)
}

object ModuleMetadataSchema : Schema<ModuleMetadataSchema>() {
    val name by string()

    val storage by schema(StorageMetadataSchema).optional()

    val calls by vector(FunctionMetadataSchema).optional()

    val events by vector(EventMetadataSchema).optional()

    val constants by vector(ModuleConstantMetadataSchema)

    val errors by vector(ErrorMetadataSchema)

    val index by uint8()
}

object StorageMetadataSchema : Schema<StorageMetadataSchema>() {
    val prefix by string()

    val entries by vector(StorageEntryMetadataSchema)
}

object StorageEntryMetadataSchema : Schema<StorageEntryMetadataSchema>() {
    val name by string()

    val modifier by enum(StorageEntryModifier::class)

    val type by enum(
        stringScale, // plain
        scalableScale(MapSchema),
        scalableScale(DoubleMapSchema),
        scalableScale(NMapSchema)
    )

    val default by byteArray() // vector<u8>

    val documentation by vector(stringScale)
}

enum class StorageEntryModifier {
    Optional, Default, Required
}

object MapSchema : Schema<MapSchema>() {
    val hasher by enum(StorageHasher::class)
    val key by string()
    val value by string()
    val unused by bool()
}

object DoubleMapSchema : Schema<DoubleMapSchema>() {
    val key1Hasher by enum(StorageHasher::class)
    val key1 by string()
    val key2 by string()
    val value by string()
    val key2Hasher by enum(StorageHasher::class)
}

object NMapSchema : Schema<NMapSchema>() {
    val keys by vector(stringScale)
    val hashers by vector(EnumScaleType(StorageHasher::class))
    val value by string()
}

enum class StorageHasher(val hashingFunction: (ByteArray) -> ByteArray) {
    Blake2_128({ it.blake2b128() }),
    Blake2_256({ it.blake2b256() }),
    Blake2_128Concat({ it.blake2b128Concat() }),
    Twox128({ it.xxHash128() }),
    Twox256({ it.xxHash256() }),
    Twox64Concat({ it.xxHash64Concat() }),
    Identity({ it })
}

object FunctionMetadataSchema : Schema<FunctionMetadataSchema>() {
    val name by string()

    val arguments by vector(FunctionArgumentMetadataSchema)

    val documentation by vector(stringScale)
}

object FunctionArgumentMetadataSchema : Schema<FunctionArgumentMetadataSchema>() {
    val name by string()

    val type by string()
}

object EventMetadataSchema : Schema<EventMetadataSchema>() {
    val name by string()

    val arguments by vector(stringScale)

    val documentation by vector(stringScale)
}

object ModuleConstantMetadataSchema : Schema<ModuleConstantMetadataSchema>() {
    val name by string()

    val type by string()

    val value by byteArray() // vector<u8>

    val documentation by vector(stringScale)
}

object ErrorMetadataSchema : Schema<ErrorMetadataSchema>() {
    val name by string()

    val documentation by vector(stringScale)
}

object ExtrinsicMetadataSchema : Schema<ExtrinsicMetadataSchema>() {
    val version by uint8()

    val signedExtensions by vector(stringScale)
}

fun EncodableStruct<RuntimeMetadataSchema>.module(name: String) =
    get(RuntimeMetadataSchema.modules).find { it[ModuleMetadataSchema.name] == name }

fun EncodableStruct<ModuleMetadataSchema>.call(name: String) =
    get(ModuleMetadataSchema.calls)?.find { it[FunctionMetadataSchema.name] == name }

fun EncodableStruct<ModuleMetadataSchema>.storage(name: String) =
    get(ModuleMetadataSchema.storage)?.get(StorageMetadataSchema.entries)
        ?.find { it[StorageEntryMetadataSchema.name] == name }
