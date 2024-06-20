package io.github.kabos

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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalTime

data class TimelineItem(
    val departureTime: LocalTime,
    val departureTimeText: String,
    val remainingTimeText: String,
) {
    companion object {
        fun of(
            now: LocalTime,
            bus: LocalTime,
        ): TimelineItem {
            return TimelineItem(
                departureTime = bus,
                departureTimeText = "${bus.hour}:${bus.minute}",
                remainingTimeText = getRemainingTimeText(start = now, end = bus)
            )
        }

        private fun getRemainingTimeText(
            start: LocalTime,
            end: LocalTime,
        ): String {
            val remainingHour = end.hour - start.hour
            val hourText = if (remainingHour > 0) end.hour else 0
            val remainingMinute = end.minute - start.minute
            return "$hourText:$remainingMinute later"
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
        BusCard(
            departureTime = name,
            remainingTime = description,
        )
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
        if (showTopLine) {
            Line(height = lineHeight)
        } else {
            Spacer(modifier = Modifier.height(lineHeight))
        }
        CirclePoint(size = 24.dp)
        if (showBottomLine) {
            Line(height = lineHeight)
        } else {
            Spacer(modifier = Modifier.height(lineHeight))
        }
    }
}

@Composable
private fun CirclePoint(
    size: Dp,
    color: Color = Color.Blue,
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
    color: Color = Color.Blue,
) {
    Spacer(
        modifier = Modifier
            .width(2.dp)
            .height(height)
            .background(color)
    )
}

@Composable
private fun BusCard(
    departureTime: String,
    remainingTime: String,
) {
    Card {
        Column(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp)
                .padding(end = 24.dp)
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
