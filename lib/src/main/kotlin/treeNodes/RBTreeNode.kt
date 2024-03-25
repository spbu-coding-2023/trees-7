package treeNodes

enum class Color {
    RED,
    BLACK,
}

class RBTreeNode<K : Comparable<K>, V>(
    key: K,
    data: V,
    private var color: Color = Color.BLACK,
    private var parent: RBTreeNode<K, V>? = null,
) : BinaryTreeNode<K, V, RBTreeNode<K, V>>(key, data) {
    fun getColor(): Color {
        return color
    }

    fun setColor(newColor: Color) {
        color = newColor
    }
    fun getParent(): RBTreeNode<K, V>? {
        return parent
    }

    fun setParent(newParent: RBTreeNode<K, V>?) {
        parent = newParent
    }
}
