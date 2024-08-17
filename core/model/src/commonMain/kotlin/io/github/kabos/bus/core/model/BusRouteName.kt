package io.github.kabos.bus.core.model

data class BusRouteName(
    val departureStationName: StationName,
    val name: String,
)

const val Tsu0506 = "tsu05_06_narashino"
const val Tsu07 = "tsu07_fresh_town"
const val Tsu08 = "tsu08_yakuendai"
