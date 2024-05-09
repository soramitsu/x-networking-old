package jp.co.soramitsu.xnetworking.core.datasources.polkaswapwhitelist.impl.models

import jp.co.soramitsu.xnetworking.core.engines.rest.api.models.AbstractRestServerRequest

internal data class InternalGetRequest(
    override val url: String
): AbstractRestServerRequest()