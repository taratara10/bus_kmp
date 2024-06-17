package io.github.bus

import kotlinx.datetime.Clock

class GetClockUseCase : Flow {

    operator fun invoke(currentTime: Clock.System) {
        val a = Clock.System.now().toEpochMilliseconds()
    }

}

data class ClockState(
    val remainingTime: String,
    val latestTrain: List<String>,
)
