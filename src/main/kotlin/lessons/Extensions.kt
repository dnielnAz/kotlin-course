package lessons/*
    The value for const val is determined at compile time
    The value for val is determined during program execution
    val can be assigned by a function.
*/

fun destructuring() {
    val equipment = "fish net" to "catching fish"
    val (tool, use) = equipment
    println("$tool is used for $use")

    val numbers = Triple(6, 9, 42)
    val (n1, n2, n3) = numbers
    println("$n1 $n2 $n3")
}

fun sum() {
    val list2 = listOf("a", "bbb", "cc")
    println(list2.sumBy { it.length })
}