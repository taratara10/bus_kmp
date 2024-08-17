import androidx.compose.runtime.Composable
import io.github.kabos.bus.feature.clock.BusTheme
import io.github.kabos.bus.feature.clock.ClockScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BusTheme {
        ClockScreen()
    }
}
