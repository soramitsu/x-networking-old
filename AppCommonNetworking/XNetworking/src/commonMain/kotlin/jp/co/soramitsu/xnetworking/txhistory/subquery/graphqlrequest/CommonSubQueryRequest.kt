package jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubQueryRequest(
    @SerialName("query")
    val query: String,
    @SerialName("variables")
    val variables: String? = null,
)

internal const val varCountRemote = "\$countRemote"
internal const val varAfterCursor = "\$afterCursor"
internal const val varMyAddress = "\$myAddress"

internal fun txHistoryGraphQLVariables(
    countRemote: Int,
    myAddress: String,
    cursor: String,
) = """
    {"countRemote": $countRemote, 
    "myAddress": "$myAddress",
    "afterCursor": "$cursor"}
    """.trimIndent()
