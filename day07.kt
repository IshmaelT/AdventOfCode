import java.io.FileReader

fun main() {

    val lines = FileReader("./day07/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    val map = mutableListOf<String>()

    val directories = mutableListOf<Dir>()
    var children = mutableListOf<String>()

    var depth = 0
    for ((index, line) in lines.withIndex()) {
        if ("$ cd " in line) {
            children = mutableListOf<String>()
            val name = line.removePrefix("$ cd ")
            if (name == "..") {
                depth--
                continue
            } else {
                depth++
                var space = ""
                repeat(depth) {
                    //print(" ")
                    space += " "
                }
                //println(name)
                if ("/" == name) {
                    map.add(space + name)
                    directories.add(Dir(name, depth, children))
                } else {
                    map.add(space + name)
                    directories.add(Dir(name, depth, children))
                }
                continue
            }
        }
        if ("dir " in line) {
            val name = line.removePrefix("dir ")
            var space = ""
            repeat(depth) {
                //print(" ")
                space += " "
            }
            //println(name)
            map.add(space + name)
            children.add(name)
            continue
        }
        if (line.any { it.isDigit() }) {
            val name = line.substringBefore(" ")
            var space = ""
            repeat(depth) {
                //print(" ")
                space += " "
            }
            //println(name)
            map.add(space + name)
            children.add(name)
            continue
        }
    }

    //directories.onEach { println(it) }

    var total = 0
    val sum = mutableSetOf<Int>()
    for (directory in directories) {
        val currentDir = totalOf(directories, directory)
        if(currentDir <= 100000) {
            total += currentDir
            sum.add(currentDir)
            println(directory)
            println("${directory.name} = $currentDir")
        }
    }

    println()
    println("sum of under 100 000 directories = $total or ${sum.sum()}")

}

data class Dir(
    val name: String,
    val depth: Int,
    val subDirs: MutableList<String>
)

fun totalOf(dirs: List<Dir>, startDir: Dir): Int {
    var sum = 0
    for (subDir in startDir.subDirs) {
        sum += if (subDir.isNotBlank() && subDir.trim().isInteger()) {
            subDir.toInt()
        } else {
            val newDir = dirs.find { it.name == subDir && it.depth == startDir.depth + 1 }
            totalOf(dirs, newDir!!)
        }
    }
    return sum
}

fun String.isInteger(): Boolean = all { it.isDigit() }
