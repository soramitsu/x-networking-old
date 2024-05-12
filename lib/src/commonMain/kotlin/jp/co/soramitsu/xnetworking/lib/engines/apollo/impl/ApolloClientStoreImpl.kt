package jp.co.soramitsu.xnetworking.lib.engines.apollo.impl

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CompiledField
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.api.json.JsonWriter
import com.apollographql.apollo3.network.http.LoggingInterceptor
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ApolloClientStoreImpl: ApolloClientStore {

    private val mutex = Mutex()

    private val apolloClientsByUrl = mutableMapOf<String, ApolloClient>()

    override suspend fun <Response : Query.Data> query(
        serverUrl: String,
        query: Query<Response>
    ): Response {
        if (serverUrl !in apolloClientsByUrl.keys) {
            mutex.withLock {
                if (serverUrl !in apolloClientsByUrl.keys) {
                    apolloClientsByUrl[serverUrl] = ApolloClient.Builder()
                        .serverUrl(serverUrl)
                        .addHttpInterceptor(LoggingInterceptor())
                        .build()
                }
            }
        }

        return apolloClientsByUrl[serverUrl]!!.query(query).execute().dataAssertNoErrors
    }

}