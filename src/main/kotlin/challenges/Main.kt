import java.util.*

fun main() {
//    println("${Solution().isValid("([)]")}")
//    println("${Solution().isValid("{}[]")}")
//    println("${Solution().isValid("{[]}")}")
//    println("${Solution().isValid("(([]){})")}")
//    println("${Solution().isValid("((")}")
//    println("${Solution().isValid("()()")}")
//    println("${Solution().isValid("{}{{}}")}")
//    println("${Solution().isValid("[](([[]]){}{[]}([]))")}")

//    println("${Solution().isValid("[](([[]]){}{[]}([]))")}")


//    val list1 = create(ListNode(1), null, 5)
//    val list2 = create(ListNode(1), null, 10)
//
////    println(list1.stringify())
////    println(list2.stringify())
//
//    val merged = merge(list1, list2)
//
//    println("${merged?.stringify()}")

    val nums = intArrayOf(2, 3, 3, 3)
    val removable = 3

    val result = removeElement(nums, removable)

    println("RESULT -> $result ELEMENTS -> ${nums.toList()}")
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    var lastNumber = -1
    var index = 0
    var lastIndex = nums.size - 1
    var newSize = 0

    while (index < nums.size) {

        if (nums[index] != `val`) {
            newSize += 1
        }

        if (nums[index] == `val` && index < lastIndex) {
            lastNumber = nums[lastIndex]
            nums[lastIndex] = nums[index]
            nums[index] = lastNumber

            lastIndex -= 1
            index -= 1
        }

        index += 1
    }


    return newSize
}

fun merge(list1: ListNode?, list2: ListNode?): ListNode? {
    return if (list1 == null) {
        list2
    } else if (list2 == null) {
        list1
    } else {
        val merged = ListNode(0)

        val reminder = compare(0, list1, 0, list2, merged)

//        println("=========COMPARED==============")
//        println(merged.stringify())

        if (reminder.first == 1) {
            finish(0, reminder.second, list1, merged)
        } else {
            finish(0, reminder.second, list2, merged)
        }

//        println("==========FINISHED=============")
//        println(merged.stringify())

        return merged
    }
}

fun compare(index1: Int, list1: ListNode?, index2: Int, list2: ListNode?, merged: ListNode): Pair<Int, Int> {
    return if (list1 == null) {
        2 to index2
    } else if (list2 == null) {
        1 to index1
    } else {

        return if (index1 == 0 && index2 == 0) {
            if (list1.data < list2.data) {
                merged.data = list1.data

                compare(1, list1.next, index2, list2, merged)
            } else {
                merged.data = list2.data

                compare(index1, list1, 1, list2.next, merged)
            }
        } else {
            if (list1.data < list2.data) {
                val new = ListNode(list1.data)
                merged.next = new

                compare(index1 + 1, list1.next, index2, list2, new)
            } else {
                val new = ListNode(list2.data)
                merged.next = new

                compare(index1, list1, index2 + 1, list2.next, new)
            }
        }
    }
}

fun finish(index: Int, limit: Int, list: ListNode?, merged: ListNode) {
    if (list == null) {
        return
    } else {
        if (index < limit) {
            finish(index + 1, limit, list.next, merged.next!!)
        } else if (merged.next != null) {
            finish(index + 1, limit, list, merged.next!!)
        } else {
            val new = ListNode(list.data)
            merged.next = new

            finish(index + 1, limit, list.next, new)
        }
    }
}

fun create(parent: ListNode, current: ListNode?, limit: Int): ListNode {
    if (parent.data == limit) {
        return parent
    }

    if (current?.data == limit) {
        return parent
    }

    return if (current == null) {
        parent.next = ListNode(parent.data.plus(1))

        create(parent, parent.next, limit)
    } else {
        current.next = ListNode(current.data.plus(1))

        create(parent, current.next, limit)
    }
}

class ListNode(var data: Int) {
    var next: ListNode? = null

    fun stringify(): String {
        return "DATA: $data - NEXT ${next?.stringify()}"
    }
}

class Solution {
//    fun solve(index: Int, input: Stack<Char>): Boolean {
//        if (input.empty()) {
//            return true
//        }
//
//        println("------------------")
//        println(input)
//
//        return when (input[index]) {
//            '(' -> {
//                if (hasValidNext(input, index) && input[index + 1] == ')') {
//                    if (index > 0) {
//                        val part1 = input.substring(0, index)
//                        val part2 = input.substring(index + 2)
//
//                        solve(index - 1, part1 + part2)
//                    } else {
//                        solve(index, input.substring(index + 2))
//                    }
//                } else if (hasValidNext(input, index) && nextIsOpenParenthesis(input[index + 1])) {
//                    solve(index + 1, input)
//                } else {
//                    false
//                }
//            }
//            '{' -> {
//                if (hasValidNext(input, index) && input[index + 1] == '}') {
//                    if (index > 0) {
//                        val part1 = input.substring(0, index)
//                        val part2 = input.substring(index + 2)
//
//                        solve(index - 1, part1 + part2)
//                    } else {
//                        solve(index, input.substring(index + 2))
//                    }
//                } else if (hasValidNext(input, index) && nextIsOpenParenthesis(input[index + 1])) {
//                    solve(index + 1, input)
//                } else {
//                    false
//                }
//            }
//            '[' -> {
//                if (hasValidNext(input, index) && input[index + 1] == ']') {
//                    if (index > 0) {
//                        val part1 = input.substring(0, index)
//                        val part2 = input.substring(index + 2)
//
//                        solve(index - 1, part1 + part2)
//                    } else {
//                        solve(index, input.substring(index + 2))
//                    }
//                } else if (hasValidNext(input, index) && nextIsOpenParenthesis(input[index + 1])) {
//                    solve(index + 1, input)
//                } else {
//                    false
//                }
//            }
//            else -> false
//        }
//    }
//
//    fun hasValidNext(input: String, current: Int): Boolean {
//        return (current + 1) < input.length
//    }
//
//    fun nextIsOpenParenthesis(next: Char): Boolean {
//        return next == '(' || next == '{' || next == '['
//    }

    fun isValid(s: String): Boolean {
        return if (s.length % 2 == 0) {
//            solve(0, s)

            val x = Stack<Char>()

            x.addAll(s.toCharArray().toList())
            println(x.toString())
            println(x.pop())

            true
        } else {
            false
        }
    }
}