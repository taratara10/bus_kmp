package io.github.kabos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClockScreen() {
    Scaffold {
        Card(
            backgroundColor = Color.Gray.copy(alpha = 0.3f),
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text("After 12:00")
                Text(
                    text = "01:23",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
            }
        }
    }
}
