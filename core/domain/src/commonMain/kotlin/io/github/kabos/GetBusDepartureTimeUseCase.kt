package io.github.kabos

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.getOr
import com.github.michaelbull.result.runCatching
import io.github.kabos.repository.TimetableRepository
import kotlinx.datetime.LocalTime

class GetBusDepartureTimeUseCase(private val timetableRepository: TimetableRepository) {
    operator fun invoke(
        stationName: StationName,
        dayType: DayType,
    ): List<LocalTime> {
        return timetableRepository.getTimetable(stationName)
            .andThen { timetables -> timetables.findTimetableByDayType(dayType) }
            .andThen { timetable -> Ok(timetable.rows.convertToLocalTime()) }
            .getOr(emptyList())
    }
}

private fun List<Timetable>.findTimetableByDayType(
    dayType: DayType,
): Result<Timetable, IllegalArgumentException> {
    return this.find { it.dayType == dayType }?.let { Ok(it) }
        ?: Err(IllegalArgumentException("Timetable not found"))
}

fun List<TimetableRow>.convertToLocalTime(): List<LocalTime> {
    return this.map { row ->
        row.minutes.map { minute ->
            // Throw IllegalArgumentException if any parameter is out of range
            runCatching {
                LocalTime(hour = row.hour, minute = minute)
            }
        }
    } // List<List<Result<LocalTime, IllegalArgumentException>>>
        .flatten() // List<Result<LocalTime, IllegalArgumentException>>
        .mapNotNull { it.getOr(null) } // List<LocalTime>
}
