package adventofcode

typealias ThreeCups = List<Int>

object Day23 {
    data class Circle(val numbers: List<Int>, var currentIndex: Int = 0) {

        fun nextIndex(): Int {
            currentIndex = if (currentIndex == numbers.size - 1) 0
            else currentIndex + 1
            return currentIndex
        }

        fun current() = numbers[currentIndex]

        fun threeCups(): ThreeCups {
            return when (currentIndex) {
                numbers.size - 1 -> numbers.subList(0, 3)
                numbers.size - 2 -> listOf(numbers.last()) + numbers.subList(0, 2)
                numbers.size - 3 -> numbers.subList(numbers.size - 2, numbers.size) + numbers.first()
                else -> numbers.subList(currentIndex + 1, currentIndex + 4)
            }
        }

        fun withoutThreeCups(): Circle {
            val threeCups = threeCups()
            return Circle(numbers - threeCups)
        }

        fun destinationIndex(): Int {
            val current = numbers[currentIndex]
            val toFind = if (current == numbers.minOrNull()!!) numbers.maxOrNull()!!
            else numbers.filter { it < current }.maxOrNull()!!
            return numbers.indexOf(toFind)
        }

        fun destination() = numbers[destinationIndex()]

        fun insertThreeCups(threeCups: ThreeCups): Circle {
            val newNumbers = when (val destinationIndex = destinationIndex()) {
                numbers.size - 1 -> threeCups + numbers
                else -> numbers.subList(0, destinationIndex + 1) +
                        threeCups +
                        numbers.subList(destinationIndex + 1, numbers.size)
            }
            return Circle(newNumbers)
        }
    }

    fun part1(input: Int): Int {
        val cups = Circle(split(input))
        val currentIndex = cups.currentIndex
        val current = cups.current()
        val pickUp = cups.threeCups()
        val withoutThreeCups = cups.withoutThreeCups()
        val destinationIndex = withoutThreeCups.destinationIndex()
        val destination = withoutThreeCups.destination()
        val insertThreeCups = withoutThreeCups.insertThreeCups(pickUp)


        return 0
    }

    private fun split(input: Int): List<Int> {
        return input.toString()
            .split("")
            .drop(1)
            .dropLast(1)
            .map { it.toInt() }
    }
}