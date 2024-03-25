package trees

import treeNodes.Color
import treeNodes.RBTreeNode

class RBTree<K : Comparable<K>, V> : BinaryTree<K, V, RBTreeNode<K, V>>() {
    override fun insert(
        key: K,
        value: V,
    ) {
        var node: RBTreeNode<K, V>? = root
        var parent: RBTreeNode<K, V>? = null

        while (node != null) {
            parent = node
            node =
                if (key < node.getKey()) {
                    node.left
                } else if (key > node.getKey()) {
                    node.right
                } else {
                    throw IllegalArgumentException("BST already contains a node with key $key")
                }
        }

        val newNode: RBTreeNode<K, V> = RBTreeNode(key, value)
        newNode.color = Color.RED
        if (parent == null) {
            root = newNode
        } else if (key < parent.getKey()) {
            parent.left = newNode
        } else {
            parent.right = newNode
        }
        newNode.parent = parent

        fixRedBlackPropertiesAfterInsert(newNode)
    }

    private fun fixRedBlackPropertiesAfterInsert(node: RBTreeNode<K, V>) {
        var parent: RBTreeNode<K, V> =
            node.parent ?: return // Uncomment the following line if you want to enforce black roots

        if (isBlack(parent)) {
            return
        }

        val grandparent: RBTreeNode<K, V>? = parent.parent

        if (grandparent == null) {
            parent.color = Color.BLACK
            return
        }

        val uncle: RBTreeNode<K, V>? = getUncle(parent)

        if (uncle != null && !isBlack(uncle)) {
            parent.color = Color.BLACK
            grandparent.color = Color.RED
            uncle.color = Color.BLACK

            fixRedBlackPropertiesAfterInsert(grandparent)
        } else if (parent === grandparent.left) {
            if (node === parent.right) {
                rotateLeft(parent)

                parent = node
            }

            rotateRight(grandparent)

            parent.color = Color.BLACK
            grandparent.color = Color.RED
        } else {
            if (node === parent.left) {
                rotateRight(parent)

                parent = node
            }

            rotateLeft(grandparent)

            parent.color = Color.BLACK
            grandparent.color = Color.RED
        }
    }

    private fun getUncle(parent: RBTreeNode<K, V>): RBTreeNode<K, V>? {
        val grandparent: RBTreeNode<K, V>? = parent.parent
        return if (grandparent?.left === parent) {
            grandparent.right
        } else if (grandparent?.right === parent) {
            grandparent.left
        } else {
            throw IllegalStateException("Parent is not a child of its grandparent")
        }
    }

    override fun remove(key: K) {
        var node: RBTreeNode<K, V>? = root

        while (node != null && node.getKey() !== key) {
            node =
                if (key < node.getKey()) {
                    node.left
                } else {
                    node.right
                }
        }

        if (node == null) {
            return
        }

        val movedUpNode: RBTreeNode<K, V>?
        val deletedNodeColor: Color

        if (node.left == null || node.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node)
            deletedNodeColor = node.color
        } else {
            val inOrderSuccessor: RBTreeNode<K, V> = findMinimum(node.right as RBTreeNode)

            node.setKey(inOrderSuccessor.getKey())

            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor)
            deletedNodeColor = inOrderSuccessor.color
        }

        if (deletedNodeColor == Color.BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode)
        }
    }

    private fun deleteNodeWithZeroOrOneChild(node: RBTreeNode<K, V>): RBTreeNode<K, V>? {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left)
            return node.left // moved-up node
        } else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right)
            return node.right // moved-up node
        } else {
            replaceParentsChild(node.parent, node, null)
            return null
        }
    }

    private fun findMinimum(node: RBTreeNode<K, V>): RBTreeNode<K, V> {
        var tmpnode: RBTreeNode<K, V> = node
        while (tmpnode.left != null) {
            tmpnode = tmpnode.left!!
        }
        return tmpnode
    }

    private fun fixRedBlackPropertiesAfterDelete(node: RBTreeNode<K, V>?) {
        if (node === root) {
            return
        }
        node?.let {
            var sibling: RBTreeNode<K, V>? = getSibling(node)

            if (!isBlack(sibling)) {
                handleRedSibling(node, sibling)
                sibling = getSibling(node)
            }
            sibling?.let {
                if (isBlack(sibling.left) && isBlack(sibling.right)) {
                    sibling.color = Color.RED

                    if (!isBlack(node.parent)) {
                        node.parent?.color = Color.BLACK
                    } else {
                        fixRedBlackPropertiesAfterDelete(node.parent)
                    }
                } else {
                    handleBlackSiblingWithAtLeastOneRedChild(node, sibling)
                }
            }
        }
    }

    private fun handleRedSibling(
        node: RBTreeNode<K, V>?,
        sibling: RBTreeNode<K, V>?,
    ) {
        node?.let {
            sibling?.color = Color.BLACK
            node.parent?.color = Color.RED

            if (node === node.parent?.left) {
                rotateLeft(node.parent)
            } else {
                rotateRight(node.parent)
            }
        }
    }

    private fun handleBlackSiblingWithAtLeastOneRedChild(
        node: RBTreeNode<K, V>?,
        sibling: RBTreeNode<K, V>?,
    ) {
        var tmpsibling: RBTreeNode<K, V> = sibling ?: return
        node?.let {
            val parent: RBTreeNode<K, V>? = node.parent
            parent?.let {
                val nodeIsLeftChild = node == parent.left
                if (nodeIsLeftChild && isBlack(tmpsibling.right)) {
                    tmpsibling.left?.color = Color.BLACK
                    tmpsibling.color = Color.RED
                    rotateRight(tmpsibling)
                    tmpsibling = node.parent?.right ?: return
                } else if (!nodeIsLeftChild && isBlack(tmpsibling.left)) {
                    tmpsibling.right?.color = Color.BLACK
                    tmpsibling.color = Color.RED
                    rotateLeft(tmpsibling)
                    tmpsibling = node.parent?.left ?: return
                }

                val parentColor = node.parent?.color ?: return
                tmpsibling.color = parentColor
                node.parent?.color = Color.BLACK
                if (nodeIsLeftChild) {
                    tmpsibling.right?.color = Color.BLACK
                    rotateLeft(node.parent)
                } else {
                    tmpsibling.left?.color = Color.BLACK
                    rotateRight(node.parent)
                }
            }
        }
    }

    private fun getSibling(node: RBTreeNode<K, V>?): RBTreeNode<K, V>? {
        val parent: RBTreeNode<K, V>? = node?.parent
        return parent?.let {
            if (node === parent.left) {
                parent.right
            } else if (node === parent.right) {
                parent.left
            } else {
                throw IllegalStateException("Parent is not a child of its grandparent")
            }
        }
    }

    private fun isBlack(node: RBTreeNode<K, V>?): Boolean {
        return node == null || node.color == Color.BLACK
    }

    private fun rotateRight(node: RBTreeNode<K, V>?) {
        node?.let {
            val parent: RBTreeNode<K, V>? = node.parent
            val leftChild: RBTreeNode<K, V>? = node.left
            leftChild?.let {
                node.left = leftChild.right
                leftChild.right?.apply { it.parent = node }

                leftChild.right = node
            }
            node.parent = leftChild

            replaceParentsChild(parent, node, leftChild)
        }
    }

    private fun rotateLeft(node: RBTreeNode<K, V>?) {
        node?.let {
            val parent: RBTreeNode<K, V>? = node.parent
            val rightChild: RBTreeNode<K, V>? = node.right
            rightChild?.let {
                node.right = rightChild.left
                rightChild.left?.apply { it.parent = node }

                rightChild.left = node
            }
            node.parent = rightChild

            replaceParentsChild(parent, node, rightChild)
        }
    }

    private fun replaceParentsChild(
        parent: RBTreeNode<K, V>?,
        oldChild: RBTreeNode<K, V>?,
        newChild: RBTreeNode<K, V>?,
    ) {
        if (parent == null) {
            root = newChild
        } else if (parent.left === oldChild) {
            parent.left = newChild
        } else if (parent.right === oldChild) {
            parent.right = newChild
        } else {
            throw IllegalStateException("RBTreeNode<K, V> is not a child of its parent")
        }

        if (newChild != null) {
            newChild.parent = parent
        }
    }
}
