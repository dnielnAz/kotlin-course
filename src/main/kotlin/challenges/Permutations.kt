package challenges

fun main() {
    println(permutations(mutableListOf('1', '2', '3')))
}

fun permutations(list: MutableList<Char>): List<List<Char>> {
    if (list.isEmpty()) {
        return listOf(listOf()) // If the list is empty the only possible permutation is an empty list
    }

    val first = list.removeAt(0) // Take the first item
    println("first: $first")

    val permutationsWithoutFirst = permutations(list)   // Get all permutations that do not have the first element
    println("permutationsWithout $first : $permutationsWithoutFirst")

    val totalPermutations = mutableListOf<List<Char>>()

    for (permutation in permutationsWithoutFirst) {
        println("permutation: $permutation")
        for (i in 0..permutation.size) {    // Loop through all permutations and insert the first element + 1 additional loop to include the first element at the end.
            totalPermutations.add(permutation.subList(0, i).plus(first).plus(permutation.subList(i, permutation.size)))

            // When i == 0 -> permutation.subList(0, i) == []
            // Contact the first element
            // When i == 0 -> permutation.subList(i, permutation.size) == [restOfElements]
            // Simplify -> [] + first + [restOfElements]
        }
        println("totalPermutations: $totalPermutations")
    }

    return totalPermutations
}
