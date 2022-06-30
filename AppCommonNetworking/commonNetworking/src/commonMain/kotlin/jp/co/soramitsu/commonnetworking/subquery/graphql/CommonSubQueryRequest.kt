package jp.co.soramitsu.commonnetworking.subquery.graphql

import kotlinx.serialization.Serializable

@Serializable
data class SubQueryRequest(
    val query: String,
    val variables: String? = null
)

internal val varCountRemote = "\$countRemote"
internal val varAfterCursor = "\$afterCursor"
internal val varMyAddress = "\$myAddress"

internal fun soraHistoryGraphQLVariables(
    countRemote: Int,
    myAddress: String,
    cursor: String,
) = """
    {"countRemote": $countRemote, 
    "myAddress": "$myAddress",
    "afterCursor": "$cursor"}
    """.trimIndent()
