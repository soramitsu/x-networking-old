package jp.co.soramitsu.xnetworking.hash

expect fun ByteArray.blake2b128(): ByteArray

expect fun ByteArray.blake2b256(): ByteArray

expect fun ByteArray.blake2b512(): ByteArray

expect fun ByteArray.keccak256(): ByteArray

expect fun ByteArray.xxHash64(): Long

expect fun ByteArray.xxHash128(): ByteArray

expect fun ByteArray.xxHash256(): ByteArray

expect fun ByteArray.xxHash64Concat(): ByteArray

expect fun ByteArray.blake2b128Concat(): ByteArray
