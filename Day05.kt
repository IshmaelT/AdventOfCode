import java.io.FileReader

fun main() {

    val stacks = listOf(
        mutableListOf('D', 'T', 'W', 'F', 'J', 'S', 'H', 'N'),
        mutableListOf('H', 'R', 'P', 'Q', 'T', 'N', 'B', 'G'),
        mutableListOf('L', 'Q', 'V'),
        mutableListOf('N', 'B', 'S', 'W', 'R', 'Q'),
        mutableListOf('N', 'D', 'F', 'T', 'V', 'M', 'B'),
        mutableListOf('M', 'D', 'B', 'V', 'H', 'T', 'R'),
        mutableListOf('D', 'B', 'Q', 'J'),
        mutableListOf('D', 'N', 'J', 'V', 'R', 'Z', 'H', 'Q'),
        mutableListOf('B', 'N', 'H', 'M', 'S')
    )

//    val stacks = listOf(
//        mutableListOf('Z', 'N'),
//        mutableListOf('M', 'C', 'D'),
//        mutableListOf('P')
//    )

    val instructions = FileReader("./day05/input.txt")
        .readText()
        .lines()
        .drop(10)

//    val instructions = listOf(
//        "move 1 from 2 to 1",
//        "move 3 from 1 to 3",
//        "move 2 from 2 to 1",
//        "move 1 from 1 to 2"
//    )

    instructions.forEachIndexed { index, line ->
        if(line.isBlank()) return@forEachIndexed
        line.split(" ")
            .filter { word ->
               word.isNotBlank() && word.all { it.isDigit() }
            }.let {
                println(stacks)
                println(it)
                println(index)

                val to = stacks[it[2].toInt() - 1]
                val from = stacks[it[1].toInt() - 1]

                // Part 2
                to.addAll(from.takeLast(it[0].toInt()))
                repeat(it[0].toInt()) {
                    from.removeLast()
                }
//                Part 1
//                repeat(it[0].toInt()) {
//                    to.add(from.removeLast())
//                }
            }
    }


    println(stacks)

    println()
    stacks.forEach {
        print(it.last())
    }
}