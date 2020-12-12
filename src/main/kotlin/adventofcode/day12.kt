package adventofcode

import adventofcode.Day12.Direction.East
import adventofcode.Day12.Direction.North
import adventofcode.Day12.Direction.South
import adventofcode.Day12.Direction.West
import java.io.File
import kotlin.math.abs
import kotlin.time.ExperimentalTime

object Day12 {

    enum class Direction(val degrees: Int) {
        North(0),
        East(90),
        South(180),
        West(270)
    }

    data class Coordinate(val x: Int, val y: Int)

    fun part1(input: List<String>): Int {
        var direction = East
        val result = input.fold(Coordinate(0, 0)) { acc, s ->
            val action = s.first()
            val num = s.drop(1).toInt()
            when (action) {
                'F' -> when (direction) {
                    North -> acc.north(num)
                    East -> acc.east(num)
                    South -> acc.south(num)
                    West -> acc.west(num)
                }
                'N' -> acc.north(num)
                'E' -> acc.east(num)
                'S' -> acc.south(num)
                'W' -> acc.west(num)
                else -> {
                    direction = direction.change(action, num)
                    acc
                }
            }
        }

        return abs(result.x) + abs(result.y)
    }

    private fun Direction.change(dir: Char, num: Int): Direction {
        val newDegrees = when (dir) {
            'R' -> (degrees + num) % 360
            else -> (degrees - num + 360) % 360
        }
        return Direction.values().find { it.degrees == newDegrees }!!
    }

    private fun Coordinate.north(num: Int) = Coordinate(x, y + num)
    private fun Coordinate.east(num: Int) = Coordinate(x + num, y)
    private fun Coordinate.south(num: Int) = Coordinate(x, y - num)
    private fun Coordinate.west(num: Int) = Coordinate(x - num, y)

    @ExperimentalTime
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day12.txt").readLines()

        Utils.printResult("part 1") { part1(input) }
    }
}