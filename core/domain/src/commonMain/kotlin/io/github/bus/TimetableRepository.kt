package io.github.bus

class TimetableRepository {
    fun getTimetable(stationName: StationName): Result<List<Timetable>> {
        return when (stationName) {
            StationName.takinoi -> return Result.success(takinoiTimetable)
            StationName.tsudanuma -> return Result.success(tsudanumaTimetable)
            else -> Result.failure(IllegalArgumentException("定義のない駅名です"))
        }
    }
}

private val takinoiTimetable = listOf(
    Timetable(
        stationName = StationName.takinoi,
        rows = listOf(
            TimetableRow(hour = 6, minutes = listOf(0, 15, 24, 35, 42, 55)),
            TimetableRow(hour = 7, minutes = listOf(10, 22, 27, 34, 47)),
            TimetableRow(hour = 8, minutes = listOf(6, 19, 26, 49)),
            TimetableRow(hour = 9, minutes = listOf(6, 27, 48, 54)),
            TimetableRow(hour = 10, minutes = listOf(32, 48)),
            TimetableRow(hour = 11, minutes = listOf(5, 35, 49)),
            TimetableRow(hour = 12, minutes = listOf(5, 35, 49)),
            TimetableRow(hour = 13, minutes = listOf(5, 35, 49)),
            TimetableRow(hour = 14, minutes = listOf(5, 35, 49)),
            TimetableRow(hour = 15, minutes = listOf(5, 35, 49)),
            TimetableRow(hour = 16, minutes = listOf(5, 35, 49)),
            TimetableRow(hour = 17, minutes = listOf(4, 34)),
            TimetableRow(hour = 18, minutes = listOf(4, 34)),
            TimetableRow(hour = 19, minutes = listOf(8, 38, 50)),
            TimetableRow(hour = 20, minutes = listOf(10, 59)),
            TimetableRow(hour = 21, minutes = listOf(19, 55))
        ),
        dayType = DayType.Weekday
    ),
    Timetable(
        stationName = StationName.takinoi,
        rows = listOf(
            TimetableRow(hour = 6, minutes = listOf(2, 32)),
            TimetableRow(hour = 7, minutes = listOf(2, 21, 32, 55)),
            TimetableRow(hour = 8, minutes = listOf(18, 24, 45)),
            TimetableRow(hour = 9, minutes = listOf(15, 48, 53)),
            TimetableRow(hour = 10, minutes = listOf(9, 48)),
            TimetableRow(hour = 11, minutes = listOf(8, 30, 48)),
            TimetableRow(hour = 12, minutes = listOf(8, 30, 48)),
            TimetableRow(hour = 13, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 14, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 15, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 16, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 17, minutes = listOf(12, 34)),
            TimetableRow(hour = 18, minutes = listOf(12, 38, 54)),
            TimetableRow(hour = 19, minutes = listOf(14, 43)),
            TimetableRow(hour = 20, minutes = listOf(14, 43)),
            TimetableRow(hour = 21, minutes = listOf(33, 56)),
            TimetableRow(hour = 22, minutes = listOf(26))
        ),
        dayType = DayType.Saturday
    ),
    Timetable(
        stationName = StationName.takinoi,
        rows = listOf(
            TimetableRow(hour = 6, minutes = listOf(2, 32)),
            TimetableRow(hour = 7, minutes = listOf(2, 21, 32, 55)),
            TimetableRow(hour = 8, minutes = listOf(18, 24, 45)),
            TimetableRow(hour = 9, minutes = listOf(15, 48, 53)),
            TimetableRow(hour = 10, minutes = listOf(9, 48)),
            TimetableRow(hour = 11, minutes = listOf(8, 30, 48)),
            TimetableRow(hour = 12, minutes = listOf(8, 30, 48)),
            TimetableRow(hour = 13, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 14, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 15, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 16, minutes = listOf(12, 34, 51)),
            TimetableRow(hour = 17, minutes = listOf(12, 34)),
            TimetableRow(hour = 18, minutes = listOf(12, 38, 54)),
            TimetableRow(hour = 19, minutes = listOf(14, 43)),
            TimetableRow(hour = 20, minutes = listOf(14, 43)),
            TimetableRow(hour = 21, minutes = listOf(33, 56)),
            TimetableRow(hour = 22, minutes = listOf(26))
        ),
        dayType = DayType.Holiday
    )
)

private val tsudanumaTimetable = listOf(
    Timetable(
        stationName = StationName.tsudanuma,
        rows = listOf(
            TimetableRow(hour = 6, minutes = listOf(25, 42, 58)),
            TimetableRow(hour = 7, minutes = listOf(27, 42)),
            TimetableRow(hour = 8, minutes = listOf(3, 22, 41)),
            TimetableRow(hour = 9, minutes = listOf(0, 25, 39)),
            TimetableRow(hour = 10, minutes = listOf(0, 22)),
            TimetableRow(hour = 11, minutes = listOf(0, 25)),
            TimetableRow(hour = 12, minutes = listOf(0, 30)),
            TimetableRow(hour = 13, minutes = listOf(0, 25)),
            TimetableRow(hour = 14, minutes = listOf(0, 25)),
            TimetableRow(hour = 15, minutes = listOf(0, 30)),
            TimetableRow(hour = 16, minutes = listOf(2, 30)),
            TimetableRow(hour = 17, minutes = listOf(0, 30)),
            TimetableRow(hour = 18, minutes = listOf(0, 30)),
            TimetableRow(hour = 19, minutes = listOf(0, 30)),
            TimetableRow(hour = 20, minutes = listOf(0, 32)),
            TimetableRow(hour = 21, minutes = listOf(19, 53)),
            TimetableRow(hour = 22, minutes = listOf(30))
        ),
        dayType = DayType.Weekday
    ),
    Timetable(
        stationName = StationName.tsudanuma,
        rows = listOf(
            TimetableRow(hour = 6, minutes = listOf(23, 57)),
            TimetableRow(hour = 7, minutes = listOf(28, 58)),
            TimetableRow(hour = 8, minutes = listOf(21, 41)),
            TimetableRow(hour = 9, minutes = listOf(8, 38)),
            TimetableRow(hour = 10, minutes = listOf(18, 39)),
            TimetableRow(hour = 11, minutes = listOf(18, 38)),
            TimetableRow(hour = 12, minutes = listOf(0, 38)),
            TimetableRow(hour = 13, minutes = listOf(0, 38)),
            TimetableRow(hour = 14, minutes = listOf(0, 38)),
            TimetableRow(hour = 15, minutes = listOf(0, 38)),
            TimetableRow(hour = 16, minutes = listOf(0, 38)),
            TimetableRow(hour = 17, minutes = listOf(0, 38)),
            TimetableRow(hour = 18, minutes = listOf(0, 38)),
            TimetableRow(hour = 19, minutes = listOf(0, 36)),
            TimetableRow(hour = 20, minutes = listOf(5, 36)),
            TimetableRow(hour = 21, minutes = listOf(12, 52)),
            TimetableRow(hour = 22, minutes = listOf(15))
        ),
        dayType = DayType.Saturday
    ),
)
