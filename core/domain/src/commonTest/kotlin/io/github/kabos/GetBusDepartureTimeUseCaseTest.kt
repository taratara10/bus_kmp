package io.github.kabos

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import io.github.kabos.repository.DummyTimetableRepository
import kotlinx.datetime.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetBusDepartureTimeUseCaseTest {

    private lateinit var repository: DummyTimetableRepository
    private lateinit var useCase: GetBusDepartureTimeUseCase
    private val baseTimetable = Timetable(
        stationName = StationName(""),
        dayType = DayType.Weekday,
        rows = emptyList(),
    )
    private val baseTimetableRow = TimetableRow(hour = 0, minutes = listOf(0))

    @BeforeTest
    fun setup() {
        repository = DummyTimetableRepository()
        useCase = GetBusDepartureTimeUseCase(repository)
    }

    @Test
    fun `returnsEmpty_WhenRepositoryError`() {
        repository.resultGetTimetable = Err(IllegalArgumentException())
        assertEquals(
            expected = emptyList(),
            actual = useCase.invoke(StationName(""), DayType.Weekday)
        )
    }

    @Test
    fun `returnsEmpty_WhenNoTimetableForWeekday`() {
        repository.resultGetTimetable = Ok(
            listOf(
                baseTimetable.copy(
                    dayType = DayType.Holiday,
                    rows = listOf(baseTimetableRow)
                ),
            )
        )
        assertEquals(
            expected = emptyList(),
            actual = useCase.invoke(StationName(""), DayType.Weekday)
        )
    }

    @Test
    fun `returnsWeekdayTimetable_WhenExist`() {
        repository.resultGetTimetable = Ok(
            listOf(
                baseTimetable.copy(
                    dayType = DayType.Weekday,
                    rows = listOf(
                        TimetableRow(hour = 1, minutes = listOf(10, 20)),
                        TimetableRow(hour = 2, minutes = listOf(10, 20)),
                    )
                ),
                baseTimetable.copy(
                    dayType = DayType.Saturday,
                    rows = listOf(
                        TimetableRow(hour = 3, minutes = listOf(10, 20)),
                        TimetableRow(hour = 4, minutes = listOf(10, 20)),
                    )
                ),
            )
        )
        assertEquals(
            expected = listOf(
                LocalTime(1, 10),
                LocalTime(1, 20),
                LocalTime(2, 10),
                LocalTime(2, 20),
            ),
            actual = useCase.invoke(StationName(""), DayType.Weekday)
        )
    }

    @Test
    fun `returnsValidTime_FilterIllegalTimeRow`() {
        repository.resultGetTimetable = Ok(
            listOf(
                baseTimetable.copy(
                    dayType = DayType.Weekday,
                    rows = listOf(
                        TimetableRow(hour = 1, minutes = listOf(10, 99)),
                        TimetableRow(hour = 25, minutes = listOf(10, 20)),
                    )
                ),
            )
        )
        assertEquals(
            expected = listOf(LocalTime(1, 10)),
            actual = useCase.invoke(StationName(""), DayType.Weekday)
        )
    }
}
