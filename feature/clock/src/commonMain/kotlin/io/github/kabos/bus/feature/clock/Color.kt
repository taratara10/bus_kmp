package io.github.kabos.bus.feature.clock

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BusColorPrimary = Color(0xFFE95098)
val BusColorOnPrimary = Color(0xFFFFFFFF)
val BusColorPrimaryContainer = Color(0xFFFFE0E8)
val BusColorSecondary = Color(0xFF9A5C99)
val BusColorOnSecondary = Color(0xFFFFFFFF)
val BusColorError = Color(0xFFB3261E)
val BusColorOnError = Color(0xFFFFFFFF)
val BusColorBackground = Color(0xFFFFFBFE)
val BusColorOnBackground = Color(0xFF1C1B1F)
val BusColorSurface = Color(0xFFFFFBFE)
val BusColorOnSurface = Color(0xFF1C1B1F)

@Composable
fun BusTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = Colors(
            primary = BusColorPrimary,
            onPrimary = BusColorOnPrimary,
            secondary = BusColorSecondary,
            onSecondary = BusColorOnSecondary,
            error = BusColorError,
            onError = BusColorOnError,
            background = BusColorBackground,
            onBackground = BusColorOnBackground,
            surface = BusColorSurface,
            onSurface = BusColorOnSurface,
            primaryVariant = BusColorPrimaryContainer,
            secondaryVariant = BusColorPrimaryContainer,
            isLight = true,
        ),
        typography = typography,
        shapes = shapes,
        content = content,
    )
}
