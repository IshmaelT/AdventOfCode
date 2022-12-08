import java.io.FileReader

fun main() {

    val lines = FileReader("./day07/input.txt")
        .readText()
        .lines()
        .dropLast(1)

    val map = mutableListOf<String>()

    var depth = 0
    for ((index, line) in lines.withIndex()) {
        if ("$ cd " in line) {
            val name = line.removePrefix("$ cd ")
            if (name == "..") {
                depth--
                continue
            } else {
                depth++
                var space = ""
                repeat(depth) {
                    print(" ")
                    space += " "
                }
                println(name)
                if ("/" == name) {
                    map.add(space+name)
                } else {
                    map.add(space+name)
                }
                continue
            }
        }
        if ("dir " in line) {
            val name = line.removePrefix("dir ")
            var space = ""
            repeat(depth) {
                print(" ")
                space += " "
            }
            println(name)
            map.add(space + name)
            continue
        }
        if (line.any { it.isDigit() }) {
            val name = line.substringBefore(" ")
            var space = ""
            repeat(depth) {
                print(" ")
                space += " "
            }
            println(name)
            map.add(space + name)
            continue
        }
    }
}
