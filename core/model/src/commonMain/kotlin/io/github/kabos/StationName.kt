package io.github.kabos

import kotlin.jvm.JvmInline

/**
 * Bus stop name
 * One [StationName] can have multiple [BusRouteName]
 */
@JvmInline
value class StationName(val name: String) {
    companion object {
        val takinoi = StationName("takinoi")
        val tsudanuma = StationName("tsudanuma")
    }
}
