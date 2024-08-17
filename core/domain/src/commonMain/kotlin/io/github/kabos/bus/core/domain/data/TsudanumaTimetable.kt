package io.github.kabos.bus.core.domain.data

import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.TimetableRow
import io.github.kabos.bus.core.model.Tsu0506
import io.github.kabos.bus.core.model.Tsu07
import io.github.kabos.bus.core.model.Tsu08
import io.github.kabos.bus.core.model.WeekTimetable

// https://www.navitime.co.jp/diagram/bus/00043845/00032183/1/
internal val tsudanumaTsu0506 = WeekTimetable(
    busRouteName = BusRouteName(
        departureStationName = StationName.tsudanuma,
        name = Tsu0506,
    ),
    weekday = listOf(
        TimetableRow(hour = 6, minutes = listOf(25)),
        TimetableRow(hour = 8, minutes = listOf(35, 53)),
        TimetableRow(hour = 10, minutes = listOf(10, 52)),
        TimetableRow(hour = 11, minutes = listOf(10, 52)),
        TimetableRow(hour = 12, minutes = listOf(52)),
        TimetableRow(hour = 13, minutes = listOf(10, 52)),
        TimetableRow(hour = 14, minutes = listOf(52)),
        TimetableRow(hour = 15, minutes = listOf(52)),
        TimetableRow(hour = 16, minutes = listOf(52)),
        TimetableRow(hour = 20, minutes = listOf(4, 47)),
        TimetableRow(hour = 22, minutes = listOf(15, 45)),
        TimetableRow(hour = 23, minutes = listOf(0)),
    ),
    saturday = listOf(
        TimetableRow(hour = 6, minutes = listOf(30)),
        TimetableRow(hour = 8, minutes = listOf(20)),
        TimetableRow(hour = 9, minutes = listOf(52)),
        TimetableRow(hour = 13, minutes = listOf(40)),
        TimetableRow(hour = 21, minutes = listOf(52)),
        TimetableRow(hour = 22, minutes = listOf(30, 47)),
    ),
    holiday = listOf(
        TimetableRow(hour = 6, minutes = listOf(30)),
        TimetableRow(hour = 8, minutes = listOf(20)),
        TimetableRow(hour = 9, minutes = listOf(52)),
        TimetableRow(hour = 13, minutes = listOf(40)),
        TimetableRow(hour = 21, minutes = listOf(52)),
        TimetableRow(hour = 22, minutes = listOf(30, 47)),
    ),
)

// https://www.navitime.co.jp/diagram/bus/00043845/00032187/1/
internal val tsudanumaTsu07 = WeekTimetable(
    busRouteName = BusRouteName(
        departureStationName = StationName.tsudanuma,
        name = Tsu07,
    ),
    weekday = listOf(
        TimetableRow(hour = 6, minutes = listOf(44)),
        TimetableRow(hour = 7, minutes = listOf(10, 31)),
        TimetableRow(hour = 8, minutes = listOf(2)),
        TimetableRow(hour = 9, minutes = listOf(0, 55)),
        TimetableRow(hour = 10, minutes = listOf(55)),
        TimetableRow(hour = 11, minutes = listOf(56)),
        TimetableRow(hour = 12, minutes = listOf(56)),
        TimetableRow(hour = 13, minutes = listOf(56)),
        TimetableRow(hour = 14, minutes = listOf(56)),
        TimetableRow(hour = 15, minutes = listOf(56)),
        TimetableRow(hour = 16, minutes = listOf(56)),
        TimetableRow(hour = 17, minutes = listOf(57)),
        TimetableRow(hour = 18, minutes = listOf(29, 59)),
        TimetableRow(hour = 19, minutes = listOf(29, 59)),
        TimetableRow(hour = 20, minutes = listOf(29, 59)),
        TimetableRow(hour = 21, minutes = listOf(37)),
        TimetableRow(hour = 22, minutes = listOf(17))
    ),
    saturday = listOf(
        TimetableRow(hour = 7, minutes = listOf(11)),
        TimetableRow(hour = 8, minutes = listOf(0, 11)),
        TimetableRow(hour = 10, minutes = listOf(13)),
        TimetableRow(hour = 11, minutes = listOf(15)),
        TimetableRow(hour = 12, minutes = listOf(13)),
        TimetableRow(hour = 13, minutes = listOf(22)),
        TimetableRow(hour = 14, minutes = listOf(22)),
        TimetableRow(hour = 15, minutes = listOf(20)),
        TimetableRow(hour = 16, minutes = listOf(20)),
        TimetableRow(hour = 17, minutes = listOf(20)),
        TimetableRow(hour = 18, minutes = listOf(20)),
        TimetableRow(hour = 19, minutes = listOf(27)),
    ),
    holiday = listOf(
        TimetableRow(hour = 7, minutes = listOf(11)),
        TimetableRow(hour = 8, minutes = listOf(0, 11)),
        TimetableRow(hour = 10, minutes = listOf(13)),
        TimetableRow(hour = 11, minutes = listOf(15)),
        TimetableRow(hour = 12, minutes = listOf(13)),
        TimetableRow(hour = 13, minutes = listOf(22)),
        TimetableRow(hour = 14, minutes = listOf(22)),
        TimetableRow(hour = 15, minutes = listOf(20)),
        TimetableRow(hour = 16, minutes = listOf(20)),
        TimetableRow(hour = 17, minutes = listOf(20)),
        TimetableRow(hour = 18, minutes = listOf(20)),
        TimetableRow(hour = 19, minutes = listOf(27)),
    ),
)

// https://www.navitime.co.jp/diagram/bus/00043845/00032180/1/
internal val tsudanumaTsu08 = WeekTimetable(
    busRouteName = BusRouteName(
        departureStationName = StationName.tsudanuma,
        name = Tsu08,
    ),
    weekday = listOf(
        TimetableRow(hour = 6, minutes = listOf(21, 53)),
        TimetableRow(hour = 7, minutes = listOf(27, 50)),
        TimetableRow(hour = 8, minutes = listOf(2, 27, 50)),
        TimetableRow(hour = 9, minutes = listOf(13, 41)),
        TimetableRow(hour = 10, minutes = listOf(14, 44)),
        TimetableRow(hour = 11, minutes = listOf(14, 44)),
        TimetableRow(hour = 12, minutes = listOf(14, 44)),
        TimetableRow(hour = 13, minutes = listOf(14, 44)),
        TimetableRow(hour = 14, minutes = listOf(14, 44)),
        TimetableRow(hour = 15, minutes = listOf(14, 44)),
        TimetableRow(hour = 16, minutes = listOf(14, 44)),
        TimetableRow(hour = 17, minutes = listOf(14, 44)),
        TimetableRow(hour = 18, minutes = listOf(14, 44)),
        TimetableRow(hour = 19, minutes = listOf(14, 44)),
        TimetableRow(hour = 20, minutes = listOf(14, 50)),
        TimetableRow(hour = 21, minutes = listOf(22, 52)),
        TimetableRow(hour = 22, minutes = listOf(30)),
    ),
    saturday = listOf(
        TimetableRow(hour = 6, minutes = listOf(53)),
        TimetableRow(hour = 7, minutes = listOf(35, 53)),
        TimetableRow(hour = 8, minutes = listOf(35)),
        TimetableRow(hour = 9, minutes = listOf(0, 36)),
        TimetableRow(hour = 10, minutes = listOf(2, 37)),
        TimetableRow(hour = 11, minutes = listOf(2, 42)),
        TimetableRow(hour = 12, minutes = listOf(2, 37)),
        TimetableRow(hour = 13, minutes = listOf(2, 42)),
        TimetableRow(hour = 14, minutes = listOf(2, 37)),
        TimetableRow(hour = 15, minutes = listOf(2, 37)),
        TimetableRow(hour = 16, minutes = listOf(2, 37)),
        TimetableRow(hour = 17, minutes = listOf(2, 37)),
        TimetableRow(hour = 18, minutes = listOf(2, 37)),
        TimetableRow(hour = 19, minutes = listOf(13, 40)),
        TimetableRow(hour = 20, minutes = listOf(7, 47)),
        TimetableRow(hour = 21, minutes = listOf(12, 42)),
        TimetableRow(hour = 21, minutes = listOf(12)),
    ),
    holiday = listOf(
        TimetableRow(hour = 6, minutes = listOf(53)),
        TimetableRow(hour = 7, minutes = listOf(35, 53)),
        TimetableRow(hour = 8, minutes = listOf(35)),
        TimetableRow(hour = 9, minutes = listOf(0, 36)),
        TimetableRow(hour = 10, minutes = listOf(2, 37)),
        TimetableRow(hour = 11, minutes = listOf(2, 42)),
        TimetableRow(hour = 12, minutes = listOf(2, 37)),
        TimetableRow(hour = 13, minutes = listOf(2, 42)),
        TimetableRow(hour = 14, minutes = listOf(2, 37)),
        TimetableRow(hour = 15, minutes = listOf(2, 37)),
        TimetableRow(hour = 16, minutes = listOf(2, 37)),
        TimetableRow(hour = 17, minutes = listOf(2, 37)),
        TimetableRow(hour = 18, minutes = listOf(2, 37)),
        TimetableRow(hour = 19, minutes = listOf(13, 40)),
        TimetableRow(hour = 20, minutes = listOf(7, 47)),
        TimetableRow(hour = 21, minutes = listOf(12, 42)),
        TimetableRow(hour = 21, minutes = listOf(12)),
    ),
)
