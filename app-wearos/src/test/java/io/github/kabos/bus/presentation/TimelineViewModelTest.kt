@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package io.github.kabos.bus.presentation

import androidx.compose.ui.platform.UriHandler
import com.github.michaelbull.result.Ok
import io.github.kabos.bus.core.domain.GetBusDepartureTimeUseCase
import io.github.kabos.bus.core.domain.repository.DummyTimetableRepository
import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.BusTimetable
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.TimetableRow
import io.github.kabos.bus.feature.clock.ClockContract.SideEffect
import io.github.kabos.bus.feature.clock.ClockContract.UiAction
import io.github.kabos.bus.feature.clock.ClockContract.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class TimelineViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: DummyTimetableRepository
    private lateinit var useCase: GetBusDepartureTimeUseCase
    private lateinit var fakeClock: FakeClock
    private lateinit var viewModel: TimelineViewModel
    private lateinit var defaultTimeZone: java.util.TimeZone

    @Before
    fun setUp() {
        // Fix timezone to UTC so LocalTime comparisons are deterministic regardless of CI environment
        defaultTimeZone = java.util.TimeZone.getDefault()
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"))

        Dispatchers.setMain(testDispatcher)
        repository = DummyTimetableRepository()
        useCase = GetBusDepartureTimeUseCase(repository)
        // UTC Monday 2024-06-03 10:00:00 → local time 10:00, DayType = Weekday
        fakeClock = FakeClock(Instant.parse("2024-06-03T10:00:00Z"))
        viewModel = TimelineViewModel(useCase, fakeClock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        java.util.TimeZone.setDefault(defaultTimeZone)
    }

    // ─── A. 初期状態 ──────────────────────────────────────────────────────────

    @Test
    fun `initialState_isListOfInit`() {
        assertEquals(
            expected = listOf(UiState.Init, UiState.Init),
            actual = viewModel.uiState.value,
        )
    }

    // ─── B. UiAction.Initialize ────────────────────────────────────────────────

    @Test
    fun `initialize_withFutureBuses_stateBecomesTimeline`() {
        repository.resultGetTimetable = Ok(futureBusTimetable)

        viewModel.onAction(UiAction.Initialize)

        val state = viewModel.uiState.value
        assertEquals(2, state.size)
        assertIs<UiState.Timeline>(state[0])
        assertIs<UiState.Timeline>(state[1])
        assertEquals(StationName.takinoi, (state[0] as UiState.Timeline).stationName)
        assertEquals(StationName.tsudanuma, (state[1] as UiState.Timeline).stationName)
    }

    @Test
    fun `initialize_withNoBuses_stateBecomesNoBus`() {
        repository.resultGetTimetable = Ok(pastBusTimetable)

        viewModel.onAction(UiAction.Initialize)

        val state = viewModel.uiState.value
        assertIs<UiState.NoBus>(state[0])
        assertIs<UiState.NoBus>(state[1])
        assertEquals(StationName.takinoi, (state[0] as UiState.NoBus).stationName)
        assertEquals(StationName.tsudanuma, (state[1] as UiState.NoBus).stationName)
    }

    @Test
    fun `initialize_filtersPastBuses`() {
        repository.resultGetTimetable = Ok(
            listOf(
                BusTimetable(
                    busRouteName = BusRouteName(StationName.takinoi, "route1"),
                    weekday = listOf(
                        TimetableRow(hour = 8, minutes = listOf(0)),  // past
                        TimetableRow(hour = 9, minutes = listOf(0)),  // past
                        TimetableRow(hour = 11, minutes = listOf(0)), // future
                        TimetableRow(hour = 12, minutes = listOf(0)), // future
                    ),
                    holiday = emptyList(),
                )
            )
        )

        viewModel.onAction(UiAction.Initialize)

        val timeline = viewModel.uiState.value[0]
        assertIs<UiState.Timeline>(timeline)
        assertEquals(2, timeline.timelines.size)
        assertTrue(timeline.timelines.all { it.timetableCell.localTime.hour >= 11 })
    }

    @Test
    fun `initialize_firstTimelineItem_hasCountdownText`() {
        repository.resultGetTimetable = Ok(futureBusTimetable)

        viewModel.onAction(UiAction.Initialize)

        val timeline = viewModel.uiState.value[0]
        assertIs<UiState.Timeline>(timeline)
        // index=0 uses getCountdownText → tommss() → "mm:ss" format
        assertTrue(
            timeline.timelines[0].remainingTimeText.matches(Regex("\\d{2}:\\d{2}")),
            "Expected 'mm:ss' countdown format but was '${timeline.timelines[0].remainingTimeText}'",
        )
    }

    @Test
    fun `initialize_subsequentTimelineItems_hasLaterText`() {
        repository.resultGetTimetable = Ok(
            listOf(
                BusTimetable(
                    busRouteName = BusRouteName(StationName.takinoi, "route1"),
                    weekday = listOf(
                        TimetableRow(hour = 11, minutes = listOf(0)),
                        TimetableRow(hour = 12, minutes = listOf(0)),
                        TimetableRow(hour = 13, minutes = listOf(0)),
                    ),
                    holiday = emptyList(),
                )
            )
        )

        viewModel.onAction(UiAction.Initialize)

        val timeline = viewModel.uiState.value[0]
        assertIs<UiState.Timeline>(timeline)
        // index >= 1 uses getRemainingTimeText → "X minute later"
        timeline.timelines.drop(1).forEach { item ->
            assertTrue(
                item.remainingTimeText.endsWith("minute later"),
                "Expected 'X minute later' format but was '${item.remainingTimeText}'",
            )
        }
    }

    @Test
    fun `initialize_busesSortedByDepartureTime`() {
        repository.resultGetTimetable = Ok(
            listOf(
                BusTimetable(
                    busRouteName = BusRouteName(StationName.takinoi, "route1"),
                    weekday = listOf(
                        TimetableRow(hour = 13, minutes = listOf(0)),
                        TimetableRow(hour = 11, minutes = listOf(0)),
                        TimetableRow(hour = 12, minutes = listOf(0)),
                    ),
                    holiday = emptyList(),
                )
            )
        )

        viewModel.onAction(UiAction.Initialize)

        val timeline = viewModel.uiState.value[0]
        assertIs<UiState.Timeline>(timeline)
        val times = timeline.timelines.map { it.timetableCell.localTime }
        assertEquals(times.sortedBy { it.toSecondOfDay() }, times)
    }

    // ─── C. UiAction.Reload ────────────────────────────────────────────────────

    @Test
    fun `reload_whenStateIsInit_remainsInit`() {
        viewModel.onAction(UiAction.Reload)

        assertEquals(
            expected = listOf(UiState.Init, UiState.Init),
            actual = viewModel.uiState.value,
        )
    }

    @Test
    fun `reload_whenStateIsNoBus_remainsNoBus`() {
        repository.resultGetTimetable = Ok(pastBusTimetable)
        viewModel.onAction(UiAction.Initialize)
        assertIs<UiState.NoBus>(viewModel.uiState.value[0])

        viewModel.onAction(UiAction.Reload)

        assertIs<UiState.NoBus>(viewModel.uiState.value[0])
        assertIs<UiState.NoBus>(viewModel.uiState.value[1])
    }

    @Test
    fun `reload_whenStateIsTimeline_refreshesBasedOnCurrentTime`() {
        repository.resultGetTimetable = Ok(
            listOf(
                BusTimetable(
                    busRouteName = BusRouteName(StationName.takinoi, "route1"),
                    weekday = listOf(
                        TimetableRow(hour = 11, minutes = listOf(0)), // future at 10:00
                        TimetableRow(hour = 12, minutes = listOf(0)), // future at 10:00
                    ),
                    holiday = emptyList(),
                )
            )
        )
        viewModel.onAction(UiAction.Initialize)
        val timelineAfterInit = viewModel.uiState.value[0]
        assertIs<UiState.Timeline>(timelineAfterInit)
        assertEquals(2, timelineAfterInit.timelines.size)

        // advance clock to 11:30 UTC → 11:00 bus is now past
        fakeClock.instant = Instant.parse("2024-06-03T11:30:00Z")

        viewModel.onAction(UiAction.Reload)

        val timelineAfterReload = viewModel.uiState.value[0]
        assertIs<UiState.Timeline>(timelineAfterReload)
        assertEquals(1, timelineAfterReload.timelines.size)
        assertEquals(12, timelineAfterReload.timelines[0].timetableCell.localTime.hour)
    }

    // ─── D. OpenBrowser / ShowStationSelectDialog ──────────────────────────────

    @Test
    fun `openBrowser_doesNotChangeState`() {
        val stateBefore = viewModel.uiState.value

        viewModel.onAction(
            UiAction.OpenBrowser(
                uriHandler = object : UriHandler {
                    override fun openUri(uri: String) {}
                },
                busRouteName = BusRouteName(StationName.takinoi, "route1"),
            )
        )

        assertEquals(stateBefore, viewModel.uiState.value)
    }

    @Test
    fun `showStationSelectDialog_doesNotChangeState`() {
        val stateBefore = viewModel.uiState.value

        viewModel.onAction(UiAction.ShowStationSelectDialog)

        assertEquals(stateBefore, viewModel.uiState.value)
    }

    // ─── E. SideEffect ────────────────────────────────────────────────────────

    @Test
    fun `noSideEffectsEmitted`() {
        val collected = mutableListOf<SideEffect>()
        val collectJob = CoroutineScope(testDispatcher).launch {
            viewModel.sideEffect.collect { collected.add(it) }
        }

        viewModel.onAction(UiAction.Initialize)
        viewModel.onAction(UiAction.Reload)
        viewModel.onAction(UiAction.ShowStationSelectDialog)

        collectJob.cancel()
        assertTrue(collected.isEmpty())
    }

    // ─── Test doubles ──────────────────────────────────────────────────────────

    private class FakeClock(var instant: Instant) : Clock {
        override fun now(): Instant = instant
    }

    // ─── Test data ─────────────────────────────────────────────────────────────

    // Buses at 11:00 and 12:00, both future relative to fakeClock (10:00 UTC)
    private val futureBusTimetable = listOf(
        BusTimetable(
            busRouteName = BusRouteName(StationName.takinoi, "route1"),
            weekday = listOf(
                TimetableRow(hour = 11, minutes = listOf(0)),
                TimetableRow(hour = 12, minutes = listOf(0)),
            ),
            holiday = emptyList(),
        )
    )

    // Buses at 8:00 and 9:00, both past relative to fakeClock (10:00 UTC)
    private val pastBusTimetable = listOf(
        BusTimetable(
            busRouteName = BusRouteName(StationName.takinoi, "route1"),
            weekday = listOf(
                TimetableRow(hour = 8, minutes = listOf(0)),
                TimetableRow(hour = 9, minutes = listOf(0)),
            ),
            holiday = emptyList(),
        )
    )
}
