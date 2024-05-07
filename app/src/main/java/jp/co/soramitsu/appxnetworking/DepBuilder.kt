package jp.co.soramitsu.appxnetworking

import android.content.Context
import jp.co.soramitsu.xnetworking.core.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.core.engines.apollo.impl.ApolloClientStoreImpl
import jp.co.soramitsu.xnetworking.core.engines.preferences.api.KeyValuePreferences
import jp.co.soramitsu.xnetworking.core.engines.preferences.impl.KeyValuePreferencesImpl
import jp.co.soramitsu.xnetworking.core.engines.preferences.impl.builder.ExpectActualKeyValuePreferencesEngineFactory
import jp.co.soramitsu.xnetworking.core.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.core.engines.rest.api.models.AbstractRestClientConfig
import jp.co.soramitsu.xnetworking.core.engines.rest.impl.RestClientImpl
import kotlinx.serialization.json.Json

object DepBuilder {

    val restClient: RestClient = RestClientImpl(
        restClientConfig = object : AbstractRestClientConfig() {

            override fun isLoggingEnabled(): Boolean = true

            override fun getConnectTimeoutMillis(): Long = 30_000L

            override fun getRequestTimeoutMillis(): Long = 30_000L

            override fun getSocketTimeoutMillis(): Long = 30_000L

            override fun getOrCreateJsonConfig(): Json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }

        }
    )

    fun build(ctx: Context) {
        val apolloClientStore: ApolloClientStore = ApolloClientStoreImpl()

        val keyValuePreferences: KeyValuePreferences = KeyValuePreferencesImpl(
            keyValuePreferencesEngine = ExpectActualKeyValuePreferencesEngineFactory(
                context = ctx,
                preferencesName = "Test"
            )
        )
    }
}
