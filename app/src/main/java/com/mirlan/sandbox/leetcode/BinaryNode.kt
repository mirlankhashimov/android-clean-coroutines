package com.mirlan.sandbox.leetcode

data class Node<T>(
    val value: T,
    var leftNode: Node<T>?,
    var rightNode: Node<T>?,
    var depth: Int = 0
) {
    fun link(left: Node<T>?, right: Node<T>?) = this.apply { linkLeft(left).linkRight(right) }

    fun linkLeft(left: Node<T>?) = this.apply { leftNode = left }

    fun linkRight(right: Node<T>?) = this.apply { rightNode = right }

    fun depth(value: Int) = this.apply { depth = value }

    override fun toString(): String {
        return StringBuffer().apply {
            append("{${value.toString()}")
            if (leftNode != null) {
                append(", ${leftNode.toString()}")
            }
            if (rightNode != null) {
                append(", ${rightNode.toString()}}")
            }
        }.toString()
    }

}

fun buildTree(): Node<Char> {
    val a = Node('a', null, null)
    val b = Node('b', null, null)
    val c = Node('c', null, null)
    val d = Node('d', null, null)
    val e = Node('e', null, null)
    val f = Node('f', null, null)
    val g = Node('g', null, null)
    val h = Node('h', null, null)
    val i = Node('i', null, null)

    a.link(b, c)
    b.link(d, e)
    c.link(f, g)
    g.link(h, i)

    return a
}

fun jk(k: Node<Char>) {
}

fun main() {
    val k = buildTree()
    jk(k)
    print(k.toString())
}
