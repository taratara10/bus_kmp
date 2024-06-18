package io.github.kabos.repository

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.github.kabos.StationName
import io.github.kabos.Timetable

/**
 * Debug Repository
 */
class DummyTimetableRepository : TimetableRepository {
    var resultGetTimetable: Result<List<Timetable>, Exception> = Ok(emptyList())

    override fun getTimetable(stationName: StationName): Result<List<Timetable>, Exception> {
        return resultGetTimetable
    }
}
