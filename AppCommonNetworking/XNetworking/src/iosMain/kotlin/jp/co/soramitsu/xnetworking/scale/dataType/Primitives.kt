package jp.co.soramitsu.xnetworking.scale.dataType

actual class StringScaleType: StringScaleTransformer<String> {
    actual override fun encode(value: String): ByteArray {
        return ByteArray(0)
    }

    actual override fun decode(bytes: ByteArray): String {
        return ""
    }

    actual override fun conformsType(value: Any?): Boolean {
        return false
    }
}