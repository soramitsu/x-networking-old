package jp.co.soramitsu.xnetworking.lib.engines.rest.impl.builder

import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.RestClientException
import jp.co.soramitsu.xnetworking.lib.engines.utils.JsonGetRequestIOS
import jp.co.soramitsu.xnetworking.lib.engines.utils.JsonPostRequestIOS
import kotlin.coroutines.cancellation.CancellationException
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("get")
@Throws(
    RestClientException::class,
    CancellationException::class
)
suspend fun RestClient.getIOS(
    request: JsonGetRequestIOS
): String = get(request)

@OptIn(ExperimentalObjCName::class)
@ObjCName("post")
@Throws(
    RestClientException::class,
    CancellationException::class
)
suspend fun RestClient.postIOS(
    request: JsonPostRequestIOS
): String = get(request)