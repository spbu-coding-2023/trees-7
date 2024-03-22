package trees

import treeNodes.AVLTreeNode


class AVLTree<K : Comparable<K>, V> : BinaryTree<K, V, AVLTreeNode<K, V>>() {
    override fun insert(key: K, value: V) {
        root = insert(root, key, value)
    }

    override fun remove(key: K) {
        root = remove(root, key)
    }

    private fun remove(node: AVLTreeNode<K, V>?, key: K): AVLTreeNode<K, V>? {

        if (node == null) {
            return null
        }

        val nodeKey = node.getKey()
        if (key < nodeKey) {
            node.left = remove(node.left, key)
        } else if (key > nodeKey) {
            node.right = remove(node.right, key)
        } else {

            when {
                node.left == null -> return node.right
                node.right == null -> return node.left
                else -> {

                    var right = node.right ?: node
                    right = getMinNodeFromNode(right)



                    val tmp = right
                    node.setKey(tmp.getKey())
                    node.setValue(tmp.getValue())
                    node.right = remove(node.right, tmp.getKey())



                }
            }
        }

        updateHeight(node)
        return removingRotate(node)
    }

    private fun insert(startNode: AVLTreeNode<K, V>?, key: K, data: V): AVLTreeNode<K, V> {

        var node = startNode
        if (node == null) {
            node = AVLTreeNode(key, data)
            return node
        }

        val nodeKey = node.getKey()
        if (nodeKey > key) {
            node.left = insert(node.left, key, data)
        } else if (nodeKey < key) {
            node.right = insert(node.right, key, data)
        } else {
            node.setValue(data)
        }

        updateHeight(node)
        return rotate(node)
    }

    private fun rightRotate(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {

        val parent = node.left ?: return node

        node.left = parent.right
        parent.right = node

        updateHeight(node)
        updateHeight(parent)

        return parent
    }


    private fun leftRotate(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {

        val parent = node.right ?: return node

        node.right = parent.left
        parent.left = node

        updateHeight(node)
        updateHeight(parent)

        return parent
    }

    private fun rotate(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {

        if (getHeight(node.left) - getHeight(node.right) > 1) {

            val leftChild = node.left
            if (leftChild != null) {
                if (getHeight(leftChild.left) - getHeight(leftChild.right) > 0) {
                    return rightRotate(node)
                }

                if (getHeight(leftChild.left) - getHeight(leftChild.right) < 0) {
                    node.left = leftRotate(leftChild)
                    return rightRotate(node)
                }
            }
        }

        if (getHeight(node.left) - getHeight(node.right) < -1) {

            val rightChild = node.right
            if (rightChild != null) {
                if (getHeight(rightChild.left) - getHeight(rightChild.right) < 0) {
                    return leftRotate(node)
                }

                if (getHeight(rightChild.left) - getHeight(rightChild.right) > 0) {
                    node.right = rightRotate(rightChild)
                    return leftRotate(node)
                }
            }
        }

        return node
    }

    private fun removingRotate(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {

        if (getHeight(node.left) - getHeight(node.right) == 2) {

            val leftChild = node.left
            if (leftChild != null) {
                if (getHeight(leftChild.left) - getHeight(leftChild.right) >= 0) {
                    return rightRotate(node)
                }

                if (getHeight(leftChild.left) - getHeight(leftChild.right) == -1) {
                    node.left = leftRotate(leftChild)
                    return rightRotate(node)
                }
            }
        }

        if (getHeight(node.left) - getHeight(node.right) == -2) {

            val rightChild = node.right
            if (rightChild != null) {
                if (getHeight(rightChild.left) - getHeight(rightChild.right) <= 0) {
                    return leftRotate(node)
                }

                if (getHeight(rightChild.left) - getHeight(rightChild.right) == 1) {
                    node.right = rightRotate(rightChild)
                    return leftRotate(node)
                }
            }
        }

        return node
    }

    private fun updateHeight(node: AVLTreeNode<K, V>?) {
        when {
            node == null -> return
            else -> node.setHeight(maxOf(getHeight(node.left), getHeight(node.right)) + 1)
        }
    }

    private fun getHeight(node: AVLTreeNode<K, V>?): Int {

        return node?.getHeight() ?: 0
    }

}

fun main() {
    val avlTree: AVLTree<Int, String> = AVLTree()
    avlTree.insert(1, "Golang")
    avlTree.insert(2, "Kotlin")
    avlTree.insert(3, "Python")
    avlTree.insert(4, "Fortran")
    avlTree.insert(5, ".Net")
    avlTree.insert(2, "NEW NEW NEW")
    print(avlTree.search(2))

}