package jp.co.soramitsu.xnetworking.basic.common

import jp.co.soramitsu.xnetworking.basic.common.platform_ios

actual fun platform(): String = platform_ios

//CFBundleShortVersionString
//internal actual fun version(): String = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion").toString()