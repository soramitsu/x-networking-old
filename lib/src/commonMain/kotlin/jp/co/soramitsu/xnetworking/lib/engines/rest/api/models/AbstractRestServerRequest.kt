package jp.co.soramitsu.xnetworking.lib.engines.rest.api.models

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient

abstract class AbstractRestServerRequest {

    open val bearerToken: String? = null

    open val headers: Map<String, String>? = null

    open val userAgent: String? = null

    open val queryParams: Map<String, String>? = null

    open val responseContentType: RestClient.ContentType = RestClient.ContentType.JSON

    abstract val url: String

    abstract class WithBody: AbstractRestServerRequest() {

        abstract val requestContentType: RestClient.ContentType
        
        abstract val body: Any
        
    }

}