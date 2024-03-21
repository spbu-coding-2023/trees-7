package trees

import treeNodes.BSTreeNode


class BSTree<K : Comparable<K>, V> : BinaryTree<K, V, BSTreeNode<K, V>>() {

    override fun insert(key: K, value: V) {
        root = insertBST(root, key, value)
    }


    private fun insert(node: BSTreeNode<K, V>?, key: K, value: V): BSTreeNode<K, V> {
        val tmpNode: BSTreeNode<K, V>?

        if (node == null) {
            tmpNode = BSTreeNode(key, value)
            return tmpNode
        }

        if (node.getKey() > key) {
            node.right = insert(node.right,key, value)
        } else if (node.getKey() < key) {
            node.left = insert(node.left,key, value)
        } else {
            node.setValue(value)

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
        when {
            key < node.key && node.left!=null -> node.left = remove(node.left, key)
            key > node.key && node.right!=null -> node.right = remove(node.right, key)
            else -> {
                if (node.left == null) {
                    return node.right
                } else if (node.right == null && node.left !=null) {
                    return node.left
                }
                if(node.right!=null){
                    val rightChild=node.right
                    if (rightChild!=null) {
                        node.key = getMinNodeFromNode(rightChild).key
                        node.right = remove(node.right, node.key)
                    }
                }
            }
        }

        return node
    }

}