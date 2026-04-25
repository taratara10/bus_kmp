package io.github.kabos.bus.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.github.kabos.bus.feature.clock.BusTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TimelineViewModel by viewModel()

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
