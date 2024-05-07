package jp.co.soramitsu.xnetworking.core.engines.apollo.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query

interface ApolloClientStore {

    suspend fun getClient(tag: Int): ApolloClient?

    suspend fun <Response: Query.Data> query(serverUrl: String, query: Query<Response>): Response

    companion object {

        const val SUBQUERY_TAG: Int = 0

    }

}