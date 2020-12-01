package adventofcode

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

object Utils {

    fun String.toLongList(): List<Long> {
        return this.split("\n")
            .filter { it.isNotBlank() }
            .map {
                it.trim().toLong()
            }
    }

    @ExperimentalTime
    fun <T> printResult(message: String, block: () -> T) {
        val (result, duration) = measureTimedValue {
            block()
        }
        println("$message: $result ($duration)")
    }
}