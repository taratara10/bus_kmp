package io.github.bus

class GetClockUseCase {
//    operator fun invoke(currentTime: Clock.System): Flow<ClockState> {
//
//    }
}

data class ClockState(
    val remainingTime: String,
    val latestTrain: List<String>,
)
