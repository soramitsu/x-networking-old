package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest
import kotlinx.serialization.DeserializationStrategy

internal data class InternalGetRequest<T>(
    override val url: String,
    override val responseDeserializer: DeserializationStrategy<T>
): AbstractRestServerRequest<T>()