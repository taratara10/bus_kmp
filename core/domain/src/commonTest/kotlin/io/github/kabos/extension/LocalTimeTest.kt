package io.github.kabos.extension

import kotlinx.datetime.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalTimeTest {
    /**
     * Subtract test
     */
    @Test
    fun `returnOk_WhenTimeIsLater`() {
        val base = LocalTime(hour = 6, minute = 30)
        val test1 = LocalTime(hour = 5, minute = 0)
        val test2 = LocalTime(hour = 5, minute = 40)
        assertEquals(
            expected = LocalTime(hour = 1, minute = 30),
            actual = base.subtract(test1).value
        )
        assertEquals(
            expected = LocalTime(hour = 0, minute = 50),
            actual = base.subtract(test2).value
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

    /**
     * tommss test
     */
    @Test
    fun `returnmmss_WhenHourIsZero`() {
        assertEquals(
            expected = "03:30",
            actual = LocalTime(hour = 0, minute = 3, second = 30).tommss()
        )
    }

    @Test
    fun `returnmmss_WhenHourIsNotZero`() {
        assertEquals(
            expected = "63:01",
            actual = LocalTime(hour = 1, minute = 3, second = 1).tommss()
        )
    }
}
