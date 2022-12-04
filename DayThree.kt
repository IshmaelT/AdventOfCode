import java.io.BufferedReader
import java.io.FileReader
import kotlin.streams.toList

fun main() {
    val file = FileReader("./daythree/input.txt")
    val br = BufferedReader(file)
    val badges = mutableListOf<Char>()

    val elves = br.lines().toList()

    var count = 0
    var previous = 0
    while (count <= elves.size - 3) {
        count += 3
        val group = elves.subList(previous, count)

        group[0].find {
            it in group[1] && it in group[2]
        }?.let {
            badges.add(it)
            println("Group number ${count / 3} with Badge = $it")
        }
        previous = count
    }

    val priorities = mutableListOf<Int>()
    badges.forEach {
        if (it.isUpperCase()) {
            priorities.add(it.code - 38)
        } else {
            priorities.add(it.code - 96)
        }
    }
    println("Priority sum is ${priorities.sum()}")
}

private fun partOne(br: BufferedReader, ranks: MutableList<Int>) {
    br.lines().forEach {
        val firstCompartment = it.take(it.length / 2)
        val secondCompartment = it.takeLast(it.length / 2)
        val repeatedItem = firstCompartment.firstOrNull { item ->
            item in secondCompartment
        }

        if (repeatedItem != null) {
            if (repeatedItem.isUpperCase()) {
                ranks.add(repeatedItem.code - 38)
                println("$repeatedItem rank is ${repeatedItem.code - 38}")
            } else {
                ranks.add(repeatedItem.code - 96)
                println("$repeatedItem rank is ${repeatedItem.code - 96}")
            }
        }
    }

    println("Priority sum is ${ranks.sum()}")
}