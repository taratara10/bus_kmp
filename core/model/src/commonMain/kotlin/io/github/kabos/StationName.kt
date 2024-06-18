package io.github.kabos

import kotlin.jvm.JvmInline

@JvmInline
value class StationName(val name: String) {
    companion object {
        val takinoi = StationName("田喜野井")
        val tsudanuma = StationName("津田沼_津08")
    }
}
