package io.github.kabos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var showDialog: SideEffect.ShowStationSelectDialog? by remember {
        mutableStateOf(null)
    }
    LaunchedEffect(sideEffect) {
        sideEffect.collect {
            when (it) {
                SideEffect.NavigateToTimetable -> TODO()
                is SideEffect.ShowStationSelectDialog -> {
                    showDialog = it
                }
            }
        }
    }

    ClockScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )

    showDialog?.let { event ->
        StationSelectDialog(
            currentStation = event.currentStation,
            stations = event.stations,
            onDismissRequest = { showDialog = null },
            onSelect = event.updateStation,
        )
    }
}

@Composable
private fun ClockScreen(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {
    when (uiState) {
        UiState.Init -> {
            onAction(UiAction.Initialize)
        }

        is UiState.NoBus -> {
            ClockScaffold(
                stationName = uiState.stationName,
                onAction = onAction,
            ) {
                Text("No Bus")
            }
        }

        is UiState.Timeline -> {
            LaunchedEffect(uiState) {
                launch {
                    delay(1000)
                    onAction(UiAction.Reload(uiState))
                }
            }
            ClockScaffold(
                stationName = uiState.stationName,
                onAction = onAction,
            ) {
                TimelineSection(uiState.timelines)
            }
        }
    }
}

@Composable
private fun ClockScaffold(
    stationName: StationName,
    onAction: (UiAction) -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            Title(
                stationName = stationName,
                onAction = onAction,
            )
        },
        content = { content() },
    )
}

@Composable
private fun Title(
    stationName: StationName,
    onAction: (UiAction) -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stationName.name, fontSize = 24.sp)
                Button(
                    onClick = { onAction(UiAction.ShowStationSelectDialog) }
                ) {
                    Text("station select")
                }
            }
        }
    )
}
