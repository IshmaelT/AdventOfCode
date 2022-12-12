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

//val input = ("addx 15\n" +
//        "addx -11\n" +
//        "addx 6\n" +
//        "addx -3\n" +
//        "addx 5\n" +
//        "addx -1\n" +
//        "addx -8\n" +
//        "addx 13\n" +
//        "addx 4\n" +
//        "noop\n" +
//        "addx -1\n" +
//        "addx 5\n" +
//        "addx -1\n" +
//        "addx 5\n" +
//        "addx -1\n" +
//        "addx 5\n" +
//        "addx -1\n" +
//        "addx 5\n" +
//        "addx -1\n" +
//        "addx -35\n" +
//        "addx 1\n" +
//        "addx 24\n" +
//        "addx -19\n" +
//        "addx 1\n" +
//        "addx 16\n" +
//        "addx -11\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 21\n" +
//        "addx -15\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx -3\n" +
//        "addx 9\n" +
//        "addx 1\n" +
//        "addx -3\n" +
//        "addx 8\n" +
//        "addx 1\n" +
//        "addx 5\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx -36\n" +
//        "noop\n" +
//        "addx 1\n" +
//        "addx 7\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 2\n" +
//        "addx 6\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 7\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "addx -13\n" +
//        "addx 13\n" +
//        "addx 7\n" +
//        "noop\n" +
//        "addx 1\n" +
//        "addx -33\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 2\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 8\n" +
//        "noop\n" +
//        "addx -1\n" +
//        "addx 2\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "addx 17\n" +
//        "addx -9\n" +
//        "addx 1\n" +
//        "addx 1\n" +
//        "addx -3\n" +
//        "addx 11\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx -13\n" +
//        "addx -19\n" +
//        "addx 1\n" +
//        "addx 3\n" +
//        "addx 26\n" +
//        "addx -30\n" +
//        "addx 12\n" +
//        "addx -1\n" +
//        "addx 3\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx -9\n" +
//        "addx 18\n" +
//        "addx 1\n" +
//        "addx 2\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 9\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx -1\n" +
//        "addx 2\n" +
//        "addx -37\n" +
//        "addx 1\n" +
//        "addx 3\n" +
//        "noop\n" +
//        "addx 15\n" +
//        "addx -21\n" +
//        "addx 22\n" +
//        "addx -6\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "addx 2\n" +
//        "addx 1\n" +
//        "noop\n" +
//        "addx -10\n" +
//        "noop\n" +
//        "noop\n" +
//        "addx 20\n" +
//        "addx 1\n" +
//        "addx 2\n" +
//        "addx 2\n" +
//        "addx -6\n" +
//        "addx -11\n" +
//        "noop\n" +
//        "noop\n" +
//        "noop").lines()