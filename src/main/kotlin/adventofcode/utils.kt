package adventofcode

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

object Utils {

    fun String.toStringList(): List<String> {
        return this.split("\n")
            .filter { it.isNotBlank() }
            .map { it.trim() }
    }

    fun String.toLongList(): List<Long> {
        return toStringList()
            .map { it.toLong() }
    }

    @ExperimentalTime
    fun <T> printResult(message: String, block: () -> T) {
        val (result, duration) = measureTimedValue {
            block()
        }
        println("$message: $result ($duration)")
    }
}