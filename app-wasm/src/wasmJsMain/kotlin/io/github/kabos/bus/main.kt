package io.github.kabos.bus

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.github.kabos.bus.core.domain.di.domainModule
import io.github.kabos.bus.feature.clock.BusTheme
import io.github.kabos.bus.feature.clock.ClockScreen
import io.github.kabos.bus.feature.clock.di.clockModule
import kotlinx.browser.document
import org.koin.compose.KoinApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
private fun App() {
    KoinApplication(application = {
        modules(domainModule, clockModule)
    }) {
        BusTheme {
            ClockScreen()
        }
    }
}
