package trees

import org.junit.jupiter.api.Test
import treeNodes.Color
import java.security.InvalidKeyException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RBTreeTest {
    @Test
    fun `search in empty tree expect special exception message `() {
        val rbTree = RBTree<Int, String>()

        val exception =
            assertFailsWith<InvalidKeyException>(
                block = { rbTree.search(1) },
            )
        assertEquals("Empty tree", exception.message)
    }

    @Test
    fun `search without needed key expect special exception message`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(2, "Dron")
        rbTree.insert(4, "Rodion")
        rbTree.insert(1, "Semyon")

        val exception =
            assertFailsWith<InvalidKeyException>(
                block = { rbTree.search(3) },
            )
        assertEquals("No such key in the Tree", exception.message)
    }

    @Test
    fun `basic search test`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(2, "I")
        rbTree.insert(4, "love")
        rbTree.insert(1, "solving")
        rbTree.insert(8, "mathematical")
        rbTree.insert(9, "physics")
        rbTree.insert(5, "equations")

        val currentlyValue = rbTree.search(8)
        val expectedValue = "mathematical"

        assertEquals(expectedValue, currentlyValue)
    }

    @Test
    fun `inorder traversal sequence of tree keys after insertion's`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(7, "H")
        rbTree.insert(2, "E")
        rbTree.insert(1, "L")
        rbTree.insert(4, "P")
        rbTree.insert(5, "M")
        rbTree.insert(6, "E")
        rbTree.insert(3, "o_0")

        val testArray = listOf(1, 2, 3, 4, 5, 6, 7)
        val currArray: MutableList<Int> = mutableListOf()
        rbTree.iterateKeys {
            currArray.add(it)
        }
        assertEquals(testArray, currArray)
    }

    @Test
    fun `remove node from empty tree`() {
        val rbTree = RBTree<Int, String>()
        rbTree.remove(0)

        val expectedColors: List<Color> = emptyList()
        val expectedKeys: List<Int> = emptyList()
        val expectedValues: List<String> = emptyList()
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove root`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(0, "R.I.P")
        rbTree.remove(0)

        val expectedColors: List<Color> = emptyList()
        val expectedKeys: List<Int> = emptyList()
        val expectedValues: List<String> = emptyList()
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove node with left child and without right child`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(1, "Эти")
        rbTree.insert(0, "Кыз")
        rbTree.remove(1)

        val expectedColors: List<Color> = listOf(Color.BLACK)
        val expectedKeys: List<Int> = listOf(0)
        val expectedValues: List<String> = listOf("Кыз")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove red node`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(1, "Карай")
        rbTree.insert(0, "Кызыл")
        rbTree.remove(0)

        val expectedColors: List<Color> = listOf(Color.BLACK)
        val expectedKeys: List<Int> = listOf(1)
        val expectedValues: List<String> = listOf("Карай")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_right when left-with-left-child_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(3, "C")
        rbTree.insert(2, "B")
        rbTree.insert(4, "D")
        rbTree.insert(1, "A")

        rbTree.remove(4)

        val expectedColors: List<Color> = listOf(Color.BLACK, Color.BLACK, Color.BLACK)
        val expectedKeys: List<Int> = listOf(1, 2, 3)
        val expectedValues: List<String> = listOf("A", "B", "C")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove left node with red sibling which have black children`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(5, "Если")
        rbTree.insert(2, "бы")
        rbTree.insert(8, "Вы")
        rbTree.insert(6, "знали")
        rbTree.insert(9, "как")
        rbTree.insert(10, "я")


        rbTree.remove(2)

        val expectedColors: List<Color> = listOf(
            Color.BLACK,
            Color.RED,
            Color.BLACK,
            Color.BLACK,
            Color.RED,
        )
        val expectedKeys: List<Int> = listOf(5,6,8,9,10)
        val expectedValues: List<String> = listOf(
            "Если",
            "знали",
            "Вы",
            "как",
            "я"
        )
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }
    @Test
    fun `remove black node with red sibling which have black children`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(100, "A")
        rbTree.insert(50, "B")
        rbTree.insert(150, "C")
        rbTree.insert(30, "D")
        rbTree.insert(70, "E")
        rbTree.insert(120, "F")
        rbTree.insert(200, "G")
        rbTree.insert(250, "H")
        rbTree.insert(35, "I")
        rbTree.insert(60, "J")
        rbTree.insert(80, "K")
        rbTree.insert(90, "L")


        rbTree.remove(30)

        val expectedColors: List<Color> = listOf(
            Color.RED,
            Color.BLACK,
            Color.RED,
            Color.BLACK,
            Color.BLACK,
            Color.RED,
            Color.BLACK,
            Color.BLACK,
            Color.BLACK,
            Color.BLACK,
            Color.RED,
        )
        val expectedKeys: List<Int> = listOf(35, 50, 60, 70, 80, 90, 100, 120, 150, 200, 250)
        val expectedValues: List<String> = listOf(
            "I", "B", "J", "E", "K", "L", "A", "F", "C", "G", "H"
        )
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_left when right-with-right-child_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(2, "B")
        rbTree.insert(1, "A")
        rbTree.insert(3, "C")
        rbTree.insert(4, "D")

        rbTree.remove(1)

        val expectedColors: List<Color> = listOf(Color.BLACK, Color.BLACK, Color.BLACK)
        val expectedKeys: List<Int> = listOf(2, 3, 4)
        val expectedValues: List<String> = listOf("B", "C", "D")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_right when left-with-right-child_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(3, "C")
        rbTree.insert(1, "B")
        rbTree.insert(4, "D")
        rbTree.insert(2, "A")

        rbTree.remove(4)

        val expectedColors: List<Color> = listOf(Color.BLACK, Color.BLACK, Color.BLACK)
        val expectedKeys: List<Int> = listOf(1, 2, 3)
        val expectedValues: List<String> = listOf("B", "A", "C")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_childless_left when right-with-left-child_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(2, "C")
        rbTree.insert(1, "B")
        rbTree.insert(4, "D")
        rbTree.insert(3, "A")

        rbTree.remove(1)

        val expectedColors: List<Color> = listOf(Color.BLACK, Color.BLACK, Color.BLACK)
        val expectedKeys: List<Int> = listOf(2, 3, 4)
        val expectedValues: List<String> = listOf("C", "A", "D")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_two-child then replacing_without_left`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(4, "E")
        rbTree.insert(1, "B")
        rbTree.insert(5, "F")
        rbTree.insert(0, "A")
        rbTree.insert(3, "D")
        rbTree.insert(6, "G")
        rbTree.insert(2, "C")

        rbTree.remove(4)

        val expectedColors: List<Color> =
            listOf(Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLACK, Color.RED)
        val expectedKeys: List<Int> = listOf(0, 1, 2, 3, 5, 6)
        val expectedValues: List<String> = listOf("A", "B", "C", "D", "F", "G")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `remove_two-child then replacing_by_successor`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(2, "C")
        rbTree.insert(1, "B")
        rbTree.insert(4, "E")
        rbTree.insert(0, "A")
        rbTree.insert(3, "D")
        rbTree.insert(6, "G")
        rbTree.insert(5, "F")

        rbTree.remove(2)

        val expectedColors: List<Color> =
            listOf(Color.RED, Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLACK)
        val expectedKeys: List<Int> = listOf(0, 1, 3, 4, 5, 6)
        val expectedValues: List<String> = listOf("A", "B", "D", "E", "F", "G")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion left-left_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(4, "E")
        rbTree.insert(2, "C")
        rbTree.insert(5, "F")
        rbTree.insert(0, "A")
        rbTree.insert(3, "D")

        rbTree.insert(1, "B")

        val expectedColors: List<Color> =
            listOf(Color.BLACK, Color.RED, Color.RED, Color.BLACK, Color.BLACK, Color.BLACK)
        val expectedKeys: List<Int> = listOf(0, 1, 2, 3, 4, 5)
        val expectedValues: List<String> = listOf("A", "B", "C", "D", "E", "F")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion left-right_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(4, "E")
        rbTree.insert(1, "B")
        rbTree.insert(5, "F")
        rbTree.insert(0, "A")
        rbTree.insert(2, "C")

        rbTree.insert(3, "D")

        val expectedColors: List<Color> =
            listOf(Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.BLACK)
        val expectedKeys: List<Int> = listOf(0, 1, 2, 3, 4, 5)
        val expectedValues: List<String> = listOf("A", "B", "C", "D", "E", "F")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion right-left_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(1, "B")
        rbTree.insert(0, "A")
        rbTree.insert(4, "E")
        rbTree.insert(3, "D")
        rbTree.insert(5, "F")

        rbTree.insert(2, "C")

        val expectedColors: List<Color> =
            listOf(Color.BLACK, Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK)
        val expectedKeys: List<Int> = listOf(0, 1, 2, 3, 4, 5)
        val expectedValues: List<String> = listOf("A", "B", "C", "D", "E", "F")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `insertion right-right_case`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(0, "A")
        rbTree.insert(3, "D")
        rbTree.insert(2, "C")
        rbTree.insert(1, "B")
        rbTree.insert(5, "F")

        rbTree.insert(4, "E")

        val expectedColors: List<Color> = listOf(Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED)
        val expectedKeys: List<Int> = listOf(0, 1, 2, 3, 4, 5)
        val expectedValues: List<String> = listOf("A", "B", "C", "D", "E", "F")
        val colors: MutableList<Color> = mutableListOf()
        val keys: MutableList<Int> = mutableListOf()
        val values: MutableList<String> = mutableListOf()
        rbTree.iterateNodes {
            colors.add(it.getColor())
            keys.add(it.getKey())
            values.add(it.getValue())
        }

        assertEquals(expectedColors, colors)
        assertEquals(expectedKeys, keys)
        assertEquals(expectedValues, values)
    }

    @Test
    fun `add, if the value already exists replaces it`() {
        val rbTree: RBTree<Int, String> = RBTree()
        rbTree.insert(1, "I")
        rbTree.insert(2, "like")
        rbTree.insert(3, "to")
        rbTree.insert(4, "debug")
        rbTree.insert(5, "balancing")
        rbTree.insert(2, "algorithm")

        val currentlyValue = rbTree.search(2)
        val expectedValue = "algorithm"

        assertEquals(expectedValue, currentlyValue)
    }
}
