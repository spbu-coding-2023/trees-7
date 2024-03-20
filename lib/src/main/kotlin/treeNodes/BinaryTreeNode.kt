package treeNodes

abstract class BinaryTreeNode<K : Comparable<K>, V, U>(
        private var key: K,
        private var value: V
) {
    var left: U? = null
    var right: U? = null

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