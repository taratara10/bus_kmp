package io.github.kabos

data class WeekTimetable(
    val busRouteName: BusRouteName,
    val weekday: List<TimetableRow>,
    val saturday: List<TimetableRow>,
    val holiday: List<TimetableRow>,
) {
    fun getDayTimetable(dayType: DayType): List<TimetableRow> {
        return when (dayType) {
            DayType.Weekday -> this.weekday
            DayType.Saturday -> this.saturday
            DayType.Holiday -> this.holiday
        }
    }
}

data class TimetableRow(
    val hour: Int,
    val minutes: List<Int>,
)

