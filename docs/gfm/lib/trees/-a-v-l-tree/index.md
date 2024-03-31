//[lib](../../../Documentation)/[trees](../index.md)/[AVLTree](index.md)

# AVLTree

[jvm]\
class [AVLTree](index.md)&lt;[K](index.md) : [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)&lt;[K](index.md)&gt;, [V](index.md)&gt; : [BinaryTree](../-binary-tree/index.md)&lt;[K](index.md), [V](index.md), [AVLTreeNode](../../treeNodes/-a-v-l-tree-node/index.md)&lt;[K](index.md), [V](index.md)&gt;&gt;

## Constructors

| | |
|---|---|
| [AVLTree](-a-v-l-tree.md) | [jvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [insert](insert.md) | [jvm]<br>open override fun [insert](insert.md)(key: [K](index.md), value: [V](index.md)) |
| [iterateKeys](index.md#-793916552%2FFunctions%2F1299105613) | [jvm]<br>fun [iterateKeys](index.md#-793916552%2FFunctions%2F1299105613)(node: [AVLTreeNode](../../treeNodes/-a-v-l-tree-node/index.md)&lt;[K](index.md), [V](index.md)&gt;? = root, action: ([K](index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [iterateValues](index.md#1400780183%2FFunctions%2F1299105613) | [jvm]<br>fun [iterateValues](index.md#1400780183%2FFunctions%2F1299105613)(node: [AVLTreeNode](../../treeNodes/-a-v-l-tree-node/index.md)&lt;[K](index.md), [V](index.md)&gt;? = root, action: ([V](index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [remove](remove.md) | [jvm]<br>open override fun [remove](remove.md)(key: [K](index.md)) |
| [search](../-binary-tree/search.md) | [jvm]<br>open fun [search](../-binary-tree/search.md)(key: [K](index.md)): [V](index.md) |
