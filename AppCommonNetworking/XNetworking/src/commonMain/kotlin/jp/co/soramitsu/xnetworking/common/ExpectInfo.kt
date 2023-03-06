package jp.co.soramitsu.xnetworking.common

internal expect fun platform(): String

internal const val platform_android = "android"
internal const val platform_ios = "ios"