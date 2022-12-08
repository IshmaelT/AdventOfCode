import java.io.FileReader
import java.text.NumberFormat
import java.util.*

fun main() {

    val lines = FileReader("./day07/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    val directories = mutableListOf<Dir>()
    var subDirectories = mutableListOf<String>()

    var depth = 0
    val parents = Stack<String>().apply { push("") }
    lines.forEach { line ->
        if ("$ cd " in line) {
            subDirectories = mutableListOf()
            val name = line.removePrefix("$ cd ")
            if (name == "..") {
                parents.pop()
                depth--
                return@forEach
            } else {
                depth++
                directories.add(Dir(name, depth, subDirectories, parents.peek()))
                parents.push(name)
                return@forEach
            }
        }
        if ("dir " in line) {
            val name = line.removePrefix("dir ")
            subDirectories.add(name)
            return@forEach
        }
        if (line.any { it.isDigit() }) {
            val name = line.substringBefore(" ")
            subDirectories.add(name)
            return@forEach
        }
    }

    directories
        //.onEach { println(it) }
        .map { totalOf(directories, it) }
        .filter { it <= 100000 }
        .sum()
        .also { total ->
            println("Sum of directories <= 100000 is $total")
        }
}

data class Dir(
    val name: String,
    val depth: Int,
    val subDirs: MutableList<String>,
    val parent: String
)

fun totalOf(dirs: List<Dir>, startDir: Dir): Int {
    var sum = 0
    for (subDir in startDir.subDirs) {
        sum += if (subDir.isNotBlank() && subDir.trim().isInteger()) {
            subDir.toInt()
        } else {
            val newDir = dirs
                .first {
                    it.name == subDir &&
                            it.depth == startDir.depth + 1 &&
                            it.parent == startDir.name
                }
            totalOf(dirs, newDir)
        }
    }
    return sum
}

fun String.isInteger(): Boolean = all { it.isDigit() }
