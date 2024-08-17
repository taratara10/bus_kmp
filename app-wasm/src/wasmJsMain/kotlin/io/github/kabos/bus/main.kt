package io.github.kabos.bus

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.github.kabos.bus.feature.clock.BusTheme
import io.github.kabos.bus.feature.clock.ClockScreen
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
private fun App() {
    BusTheme {
        ClockScreen()
    }
}
