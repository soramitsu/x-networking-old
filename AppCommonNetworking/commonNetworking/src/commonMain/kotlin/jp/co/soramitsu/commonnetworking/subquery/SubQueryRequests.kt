package jp.co.soramitsu.commonnetworking.subquery

import kotlinx.serialization.Serializable

@Serializable
data class SubqueryRequest(
    val query: String,
    val variables: String? = null
)
