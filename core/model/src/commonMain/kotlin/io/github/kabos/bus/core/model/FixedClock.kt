package io.github.kabos.bus.core.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * A clock use at tests. It always returns a fixed instant.
 *
 * e.g.
 * val testClock = FixedClock(Instant.parse("2024-06-03T00:00:00+09:00"))
 */
class FixedClock(private val instant: Instant) : Clock {
    override fun now(): Instant = instant
}

