package io.github.kabos.bus.core.domain.di

import io.github.kabos.bus.core.domain.GetBusDepartureTimeUseCase
import io.github.kabos.bus.core.domain.repository.DefaultTimetableRepository
import io.github.kabos.bus.core.domain.repository.TimetableRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    single<TimetableRepository> { DefaultTimetableRepository() }
    factoryOf(::GetBusDepartureTimeUseCase)
}
