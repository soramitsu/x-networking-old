package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain

import kotlinx.serialization.Serializable

@Serializable
internal class GraphQLResponseDataWrapper<T>(
    val data: T
)

@Serializable
class GraphQLSerialiableRequestWrapper(
    val query: String
)
