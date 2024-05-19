package jp.co.soramitsu.xnetworking.lib.engines.rest.api.models

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

abstract class AbstractRestServerRequest<T> {

    open val bearerToken: String? = null

    open val headers: Map<String, String>? = null

    open val userAgent: String? = null

    open val queryParams: Map<String, String>? = null

    open val responseContentType: RestClient.ContentType = RestClient.ContentType.JSON

    abstract val url: String

    @OptIn(ExperimentalObjCRefinement::class)
    @HiddenFromObjC
    @Transient
    abstract val responseDeserializer: DeserializationStrategy<T>

    override fun equals(other: Any?): Boolean {
        if (other !is AbstractRestServerRequest<*>)
            return false

        if (this !is WithBody && other is WithBody<*>)
            return false

        var areEqual = true

        areEqual = areEqual.and(bearerToken == other.bearerToken)
        if (headers != null) {
            if (other.headers == null)
                return false

            areEqual = areEqual.and(headers!!.keys.containsAll(other.headers!!.keys))
            areEqual = areEqual.and(headers!!.values.containsAll(other.headers!!.values))
        }
        areEqual = areEqual.and(userAgent == other.userAgent)
        if (queryParams != null) {
            if (other.queryParams == null)
                return false

            areEqual = areEqual.and(queryParams!!.keys.containsAll(other.queryParams!!.keys))
            areEqual = areEqual.and(queryParams!!.values.containsAll(other.queryParams!!.values))
        }
        areEqual = areEqual.and(responseContentType === other.responseContentType)
        areEqual = areEqual.and(url == other.url)

        return areEqual
    }

    abstract class WithBody<Response>: AbstractRestServerRequest<Response>() {

        abstract val requestContentType: RestClient.ContentType
        
        abstract val body: Any

        override fun equals(other: Any?): Boolean {
            if (other !is WithBody<*>)
                return false

            var areEqual = super.equals(other)

            areEqual = areEqual.and(requestContentType === other.requestContentType)
            areEqual = areEqual.and(body == other.body)

            return areEqual
        }
        
    }

}