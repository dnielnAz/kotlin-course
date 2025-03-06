package challenges

fun main() {
    println("Recursive sum")
    println(sum(mutableListOf(1, 3, 6, 9, 19)))
    println(sum2(mutableListOf(1, 3, 6, 9, 19), 0))
    println("Fib")
    println(fib(7))
}

fun sum(list: MutableList<Int>): Int {
    if (list.isEmpty()) {
        return 0
    }

    val first = list.removeAt(0)

    return first + sum(list)
}

fun sum2(list: MutableList<Int>, index: Int): Int {
    if (list.size == index) {
        return 0
    }

    return list[index] + sum2(list, index + 1)
}

fun fib(int: Int): Int {
    if (int == 1 || int == 2) {
        return 1
    }

    return fib(int - 1) + fib(int - 2)
}