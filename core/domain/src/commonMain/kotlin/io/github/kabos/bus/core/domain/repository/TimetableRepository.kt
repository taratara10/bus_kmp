package io.github.kabos.bus.core.domain.repository

import com.github.michaelbull.result.Result
import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.BusTimetable

interface TimetableRepository {
    fun getTimetableForEachRoute(stationName: StationName): Result<List<BusTimetable>, Exception>
    fun getTimetableUrl(busRouteName: BusRouteName): Result<String, Exception>
}
