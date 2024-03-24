package treeNodes

public enum class Color {
    RED,
    BLACK,
}

class RBTreeNode<K : Comparable<K>, V>(
    key: K,
    data: V,
    var color: Color = Color.BLACK,
    var parent: RBTreeNode<K, V>? = null,
) : BinaryTreeNode<K, V, RBTreeNode<K, V>>(key, data)
