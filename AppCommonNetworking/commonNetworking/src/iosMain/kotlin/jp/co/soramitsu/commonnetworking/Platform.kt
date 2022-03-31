package jp.co.soramitsu.commonnetworking

import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CC_SHA256_CTX
import platform.CoreCrypto.CC_SHA256_Final
import platform.CoreCrypto.CC_SHA256_Init
import platform.CoreCrypto.CC_SHA256_Update
import platform.Foundation.NSBundle

actual class HttpEngineFactory actual constructor() {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Ios
}

internal actual fun platform(): String = "ios"

//CFBundleShortVersionString
internal actual fun version(): String = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion").toString()

actual fun sha(a: ByteArray): String = ""
//actual fun sha(a: ByteArray): String = memScoped {
//    val ctx = alloc<CC_SHA256_CTX>()
//    CC_SHA256_Init(ctx.ptr)
//    //CC_SHA256_Update(ctx.ptr, a.refTo(0), a.size)
//    val s: String
//    a.asUByteArray().usePinned {
//        CC_SHA256_Final(it.addressOf(0), ctx.ptr)
//    }
//    ""
//}
