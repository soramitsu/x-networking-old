package jp.co.soramitsu.xnetworking.hash

expect fun ByteArray.blake2b128(): ByteArray

expect fun ByteArray.blake2b256(): ByteArray

expect fun ByteArray.blake2b512(): ByteArray

expect fun ByteArray.keccak256(): ByteArray