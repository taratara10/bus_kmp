package io.github.kabos

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek.SATURDAY
import kotlinx.datetime.DayOfWeek.SUNDAY
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

enum class DayType {
    Weekday,
    Saturday,
    Holiday,
}

fun Clock.toDayType(): DayType {
    val dayOfWeek = this.now().toLocalDateTime(TimeZone.of("Asia/Tokyo")).dayOfWeek
    return when (dayOfWeek) {
        SATURDAY -> DayType.Saturday
        SUNDAY -> DayType.Holiday
        else -> DayType.Weekday
    }
}
