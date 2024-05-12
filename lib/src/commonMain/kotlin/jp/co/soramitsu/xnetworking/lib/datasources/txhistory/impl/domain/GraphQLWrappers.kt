package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain

import kotlinx.serialization.Serializable

@Serializable
class GraphQLResponseDataWrapper<T>(
    val data: T
)

@Serializable
class GraphQLSerializableRequestWrapper(
    val query: String
)
