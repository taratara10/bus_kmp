package io.github.kabos.bus.core.domain.repository

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.WeekTimetable

/**
 * Debug Repository
 */
class DummyTimetableRepository : TimetableRepository {
    var resultGetTimetable: Result<List<WeekTimetable>, Exception> = Ok(emptyList())

    override fun getTimetableForEachRoute(stationName: StationName): Result<List<WeekTimetable>, Exception> {
        return resultGetTimetable
    }

    override fun getTimetableUrl(busRouteName: BusRouteName): Result<String, Exception> {
        return Ok("https://www.navitime.co.jp/diagram/bus/00043845/00012557/1/")
    }
}
