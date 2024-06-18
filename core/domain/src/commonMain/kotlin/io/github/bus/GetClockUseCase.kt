package io.github.bus

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock

class GetClockUseCase(private val timetableRepository: TimetableRepository) {
    operator fun invoke(stationName: StationName, current: Clock): Flow<ClockState> {
        val timetable: Result<Timetable, Exception> = timetableRepository.getTimetable(stationName)
            .andThen { timetables -> timetables.getCurrentDayTypeTimetable(dayType = current.toDayType()) }
            .onFailure { return@invoke flowOf(ClockState.Nothing) }

        // todo 仮作成
        return flowOf(ClockState.Nothing)
    }
}

private fun List<Timetable>.getCurrentDayTypeTimetable(
    dayType: DayType,
): Result<Timetable, IllegalArgumentException> {
    return this.find { it.dayType == dayType }?.let { Ok(it) }
        ?: Err(IllegalArgumentException("Timetable not found"))
}

sealed interface ClockState {
    data class Exist(
        val remainingTime: String,
        val latestTrain: List<String>,
    ) : ClockState

    data object Nothing : ClockState
}

