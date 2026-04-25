package io.github.kabos.bus.feature.clock.di

import io.github.kabos.bus.feature.clock.ClockViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val clockModule = module {
    viewModelOf(::ClockViewModel)
}
