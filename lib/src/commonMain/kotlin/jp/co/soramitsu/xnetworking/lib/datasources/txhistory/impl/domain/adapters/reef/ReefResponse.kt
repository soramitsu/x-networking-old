package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.reef

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ReefResponse(
    val transfersConnection: TransfersConnection? = null,
    @SerialName("stakingsConnection")
    val rewardsConnection: RewardsConnection? = null,
    val extrinsicsConnection: ExtrinsicsConnection? = null
) {
    @Serializable
    class TransfersConnection(
        val pageInfo: PageInfo,
        val edges: List<Node>
    ) {
        @Serializable
        class Node(
            val node: TransferElement
        ) {
            @Serializable
            class TransferElement(
                val id: String? = null,
                val amount: String,
                val type: String,
                val timestamp: String,
                val success: Boolean,
                val to: ReefAddress,
                val from: ReefAddress,
                val extrinsicHash: String?,
                val signedData: ReefSignedData?
            )
        }
    }

    @Serializable
    class RewardsConnection(
        val pageInfo: PageInfo,
        val edges: List<Node>
    ) {
        @Serializable
        class Node(
            val node: RewardElement
        ) {
            @Serializable
            class RewardElement(
                val id: String,
                val type: String,
                val amount: String,
                val timestamp: String,
                val signer: ReefAddress
            )
        }
    }

    @Serializable
    class ExtrinsicsConnection(
        val pageInfo: PageInfo,
        val edges: List<Node>
    ) {
        @Serializable
        class Node(
            val node: ExtrinsicElement
        ) {
            @Serializable
            class ExtrinsicElement(
                val id: String,
                val hash: String,
                val method: String,
                val section: String,
                val signedData: ReefSignedData?,
                val status: String,
                val signer: String,
                val timestamp: String,
                val type: String
            )
        }
    }

    @Serializable
    class PageInfo(
        val endCursor: String,
        val hasNextPage: Boolean,
        val startCursor: String? = null
    )


    @Serializable
    class ReefAddress(
        val id: String
    )

    @Serializable
    class ReefSignedData(
        val fee: ReefFeeData?
    ) {
        @Serializable
        class ReefFeeData(
            val partialFee: String?
        )
    }
}