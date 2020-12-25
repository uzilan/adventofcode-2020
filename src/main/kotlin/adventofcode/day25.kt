package adventofcode

import kotlin.time.ExperimentalTime

object Day25 {

    private const val divider = 20201227L

    fun part1(cardPK: Long, doorPk: Long): Long {
        val cardLoopSize = transforms(cardPK)
//        val doorLoopSize = transforms(doorPk)

        return (0L until cardLoopSize).fold(1L) { acc, _ ->
            acc * doorPk % divider
        }
    }

    private fun transforms(publicKey: Long): Long {
        val subjectNumber = 7L
        (0L..Long.MAX_VALUE).fold(1L) { acc, curr ->
            if (acc == publicKey) return curr
            acc * subjectNumber % divider
        }
        return 0
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        Utils.printResult("part 1") { Day25.part1(16616892, 14505727) }
    }
}