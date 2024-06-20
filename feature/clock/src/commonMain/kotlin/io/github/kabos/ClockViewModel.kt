package io.github.kabos

import androidx.lifecycle.ViewModel
import io.github.kabos.ClockContract.SideEffect
import io.github.kabos.ClockContract.UiAction
import io.github.kabos.ClockContract.UiState
import io.github.kabos.ClockContract.UiState.Init
import io.github.kabos.mvi.MVI
import io.github.kabos.mvi.mviDelegate

interface ClockContract {

    sealed interface UiState {
        data object Init : UiState
        data class Timeline(
            val stationName: StationName,
            val timelines: List<TimelineItem>,
        ) : UiState

        data object NoBus : UiState
    }

    sealed interface UiAction {
        data object Initialize : UiAction
        data class Reload(val uiState: UiState.Timeline) : UiAction
    }

    sealed interface SideEffect {
        data object ShowStationSelectDialog : SideEffect
        data object NavigateToTimetable : SideEffect
    }
}

class ClockViewModel : ViewModel(),
    MVI<UiState, UiAction, SideEffect> by mviDelegate(initialUiState = Init) {
    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.Reload -> {
                updateUiState {
                    uiAction.uiState.copy(
                        timelines = previewTimelines,
                    )
                }
            }

            UiAction.Initialize -> TODO()
        }
    }
}
