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

        val expectedKeys: List<Int> = listOf(69, 52, 19, 18, 17, 7, 6, 5, 4, 3)
        val expectedValues: List<String> =
            listOf(
                "Walter White",
                "Tandrew Ate",
                "Arthur Shelby",
                "John Shelby",
                "Elon Musk",
                "Thomas Shelby",
                "Patrick Bateman",
                "Tristan Tate",
                "Tyler Durden",
                "Ryan Gosling",
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

        val expectedKeys: List<Int> = listOf(74, 69, 52, 19, 18, 17, 7, 6, 5, 4, 3)
        val expectedValues: List<String> =
            listOf(
                "Iakov",
                "Walter White",
                "Tandrew Ate",
                "Arthur Shelby",
                "John Shelby",
                "Elon Musk",
                "Thomas Shelby",
                "Patrick Bateman",
                "Tristan Tate",
                "Tyler Durden",
                "Ryan Gosling",
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
        val expectedKeys: List<Int> = listOf(69, 52, 19, 18, 17, 7, 6, 5, 4)
        val expectedValues: List<String> =
            listOf(
                "Walter White",
                "Andrew Tate",
                "Arthur Shelby",
                "John Shelby",
                "Elon Musk",
                "Thomas Shelby",
                "Patrick Bateman",
                "Tristan Tate",
                "Tyler Durden",
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
        val expectedKeys: List<Int> = listOf(69, 52, 19, 17, 7, 6, 5, 4, 3)
        val expectedValues: List<String> =
            listOf(
                "Walter White",
                "Andrew Tate",
                "Arthur Shelby",
                "Elon Musk",
                "Thomas Shelby",
                "Patrick Bateman",
                "Tristan Tate",
                "Tyler Durden",
                "Ryan Gosling",
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
        val expectedKeys: List<Int> = listOf(69, 19, 18, 17, 7, 6, 5, 4, 3)
        val expectedValues: List<String> =
            listOf(
                "Walter White",
                "Arthur Shelby",
                "John Shelby",
                "Elon Musk",
                "Thomas Shelby",
                "Patrick Bateman",
                "Tristan Tate",
                "Tyler Durden",
                "Ryan Gosling",
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
