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
            node = if (key < node.getKey()) {
                node.left
            } else if (key > node.getKey()) {
                node.right
            } else {
                node.setValue(value)
                return
            }
        }

        val newNode: RBTreeNode<K, V> = RBTreeNode(key, value)
        newNode.setColor(Color.RED)
        if (parent == null) {
            root = newNode
        } else if (key < parent.getKey()) {
            parent.left = newNode
        } else {
            parent.right = newNode
        }
        newNode.setParent(parent)

        fixRedBlackPropertiesAfterInsert(newNode)
    }

    private fun fixRedBlackPropertiesAfterInsert(node: RBTreeNode<K, V>) {
        if (node.getParent() == null) {
            node.setColor(Color.BLACK)
            return
        } else {
            var parent: RBTreeNode<K, V> = node.getParent() ?: return

            if (isBlack(parent)) {
                return
            }

            val grandparent: RBTreeNode<K, V> = parent.getParent() ?: return


            val uncle: RBTreeNode<K, V>? = getUncle(parent)

            if (uncle != null && !isBlack(uncle)) {
                parent.setColor(Color.BLACK)
                grandparent.setColor(Color.RED)
                uncle.setColor(Color.BLACK)

                fixRedBlackPropertiesAfterInsert(grandparent)
            } else if (parent === grandparent.left) {
                if (node === parent.right) {
                    rotateLeft(parent)

                    parent = node
                }

                rotateRight(grandparent)

                parent.setColor(Color.BLACK)
                grandparent.setColor(Color.RED)
            } else {
                if (node === parent.left) {
                    rotateRight(parent)

                    parent = node
                }

                rotateLeft(grandparent)

                parent.setColor(Color.BLACK)
                grandparent.setColor(Color.RED)
            }
        }
    }

    private fun getUncle(parent: RBTreeNode<K, V>): RBTreeNode<K, V>? {
        val grandparent: RBTreeNode<K, V>? = parent.getParent()
        return grandparent?.let {
            if (grandparent.left === parent) {
                grandparent.right
            } else if (grandparent.right === parent) {
                grandparent.left
            } else {
                throw IllegalStateException("Parent is not a child of its grandparent")
            }
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
        var flag = false

        if (node.left == null || node.right == null) {
            deletedNodeColor = node.getColor()
            movedUpNode = deleteNodeWithZeroOrOneChild(node)
        } else {
            val inOrderSuccessor: RBTreeNode<K, V> = findMinimum(node.right as RBTreeNode)

            node.setKey(inOrderSuccessor.getKey())
            node.setValue(inOrderSuccessor.getValue())

            deletedNodeColor = inOrderSuccessor.getColor()
            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor)
            if (movedUpNode == inOrderSuccessor)
                flag = true
        }

        if (deletedNodeColor == Color.BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode)
        }
        if (node.left == null && node.right == null) {
            replaceParentsChild(node.getParent(), node, null)
        } else if (flag) {
            replaceParentsChild(movedUpNode?.getParent(), movedUpNode, null)
        }
    }

    private fun deleteNodeWithZeroOrOneChild(node: RBTreeNode<K, V>): RBTreeNode<K, V>? {
        if (node.left != null) {
            replaceParentsChild(node.getParent(), node, node.left)
            return node.left // moved-up node
        } else if (node.right != null) {
            replaceParentsChild(node.getParent(), node, node.right)
            return node.right // moved-up node
        } else {
            node.setColor(Color.BLACK)
            return node
        }
    }

    private fun findMinimum(node: RBTreeNode<K, V>): RBTreeNode<K, V> {
        var tmpnode: RBTreeNode<K, V> = node
        while (tmpnode.left != null) {
            tmpnode = tmpnode.left ?: tmpnode
        }
        return tmpnode
    }

    private fun fixRedBlackPropertiesAfterDelete(node: RBTreeNode<K, V>?) {
        node?.let {
        if (node === root) {
            node.setColor(Color.BLACK)
            return
        }
            var sibling: RBTreeNode<K, V>? = getSibling(node)

            if (!isBlack(sibling)) {
                handleRedSibling(node, sibling)
                sibling = getSibling(node)
            }
            if (isBlack(sibling?.left) && isBlack(sibling?.right)) {
                sibling?.setColor(Color.RED)

                if (!isBlack(node.getParent())) {
                    node.getParent()?.setColor(Color.BLACK)
                } else {
                    fixRedBlackPropertiesAfterDelete(node.getParent())
                }
            } else {
                handleBlackSiblingWithAtLeastOneRedChild(node, sibling)
            }
        }
    }

    private fun handleRedSibling(
        node: RBTreeNode<K, V>?,
        sibling: RBTreeNode<K, V>?,
    ) {
        node?.let {
            sibling?.setColor(Color.BLACK)
            node.getParent()?.setColor(Color.RED)

            if (node === node.getParent()?.left) {
                rotateLeft(node.getParent())
            } else {
                rotateRight(node.getParent())
            }
        }
    }

    private fun handleBlackSiblingWithAtLeastOneRedChild(
        node: RBTreeNode<K, V>?,
        sibling: RBTreeNode<K, V>?,
    ) {
        var tmpsibling: RBTreeNode<K, V> = sibling ?: return
        node?.let {
            val parent: RBTreeNode<K, V>? = node.getParent()
            parent?.let {
                val nodeIsLeftChild = node == parent.left
                if (nodeIsLeftChild && isBlack(tmpsibling.right)) {
                    tmpsibling.left?.setColor(Color.BLACK)
                    tmpsibling.setColor(Color.RED)
                    rotateRight(tmpsibling)
                    tmpsibling = node.getParent()?.right ?: return
                } else if (!nodeIsLeftChild && isBlack(tmpsibling.left)) {
                    tmpsibling.right?.setColor(Color.BLACK)
                    tmpsibling.setColor(Color.RED)
                    rotateLeft(tmpsibling)
                    tmpsibling = node.getParent()?.left ?: return
                }

                val parentColor = node.getParent()?.getColor() ?: return
                tmpsibling.setColor(parentColor)
                node.getParent()?.setColor(Color.BLACK)
                if (nodeIsLeftChild) {
                    tmpsibling.right?.setColor(Color.BLACK)
                    rotateLeft(node.getParent())
                } else {
                    tmpsibling.left?.setColor(Color.BLACK)
                    rotateRight(node.getParent())
                }
            }
        }
    }

    private fun getSibling(node: RBTreeNode<K, V>?): RBTreeNode<K, V>? {
        val parent: RBTreeNode<K, V>? = node?.getParent()
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
        return node == null || node.getColor() == Color.BLACK
    }

    private fun rotateRight(node: RBTreeNode<K, V>?) {
        node?.let {
            val parent: RBTreeNode<K, V>? = node.getParent()
            val leftChild: RBTreeNode<K, V>? = node.left
            leftChild?.let {
                node.left = leftChild.right
                leftChild.right?.apply { it.setParent(node)}

                leftChild.right = node
            }
            node.setParent(leftChild)

            replaceParentsChild(parent, node, leftChild)
        }
    }

    private fun rotateLeft(node: RBTreeNode<K, V>?) {
        node?.let {
            val parent: RBTreeNode<K, V>? = node.getParent()
            val rightChild: RBTreeNode<K, V>? = node.right
            rightChild?.let {
                node.right = rightChild.left
                rightChild.left?.apply { it.setParent(node) }

                rightChild.left = node
            }
            node.setParent(rightChild)

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

        newChild?.setParent(parent)
    }
}
