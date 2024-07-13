import androidx.compose.runtime.Composable
import io.github.kabos.BusTheme
import io.github.kabos.ClockScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BusTheme {
        ClockScreen()
    }
}
