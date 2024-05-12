package jp.co.soramitsu.xnetworking.lib.datasources.polkaswapwhitelist.impl.models

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestServerRequest

internal data class InternalGetRequest(
    override val url: String
): AbstractRestServerRequest()