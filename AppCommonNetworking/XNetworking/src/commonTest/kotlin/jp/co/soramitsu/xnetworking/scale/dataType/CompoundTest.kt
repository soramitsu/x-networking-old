package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import jp.co.soramitsu.xnetworking.scale.string
import kotlin.test.Test
import kotlin.test.assertEquals

class CompoundTest {

    private val expectedDecodedTuple: Pair<Int, Int> = Pair(100, 234)
    private val expectedEncodedTupleHex = "6400ea00"

    private val expectedDecodedOptional: Int = 234
    private val expectedEncodedOptionalHex = "01ea00"

    private val expectedDecodedNullableOptional: Int? = null
    private val expectedEncodedNullableOptionalHex = "00"

    private val expectedDecodedList: List<Int> = listOf(100, 234)
    private val expectedEncodedListHex = "086400ea00"

    private object TestRuntimeMetadataSchema : Schema<TestRuntimeMetadataSchema>() {
        val name by string("test")
    }

    private val expectedDecodedSchema: TestRuntimeMetadataSchema = TestRuntimeMetadataSchema
    private val expectedEncodedSchemaHex = "1074657374"

    private enum class TestEnumClass(name: String) {
        Test1(name = "test1"),
        Test2(name = "test2")
    }

    private val expectedDecodedEnum: TestEnumClass = TestEnumClass.Test1
    private val expectedEncodedEnumHex = "00"

    private val collectionEnumList = listOf("Test1", "Test2")

    private val expectedDecodedCollectionEnum: String = collectionEnumList.first()
    private val expectedEncodedCollectionEnumHex = "00"

    private val typesUnionList = arrayOf(uInt16Scale, booleanScale)

    private val expectedDecodedUnion = 1
    private val expectedEncodedUnionHex = "000100"

    @Test
    fun `check Tuple scale type encode`() {
        val result = tupleScale(uInt16Scale, uInt16Scale).encode(expectedDecodedTuple)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedTupleHex, actual = resultHex)
    }

    @Test
    fun `check Tuple scale type decode`() {
        val result = tupleScale(uInt16Scale, uInt16Scale).decode(expectedEncodedTupleHex.fromHex())
        assertEquals(expected = expectedDecodedTuple, actual = result)
    }

    @Test
    fun `check non-nullable Optional scale type encode`() {
        val result = optionalScale(uInt16Scale).encode(expectedDecodedOptional)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedOptionalHex, actual = resultHex)
    }

    @Test
    fun `check non-nullable Optional scale type decode`() {
        val result = optionalScale(uInt16Scale).decode(expectedEncodedOptionalHex.fromHex())
        assertEquals(expected = expectedDecodedOptional, actual = result)
    }

    @Test
    fun `check nullable Optional scale type encode`() {
        val result = optionalScale(uInt16Scale).encode(expectedDecodedNullableOptional)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedNullableOptionalHex, actual = resultHex)
    }

    @Test
    fun `check nullable Optional scale type decode`() {
        val result = optionalScale(uInt16Scale).decode(expectedEncodedNullableOptionalHex.fromHex())
        assertEquals(expected = expectedDecodedNullableOptional, actual = result)
    }

    @Test
    fun `check List scale type encode`() {
        val result = listScale(uInt16Scale).encode(expectedDecodedList)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedListHex, actual = resultHex)
    }

    @Test
    fun `check List scale type decode`() {
        val result = listScale(uInt16Scale).decode(expectedEncodedListHex.fromHex())
        assertEquals(expected = expectedDecodedList, actual = result)
    }

    @Test
    fun `check Scalable scale type encode`() {
        val result = scalableScale(TestRuntimeMetadataSchema).encode(EncodableStruct(TestRuntimeMetadataSchema))
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedSchemaHex, actual = resultHex)
    }

    @Test
    fun `check Scalable scale type decode`() {
        val result = scalableScale(TestRuntimeMetadataSchema).decode(expectedEncodedSchemaHex.fromHex())
        assertEquals(expected = expectedDecodedSchema, actual = result.schema)
    }

    @Test
    fun `check Enum scale type encode`() {
        val result = enumScale(TestEnumClass::class).encode(expectedDecodedEnum)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedEnumHex, actual = resultHex)
    }

    @Test
    fun `check Enum scale type decode`() {
        val result = enumScale(TestEnumClass::class).decode(expectedEncodedEnumHex.fromHex())
        assertEquals(expected = expectedDecodedEnum, actual = result)
    }

    @Test
    fun `check CollectionEnum scale type encode`() {
        val result = collectionEnumScale(collectionEnumList).encode(expectedDecodedCollectionEnum)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedCollectionEnumHex, actual = resultHex)
    }

    @Test
    fun `check CollectionEnum scale type decode`() {
        val result = collectionEnumScale(collectionEnumList).decode(expectedEncodedCollectionEnumHex.fromHex())
        assertEquals(expected = expectedDecodedCollectionEnum, actual = result)
    }

    @Test
    fun `check Union scale type encode`() {
        val result = unionScale(typesUnionList).encode(expectedDecodedUnion)
        val resultHex = result.toHexString()
        assertEquals(expected = expectedEncodedUnionHex, actual = resultHex)
    }

    @Test
    fun `check Union scale type decode`() {
        val result = unionScale(typesUnionList).decode(expectedEncodedUnionHex.fromHex())
        assertEquals(expected = expectedDecodedUnion, actual = result)
    }
}