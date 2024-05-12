package jp.co.soramitsu.xnetworking.lib.engines.apollo.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query

interface ApolloClientStore {

    suspend fun <Response: Query.Data> query(serverUrl: String, query: Query<Response>): Response

}