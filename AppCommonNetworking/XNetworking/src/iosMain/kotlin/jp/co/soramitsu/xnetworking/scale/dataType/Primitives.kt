package jp.co.soramitsu.xnetworking.scale.dataType

actual class StringScaleType: ScaleTransformer<String> {
    actual override fun encode(value: String): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): String {
        TODO()
    }

    override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class BooleanScaleType: ScaleTransformer<Boolean> {
    actual override fun encode(value: Boolean): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): Boolean {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ByteArrayScaleType: ScaleTransformer<ByteArray> {
    actual override fun encode(value: ByteArray): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): ByteArray {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ByteArraySizedScaleType actual constructor(
    private val length: Int
): ScaleTransformer<ByteArray> {
    actual override fun encode(value: ByteArray): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): ByteArray {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}