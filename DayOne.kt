import java.io.BufferedReader
import java.io.FileReader
import kotlin.collections.HashMap

fun main() {
    val file = FileReader("input.txt")
    val br = BufferedReader(file)

    val map = HashMap<Int, Int>()
    var sum = 0
    br.lines().forEach {
        if (it.isEmpty()) {
            map[map.size] = sum
            sum = 0
            return@forEach
        }
        sum += it.toInt()
    }
    println(map.toList())
    println(map.values.max()) // highest calorie elf

    val topThree = map.values.sortedDescending().subList(0, 3)
    println(topThree)
    println(topThree.sum()) // highest calorie top 3 elves
}