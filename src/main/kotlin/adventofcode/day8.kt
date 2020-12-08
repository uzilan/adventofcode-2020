package adventofcode

import adventofcode.Utils.printResult
import java.io.File
import kotlin.time.ExperimentalTime

object Day8 {

    private enum class Op { nop, acc, jmp, end }
    private data class Row(val op: Op, val num: Int, var called: Boolean = false)
    private data class ResultAndSignal(val result: Int, val signal: Boolean = false)

    fun part1(input: List<String>): Int {
        val rows = parseInput(input)

        return applyRow(rows, 0, 0)
    }

    fun part2(input: List<String>): Int {
        val rows = parseInput(input) + Row(Op.end, 0)

        rows.forEachIndexed { i, row ->
            when (row.op) {
                Op.nop -> {
                    val newRows = rows.reset().replace(i, row.copy(op = Op.jmp))
                    val res = applyRowWithCheck(newRows, 0, ResultAndSignal(0))
                    if (res.signal) return res.result
                }
                Op.jmp -> {
                    val newRows = rows.reset().replace(i, row.copy(op = Op.nop))
                    val res = applyRowWithCheck(newRows, 0, ResultAndSignal(0))
                    if (res.signal) return res.result
                }
                else -> {
                }
            }
        }
        return 0
    }

    private fun applyRowWithCheck(rows: List<Row>, currentIndex: Int, result: ResultAndSignal): ResultAndSignal {
        val currentRow = rows[currentIndex]
        if (currentRow.called) return result

        currentRow.called = true
        return when (currentRow.op) {
            Op.end -> ResultAndSignal(result.result, true)
            Op.nop -> applyRowWithCheck(rows, currentIndex + 1, result)
            Op.acc -> applyRowWithCheck(rows, currentIndex + 1, ResultAndSignal(result.result + currentRow.num))
            Op.jmp -> applyRowWithCheck(rows, currentIndex + currentRow.num, result)
        }
    }

    private fun applyRow(rows: List<Row>, currentIndex: Int, result: Int): Int {
        val currentRow = rows[currentIndex]
        if (currentRow.called) return result

        currentRow.called = true
        return when (currentRow.op) {
            Op.nop, Op.end -> applyRow(rows, currentIndex + 1, result)
            Op.acc -> applyRow(rows, currentIndex + 1, result + currentRow.num)
            Op.jmp -> applyRow(rows, currentIndex + currentRow.num, result)
        }
    }

    private fun parseInput(input: List<String>): List<Row> {
        return input.map {
            it.split(" ").run {
                val op = Op.valueOf(this[0])
                val num = this[1].toInt()
                Row(op, num)
            }
        }
    }

    private fun List<Row>.replace(i: Int, element: Row): List<Row> {
        return this.take(i) + element + this.takeLast(this.size - i - 1)
    }

    private fun List<Row>.reset(): List<Row> {
        this.forEach { it.called = false }
        return this
    }

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day8.txt").readLines()

        printResult("part 1") { part1(input) }
        printResult("part 2") { part2(input) }
    }
}


