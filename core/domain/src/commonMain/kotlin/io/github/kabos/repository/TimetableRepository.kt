package io.github.kabos.repository

import com.github.michaelbull.result.Result
import io.github.kabos.StationName
import io.github.kabos.Timetable

interface TimetableRepository {
    fun getTimetable(stationName: StationName): Result<List<Timetable>, Exception>
}
