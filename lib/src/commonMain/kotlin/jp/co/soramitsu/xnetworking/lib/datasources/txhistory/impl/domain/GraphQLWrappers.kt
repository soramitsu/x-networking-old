package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponseDataWrapper<T>(
    val data: T
)

@Serializable
data class GraphQLSerializableRequestWrapper(
    val query: String
)
