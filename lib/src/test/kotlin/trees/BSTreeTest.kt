package trees

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BSTreeTest {
    private lateinit var bsTree: BSTree<Int, String>

    @BeforeEach
    fun setup() {
        bsTree = BSTree()
        bsTree.insert(7, "Thomas Shelby")
        bsTree.insert(52, "Andrew Tate")
        bsTree.insert(5, "Tristan Tate")
        bsTree.insert(4, "Tyler Durden")
        bsTree.insert(3, "Ryan Gosling")
        bsTree.insert(6, "Patrick Bateman")
        bsTree.insert(69, "Walter White")
        bsTree.insert(17, "Elon Musk")
        bsTree.insert(18, "John Shelby")
        bsTree.insert(19, "Arthur Shelby")
    }

    @Test
    fun `insert two nodes with same key`() {
        bsTree.insert(52, "Tandrew Ate")

        val expectedKeys: List<Int> = listOf(3, 4, 5, 6, 7, 17, 18, 19, 52, 69)
        val expectedValues: List<String> =
            listOf(
                "Ryan Gosling",
                "Tyler Durden",
                "Tristan Tate",
                "Patrick Bateman",
                "Thomas Shelby",
                "Elon Musk",
                "John Shelby",
                "Arthur Shelby",
                "Tandrew Ate",
                "Walter White",
            )
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insert node in empty tree`() {
        bsTree = BSTree()
        bsTree.insert(69, "Walter White")
        val expectedKeys: List<Int> = listOf(69)
        val expectedValues: List<String> = listOf("Walter White")
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `basic insert`() {
        bsTree.insert(74, "Iakov")

        val expectedKeys: List<Int> = listOf(3, 4, 5, 6, 7, 17, 18, 19, 52, 69, 74)
        val expectedValues: List<String> =
            listOf(
                "Ryan Gosling",
                "Tyler Durden",
                "Tristan Tate",
                "Patrick Bateman",
                "Thomas Shelby",
                "Elon Musk",
                "John Shelby",
                "Arthur Shelby",
                "Andrew Tate",
                "Walter White",
                "Iakov",
            )
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove from empty tree`() {
        bsTree = BSTree()
        bsTree.remove(69)
        val expectedKeys: List<Int> = listOf()
        val expectedValues: List<String> = listOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove node with zero children`() {
        bsTree.remove(3)
        val expectedKeys: List<Int> = listOf(4, 5, 6, 7, 17, 18, 19, 52, 69)
        val expectedValues: List<String> =
            listOf(
                "Tyler Durden",
                "Tristan Tate",
                "Patrick Bateman",
                "Thomas Shelby",
                "Elon Musk",
                "John Shelby",
                "Arthur Shelby",
                "Andrew Tate",
                "Walter White",
            )
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove node with one children`() {
        bsTree.remove(18)
        val expectedKeys: List<Int> = listOf(3, 4, 5, 6, 7, 17, 19, 52, 69)
        val expectedValues: List<String> =
            listOf(
                "Ryan Gosling",
                "Tyler Durden",
                "Tristan Tate",
                "Patrick Bateman",
                "Thomas Shelby",
                "Elon Musk",
                "Arthur Shelby",
                "Andrew Tate",
                "Walter White",
            )
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove node with two children`() {
        bsTree.remove(52)
        val expectedKeys: List<Int> = listOf(3, 4, 5, 6, 7, 17, 18, 19, 69)
        val expectedValues: List<String> =
            listOf(
                "Ryan Gosling",
                "Tyler Durden",
                "Tristan Tate",
                "Patrick Bateman",
                "Thomas Shelby",
                "Elon Musk",
                "John Shelby",
                "Arthur Shelby",
                "Walter White",
            )
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        bsTree.iterateNodes {
            keys.add(it.getKey())
            values.add(it.getValue())
        }
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }
}
