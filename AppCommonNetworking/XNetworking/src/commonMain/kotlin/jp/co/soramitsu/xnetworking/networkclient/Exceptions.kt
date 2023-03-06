package jp.co.soramitsu.xnetworking.networkclient

class SoramitsuNetworkException(val m: String, c: Throwable?, val reason: SoramitsuNetworkExceptionsReason) : Throwable(m, c)

interface SoramitsuNetworkExceptionsReason

class CodeNetworkException(val code: Int) : SoramitsuNetworkExceptionsReason

class SerializationNetworkException : SoramitsuNetworkExceptionsReason

class GeneralNetworkException : SoramitsuNetworkExceptionsReason
