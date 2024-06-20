package io.github.kabos.extension

import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalTimeTest {
    @Test
    fun `returnOk_WhenTimeIsLater`() {
        val base = LocalTime(hour = 6, minute = 30)
        val test = LocalTime(hour = 5, minute = 0)
        assertEquals(
            expected = LocalTime(hour = 1, minute = 30),
            actual = base.subtract(test).value
        )
    }

    @Test
    fun `returnErr_WhenTimeIsEarly`() {
        val base = LocalTime(hour = 6, minute = 30)
        val test = LocalTime(hour = 7, minute = 0)
        assertTrue(actual = base.subtract(test).isErr)
    }


    @Test
    fun `returnOk_WhenTimeIsSame`() {
        val base = LocalTime(hour = 6, minute = 30)
        val test = LocalTime(hour = 6, minute = 30)
        assertEquals(
            expected = LocalTime(hour = 0, minute = 0),
            actual = base.subtract(test).value
        )
    }
}
