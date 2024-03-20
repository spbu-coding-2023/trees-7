package trees

import BinaryTree
import treeNodes.BSTreeNode


class BSTree<K : Comparable<K>, V> : BinaryTree<K, V, BSTreeNode<K, V>>() {

    override fun insert(key: K, value: V) {
        root = insertBST(root, key, value)
    }

    private fun insertBST(node: BSTreeNode<K, V>?, key: K, value: V): BSTreeNode<K, V> {
        var tmpNode = node
        if (node == null) {
            tmpNode = BSTreeNode(key, value)
            return tmpNode
        }
        if (node.getKey() > key) {
            node.right = insertBST(node.right,key, value)
        } else {
            node.left = insertBST(node.left,key, value)
        }
        return node
    }

    //
    override fun remove(key: K) {
        if (root==null){
            return
        }else{
            root=remove(root,key)
        }
    }
    //TODO fix this shitt
    private fun remove(node: BSTreeNode<K, V>?, key: K): BSTreeNode<K, V>? {
        if (node == null) {
            return null
        }
        val nodeKey = node.getKey()
        when {
            key < nodeKey && node.left!=null -> node.left = remove(node.left, key)
            key > nodeKey && node.right!=null -> node.right = remove(node.right, key)
            else -> {
                if (node.left == null) {
                    return node.right
                } else if (node.right == null && node.left !=null) {
                    return node.left
                }
                if(node.right!=null){
                    val rightChild=node.right
                    if (rightChild!=null) {
                        node.setKey(getMinNodeFromNode(rightChild).getKey())
                        node.right = remove(node.right, node.getKey())
                    }
                }
            }
        }

        return node
    }

}