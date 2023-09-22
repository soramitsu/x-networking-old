package jp.co.soramitsu.xnetworking.basic.datasources.polkaswapconfig.api.models.mobile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CurrencyInfo(
    @SerialName("code")
    val code: String,
    @SerialName("name")
    val name: String,
    @SerialName("sign")
    val sign: String,
)