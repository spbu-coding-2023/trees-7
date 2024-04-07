package trees

import treeNodes.BSTreeNode

class BSTree<K : Comparable<K>, V> : BinaryTree<K, V, BSTreeNode<K, V>>() {
    override fun insert(
        key: K,
        value: V,
    ) {
        root = insert(root, key, value)
    }

    private fun insert(
        node: BSTreeNode<K, V>?,
        key: K,
        value: V,
    ): BSTreeNode<K, V> {
        val tmpNode: BSTreeNode<K, V>?

        if (node == null) {
            tmpNode = BSTreeNode(key, value)
            return tmpNode
        }

        if (key > node.getKey()) {
            node.right = insert(node.right, key, value)
        } else if (key < node.getKey()) {
            node.left = insert(node.left, key, value)
        } else {
            node.setValue(value)
        }
        return node
    }

    override fun remove(key: K) {
        if (root == null) {
            return
        } else {
            root = remove(root, key)
        }
    }

    private fun remove(
        node: BSTreeNode<K, V>?,
        key: K,
    ): BSTreeNode<K, V>? {
        if (node == null) {
            return null
        }
        val nodeKey = node.getKey()
        when {
            key < nodeKey && node.left != null -> node.left = remove(node.left, key)
            key > nodeKey && node.right != null -> node.right = remove(node.right, key)
            else -> {
                if (node.left == null) {
                    return node.right
                } else if (node.right == null) {
                    return node.left
                }

                val rightChild = node.right
                if (rightChild != null) {
                    node.setValue(getMinNodeFromNode(rightChild).getValue())
                    node.setKey(getMinNodeFromNode(rightChild).getKey())
                    node.right = remove(node.right, node.getKey())
                }
            }
        }
        return node
    }
}
