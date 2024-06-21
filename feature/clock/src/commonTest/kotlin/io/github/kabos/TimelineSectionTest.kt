package io.github.kabos

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class TimelineSectionTest {
    @Test
    fun a() {
        assertEquals(2, 1)
    }

    @Test
    fun test() {
        val departureTime = LocalTime(8, 0)
        val testClock = FixedClock(Instant.parse("2024-06-01T09:30:00")).now()
            .toLocalDateTime(TimeZone.UTC).time
        assertEquals(
            expected = TimelineItem(
                departureTime = departureTime,
                departureTimeText = "9:30",
                remainingTimeText = "90 minute later"
            ),
            actual = TimelineItem.of(now = testClock, bus = departureTime)
        )
    }
}
