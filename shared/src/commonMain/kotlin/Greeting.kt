import io.github.kabos.DayType

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${DayType.Holiday.name}!"
    }
}
