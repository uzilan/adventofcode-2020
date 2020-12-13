package adventofcode

import adventofcode.Day12.Companion.Direction.East
import adventofcode.Day12.Companion.Direction.North
import adventofcode.Day12.Companion.Direction.South
import adventofcode.Day12.Companion.Direction.West
import java.io.File
import kotlin.math.abs
import kotlin.time.ExperimentalTime

class Day12(val eastFactor: Int = 1, val northFactor: Int = 1, var direction: Direction = East) {
    companion object {
        enum class Direction(val degrees: Int) {
            North(0),
            East(90),
            South(180),
            West(270)
        }

        data class Coordinate(val x: Int, val y: Int)

        fun part1(input: List<String>): Int {
            val day12 = Day12()
            val result = followActions(input, day12)

            return abs(result.x) + abs(result.y)
        }

        fun part2(input: List<String>): Int {
            val day12 = Day12(10, )
            val result = followActions(input, day12)

            return 0
        }

        private fun followActions(input: List<String>, day12: Day12): Coordinate {
            return input.fold(Coordinate(0, 0)) { acc, s ->
                val action = s.first()
                val num = s.drop(1).toInt()
                when (action) {
                    'F' -> when (day12.direction) {
                        North -> acc.north(num, day12)
                        East -> acc.east(num, day12)
                        South -> acc.south(num, day12)
                        West -> acc.west(num, day12)
                    }
                    'N' -> acc.north(num, day12)
                    'E' -> acc.east(num, day12)
                    'S' -> acc.south(num, day12)
                    'W' -> acc.west(num, day12)
                    else -> {
                        day12.direction = day12.direction.change(action, num)
                        acc
                    }
                }
            }
        }

        private fun Coordinate.north(num: Int, day12: Day12) =
            Coordinate(x * day12.eastFactor, (y + num) * day12.northFactor)

        private fun Coordinate.east(num: Int, day12: Day12) =
            Coordinate((x + num) * day12.eastFactor, y * day12.northFactor)

        private fun Coordinate.south(num: Int, day12: Day12) =
            Coordinate(x * day12.eastFactor, (y - num) * day12.northFactor)

        private fun Coordinate.west(num: Int, day12: Day12) =
            Coordinate((x - num) * day12.eastFactor, y * day12.northFactor)

        private fun Direction.change(dir: Char, num: Int): Direction {
            val newDegrees = when (dir) {
                'R' -> (degrees + num) % 360
                else -> (degrees - num + 360) % 360
            }
            return Direction.values().find { it.degrees == newDegrees }!!
        }

        @ExperimentalTime
        @JvmStatic
        fun main(args: Array<String>) {
            val input = File("src/main/resources/day12.txt").readLines()

            Utils.printResult("part 1") { part1(input) }
        }
    }
}