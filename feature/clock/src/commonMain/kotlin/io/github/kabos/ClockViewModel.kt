package io.github.kabos

import androidx.compose.ui.platform.UriHandler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.runCatching
import io.github.kabos.ClockContract.SideEffect
import io.github.kabos.ClockContract.UiAction
import io.github.kabos.ClockContract.UiState
import io.github.kabos.ClockContract.UiState.Init
import io.github.kabos.mvi.MVI
import io.github.kabos.mvi.mviDelegate
import io.github.kabos.repository.DefaultTimetableRepository
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

interface ClockContract {
    sealed interface UiState {
        data object Init : UiState
        data class Timeline(
            val stationName: StationName,
            val timelines: List<TimelineItem>,
        ) : UiState

        data class NoBus(
            val stationName: StationName,
        ) : UiState
    }

    sealed interface UiAction {
        data object Initialize : UiAction
        data class Reload(val uiState: UiState.Timeline) : UiAction
        data object ShowStationSelectDialog : UiAction
        data class OpenBrowser(val uriHandler: UriHandler, val stationName: StationName) : UiAction
    }

    sealed interface SideEffect {
        data class ShowStationSelectDialog(
            val currentStation: StationName,
            val stations: List<StationName>,
            val updateStation: (StationName) -> Unit,
        ) : SideEffect

        data object ShowSorryUrlSnackBar : SideEffect
    }
}

class ClockViewModel : ViewModel(),
    MVI<UiState, UiAction, SideEffect> by mviDelegate(initialUiState = Init) {

    // todo DI
    private val repository = DefaultTimetableRepository()
    private val useCase = GetBusDepartureTimeUseCase(repository)
    private val stationNames = listOf(StationName.takinoi, StationName.tsudanuma)

    private var selectedStation = stationNames.first()

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.Reload -> {
                    updateUiState {
                        uiAction.uiState.updateTime(now = now())
                    }
                }

                UiAction.Initialize -> {
                    getInitState(
                        stationName = selectedStation,
                        timetable = useCase.invoke(
                            stationName = selectedStation,
                            dayType = DayType.of(Clock.System),
                        ),
                        now = now(),
                    ).let { updateUiState(it) }
                }

                UiAction.ShowStationSelectDialog -> {
                    SideEffect.ShowStationSelectDialog(
                        currentStation = selectedStation,
                        stations = stationNames,
                        updateStation = {
                            selectedStation = it
                            onAction(UiAction.Initialize)
                        },
                    ).let { emitSideEffect(it) }
                }

                is UiAction.OpenBrowser -> {
                    repository.getTimetableUrl(stationName = uiAction.stationName)
                        .andThen { url ->
                            runCatching { uiAction.uriHandler.openUri(url) }
                        }.onFailure {
                            emitSideEffect(SideEffect.ShowSorryUrlSnackBar)
                        }
                }
            }
        }
    }

    private fun now(): LocalTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        // .subtract( LocalTime(12, 0) ).value
    }
}

private fun getInitState(
    stationName: StationName,
    timetable: List<LocalTime>,
    now: LocalTime,
): UiState {
    val timelines = timetable
        .filter { schedule -> schedule > now } // get available bus
        .map { schedule -> TimelineItem.of(now = now, bus = schedule) }

    return if (timelines.isEmpty()) {
        UiState.NoBus(stationName = stationName)
    } else {
        UiState.Timeline(
            stationName = stationName,
            timelines = timelines,
        )
    }
}

private fun UiState.Timeline.updateTime(now: LocalTime): UiState.Timeline {
    return this.copy(
        timelines = this.timelines
            .filter { timeline -> timeline.departureTime > now } // get available bus
            .map { timeline -> TimelineItem.of(now = now, bus = timeline.departureTime) }
    )
}
