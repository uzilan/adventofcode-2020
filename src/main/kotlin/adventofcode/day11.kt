package adventofcode

import adventofcode.Day11.Companion.Strategy
import adventofcode.Day11.Contents.Empty
import adventofcode.Day11.Contents.Floor
import adventofcode.Day11.Contents.Occupied
import java.io.File
import kotlin.time.ExperimentalTime

typealias Layout = List<List<Day11.Cell>>

class Day11(private val strategy: Strategy) {

    enum class Contents { Floor, Empty, Occupied }
    data class Cell(val rowIndex: Int, val colIndex: Int, val contents: Contents) {
        override fun toString(): String = when (contents) {
            Floor -> "."
            Empty -> "L"
            Occupied -> "#"
        }
    }

    fun stabiliseAndCount(input: List<String>): Int {
        val layout = parseLayout(input)

        return stabilise(layout).sumBy { row ->
            row.count { cell ->
                cell.contents == Occupied
            }
        }
    }

    private fun stabilise(layout: Layout): Layout {
        return (0..Int.MAX_VALUE)
            .fold(layout) { acc, index ->
                println("iteration $index")
                printLayout(acc)
                val newLayout = changeLayout(acc)
                if (newLayout == acc) return acc
                else newLayout
            }
    }

    private fun changeLayout(layout: Layout): Layout {
        return layout.map { row ->
            row.map { cell ->
                cell.change(layout)
            }
        }
    }

    private fun Cell.change(layout: Layout): Cell {
        val occupiedAround = strategy.countOccupied(layout, this)
        val newContents = when (contents) {
            Empty -> if (occupiedAround == 0) Occupied else Empty
            Occupied -> if (occupiedAround >= 4) Empty else Occupied
            else -> Floor
        }
        return this.copy(contents = newContents)
    }

    private fun printLayout(layout: Layout) {
        println("\n${string(layout)}\n")
    }

    private fun string(layout: Layout) = layout.joinToString("\n") { row ->
        row.joinToString("") { cell ->
            cell.toString()
        }
    }

    private fun parseLayout(input: List<String>): Layout {
        return input.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, char ->
                val cell = when (char) {
                    '.' -> Floor
                    else -> Empty
                }
                Cell(rowIndex, colIndex, cell)
            }
        }
    }

    companion object {

        private fun Layout.getOccupiedContentsAt(rowIndex: Int, colIndex: Int): Int {
            return when {
                rowIndex < 0 -> 0
                colIndex < 0 -> 0
                rowIndex >= size -> 0
                colIndex >= this[0].size -> 0
                this[rowIndex][colIndex].contents == Occupied -> 1
                else -> 0
            }
        }

        fun interface Strategy {
            fun countOccupied(layout: Layout, cell: Cell): Int
        }

        val adjacentSeats = Strategy { layout, cell ->
            val above = (cell.colIndex - 1..cell.colIndex + 1).sumBy {
                layout.getOccupiedContentsAt(cell.rowIndex - 1, it)
            }
            val sameRow = listOf(cell.colIndex - 1, cell.colIndex + 1).sumBy {
                layout.getOccupiedContentsAt(cell.rowIndex, it)
            }
            val below = (cell.colIndex - 1..cell.colIndex + 1).sumBy {
                layout.getOccupiedContentsAt(cell.rowIndex + 1, it)
            }
            above + sameRow + below
        }

        val FirstSeat = Strategy { layout, cell ->
//            val size = layout[0].size
//            val right = (cell.colIndex+1..size).foldIndexed(0){index, acc, curr->
//                val currCell = layout.getOccupiedContentsAt(cell.rowIndex, index)
//                when(currCell){
//                    Floor->
//                }
//            }

            0
        }

        @ExperimentalTime
        @JvmStatic
        fun main(args: Array<String>) {
            val input = File("src/main/resources/day11.txt").readLines()

            Utils.printResult("part 1") { Day11(adjacentSeats).stabiliseAndCount(input) }
        }
    }
}