package io.github.kabos

import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class TimelineSectionTest {
    @Test
    fun test() {
        val departure = LocalTime(8, 0)
        assertEquals(
            expected = TimelineItem(
                departureTime = departure,
                departureTimeText = "08:00",
                remainingTimeText = "90 minute later"
            ),
            actual = TimelineItem.of(now = LocalTime(6, 30), bus = departure)
        )
    }
}
