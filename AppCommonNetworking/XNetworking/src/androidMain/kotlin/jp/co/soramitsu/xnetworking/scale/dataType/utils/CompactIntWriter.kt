package jp.co.soramitsu.xnetworking.scale.dataType.utils

import java.math.BigInteger
import io.emeraldpay.polkaj.scale.CompactMode
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.emeraldpay.polkaj.scale.writer.CompactULongWriter
import jp.co.soramitsu.xnetworking.common.toSharedBigInteger
import java.io.IOException

private val LONG_WRITER = CompactULongWriter()

internal class CompactBigIntWriter : ScaleWriter<BigInteger> {

    @Throws(IOException::class)
    override fun write(wrt: ScaleCodecWriter, value: BigInteger) {
        val mode = CompactMode.forNumber(value)
        val data = value.toSharedBigInteger().toUnsignedBytes()
        var pos = data.size - 1
        if (mode != CompactMode.BIGINT) {
            LONG_WRITER.write(wrt, value.toLong())
        } else {
            wrt.directWrite((data.size - 4 shl 2) + mode.value)
            while (pos >= 0) {
                wrt.directWrite(data[pos].toInt())
                --pos
            }
        }
    }
}