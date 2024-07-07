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

    override fun getTimetableUrl(stationName: StationName): Result<String, Exception> {
        return Ok("https://www.navitime.co.jp/diagram/bus/00043845/00012557/1/")
    }
}
