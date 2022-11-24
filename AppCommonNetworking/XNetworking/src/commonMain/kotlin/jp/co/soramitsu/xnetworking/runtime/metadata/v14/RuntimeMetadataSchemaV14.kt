package jp.co.soramitsu.xnetworking.runtime.metadata.v14

import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import jp.co.soramitsu.xnetworking.scale.compactInt
import jp.co.soramitsu.xnetworking.scale.dataType.EnumScaleType
import jp.co.soramitsu.xnetworking.scale.dataType.compactIntScale
import jp.co.soramitsu.xnetworking.scale.dataType.listScale
import jp.co.soramitsu.xnetworking.scale.dataType.scalableScale
import jp.co.soramitsu.xnetworking.scale.dataType.stringScale
import jp.co.soramitsu.xnetworking.scale.enum
import jp.co.soramitsu.xnetworking.scale.schema
import jp.co.soramitsu.xnetworking.scale.string
import jp.co.soramitsu.xnetworking.scale.uint32
import jp.co.soramitsu.xnetworking.scale.uint8
import jp.co.soramitsu.xnetworking.scale.vector

object RuntimeMetadataSchemaV14 : Schema<RuntimeMetadataSchemaV14>() {
    val lookup by schema(LookupSchema)
    val pallets by vector(PalletMetadataV14)
    val extrinsic by schema(ExtrinsicMetadataV14)
    val type by compactInt()
}

object LookupSchema : Schema<LookupSchema>() {
    val types by vector(PortableType)
}

object PortableType : Schema<PortableType>() {
    val id by compactInt()
    val type by schema(RegistryType)
}

val EncodableStruct<PortableType>.id
    get() = get(PortableType.id)

val EncodableStruct<PortableType>.type
    get() = get(PortableType.type)

object RegistryType : Schema<RegistryType>() {
    val path by vector(stringScale)
    val params by vector(TypeParameter)
    val def by enum(
        scalableScale(TypeDefComposite),
        scalableScale(TypeDefVariant),
        scalableScale(TypeDefSequence),
        scalableScale(TypeDefArray),
        listScale(compactIntScale),
        EnumScaleType(TypeDefEnum::class),
        scalableScale(TypeDefCompact),
        scalableScale(TypeDefBitSequence),
    )
    val docs by vector(stringScale)
}

val EncodableStruct<RegistryType>.path
    get() = get(RegistryType.path)

val EncodableStruct<RegistryType>.def
    get() = get(RegistryType.def)

val EncodableStruct<RegistryType>.params
    get() = get(RegistryType.params)

enum class TypeDefEnum(val localName: String) {
    bool("bool"),
    char(""),
    str(""),
    u8("u8"),
    u16("u16"),
    u32("u32"),
    u64("u64"),
    u128("u128"),
    u256("u256"),
    i8("u8"),
    i16("u16"),
    i32("u32"),
    i64("u64"),
    i128("u128"),
    i256("u256")
}

object TypeDefBitSequence : Schema<TypeDefBitSequence>() {
    val bit_store_type by compactInt()
    val bit_order_type by compactInt()
}

object TypeDefCompact : Schema<TypeDefCompact>() {
    val type by compactInt()
}

object TypeDefArray : Schema<TypeDefArray>() {
    val len by uint32()
    val type by compactInt()
}

object TypeDefSequence : Schema<TypeDefSequence>() {
    val type by compactInt()
}

object TypeDefVariant : Schema<TypeDefVariant>() {
    val variants by vector(TypeDefVariantItem)
}

object TypeDefVariantItem : Schema<TypeDefVariantItem>() {
    val name by string()
    val fields2 by vector(TypeDefCompositeField)
    val index by uint8()
    val docs by vector(stringScale)
}

object TypeDefComposite : Schema<TypeDefComposite>() {
    val fields2 by vector(TypeDefCompositeField)
}

val EncodableStruct<TypeDefComposite>.fields
    get() = get(TypeDefComposite.fields2)

val EncodableStruct<TypeDefCompositeField>.type
    get() = get(TypeDefCompositeField.type)

val EncodableStruct<TypeDefCompositeField>.name
    get() = get(TypeDefCompositeField.name)

object TypeDefCompositeField : Schema<TypeDefCompositeField>() {
    val name by string().optional()
    val type by compactInt()
    val typeName by string().optional()
    val docs by vector(stringScale)
}

object TypeParameter : Schema<TypeParameter>() {
    val name by string()
    val type by compactInt().optional()
}
