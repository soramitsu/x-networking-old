package jp.co.soramitsu.xnetworking.basic.datasources.polkaswapconfig.api

import jp.co.soramitsu.xnetworking.basic.datasources.polkaswapconfig.api.models.common.CommonConfig
import jp.co.soramitsu.xnetworking.basic.datasources.polkaswapconfig.api.models.mobile.MobileConfig

interface ConfigRepository {

    suspend fun getCommonConfig(): CommonConfig?

    suspend fun getMobileConfig(): MobileConfig?

}