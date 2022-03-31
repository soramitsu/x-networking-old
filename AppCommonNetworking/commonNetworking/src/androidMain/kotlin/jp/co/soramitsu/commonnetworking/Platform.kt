package jp.co.soramitsu.commonnetworking

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*

actual class HttpEngineFactory actual constructor() {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return OkHttp
    }
}

internal actual fun platform(): String = "android"

// content this.PackageManager.GetPackageInfo(this.PackageName, 0)
internal actual fun version(): String = ""

actual fun sha(a: ByteArray): String = ""
