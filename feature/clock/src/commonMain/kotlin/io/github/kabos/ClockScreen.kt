package io.github.kabos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
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
    val showSnackBar: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(sideEffect) {
        sideEffect.collect {
            when (it) {
                is SideEffect.ShowStationSelectDialog -> {
                    showDialog = it
                }

                SideEffect.ShowSorryUrlSnackBar -> {
                    showSnackBar.value = true
                }
            }
        }
    }

    ClockScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        showSnackBar = showSnackBar,
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
    showSnackBar: MutableState<Boolean>,
) {
    Box(modifier = Modifier.fillMaxSize()) {
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
                        onAction(UiAction.Reload)
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

        if (showSnackBar.value) {
            LaunchedEffect(Unit) {
                delay(2000)
                showSnackBar.value = false
            }
            Snackbar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                Text("Sorry! website not found.")
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
    val handler = LocalUriHandler.current
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stationName.name, fontSize = 24.sp)
                Row {
                    Button(
                        onClick = {
                            onAction(
                                UiAction.OpenBrowser(
                                    uriHandler = handler,
                                    stationName = stationName
                                )
                            )
                        }
                    ) {
                        Text("timetable")
                    }
                    Button(
                        onClick = { onAction(UiAction.ShowStationSelectDialog) }
                    ) {
                        Text("station")
                    }
                }
            }
        }
    )
}
