package lessons

fun main() {
    val strNumbers = listOf("One", "Two", "Three", "Four")

    val strNumbersSize = strNumbers
        .filter { it.length > 3 } // Generated a list
        .map { it.length } // Generated another list

    val strNumbersSize2 = strNumbers
        .asSequence()               // 1 Apply filter for Three
        .filter { it.length > 3 }   // 2 Apply map for Three
        .map { it.length }          // 3 Apply filter for Four
        .toList()                   // 4 Apply map for Four

    // operations stateless - state is not required -> map, filter
    // op stateful - require state -> shorted
    // op intermediate - generates another sequence -> map, filter
    // op terminal - starts sequence execution -> toList, lessons.sum
}