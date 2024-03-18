package treeNodes

class AVLTreeNode<K : Comparable<K>, V>(
    key: K,
    data: V,
) : BinaryTreeNode<K, V, AVLTreeNode<K, V>>(key, data){
    var height: Int = 1
}
