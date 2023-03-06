package jp.co.soramitsu.xnetworking.sorawallet.mainconfig

expect class SoraRemoteConfigProvider {
    fun provide(): SoraRemoteConfigBuilder
}

internal const val configName = "remote_config"
