package trees

import org.junit.jupiter.api.Test
import treeNodes.Color
import kotlin.test.assertEquals

class RBTreeTest {
    @Test
    fun `inorder traversal sequence of tree keys after insertions`() {
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

    /*

              _1B  <---REMOVE                                      0B
             /                             ===>
            0R

     */
    @Test
    fun `remove node with left_child and without right_child`() {
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

    /*

              _1B                                           1B
             /                             ===>
            0R <---REMOVE

     */
    @Test
    fun `remove red_node`() {
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

    /*

              __3B_                                           _2B_
             /     \                                         /    \
           _2B     4B   <---REMOVE                          1B    3B
          /                                ===>
         1R

     */
    @Test
    fun `remove childless_right_node when left_with_left_child exists`() {
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

    /*

               _5B___                                          __8B__
              /      \                                        /      \
             2B      _8R__                                   5B_     9B_
             ^      /     \                 ===>                \       \
             |     6B     9B                                    6R      10R
          REMOVE            \
                           10R

     */
    @Test
    fun `remove left_node with red_sibling which have black children`() {
        val rbTree = RBTree<Int, String>()
        rbTree.insert(5, "Если")
        rbTree.insert(2, "бы")
        rbTree.insert(8, "Вы")
        rbTree.insert(6, "знали")
        rbTree.insert(9, "как")
        rbTree.insert(10, "я")

        rbTree.remove(2)

        val expectedColors: List<Color> =
            listOf(
                Color.BLACK,
                Color.RED,
                Color.BLACK,
                Color.BLACK,
                Color.RED,
            )
        val expectedKeys: List<Int> = listOf(5, 6, 8, 9, 10)
        val expectedValues: List<String> =
            listOf(
                "Если",
                "знали",
                "Вы",
                "как",
                "я",
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

    /*

                        ____100B______                                          ____100B______
                       /              \                                        /              \
                   ___50B___          _150B__                              ___70B__          _150B__
                  /         \        /       \              ===>          /        \        /       \
      REMOVE--> 30B       _70R__   120B    200B_                        _50B_       80B_   120B    200B_
                  \      /      \               \                      /     \          \               \
                  35R   60B     80B_           250R                   35R    60R        90R            250R
                                    \
                                    90R

     */
    @Test
    fun `remove black_node with red_sibling which has black children`() {
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

        val expectedColors: List<Color> =
            listOf(
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
        val expectedValues: List<String> =
            listOf(
                "I",
                "B",
                "J",
                "E",
                "K",
                "L",
                "A",
                "F",
                "C",
                "G",
                "H",
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

/*

           _2B__                                          _3B_
          /     \                                        /    \
         1B      3B_                                    2B    4B
         ^          \                 ===>
         |          4R
      REMOVE

*/
    @Test
    fun `remove childless_left_node when right_with_right_child exists`() {
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

/*

          __3B_                                           _2B_
         /     \                                         /    \
        1B_     4B   <---REMOVE                         1B    3B
           \                              ===>
           2R

 */
    @Test
    fun `remove childless_right_node when left_with_right_child exists`() {
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

/*

           _2B__                                          _3B_
          /     \                                        /    \
         1B     _4B                                     2B    4B
         ^     /                    ===>
         |    3R
      REMOVE

*/
    @Test
    fun `remove childless_left_node when right_with_left_child exists`() {
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

/*

           ___4B__   <---REMOVE                             _1B___
          /       \                                        /      \
        _1R__     5B_                                     0B     _3R__
       /     \       \              ===>                        /     \
      0B     _3B     6R                                        2B     5B
            /                                                           \
           2R                                                           6R

*/
    @Test
    fun `remove parent_with_two_children which has right_child without left_child`() {
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

    /*

               __2B___   <---REMOVE                             __3B__
              /       \                                        /      \
            _1R      _4R__                                   _1B     _5R_
           /        /     \              ===>               /       /    \
          0R       3B     6B                               0R      4B    6B
                         /
                        5R

     */
    @Test
    fun `remove parent_with_two_children which has right_child with left_child`() {
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

    /*

              __4B_                                                        ___4B_
             /     \                                                      /      \
           _2B_    5B                                                  __2R_     5B
          /    \                            ===>                      /     \
         0R    3R                                                    0B_    3B
          ^                                                             \
          |                                                             1R
          |
      INSERT 1

     */
    @Test
    fun `insertion to the left_left_red_child`() {
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

    /*

              __4B_                                                        ___4B_
             /     \                                                      /      \
           _1B_    5B                                                  _1R__     5B
          /    \                            ===>                      /     \
         0R    2R                                                    0B     2B_
                ^                                                              \
                |                                                              3R
                |
             INSERT 3

     */
    @Test
    fun `insertion to the left_right_red_child`() {
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

    /*

              _1B__                                                        _1B___
             /     \                                                      /      \
            0B    _4B_                                                  0B      __4R_
                 /    \                            ===>                        /     \
                3R    5R                                                     _3B      5B
                ^                                                           /
                |                                                          2R
                |
             INSERT 2

     */
    @Test
    fun `insertion to the right_left_red_child`() {
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

    /*

              __2B__                                                        __2B___
             /      \                                                      /       \
            0B_      3B_                                                  0B_       _4B_
               \        \                            ===>                    \     /    \
                1R      5R                                                    1R   3R   5R
                        ^
                        |
                        |
                     INSERT 4

     */
    @Test
    fun `insertion to the right_right_red_child`() {
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
