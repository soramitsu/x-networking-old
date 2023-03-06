package jp.co.soramitsu.xnetworking.sorawallet.mainconfig

import com.russhwolf.settings.NSUserDefaultsSettings
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient

actual class SoraRemoteConfigProvider(
    private val client: SoramitsuNetworkClient,
    private val commonUrl: String,
    private val mobileUrl: String,
) {
    actual fun provide(): SoraRemoteConfigBuilder {
        return SoraRemoteConfigBuilder(
            client,
            commonUrl,
            mobileUrl,
            NSUserDefaultsSettings.Factory().create(configName)
        )
    }
}