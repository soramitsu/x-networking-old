package jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ChainsConfig(
    val chainId: String,
    val assets: List<Asset>,
    val externalApi: ExternalApi? = null
) {
    @Serializable
    class ExternalApi(
        val staking: PlainSection? = null,
        val history: PlainSection? = null,
        val explorers: List<TypedSection>? = null
    ) {
        @Serializable
        class PlainSection(
            val type: Type,
            val url: String
        )

        @Serializable
        class TypedSection(
            val type: String,
            val types: List<String>,
            val url: String
        )

        @Serializable
        enum class Type {
            @SerialName("etherscan")
            EtherScan,
            @SerialName("giantsquid")
            GiantSquid,
            @SerialName("github")
            Github,
            @SerialName("oklink")
            OkLink,
            @SerialName("reef")
            Reef,
            @SerialName("sora")
            Sora,
            @SerialName("subquery")
            SubQuery,
            @SerialName("subsquid")
            SubSquid,
            @SerialName("unknown")
            Unknown,
            @SerialName("zeta")
            Zeta
        }
    }

    @Serializable
    class Asset(
        val id: String,
        val isUtility: Boolean? = null,
        val symbol: String? = null,
        val precision: String? = null,
        val staking: String? = null,
        val type: String? = null,
        val ethereumType: String? = null,
    )
}