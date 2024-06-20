package io.github.kabos.extension

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
        LocalTime(
            hour = this.hour - before.hour,
            minute = this.minute - before.minute
        )
    }
}
