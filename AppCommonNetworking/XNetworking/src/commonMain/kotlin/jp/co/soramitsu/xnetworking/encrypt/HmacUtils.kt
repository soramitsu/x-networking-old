package jp.co.soramitsu.xnetworking.encrypt

expect fun ByteArray.hmacSHA256(secret: ByteArray): ByteArray

expect fun ByteArray.hmacSHA512(secret: ByteArray): ByteArray
