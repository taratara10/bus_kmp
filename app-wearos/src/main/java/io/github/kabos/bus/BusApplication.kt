package io.github.kabos.bus

import android.app.Application
import io.github.kabos.bus.core.domain.di.domainModule
import io.github.kabos.bus.di.wearosModule
import io.github.kabos.bus.feature.clock.di.clockModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BusApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BusApplication)
            modules(domainModule, clockModule, wearosModule)
        }
    }
}
