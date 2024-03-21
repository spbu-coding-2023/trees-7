import java.security.InvalidKeyException
import treeNodes.BinaryTreeNode

abstract class BinaryTree<K : Comparable<K>, V, U : BinaryTreeNode<K, V, U>> {
    protected open var root: U? = null
    public var size: Int = 0
        protected set

    abstract fun insert(key: K, value: V)

    abstract fun remove(key: K)

    open fun search(key: K): V {
        return searchNode(key).getValue()
    }

    protected fun searchNode(key: K): U {
        if (this.root == null) {
            throw InvalidKeyException("Empty tree")
        }
        var curr: U? = root
        while (curr != null && curr.getKey() != key) {
            curr = if (curr.getKey() > key) {
                curr.left
            } else {
                curr.right
            }
        }
        if (curr == null) {
            throw InvalidKeyException("No such key in the Tree")
        }
        return curr
    }

    protected fun getMinNodeFromNode(node: U): U {
        var leftChild = node.left
        do {
            if (leftChild != null) {
                val tmpLeftChild = leftChild
                leftChild = leftChild.left
                if (leftChild == null) {
                    return tmpLeftChild
                }
            }
        } while (leftChild != null)
        return node
    }


    protected fun isEmpty(): Boolean {
        return when {
            root == null -> true
            else -> false
        }
    }
    fun iterateKeys(node: U? = root, action: (K) -> Unit) {
        if (node != null) {
            iterateKeys(node.left, action)
            action(node.getKey())
            iterateKeys(node.right, action)
        }
    }
    fun iterateValues(node: U? = root, action: (V) -> Unit) {
        if (node != null) {
            iterateValues(node.left, action)
            action(node.getValue())
            iterateValues(node.right, action)
        }
    }

}

