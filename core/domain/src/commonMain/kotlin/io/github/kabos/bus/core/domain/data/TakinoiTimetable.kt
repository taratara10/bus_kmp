package io.github.kabos.bus.core.domain.data

import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.TimetableRow
import io.github.kabos.bus.core.model.Tsu07
import io.github.kabos.bus.core.model.Tsu08
import io.github.kabos.bus.core.model.WeekTimetable

// https://www.navitime.co.jp/diagram/bus/00139553/00032180/0/
internal val takinoiTsu07: WeekTimetable = WeekTimetable(
    busRouteName = BusRouteName(
        departureStationName = StationName.takinoi,
        name = Tsu07,
    ),
    weekday = listOf(
        TimetableRow(hour = 6, minutes = listOf(15, 42)),
        TimetableRow(hour = 7, minutes = listOf(7, 37)),
        TimetableRow(hour = 8, minutes = listOf(28)),
        TimetableRow(hour = 9, minutes = listOf(31)),
        TimetableRow(hour = 10, minutes = listOf(31)),
        TimetableRow(hour = 11, minutes = listOf(33)),
        TimetableRow(hour = 12, minutes = listOf(33)),
        TimetableRow(hour = 13, minutes = listOf(33)),
        TimetableRow(hour = 14, minutes = listOf(33)),
        TimetableRow(hour = 15, minutes = listOf(33)),
        TimetableRow(hour = 16, minutes = listOf(33)),
    ),
    saturday = listOf(
        TimetableRow(hour = 6, minutes = listOf(2, 52)),
        TimetableRow(hour = 7, minutes = listOf(52)),
        TimetableRow(hour = 8, minutes = listOf(56)),
        TimetableRow(hour = 9, minutes = listOf(42)),
        TimetableRow(hour = 10, minutes = listOf(50)),
        TimetableRow(hour = 11, minutes = listOf(48)),
        TimetableRow(hour = 12, minutes = listOf(57)),
        TimetableRow(hour = 13, minutes = listOf(57)),
        TimetableRow(hour = 14, minutes = listOf(55)),
        TimetableRow(hour = 15, minutes = listOf(55)),
        TimetableRow(hour = 16, minutes = listOf(55)),
        TimetableRow(hour = 19, minutes = listOf(7)),
    ),
    holiday = listOf(
        TimetableRow(hour = 6, minutes = listOf(2, 52)),
        TimetableRow(hour = 7, minutes = listOf(52)),
        TimetableRow(hour = 8, minutes = listOf(56)),
        TimetableRow(hour = 9, minutes = listOf(42)),
        TimetableRow(hour = 10, minutes = listOf(50)),
        TimetableRow(hour = 11, minutes = listOf(48)),
        TimetableRow(hour = 12, minutes = listOf(57)),
        TimetableRow(hour = 13, minutes = listOf(57)),
        TimetableRow(hour = 14, minutes = listOf(55)),
        TimetableRow(hour = 15, minutes = listOf(55)),
        TimetableRow(hour = 16, minutes = listOf(55)),
        TimetableRow(hour = 19, minutes = listOf(7)),
    ),
)

internal val takinoiTsu08 = WeekTimetable(
    busRouteName = BusRouteName(
        departureStationName = StationName.takinoi,
        name = Tsu08,
    ),
    weekday = listOf(
        TimetableRow(hour = 6, minutes = listOf(0, 30, 55)),
        TimetableRow(hour = 7, minutes = listOf(16, 26, 47)),
        TimetableRow(hour = 8, minutes = listOf(12, 43)),
        TimetableRow(hour = 9, minutes = listOf(13, 41)),
        TimetableRow(hour = 10, minutes = listOf(11, 41)),
        TimetableRow(hour = 11, minutes = listOf(14, 44)),
        TimetableRow(hour = 12, minutes = listOf(14, 44)),
        TimetableRow(hour = 13, minutes = listOf(14, 44)),
        TimetableRow(hour = 14, minutes = listOf(14, 44)),
        TimetableRow(hour = 15, minutes = listOf(14, 44)),
        TimetableRow(hour = 16, minutes = listOf(14, 43)),
        TimetableRow(hour = 17, minutes = listOf(12)),
        TimetableRow(hour = 18, minutes = listOf(12, 42)),
        TimetableRow(hour = 19, minutes = listOf(17, 52)),
        TimetableRow(hour = 20, minutes = listOf(31)),
        TimetableRow(hour = 21, minutes = listOf(34))
    ),
    saturday = listOf(
        TimetableRow(hour = 6, minutes = listOf(32)),
        TimetableRow(hour = 7, minutes = listOf(12, 30)),
        TimetableRow(hour = 8, minutes = listOf(12, 35)),
        TimetableRow(hour = 9, minutes = listOf(8, 28)),
        TimetableRow(hour = 10, minutes = listOf(2, 27)),
        TimetableRow(hour = 11, minutes = listOf(7, 27)),
        TimetableRow(hour = 12, minutes = listOf(2, 27)),
        TimetableRow(hour = 13, minutes = listOf(11, 32)),
        TimetableRow(hour = 14, minutes = listOf(6, 32)),
        TimetableRow(hour = 15, minutes = listOf(6, 31)),
        TimetableRow(hour = 16, minutes = listOf(6, 31)),
        TimetableRow(hour = 17, minutes = listOf(6, 31)),
        TimetableRow(hour = 18, minutes = listOf(2, 42)),
        TimetableRow(hour = 19, minutes = listOf(18, 46)),
        TimetableRow(hour = 20, minutes = listOf(25, 51)),
        TimetableRow(hour = 21, minutes = listOf(21, 51))
    ),
    holiday = listOf(
        TimetableRow(hour = 6, minutes = listOf(32)),
        TimetableRow(hour = 7, minutes = listOf(12, 30)),
        TimetableRow(hour = 8, minutes = listOf(12, 35)),
        TimetableRow(hour = 9, minutes = listOf(8, 28)),
        TimetableRow(hour = 10, minutes = listOf(2, 27)),
        TimetableRow(hour = 11, minutes = listOf(7, 27)),
        TimetableRow(hour = 12, minutes = listOf(2, 27)),
        TimetableRow(hour = 13, minutes = listOf(11, 32)),
        TimetableRow(hour = 14, minutes = listOf(6, 32)),
        TimetableRow(hour = 15, minutes = listOf(6, 31)),
        TimetableRow(hour = 16, minutes = listOf(6, 31)),
        TimetableRow(hour = 17, minutes = listOf(6, 31)),
        TimetableRow(hour = 18, minutes = listOf(2, 42)),
        TimetableRow(hour = 19, minutes = listOf(18, 46)),
        TimetableRow(hour = 20, minutes = listOf(25, 51)),
        TimetableRow(hour = 21, minutes = listOf(21, 51))
    ),
)
