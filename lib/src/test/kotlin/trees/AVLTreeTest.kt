package trees

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AVLTreeTest {

    @Test
    fun `inorder traversal sequence of tree keys after insertion's`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(7,"H")
        avlTree.insert(2,"B")
        avlTree.insert(1,"A")
        avlTree.insert(4,"D")
        avlTree.insert(5,"F")
        avlTree.insert(6,"G")
        avlTree.insert(3,"C")

        val testArray = listOf(1,2,3,4,5,6,7)
        val currArray: MutableList<Int> = mutableListOf()
        avlTree.iterateKeys {
            currArray.add(it)
        }
        assertEquals(testArray, currArray)
    }

    // Removing special cases
    //       5
    //     /   \
    //    2     7
    //   / \    /         <--- avlTree
    //  1   4  6
    //      /
    //     3
    @Test
    fun `off-balance childless`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(7,"H")
        avlTree.insert(2,"B")
        avlTree.insert(1,"A")
        avlTree.insert(4,"D")
        avlTree.insert(5,"F")
        avlTree.insert(6,"G")
        avlTree.insert(3,"C")
        avlTree.remove(1)

        val heightsExpected = listOf(1,2,1,3,1,2)
        val keysExpected = listOf(2,3,4,5,6,7)
        val valuesExpected = listOf("B","C","D","F","G","H")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()

        avlTree.iterateNode {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(heightsExpected,heights)
        assertEquals(keysExpected, keys)
        assertEquals(valuesExpected, values)
    }

}