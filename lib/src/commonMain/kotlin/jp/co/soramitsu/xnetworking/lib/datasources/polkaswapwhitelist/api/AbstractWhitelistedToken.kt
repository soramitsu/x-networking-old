package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api

abstract class AbstractWhitelistedToken {

    abstract fun getAddress(): String

    abstract fun getRawIcon(): String

    abstract fun getIconExtension(): String?

}