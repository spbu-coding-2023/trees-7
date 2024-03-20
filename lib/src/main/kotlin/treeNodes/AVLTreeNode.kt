package treeNodes

class AVLTreeNode<K : Comparable<K>, V>(
        key: K,
        data: V,
) : BinaryTreeNode<K, V, AVLTreeNode<K, V>>(key, data){
    private var height: Int = 1

    fun getHeight(): Int {
        return height
    }

    fun setHeight(newHeight: Int) {
        height = newHeight
    }
}
