package io.github.kabos

data class Timetable(
    val stationName: StationName,
    val dayType: DayType,
    val rows: List<TimetableRow>,
)

data class TimetableRow(
    val timetableName: TimetableName = TimetableName(""),
    val hour: Int,
    val minutes: List<Int>,
)
