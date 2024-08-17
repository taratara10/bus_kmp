package io.github.kabos.bus.core.domain

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import io.github.kabos.bus.core.domain.repository.DummyTimetableRepository
import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.DayType
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.TimetableCell
import io.github.kabos.bus.core.model.TimetableRow
import io.github.kabos.bus.core.model.WeekTimetable
import kotlinx.datetime.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetBusDepartureTimeUseCaseTest {

    private lateinit var repository: DummyTimetableRepository
    private lateinit var useCase: GetBusDepartureTimeUseCase
    private val baseTimetable = WeekTimetable(
        busRouteName = BusRouteName(departureStationName = StationName(name = ""), name = ""),
        weekday = emptyList(),
        saturday = emptyList(),
        holiday = emptyList(),
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
                    weekday = emptyList(),
                    saturday = listOf(baseTimetableRow),
                    holiday = listOf(baseTimetableRow)
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
                    weekday = listOf(
                        TimetableRow(hour = 1, minutes = listOf(10, 20)),
                        TimetableRow(hour = 2, minutes = listOf(10, 20)),
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
            actual = useCase.invoke(StationName(""), DayType.Weekday).map { it.localTime }
        )
    }

    @Test
    fun `returnsValidTime_FilterIllegalTimeRow`() {
        repository.resultGetTimetable = Ok(
            listOf(
                baseTimetable.copy(
                    weekday = listOf(
                        TimetableRow(hour = 1, minutes = listOf(10, 99)), // illegal minute
                        TimetableRow(hour = 25, minutes = listOf(10, 20)), // illegal hour
                    )
                ),
            )
        )
        assertEquals(
            expected = listOf(LocalTime(1, 10)),
            actual = useCase.invoke(StationName(""), DayType.Weekday).map { it.localTime }
        )
    }

    @Test
    fun `returnJoinedTimetable_WhenMultipleTables`() {
        val firstBusRoute = BusRouteName(departureStationName = StationName.takinoi, name = "first")
        val secondBusRoute =
            BusRouteName(departureStationName = StationName.takinoi, name = "second")
        repository.resultGetTimetable = Ok(
            listOf(
                WeekTimetable(
                    busRouteName = firstBusRoute,
                    weekday = listOf(
                        TimetableRow(hour = 2, minutes = listOf(30)),
                        TimetableRow(hour = 18, minutes = listOf(30)),
                    ),
                    saturday = listOf(
                        TimetableRow(hour = 1, minutes = listOf(10)),
                    ),
                    holiday = listOf(
                        TimetableRow(hour = 1, minutes = listOf(20)),
                    ),
                ),
                WeekTimetable(
                    busRouteName = secondBusRoute,
                    weekday = listOf(
                        TimetableRow(hour = 2, minutes = listOf(0)),
                        TimetableRow(hour = 19, minutes = listOf(0)),
                    ),
                    saturday = listOf(),
                    holiday = listOf(),
                )
            )
        )
        assertEquals(
            expected = listOf(
                TimetableCell(busRouteName = secondBusRoute, localTime = LocalTime(2, 0)),
                TimetableCell(busRouteName = firstBusRoute, localTime = LocalTime(2, 30)),
                TimetableCell(busRouteName = firstBusRoute, localTime = LocalTime(18, 30)),
                TimetableCell(busRouteName = secondBusRoute, localTime = LocalTime(19, 0)),
            ),
            actual = useCase.invoke(StationName(""), DayType.Weekday)
        )
    }
}
