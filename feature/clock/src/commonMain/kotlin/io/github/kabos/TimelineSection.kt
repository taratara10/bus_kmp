package io.github.kabos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.michaelbull.result.getOr
import com.github.michaelbull.result.map
import io.github.kabos.extension.subtract
import io.github.kabos.extension.toHHmm
import io.github.kabos.extension.tommss
import kotlinx.datetime.LocalTime

data class TimelineItem(
    val departureTime: LocalTime,
    val departureTimeText: String,
    val remainingTimeText: String,
) {
    companion object {
        fun of(
            now: LocalTime,
            departure: LocalTime,
            index: Int,
        ): TimelineItem {
            return TimelineItem(
                departureTime = departure,
                departureTimeText = departure.toHHmm(),
                remainingTimeText = if (index == 0) {
                    getCountdownText(start = now, end = departure)
                } else {
                    getRemainingTimeText(start = now, end = departure)
                }
            )
        }

        private fun getCountdownText(
            start: LocalTime,
            end: LocalTime,
        ): String {
            return end.subtract(start)
                .map { remaining -> remaining.tommss() }
                .getOr("")
        }

        private fun getRemainingTimeText(
            start: LocalTime,
            end: LocalTime,
        ): String {
            return end.subtract(start)
                .map { remaining -> "${(remaining.hour * 60) + remaining.minute} minute later" }
                .getOr("")
        }
    }
}

@Composable
internal fun TimelineSection(
    timelines: List<TimelineItem>,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        timelines.forEachIndexed { index, item ->
            TimelineItem(
                name = item.departureTimeText,
                description = item.remainingTimeText,
                isFirst = index == 0,
                isLast = index == timelines.lastIndex
            )
        }
    }
}

@Composable
private fun TimelineItem(
    name: String,
    description: String,
    isFirst: Boolean,
    isLast: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        DotLines(showTopLine = !isFirst, showBottomLine = !isLast)
        Spacer(modifier = Modifier.width(8.dp))
        if (isFirst) {
            FeaturedBusCard(
                departureTime = name,
                remainingTime = description,
            )
        } else {
            BusCard(
                departureTime = name,
                remainingTime = description,
            )
        }
    }
}

@Composable
private fun DotLines(
    lineHeight: Dp = 40.dp,
    showTopLine: Boolean = true,
    showBottomLine: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(32.dp),
    ) {
        val circleSize = 24.dp
        if (showTopLine) {
            Line(height = lineHeight)
            Donuts(size = circleSize)
        } else {
            Spacer(modifier = Modifier.height(lineHeight))
            Circle(size = circleSize * 1.5f)
        }
        if (showBottomLine) {
            Line(height = lineHeight)
        } else {
            Spacer(modifier = Modifier.height(lineHeight))
        }
    }
}

@Composable
private fun Circle(
    size: Dp,
    color: Color = MaterialTheme.colors.secondary,
) {
    Canvas(modifier = Modifier.size(size)) {
        drawCircle(
            color = color,
            center = center,
        )
    }
}

@Composable
private fun Donuts(
    size: Dp,
    color: Color = MaterialTheme.colors.secondary,
) {
    Canvas(modifier = Modifier.size(size)) {
        drawCircle(
            color = color,
            center = center,
            style = Stroke(width = 5f)  // 外枠の幅を指定
        )
        drawCircle(
            color = color,
            radius = drawContext.size.minDimension / 4,
            center = center,
        )
    }
}

@Composable
private fun Line(
    height: Dp,
    color: Color = MaterialTheme.colors.secondary,
) {
    Spacer(
        modifier = Modifier
            .width(2.dp)
            .height(height)
            .background(color)
    )
}

val CardWidth = 150.dp
val CardBackground = Color.Gray.copy(alpha = 0.1f)

@Composable
private fun FeaturedBusCard(
    departureTime: String,
    remainingTime: String,
) {
    Card(
        border = BorderStroke(3.dp, MaterialTheme.colors.primarySurface),
    ) {
        Column(
            modifier = Modifier
                .background(CardBackground)
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .width(CardWidth)
        ) {
            Text(text = departureTime, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = remainingTime,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = "  later",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Composable
private fun BusCard(
    departureTime: String,
    remainingTime: String,
) {
    Card {
        Column(
            modifier = Modifier
                .background(CardBackground)
                .padding(8.dp)
                .width(CardWidth)
        ) {
            Text(text = departureTime, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = remainingTime, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

val previewTimelines = listOf(
    TimelineItem(
        departureTime = LocalTime(10, 0),
        departureTimeText = "10:00",
        remainingTimeText = "5 min later",
    ),
    TimelineItem(
        departureTime = LocalTime(11, 0),
        departureTimeText = "11:00",
        remainingTimeText = "10 min later",
    ),
    TimelineItem(
        departureTime = LocalTime(12, 0),
        departureTimeText = "12:00",
        remainingTimeText = "15 min later",
    )
)
