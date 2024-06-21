package io.github.kabos.extension

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching
import kotlinx.datetime.LocalTime

/**
 * Subtracts the given [before] from this LocalTime, returning a new LocalTime.
 *
 * @param before The LocalTime earlier than this one.
 */
fun LocalTime.subtract(before: LocalTime): Result<LocalTime, Throwable> {
    return runCatching {
        val diff = this.toSecondOfDay() - before.toSecondOfDay()
        if (diff < 0) Err(IllegalArgumentException("before must be earlier than this"))
        LocalTime(
            hour = diff / (60 * 60),
            minute = (diff / 60) % 60,
        )
    }
}

/**
 * @return The formatted time string in "HH:mm" format.
 */
fun LocalTime.toHHmm(): String {
    val hour = this.hour.toString().padStart(2, '0')
    val minute = this.minute.toString().padStart(2, '0')
    return "$hour:$minute"
}
