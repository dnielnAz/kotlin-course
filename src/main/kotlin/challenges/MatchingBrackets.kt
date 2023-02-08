package challenges

import java.util.Stack

fun main() {
    val test1 = "()()(){}{}[][]"

    println("test1 $test1")
    println("isValid? ${isValid(test1)}")
    println("---------------------------------------------------------------------------------------------------------")

    val test2 = "()()()}{}[][]"

    println("test2 $test2")
    println("isValid? ${isValid(test2)}")
    println("---------------------------------------------------------------------------------------------------------")

    val test3 = "()()()()()()()()()()()()()()()(){{{{{{{{{{}}}}}}}}}}[[[[[[[(({}))]]]]]]]"

    println("test3 $test3")
    println("isValid? ${isValid(test3)}")
    println("---------------------------------------------------------------------------------------------------------")
}

fun isValid(string: String): Boolean {
    val stack = Stack<String>()

    for (char in string) {
        if (char == '(' || char == '{' || char == '[') {
            stack.push("$char")
        } else {
            if (stack.empty()) {
                return false
            } else {
                stack.pop()
            }
        }
    }

    return stack.empty()
}