package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain

import jp.co.soramitsu.xnetworking.core.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.core.engines.rest.api.models.AbstractRestServerRequest
import kotlinx.serialization.Serializable

class JsonPostRequest(
    override val url: String,
    override val body: Any,
): AbstractRestServerRequest.WithBody() {
    override val requestContentType: RestClient.ContentType = RestClient.ContentType.JSON
}

class JsonGetRequest(
    override val url: String,
    override val headers: Map<String, String>? = null,
    override val queryParams: Map<String, String>? = null
): AbstractRestServerRequest()