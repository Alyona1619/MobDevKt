import java.io.FileInputStream
import java.util.*

fun main() {
    val sc = Scanner(FileInputStream("src/main/resources/roles.txt"))

    val roles = arrayListOf<String>()
    while (sc.hasNextLine())
        roles.add(sc.nextLine())

    val textLines = arrayListOf<String>()
    val textSc = Scanner(FileInputStream("src/main/resources/textLines"))
    while (textSc.hasNextLine())
        textLines.add(textSc.nextLine())

    val roleLinesMap = mutableMapOf<String, MutableList<Pair<Int, String>>>()

    for (role in roles) {
        val roleName = role.substringBefore(":")
        roleLinesMap[roleName] = mutableListOf()
    }

    for ((index, text) in textLines.withIndex()) {
        val roleName = text.substringBefore(":")
        roleLinesMap[roleName]?.add(index + 1 to text.substringAfter(":").trim())
    }

    for ((role, lines) in roleLinesMap) {
        println("$role:")
        for ((index, line) in lines) {
            println("$index) $line")
        }
        println()
    }
}

