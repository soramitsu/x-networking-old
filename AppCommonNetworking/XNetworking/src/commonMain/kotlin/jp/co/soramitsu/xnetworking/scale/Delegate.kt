package jp.co.soramitsu.xnetworking.scale

import jp.co.soramitsu.xnetworking.scale.dataType.OptionalScaleType
import jp.co.soramitsu.xnetworking.scale.dataType.ScaleTransformer
import jp.co.soramitsu.xnetworking.scale.dataType.optionalScale
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NonNullFieldDelegate<S : Schema<S>, T>(
    private val dataType: ScaleTransformer<T>,
    private val schema: S,
    default: T? = null
) : ReadOnlyProperty<Schema<S>, Field<T>> {

    private var field: Field<T> = schema.field(dataType, default)

    override fun getValue(thisRef: Schema<S>, property: KProperty<*>) = field

    fun optional(): NullableFieldDelegate<S, T> {
        schema.fields.remove(field)

        return NullableFieldDelegate(optionalScale(dataType), schema, field.defaultValue)
    }
}

class NullableFieldDelegate<S : Schema<S>, T>(
    dataType: OptionalScaleType<T>,
    schema: S,
    default: T? = null
) :
    ReadOnlyProperty<Schema<S>, Field<T?>> {

    private var field: Field<T?> = schema.nullableField(dataType, default)

    override fun getValue(thisRef: Schema<S>, property: KProperty<*>) = field
}
