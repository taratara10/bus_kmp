package io.github.kabos

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek.SATURDAY
import kotlinx.datetime.DayOfWeek.SUNDAY
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

enum class DayType {
    Weekday,
    Saturday,
    Holiday;

    companion object {
        fun of(clock: Clock): DayType {
            val dayOfWeek = clock.now().toLocalDateTime(TimeZone.of("Asia/Tokyo")).dayOfWeek
            return when (dayOfWeek) {
                SATURDAY -> Saturday
                SUNDAY -> Holiday
                else -> Weekday
            }
        }
    }
}
