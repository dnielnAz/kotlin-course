package challenges

fun main() {
    val list = KLinkedList()

    println(list.toString())

    println("ADD")
    list.add("A")
    list.add("B")
    list.add("C")
    list.add("D")
    list.add("E")
    println(list.toString())

    println("CONTAINS")
    println("kLinkList.contains(\"A\"): ${list.contains("A")}")
    println("kLinkList.contains(\"X\"): ${list.contains("X")}")
    println("kLinkList.contains(\"C\"): ${list.contains("C")}")

    println(list.toString())

    println("REMOVE")
    list.remove("E")
    println(list.toString())

    println("REVERSED")
    list.reverse()
    println(list.toString())

    println("---------------------------------------------------------------------------------------------------------")

    val listRecursive = KLinkedList()

    println(listRecursive.toStringRecursive())

    println("ADD RECURSIVE")
    listRecursive.addRecursive("V")
    listRecursive.addRecursive("W")
    listRecursive.addRecursive("X")
    listRecursive.addRecursive("Y")
    listRecursive.addRecursive("Z")
    println(listRecursive.toStringRecursive())

    println("CONTAINS RECURSIVE")
    println("kLinkList.containsRecursive(\"V\"): ${listRecursive.containsRecursive("V")}")
    println("kLinkList.containsRecursive(\"E\"): ${listRecursive.containsRecursive("E")}")
    println("kLinkList.containsRecursive(\"X\"): ${listRecursive.containsRecursive("X")}")

    println(listRecursive.toStringRecursive())

    println("REMOVE RECURSIVE")
    listRecursive.removeRecursive("Z")
    println(listRecursive.toStringRecursive())

    println("REVERSED RECURSIVE")
    listRecursive.reverseRecursive()
    println(listRecursive.toStringRecursive())
}

data class Node(val value: String) {
    var next: Node? = null
}

class KLinkedList {
    var head: Node? = null


    fun add(value: String) {
        if (head == null) {
            head = Node(value)
        } else {
            var current = head

            while (current?.next != null) {
                current = current.next
            }

            current?.next = Node(value)
        }
    }

    fun addRecursive(value: String) {
        if (head == null) {
            head = Node(value)
        } else {
            addRecursive(head, value)
        }
    }

    private fun addRecursive(current: Node?, value: String) {
        if (current?.next == null) {
            current?.next = Node(value)
            return
        } else {
            addRecursive(current.next, value)
        }
    }

    fun remove(value: String) {
        if (head?.value == value) {
            head = head?.next
        } else {
            var previous: Node? = null
            var current: Node? = head

            while (current != null) {
                if (current.value == value) {
                    previous?.next = current.next
                    break;
                }

                previous = current
                current = current.next
            }
        }
    }

    fun removeRecursive(value: String) {
        if (head?.value == value) {
            head = head?.next
        } else {
            removeRecursive(head, null, value)
        }
    }

    private fun removeRecursive(current: Node?, previous: Node?, value: String) {
        if (current == null) {
            return
        }

        if (current.value == value) {
            previous?.next = current.next
            return
        }

        removeRecursive(current.next, current, value)
    }

    fun contains(value: String): Boolean {
        var current = head

        while (current != null) {
            if (current.value == value) {
                return true
            }

            current = current.next
        }

        return false
    }

    fun containsRecursive(value: String): Boolean {
        return containsRecursive(head, value)
    }

    private fun containsRecursive(current: Node?, value: String): Boolean {
        return if (current == null) {
            false
        } else if (current.value == value) {
            true
        } else {
            containsRecursive(current.next, value)
        }
    }

    fun reverse() {
        var previous: Node? = null
        var current: Node? = head

        while (current != null) {
            val next = current.next
            current.next = previous

            previous = current
            current = next
        }

        head = previous
    }

    fun reverseRecursive() {
        head = reverseRecursive(null, head)
    }

    private fun reverseRecursive(previous: Node?, current: Node?): Node? {
        if (current == null) {
            return previous
        }

        val next = current.next
        current.next = previous

        return reverseRecursive(current, next)
    }

    override fun toString(): String {
        var string = "{"
        var current = head

        while (current != null) {
            string += " ${current.value} ->"
            current = current.next
        }

        string += " null }"

        return string
    }

    fun toStringRecursive(): String {
        var string = "{"

        string += toStringRecursive(head, "")

        string += " null }"

        return string
    }

    private fun toStringRecursive(current: Node?, string: String): String {
        return if (current == null) {
            string
        } else {
            toStringRecursive(current.next, string + " ${current.value} ->")
        }
    }
}