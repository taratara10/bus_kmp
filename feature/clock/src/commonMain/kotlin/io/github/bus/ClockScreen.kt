package io.github.bus

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ClockScreen() {
    Scaffold {
        Card {
            Column {
                Text("12:00発まで")
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
