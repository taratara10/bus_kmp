package io.github.kabos

import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class TimelineSectionTest {
    @Test
    fun `returnOk_WhenTimeIsLater`() {
        val departure = LocalTime(8, 0)
        // first item return "mm:ss" format
        assertEquals(
            expected = TimelineItem(
                departureTime = departure,
                departureTimeText = "08:00",
                remainingTimeText = "29:40"
            ),
            actual = TimelineItem.of(
                now = LocalTime(7, 30, 20),
                departure = departure,
                index = 0,
            )
        )
        // second item return "mm" format
        assertEquals(
            expected = TimelineItem(
                departureTime = departure,
                departureTimeText = "08:00",
                remainingTimeText = "90 minute later"
            ),
            actual = TimelineItem.of(now = LocalTime(6, 30), departure = departure, index = 1)
        )
    }

    @Test
    fun `returnErr_WhenTimeIsEarly`() {
        val departure = LocalTime(8, 0)
        assertEquals(
            expected = TimelineItem(
                departureTime = departure,
                departureTimeText = "08:00",
                remainingTimeText = ""
            ),
            actual = TimelineItem.of(now = LocalTime(9, 30), departure = departure, index = 1)
        )
    }
}
