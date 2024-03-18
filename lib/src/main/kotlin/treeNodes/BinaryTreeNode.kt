package treeNodes

abstract class BinaryTreeNode<K : Comparable<K>, V, U>(
    var key: K,
    var value: V
) {
    var left: U? = null
    open var right: U? = null
}