package io.github.kabos

import kotlin.jvm.JvmInline

@JvmInline
value class StationName(val name: String) {
    companion object {
        val takinoi = StationName("takinoi")
        val tsudanuma = StationName("tsudanuma")
    }
}
