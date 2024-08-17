package io.github.kabos.bus.core.model

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class DayTypeTest {
    @Test
    fun `ReturnsSaturday_WhenDateIsSaturday`() {
        val testClock = FixedClock(Instant.parse("2024-06-01T00:00:00+09:00"))
        assertEquals(
            expected = DayType.Saturday,
            actual = DayType.of(testClock),
        )
    }

    @Test
    fun `ReturnsHoliday_WhenDateIsSunday`() {
        val testClock = FixedClock(Instant.parse("2024-06-02T00:00:00+09:00"))
        assertEquals(
            expected = DayType.Holiday,
            actual = DayType.of(testClock),
        )
    }

    @Test
    fun `ReturnsWeekday_WhenDateIsMonday`() {
        val testClock = FixedClock(Instant.parse("2024-06-03T00:00:00+09:00"))
        assertEquals(
            expected = DayType.Weekday,
            actual = DayType.of(testClock),
        )
    }
}
