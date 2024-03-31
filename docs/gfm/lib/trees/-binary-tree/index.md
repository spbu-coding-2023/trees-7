//[lib](../../../Documentation)/[trees](../index.md)/[BinaryTree](index.md)

# BinaryTree

abstract class [BinaryTree](index.md)&lt;[K](index.md) : [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)&lt;[K](index.md)&gt;, [V](index.md), [U](index.md) : [BinaryTreeNode](../../treeNodes/-binary-tree-node/index.md)&lt;[K](index.md), [V](index.md), [U](index.md)&gt;&gt;

#### Inheritors

| |
|---|
| [AVLTree](../-a-v-l-tree/index.md) |
| [BSTree](../-b-s-tree/index.md) |
| [RBTree](../-r-b-tree/index.md) |

## Constructors

| | |
|---|---|
| [BinaryTree](-binary-tree.md) | [jvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [insert](insert.md) | [jvm]<br>abstract fun [insert](insert.md)(key: [K](index.md), value: [V](index.md)) |
| [iterateKeys](iterate-keys.md) | [jvm]<br>fun [iterateKeys](iterate-keys.md)(node: [U](index.md)? = root, action: ([K](index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [iterateValues](iterate-values.md) | [jvm]<br>fun [iterateValues](iterate-values.md)(node: [U](index.md)? = root, action: ([V](index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [remove](remove.md) | [jvm]<br>abstract fun [remove](remove.md)(key: [K](index.md)) |
| [search](search.md) | [jvm]<br>open fun [search](search.md)(key: [K](index.md)): [V](index.md) |
