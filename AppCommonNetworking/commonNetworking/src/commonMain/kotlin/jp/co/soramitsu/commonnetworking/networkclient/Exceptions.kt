package jp.co.soramitsu.commonnetworking.networkclient

open class SoraNetworkException(m: String, c: Throwable?) : Throwable(m, c)

class CodeNetworkException(val code: Int, m: String, c: Throwable?) : SoraNetworkException(m, c)

class SerializationNetworkException(m: String, c: Throwable?) : SoraNetworkException(m, c)

class GeneralNetworkException(m: String, c: Throwable?) : SoraNetworkException(m, c)