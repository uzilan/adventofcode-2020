package adventofcode

object Day15 {

    fun part1(input: List<Int>): Int {
        val visited = mutableMapOf<Int, MutableList<Int>>()
        input.dropLast(1).forEachIndexed { index, i -> visited[i] = mutableListOf(index + 1) }

        var last = input.last()
        (input.size..2020).forEach { index ->
            val current = when {
                visited.containsKey(last) -> visited[last]!!.getDiff(index)
                else -> 0
            }
            if (visited[last] == null) {
                visited[last] = mutableListOf(index)
            }
            else {
                visited[last]!!.add(index)
            }
            last = current
        }

        return 0
    }

    private fun List<Int>.getDiff(currentIndex: Int): Int {
        return if (this.size == 1) currentIndex - this.first()
        else this.last() - this[size - 2]
    }
}
