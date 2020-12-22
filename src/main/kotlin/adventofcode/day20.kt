package adventofcode

object Day20 {

    data class Matrix(val tile:String, val data: List<String>) {
        fun rotateLeft(): Matrix {
            val size = data.size
            val matrix = (data[0].indices).map { col ->
                data.map { it[size - col-1] }.joinToString("")
            }
            return Matrix(tile, matrix)
        }

        override fun toString(): String {
            return "Tile $tile:\n${data.joinToString("\n")}"
        }
    }

    fun part1(input: String): Long {
        val split = input.split("\n\n")
        val rows = split[0].split("\n")
        val name = rows[0].split(" ")[1].dropLast(1)
        val matrix = Matrix(name, rows.drop(1))

        val rotated = matrix.rotateLeft()
        return 0
    }
}