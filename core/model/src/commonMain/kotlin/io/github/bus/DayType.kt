package io.github.bus

import kotlinx.datetime.Clock

enum class DayType {
    Weekday,
    Saturday,
    Holiday,
}

fun Clock.toDayType(): DayType {
    // todo TDDで実装する
    return DayType.Weekday
}
