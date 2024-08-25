package io.github.kabos.bus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.kabos.bus.core.domain.GetBusDepartureTimeUseCase
import io.github.kabos.bus.core.domain.extension.now
import io.github.kabos.bus.core.domain.mvi.MVI
import io.github.kabos.bus.core.domain.mvi.mviDelegate
import io.github.kabos.bus.core.domain.repository.DefaultTimetableRepository
import io.github.kabos.bus.core.model.DayType
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.feature.clock.ClockContract.SideEffect
import io.github.kabos.bus.feature.clock.ClockContract.UiAction
import io.github.kabos.bus.feature.clock.ClockContract.UiState
import io.github.kabos.bus.feature.clock.ClockContract.UiState.Init
import io.github.kabos.bus.feature.clock.getTimeLine
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

// ベタ打ちであんまり良くない
private val InitState = listOf(Init, Init)

class TimelineViewModel :
    ViewModel(),
    MVI<List<UiState>, UiAction, SideEffect> by mviDelegate(initialUiState = InitState) {
    private val repository = DefaultTimetableRepository()
    private val useCase = GetBusDepartureTimeUseCase(repository)

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                UiAction.Initialize -> {
                    listOf(StationName.takinoi, StationName.tsudanuma).map { station ->
                        getTimeLine(
                            stationName = station,
                            timetable = useCase.invoke(
                                stationName = station,
                                dayType = DayType.of(Clock.System),
                            ),
                            now = now(),
                        )
                    }.let { updateUiState(it) }

                }

                is UiAction.Reload -> {
                    updateUiState {
                        this.map { uiState ->
                            when (uiState) {
                                Init -> uiState
                                is UiState.NoBus -> uiState
                                is UiState.Timeline -> {
                                    getTimeLine(
                                        stationName = uiState.stationName,
                                        timetable = uiState.timelines.map { it.timetableCell },
                                        now = now(),
                                    )
                                }
                            }
                        }
                    }
                }

                is UiAction.OpenBrowser -> {}
                UiAction.ShowStationSelectDialog -> {}
            }
        }
    }
}
