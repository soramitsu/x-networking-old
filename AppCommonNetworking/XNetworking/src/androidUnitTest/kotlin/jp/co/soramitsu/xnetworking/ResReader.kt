package jp.co.soramitsu.xnetworking

actual fun readBinaryResource(
    resourceName: String
): ByteArray {
    return ClassLoader
        .getSystemResourceAsStream(resourceName)
        .readBytes()
}