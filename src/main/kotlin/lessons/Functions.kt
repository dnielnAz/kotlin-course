package lessons

/**
 * Behaves like if / if else
 *
 * A function used as a default value is evaluated at runtime,
 * so do not put an expensive operation like a file read or a large memory allocation in the function.
 * The operation is executed every time your function is called, which may slow down your program.
 *
 * @param day Required
 * @param temperature Optional
 * @param dirty Default function
 */
fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = getDirtySensorReading()): Boolean {
    return when {
        temperature > 30 -> true
        dirty > 30 -> true
        day == "Sunday" ->  true
        else -> false
    }
}

fun getDirtySensorReading(): Int = 20

fun filters() {
    val decorations = listOf ("rock", "pagoda", "plastic plant", "alligator", "flowerpot")

    // eager, creates a new list
    val eager = decorations.filter { it [0] == 'p' }
    println("eager: $eager")

    // lazy, will wait until asked to evaluate
    // only access to the elements that satisfy the filter when those elements are requested
    val filtered = decorations.asSequence().filter { it[0] == 'p' }
    println("filtered: $filtered")
}

fun highOrder() {
    val waterFilter: (Int) -> Int = { dirty -> dirty / 2 }
    println(updateDirty(30, waterFilter))
    println(updateDirty(15, ::increaseDirty)) // Passing a named function
}

fun increaseDirty( start: Int ) = start + 1

fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
    return operation(dirty)
}
