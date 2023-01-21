package challenges

fun main() {
    println(combinations(mutableListOf('A', 'B', 'C')))
}

fun combinations(list: MutableList<Char>): List<List<Char>> {
    if (list.isEmpty()) {
        return listOf(listOf()) // If the list is empty the only possible combination is an empty list
    }

    val first = list.removeAt(0)    // Take the first item
    val combinationsWithoutFirst = combinations(list)   // Get all combinations that do not have the first element

    val totalCombinations = mutableListOf<List<Char>>()

    for (combination in combinationsWithoutFirst) { // For every combination
        totalCombinations.add(combination)  // Add the current combination
        totalCombinations.add(combination.plus(first))  // Add the current combination with the first element
    }

    return totalCombinations
}