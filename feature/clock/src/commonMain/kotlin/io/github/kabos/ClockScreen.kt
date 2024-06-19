package io.github.kabos

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ClockScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "takinoi", fontSize = 24.sp) },
            )
        }
    ) {
        TimelineSection(previewTimelines)
    }
}

