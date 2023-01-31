package challenges

fun main() {
    val list = SinglyLinkedList()

    println(list.toString())

    println("ADD")
    list.add("A")
    list.add("B")
    list.add("C")
    list.add("D")
    list.add("E")
    println(list.toString())

    println("CONTAINS")
    println("A: ${list.contains("A")}")
    println("X: ${list.contains("X")}")
    println("C: ${list.contains("C")}")

    println(list.toString())

    println("REMOVE: E")
    list.remove("E")
    println(list.toString())

    println("REVERSED")
    list.reverse()
    println(list.toString())

    println("---------------------------------------------------------------------------------------------------------")

    val recursiveList = SinglyRecursiveLinkedList()

    println(recursiveList.toString())

    println("ADD")
    recursiveList.add("A")
    recursiveList.add("B")
    recursiveList.add("C")
    recursiveList.add("D")
    recursiveList.add("E")
    println(recursiveList.toString())

    println("CONTAINS")
    println("A: ${recursiveList.contains("A")}")
    println("X: ${recursiveList.contains("X")}")
    println("C: ${recursiveList.contains("C")}")

    println(recursiveList.toString())

    println("REMOVE: E")
    recursiveList.remove("E")
    println(recursiveList.toString())

    println("REVERSED")
    recursiveList.reverse()
    println(recursiveList.toString())
}

data class SinglyNode(val value: String) {
    var next: SinglyNode? = null
}

class SinglyLinkedList {
    var head: SinglyNode? = null

    fun add(item: String) {
        if (head == null) {
            head = SinglyNode(item)
        } else {
            var current = head

            while (current?.next != null) {
                current = current.next
            }

            current?.next = SinglyNode(item)
        }
    }

    fun remove(item: String) {
        if (head?.value == item) {
            head = head?.next
        } else {
            var previous: SinglyNode? = null
            var current: SinglyNode? = head

            while (current != null) {
                if (current.value == item) {
                    previous?.next = current.next
                    break;
                }

                previous = current
                current = current.next
            }
        }
    }

    fun contains(item: String): Boolean {
        var current = head

        while (current != null) {
            if (current.value == item) {
                return true
            }

            current = current.next
        }

        return false
    }

    fun reverse() {
        var previous: SinglyNode? = null
        var current: SinglyNode? = head

        while (current != null) {
            val next = current.next
            current.next = previous

            previous = current
            current = next
        }

        head = previous
    }

    override fun toString(): String {
        var string = "{"
        var current = head

        while (current != null) {
            string += " ${current.value} \uD83E\uDC06"
            current = current.next
        }

        string += " null }"

        return string
    }
}

class SinglyRecursiveLinkedList {
    private var head: SinglyNode? = null

    fun add(item: String) {
        if (head == null) {
            head = SinglyNode(item)
        } else {
            addRecursive(head, item)
        }
    }

    private fun addRecursive(current: SinglyNode?, item: String) {
        if (current?.next == null) {
            current?.next = SinglyNode(item)
            return
        } else {
            addRecursive(current.next, item)
        }
    }

    fun remove(item: String) {
        if (head?.value == item) {
            head = head?.next
        } else {
            removeRecursive(head, null, item)
        }
    }

    private fun removeRecursive(current: SinglyNode?, previous: SinglyNode?, item: String) {
        if (current == null) {
            return
        }

        if (current.value == item) {
            previous?.next = current.next
            return
        }

        removeRecursive(current.next, current, item)
    }

    fun contains(item: String): Boolean {
        return containsRecursive(head, item)
    }

    private fun containsRecursive(current: SinglyNode?, item: String): Boolean {
        return if (current == null) {
            false
        } else if (current.value == item) {
            true
        } else {
            containsRecursive(current.next, item)
        }
    }

    fun reverse() {
        head = reverseRecursive(null, head)
    }

    private fun reverseRecursive(previous: SinglyNode?, current: SinglyNode?): SinglyNode? {
        if (current == null) {
            return previous
        }

        val next = current.next
        current.next = previous

        return reverseRecursive(current, next)
    }

    override fun toString(): String {
        var string = "{"

        string += toStringRecursive(head, "")

        string += " null }"

        return string
    }

    private fun toStringRecursive(current: SinglyNode?, string: String): String {
        return if (current == null) {
            string
        } else {
            toStringRecursive(current.next, string + " ${current.value} ➞")
        }
    }
}