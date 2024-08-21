package io.github.kabos.bus.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.itemsIndexed
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.tooling.preview.devices.WearDevices
import io.github.kabos.bus.core.model.BusRouteName
import io.github.kabos.bus.core.model.StationName
import io.github.kabos.bus.core.model.TimetableCell
import io.github.kabos.bus.feature.clock.ClockContract.UiAction
import io.github.kabos.bus.feature.clock.ClockContract.UiState
import io.github.kabos.bus.feature.clock.ClockViewModel
import io.github.kabos.bus.feature.clock.TimelineItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

@Composable
fun ClockScreen(
    viewModel: ClockViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    ClockScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                text = "バスはありません🪦"
            )
        }

        is UiState.Timeline -> {
            LaunchedEffect(uiState) {
                launch {
                    delay(1000)
                    onAction(UiAction.Reload)
                }
            }
            TimelineContent(
                stationName = uiState.stationName,
                timelines = uiState.timelines,
            )
        }
    }
}

@Composable
private fun TimelineContent(
    stationName: StationName,
    timelines: List<TimelineItem>,
) {
    val state = rememberScalingLazyListState()
    Scaffold(
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(scalingLazyListState = state)
        },
        timeText = {
            TimeText()
        },
    ) {
        ScalingLazyColumn(state = state) {
            item {
                Text(
                    text = stationName.name,
                    fontSize = WearOsTypography.Title.size,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            itemsIndexed(timelines) { index, item ->
                if (index == 0) {
                    FeaturedBusCard(timetableItem = item)
                } else {
                    BusCard(timetableItem = item)
                }
            }
        }
    }
}

@Composable
private fun FeaturedBusCard(
    timetableItem: TimelineItem,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {},
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Column {
            Text(
                text = timetableItem.departureTimeText,
                fontSize = WearOsTypography.Title.size,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = timetableItem.timetableCell.busRouteName.name,
                fontSize = WearOsTypography.Body.size,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = timetableItem.remainingTimeText,
                    fontSize = WearOsTypography.H1.size,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.alignByBaseline(),
                )
                Text(
                    text = "  later",
                    fontSize = WearOsTypography.Body.size,
                    color = Color.Gray,
                    modifier = Modifier.alignByBaseline(),
                )
            }
        }
    }
}

@Composable
private fun BusCard(
    timetableItem: TimelineItem,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {},
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Column {
            Text(
                text = timetableItem.departureTimeText,
                fontSize = WearOsTypography.Title.size,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = timetableItem.timetableCell.busRouteName.name,
                fontSize = WearOsTypography.Body.size,
                color = Color.Gray,
            )
            Text(
                text = timetableItem.remainingTimeText,
                fontSize = WearOsTypography.Body.size,
                color = Color.Gray,
            )
        }
    }
}

private val previewTimelineTime = TimelineItem(
    timetableCell = TimetableCell(
        busRouteName = BusRouteName(
            departureStationName = StationName.takinoi,
            name = "Tsudanuma 08"
        ),
        localTime = LocalTime(hour = 10, minute = 30)
    ),
    departureTimeText = "10:30",
    remainingTimeText = "03:20"
)

@Preview(widthDp = 150)
@Composable
private fun PreviewFeatureCard() {
    FeaturedBusCard(timetableItem = previewTimelineTime)
}

@Preview(widthDp = 150)
@Composable
private fun PreviewBusCard() {
    BusCard(
        timetableItem = previewTimelineTime.copy(
            remainingTimeText = "20 min later"
        )
    )
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun PreviewTimeline() {
    TimelineContent(
        stationName = StationName("takinoi"),
        timelines = listOf(
            previewTimelineTime,
            previewTimelineTime,
        )
    )
}
