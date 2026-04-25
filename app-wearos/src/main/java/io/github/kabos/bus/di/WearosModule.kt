package io.github.kabos.bus.di

import io.github.kabos.bus.presentation.TimelineViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val wearosModule = module {
    viewModelOf(::TimelineViewModel)
}
