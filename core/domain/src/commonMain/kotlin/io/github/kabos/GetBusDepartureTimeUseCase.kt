package io.github.kabos

import com.github.michaelbull.result.getOr
import com.github.michaelbull.result.map
import com.github.michaelbull.result.runCatching
import io.github.kabos.repository.TimetableRepository
import kotlinx.datetime.LocalTime

class GetBusDepartureTimeUseCase(private val timetableRepository: TimetableRepository) {
    operator fun invoke(
        stationName: StationName,
        dayType: DayType,
    ): List<TimetableCell> {
        return timetableRepository.getTimetableForEachRoute(stationName)
            .map { routes: List<WeekTimetable> -> routes.flatMap { it.toTimetableCell(dayType) } }
            .map { cells: List<TimetableCell> -> cells.sortedBy { it.localTime } }
            .getOr(emptyList())
    }
}

private fun List<TimetableRow>.convertToLocalTime(): List<LocalTime> {
    return this.map { row ->
        row.minutes.map { minute ->
            runCatching {
                // Throw IllegalArgumentException if any parameter is out of range
                LocalTime(hour = row.hour, minute = minute)
            }
        }
    } // List<List<Result<LocalTime, IllegalArgumentException>>>
        .flatten() // List<Result<LocalTime, IllegalArgumentException>>
        .mapNotNull { it.getOr(null) } // List<LocalTime>
}

private fun WeekTimetable.toTimetableCell(dayType: DayType): List<TimetableCell> {
    return this.getDayTimetable(dayType)
        .convertToLocalTime()
        .map { TimetableCell(busRouteName = this.busRouteName, localTime = it) }

}

