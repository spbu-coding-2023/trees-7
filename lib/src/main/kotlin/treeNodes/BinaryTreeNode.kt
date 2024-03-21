package treeNodes

abstract class BinaryTreeNode<K : Comparable<K>, V, U>(
    var key: K,
    var value: V
) {

    internal var left: U? = null
    internal var right: U? = null

    fun getKey(): K {
        return key
    }

    fun getValue(): V {
        return value
    }

    fun setKey(key: K) {
        this.key = key
    }

    fun setValue(value: V) {
        this.value = value
    }

}