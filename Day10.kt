import java.io.FileReader

fun main() {
    val input = FileReader("./day10/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    operator fun String.component1() = substringBefore(" ")
    operator fun String.component2() = substringAfter(" ").toIntOrNull() ?: 0

    data class Cycle(
        val cycleNumber: Int,
        val xRegister: Int
    ) {
        fun signalStrength() = cycleNumber * xRegister
    }

    val cycles = mutableListOf<Cycle>()
    var index = 0
    var register = 1
    input.forEach { (instr, value) ->
        when (instr) {
            "addx" -> {
                cycles.run {
                    index++
                    add(Cycle(cycleNumber = index, xRegister = register))
                    index++
                    add(Cycle(cycleNumber = index, xRegister = register))
                    register += value
                }
            }

            "noop" -> cycles.run {
                index++
                add(Cycle(cycleNumber = index, xRegister = register))
            }
        }
    }

    val range = listOf(20, 60, 100, 140, 180, 220)
    cycles.filter { it.cycleNumber in range }
        .onEach { println("$it  ${it.signalStrength()}") }
        .sumOf { it.signalStrength() }
        .also { println("Sum is $it") }

}

private fun part2(input: List<String>) {

    operator fun String.component1() = substringBefore(" ")
    operator fun String.component2() = substringAfter(" ").toIntOrNull() ?: 0
    var index = 0
    var register = 0
    val screen = MutableList(40 * 6) { '.' }
    var nextLine = 0
    input.forEach { (instr, value) ->
        when (instr) {
            "addx" -> {
                nextLine = (index / 40) * 40
                if ((index) in (register + nextLine)..(register + nextLine + 2)) {
                    screen[index] = '#'
                } else {
                    screen[index] = '.'
                }
                index++
                if ((index) in (register + nextLine)..(register + nextLine + 2)) {
                    screen[index] = '#'
                } else {
                    screen[index] = '.'
                }
                index++
                register += value

            }

            "noop" -> {
                nextLine = (index / 40) * 40
                if ((index) in (register + nextLine)..(register + nextLine + 2)) {
                    screen[index] = '#'
                } else {
                    screen[index] = '.'
                }
                index++
            }
        }
    }

    screen.chunked(40).forEach {
        it.onEach { char -> print(char) }
        println()
    }
}