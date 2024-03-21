package trees

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.security.InvalidKeyException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AVLTreeTest {

    @Test
    fun `search in empty tree expect special exception message `() {
        val avlTree = AVLTree<Int, String>()

        val exception = assertFailsWith<InvalidKeyException> (
                block = { avlTree.search(1) }
        )
        assertEquals("Empty tree", exception.message)
    }

    @Test
    fun `search without needed key expect special exception message`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"Python")
        avlTree.insert(4,"Ruby")
        avlTree.insert(1,"Flutter")

        val exception = assertFailsWith<InvalidKeyException> (
                block = { avlTree.search(3) }
        )
        assertEquals("No such key in the Tree", exception.message)
    }

    @Test
    fun `basic search test`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"Python")
        avlTree.insert(4,"Ruby")
        avlTree.insert(1,"Flutter")
        avlTree.insert(8,"Rust")
        avlTree.insert(9,"Pascal")

        val currentlyValue = avlTree.search(8)
        val expectedValue = "Rust"

        assertEquals(expectedValue, currentlyValue)
    }
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


    @Test
    fun `remove_childless_right when left-with-left-child_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(3,"C")
        avlTree.insert(2,"B")
        avlTree.insert(4,"D")
        avlTree.insert(1,"A")

        avlTree.remove(4)

        val expectedHeights: List<Int> = listOf(1,2,1)
        val expectedKeys: List<Int> = listOf(1,2,3)
        val expectedValues: List<String> = listOf("A","B","C")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_left when right-with-right-child_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"B")
        avlTree.insert(1,"A")
        avlTree.insert(3,"C")
        avlTree.insert(4,"D")

        avlTree.remove(1)

        val expectedHeights: List<Int> = listOf(1,2,1)
        val expectedKeys: List<Int> = listOf(2,3,4)
        val expectedValues: List<String> = listOf("B","C","D")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_right when left-with-right-child_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(3,"C")
        avlTree.insert(2,"B")
        avlTree.insert(4,"D")
        avlTree.insert(1,"A")

        avlTree.remove(4)

        val expectedHeights: List<Int> = listOf(1,2,1)
        val expectedKeys: List<Int> = listOf(1,2,3)
        val expectedValues: List<String> = listOf("A","B","C")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_left when right-with-left-child_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"B")
        avlTree.insert(1,"A")
        avlTree.insert(4,"D")
        avlTree.insert(3,"C")

        avlTree.remove(1)

        val expectedHeights: List<Int> = listOf(1,2,1)
        val expectedKeys: List<Int> = listOf(2,3,4)
        val expectedValues: List<String> = listOf("B","C","D")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_one-child unbalanced_left-heavy left-right_rotation_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(4,"E")
        avlTree.insert(1,"B")
        avlTree.insert(6,"G")
        avlTree.insert(0,"A")
        avlTree.insert(3,"D")
        avlTree.insert(5,"F")
        avlTree.insert(2,"C")

        avlTree.remove(6)

        val expectedHeights: List<Int> = listOf(1,2,1,3,2,1)
        val expectedKeys: List<Int> = listOf(0,1,2,3,4,5)
        val expectedValues: List<String> = listOf("A","B","C","D","E","F")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_one-child unbalanced_left-heavy right_rotation_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(4,"E")
        avlTree.insert(2,"C")
        avlTree.insert(6,"G")
        avlTree.insert(0,"A")
        avlTree.insert(3,"D")
        avlTree.insert(5,"F")
        avlTree.insert(1,"B")

        avlTree.remove(6)

        val expectedHeights: List<Int> = listOf(2,1,3,1,2,1)
        val expectedKeys: List<Int> = listOf(0,1,2,3,4,5)
        val expectedValues: List<String> = listOf("A","B","C","D","E","F")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_one-child unbalanced_right-heavy left_rotation_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"C")
        avlTree.insert(0,"A")
        avlTree.insert(4,"E")
        avlTree.insert(1,"B")
        avlTree.insert(3,"D")
        avlTree.insert(5,"F")
        avlTree.insert(6,"G")

        avlTree.remove(0)

        val expectedHeights: List<Int> = listOf(1,2,1,3,2,1)
        val expectedKeys: List<Int> = listOf(1,2,3,4,5,6)
        val expectedValues: List<String> = listOf("B","C","D","E","F","G")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_one-child unbalanced_right-heavy right_left_rotation_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"C")
        avlTree.insert(0,"A")
        avlTree.insert(5,"F")
        avlTree.insert(1,"B")
        avlTree.insert(3,"D")
        avlTree.insert(6,"G")
        avlTree.insert(4,"E")

        avlTree.remove(0)

        val expectedHeights: List<Int> = listOf(1,2,3,1,2,1)
        val expectedKeys: List<Int> = listOf(1,2,3,4,5,6)
        val expectedValues: List<String> = listOf("B","C","D","E","F","G")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_two-child then replacing_without_left`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(4,"E")
        avlTree.insert(1,"B")
        avlTree.insert(5,"F")
        avlTree.insert(0,"A")
        avlTree.insert(3,"D")
        avlTree.insert(6,"G")
        avlTree.insert(2,"C")

        avlTree.remove(4)

        val expectedHeights: List<Int> = listOf(1,2,1,3,2,1)
        val expectedKeys: List<Int> = listOf(0,1,2,3,5,6)
        val expectedValues: List<String> = listOf("A","B","C","D","F","G")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_two-child then replacing_by_successor`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(2,"C")
        avlTree.insert(1,"B")
        avlTree.insert(4,"E")
        avlTree.insert(0,"A")
        avlTree.insert(3,"D")
        avlTree.insert(6,"G")
        avlTree.insert(5,"F")

        avlTree.remove(2)

        val expectedHeights: List<Int> = listOf(1,2,3,1,2,1)
        val expectedKeys: List<Int> = listOf(0,1,3,4,5,6)
        val expectedValues: List<String> = listOf("A","B","D","E","F","G")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion left-left_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(4,"E")
        avlTree.insert(2,"C")
        avlTree.insert(5,"F")
        avlTree.insert(0,"A")
        avlTree.insert(3,"D")

        avlTree.insert(1,"B")

        val expectedHeights: List<Int> = listOf(2,1,3,1,2,1)
        val expectedKeys: List<Int> = listOf(0,1,2,3,4,5)
        val expectedValues: List<String> = listOf("A","B","C","D","E","F")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion left-right_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(4,"E")
        avlTree.insert(1,"B")
        avlTree.insert(5,"F")
        avlTree.insert(0,"A")
        avlTree.insert(2,"C")

        avlTree.insert(3,"D")

        val expectedHeights: List<Int> = listOf(1,2,3,1,2,1)
        val expectedKeys: List<Int> = listOf(0,1,2,3,4,5)
        val expectedValues: List<String> = listOf("A","B","C","D","E","F")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion right-left_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(1,"B")
        avlTree.insert(0,"A")
        avlTree.insert(4,"E")
        avlTree.insert(3,"D")
        avlTree.insert(5,"F")

        avlTree.insert(2,"C")

        val expectedHeights: List<Int> = listOf(1,2,1,3,2,1)
        val expectedKeys: List<Int> = listOf(0,1,2,3,4,5)
        val expectedValues: List<String> = listOf("A","B","C","D","E","F")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion right-right_case`() {
        val avlTree = AVLTree<Int, String>()
        avlTree.insert(1,"B")
        avlTree.insert(0,"A")
        avlTree.insert(3,"D")
        avlTree.insert(2,"C")
        avlTree.insert(5,"F")

        avlTree.insert(4,"E")


        val expectedHeights: List<Int> = listOf(1,2,1,3,1,2)
        val expectedKeys: List<Int> = listOf(0,1,2,3,4,5)
        val expectedValues: List<String> = listOf("A","B","C","D","E","F")
        val heights: MutableList<Int> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        avlTree.iterateNodes {
            heights.add(it.getHeight())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedHeights, heights)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `add, if the value already exists replaces it`() {
        val avlTree: AVLTree<Int, String> = AVLTree()
        avlTree.insert(1, "Golang")
        avlTree.insert(2, "Kotlin")
        avlTree.insert(3, "Python")
        avlTree.insert(4, "Fortran")
        avlTree.insert(5, ".Net")
        avlTree.insert(2, "Java")

        val currentlyValue = avlTree.search(2)
        val expectedValue = "Java"

        assertEquals(expectedValue, currentlyValue)
    }
}
