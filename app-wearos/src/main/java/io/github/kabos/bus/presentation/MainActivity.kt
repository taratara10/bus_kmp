package io.github.kabos.bus.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.github.kabos.bus.feature.clock.BusTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TimelineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            BusTheme {
                TimelineScreen(viewModel = viewModel)
            }
        }
    }
}
