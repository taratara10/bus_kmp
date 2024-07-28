package io.github.kabos.data

import io.github.kabos.BusRouteName
import io.github.kabos.StationName
import io.github.kabos.TimetableRow
import io.github.kabos.Tsu07
import io.github.kabos.WeekTimetable

val a = listOf(
    TimetableRow(hour = 6, minutes = listOf()),
    TimetableRow(hour = 7, minutes = listOf()),
    TimetableRow(hour = 8, minutes = listOf()),
    TimetableRow(hour = 9, minutes = listOf()),
    TimetableRow(hour = 10, minutes = listOf()),
    TimetableRow(hour = 11, minutes = listOf()),
    TimetableRow(hour = 12, minutes = listOf()),
    TimetableRow(hour = 13, minutes = listOf()),
    TimetableRow(hour = 14, minutes = listOf()),
    TimetableRow(hour = 15, minutes = listOf()),
    TimetableRow(hour = 16, minutes = listOf()),
    TimetableRow(hour = 17, minutes = listOf()),
    TimetableRow(hour = 18, minutes = listOf()),
    TimetableRow(hour = 19, minutes = listOf()),
    TimetableRow(hour = 20, minutes = listOf()),
    TimetableRow(hour = 21, minutes = listOf())
)


internal val tsudanumaTsu07 = WeekTimetable(
    busRouteName = BusRouteName(
        departureStationName = StationName.tsudanuma,
        name = Tsu07,
    ),
    weekday = listOf(
        TimetableRow(hour = 6, minutes = listOf(55)),
        TimetableRow(hour = 7, minutes = listOf(22, 43)),
        TimetableRow(hour = 8, minutes = listOf(14)),
        TimetableRow(hour = 9, minutes = listOf(12)),
        TimetableRow(hour = 10, minutes = listOf(7)),
        TimetableRow(hour = 11, minutes = listOf(7)),
        TimetableRow(hour = 12, minutes = listOf(8)),
        TimetableRow(hour = 13, minutes = listOf(8)),
        TimetableRow(hour = 14, minutes = listOf(8)),
        TimetableRow(hour = 15, minutes = listOf(8)),
        TimetableRow(hour = 16, minutes = listOf(8)),
        TimetableRow(hour = 17, minutes = listOf(8)),
        TimetableRow(hour = 18, minutes = listOf(9, 41)),
        TimetableRow(hour = 19, minutes = listOf(11, 41)),
        TimetableRow(hour = 20, minutes = listOf(11, 41)),
        TimetableRow(hour = 21, minutes = listOf(11, 48)),
        TimetableRow(hour = 22, minutes = listOf(28))
    ),
    saturday = listOf(
        TimetableRow(hour = 7, minutes = listOf(22)),
        TimetableRow(hour = 8, minutes = listOf(11, 22)),
        TimetableRow(hour = 10, minutes = listOf(25)),
        TimetableRow(hour = 11, minutes = listOf(27)),
        TimetableRow(hour = 12, minutes = listOf(25)),
        TimetableRow(hour = 13, minutes = listOf(34)),
        TimetableRow(hour = 14, minutes = listOf(34)),
        TimetableRow(hour = 15, minutes = listOf(32)),
        TimetableRow(hour = 16, minutes = listOf(32)),
        TimetableRow(hour = 17, minutes = listOf(32)),
        TimetableRow(hour = 18, minutes = listOf(32)),
        TimetableRow(hour = 19, minutes = listOf(39)),
    ),
    holiday = listOf(
        TimetableRow(hour = 7, minutes = listOf(22)),
        TimetableRow(hour = 8, minutes = listOf(11, 22)),
        TimetableRow(hour = 10, minutes = listOf(25)),
        TimetableRow(hour = 11, minutes = listOf(27)),
        TimetableRow(hour = 12, minutes = listOf(25)),
        TimetableRow(hour = 13, minutes = listOf(34)),
        TimetableRow(hour = 14, minutes = listOf(34)),
        TimetableRow(hour = 15, minutes = listOf(32)),
        TimetableRow(hour = 16, minutes = listOf(32)),
        TimetableRow(hour = 17, minutes = listOf(32)),
        TimetableRow(hour = 18, minutes = listOf(32)),
        TimetableRow(hour = 19, minutes = listOf(39)),
    ),
)
