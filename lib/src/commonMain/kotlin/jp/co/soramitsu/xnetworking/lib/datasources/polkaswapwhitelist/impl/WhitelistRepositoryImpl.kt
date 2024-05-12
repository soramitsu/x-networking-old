package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl

import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.AbstractWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.api.WhitelistRepository
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models.InternalGetRequest
import jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models.InternalWhitelistedToken
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.serialization.json.JsonArray

class WhitelistRepositoryImpl(
    private val restClient: RestClient
): WhitelistRepository {

    override suspend fun getWhitelistedTokens(requestUrl: String): List<AbstractWhitelistedToken> {
        val responseAsJsonArray = restClient.get(
            request = InternalGetRequest(
                url = requestUrl
            ),
            kSerializer = JsonArray.serializer()
        )

        return responseAsJsonArray.map {
            InternalWhitelistedToken(
                tokenAddress = it.asJsonObjectNullable.getPrimitiveContentOrEmpty("address"),
                rawIconLink = it.asJsonObjectNullable.getPrimitiveContentOrEmpty("icon"),
            )
        }
    }

}