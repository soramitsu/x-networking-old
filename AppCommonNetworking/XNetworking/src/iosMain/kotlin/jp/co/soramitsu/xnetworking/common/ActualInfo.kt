package jp.co.soramitsu.xnetworking.common

import platform.Foundation.NSBundle

internal actual fun platform(): String = platform_ios

//CFBundleShortVersionString
//internal actual fun version(): String = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion").toString()