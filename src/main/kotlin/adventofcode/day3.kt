package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.math.roundToInt
import kotlin.time.ExperimentalTime

object Day3 {

    private data class Step(val right: Int, val down: Int)

    private val slopeSequence = listOf(
        Step(1, 1),
        Step(3, 1),
        Step(5, 1),
        Step(7, 1),
        Step(1, 2),
    )

    fun countTrees(input: List<String>): Int {
        val board = createBoard(input, 3)

        return foldTrees(board, Step(3, 1))
    }

    fun countTreesInEachSlope(input: List<String>): Int {
        val board = createBoard(input, 7)

        return slopeSequence.fold(1) { acc, steps ->
            acc * foldTrees(board, steps)
        }
    }

    private fun createBoard(input: List<String>, rowMultiplier: Int): List<String> {
        val rowLength = input[0].length
        val boardSize = input.size
        val multiplier = ((boardSize.toDouble() / rowLength).roundToInt() + 1) * rowMultiplier
        return input.map { it.repeat(multiplier) }
    }

    private fun foldTrees(board: List<String>, step: Step): Int {
        val reducedBoard = reduceBoard(board, step)

        var position = 0
        return reducedBoard.fold(0) { acc, row ->
            val newAcc = if (row[position] == '#') acc + 1 else acc
            position += step.right
            newAcc
        }
    }

    private fun reduceBoard(board: List<String>, step: Step): List<String> {
        return board.mapIndexedNotNull { i, row ->
            if (i % step.down == 0) row else null
        }
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day3.txt").readLines()

        printResult("part 1") { countTrees(input) }
        printResult("part 2") { countTreesInEachSlope(input) }
    }
}