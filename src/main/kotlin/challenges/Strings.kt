package challenges

fun main() {
    var string: String = ""

    println("Reverse a string")
    string = "This string will be reversed"
    val reversed = reverse(string)
    println("-> $string")
    println("-> $reversed")
    println("Success? ${reversed == string.reversed()}")

    println("---------------------------------------------------------------------------------------------------------")

    println("Find first non repetitive char in a string")
    string = "nimd9unidfhun9adfy82un9c2yhu8d1un9pd12ni9u"
    val nonRepetitive = nonRepetitive(string)
    println("-> $string")
    println("-> $nonRepetitive")
    println("Success? ${nonRepetitive == "m"}")

    println("---------------------------------------------------------------------------------------------------------")
}

fun reverse(string: String): String {
    var reversed = ""

    for (i in (string.length - 1) downTo 0) {
        reversed += string[i]
    }

    return reversed
}

fun nonRepetitive(string: String): String {
    var nonRepetitive = ""

    outer@ for (i in string.indices) {
        for (j in string.indices) {
            if (string[i] == string[j] && i != j) {
                continue@outer
            }
        }

        nonRepetitive = "${string[i]}"
        break@outer
    }

    return nonRepetitive
}
