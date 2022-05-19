package jp.co.soramitsu.commonnetworking.subquery.graphql

import kotlinx.serialization.Serializable

@Serializable
data class SubQueryRequest(
    val query: String,
    val variables: String? = null
)
