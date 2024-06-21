package io.github.kabos

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.kabos.ClockContract.SideEffect
import io.github.kabos.ClockContract.UiAction
import io.github.kabos.ClockContract.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-viewmodel.html#using-viewmodel-in-common-code
@Composable
fun ClockScreen(
    viewModel: ClockViewModel = viewModel { ClockViewModel() },
) {
    val uiState by viewModel.uiState.collectAsState()
    val sideEffect = viewModel.sideEffect
    LaunchedEffect(sideEffect) {
        sideEffect.collect {
            when (it) {
                SideEffect.NavigateToTimetable -> TODO()
                SideEffect.ShowStationSelectDialog -> TODO()
            }
        }
    }

    ClockScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
fun ClockScreen(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "takinoi", fontSize = 24.sp) }) }
    ) {
        when (uiState) {
            UiState.Init -> {
                onAction(UiAction.Initialize)
            }

            UiState.NoBus -> {
                Text("No Bus")
            }

            is UiState.Timeline -> {
                LaunchedEffect(uiState) {
                    launch {
                        delay(1000)
                        onAction(UiAction.Reload(uiState))
                    }
                }
                TimelineSection(uiState.timelines)
            }
        }
    }
}

