package io.github.kabos.repository

import com.github.michaelbull.result.Result
import io.github.kabos.BusRouteName
import io.github.kabos.StationName
import io.github.kabos.WeekTimetable

interface TimetableRepository {
    fun getTimetableForEachRoute(stationName: StationName): Result<List<WeekTimetable>, Exception>
    fun getTimetableUrl(busRouteName: BusRouteName): Result<String, Exception>
}
