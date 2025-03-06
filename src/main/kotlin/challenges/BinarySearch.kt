package challenges

fun binarySearch(values: Array<Int>, value: Int): Boolean {
    var low = 0
    var high = values.size

    var found = false

    while (low < high) {
        val mid = low + (high - low) / 2

        val current = values[mid]

        if (current == value) {
            found = true
            break
        } else if (current > value) {
            high = mid
        } else {
            low = mid + 1
        }
    }

    return found
}
