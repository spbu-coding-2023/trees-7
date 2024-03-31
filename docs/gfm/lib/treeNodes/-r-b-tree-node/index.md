//[lib](../../../Documentation)/[treeNodes](../index.md)/[RBTreeNode](index.md)

# RBTreeNode

[jvm]\
class [RBTreeNode](index.md)&lt;[K](index.md) : [Comparable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparable/index.html)&lt;[K](index.md)&gt;, [V](index.md)&gt;(key: [K](index.md), data: [V](index.md), color: [Color](../-color/index.md) = Color.BLACK, parent: [RBTreeNode](index.md)&lt;[K](index.md), [V](index.md)&gt;? = null) : [BinaryTreeNode](../-binary-tree-node/index.md)&lt;[K](index.md), [V](index.md), [RBTreeNode](index.md)&lt;[K](index.md), [V](index.md)&gt;&gt;

## Constructors

| | |
|---|---|
| [RBTreeNode](-r-b-tree-node.md) | [jvm]<br>constructor(key: [K](index.md), data: [V](index.md), color: [Color](../-color/index.md) = Color.BLACK, parent: [RBTreeNode](index.md)&lt;[K](index.md), [V](index.md)&gt;? = null) |

## Functions

| Name | Summary |
|---|---|
| [getColor](get-color.md) | [jvm]<br>fun [getColor](get-color.md)(): [Color](../-color/index.md) |
| [getKey](../-binary-tree-node/get-key.md) | [jvm]<br>fun [getKey](../-binary-tree-node/get-key.md)(): [K](index.md) |
| [getParent](get-parent.md) | [jvm]<br>fun [getParent](get-parent.md)(): [RBTreeNode](index.md)&lt;[K](index.md), [V](index.md)&gt;? |
| [getValue](../-binary-tree-node/get-value.md) | [jvm]<br>fun [getValue](../-binary-tree-node/get-value.md)(): [V](index.md) |
| [setColor](set-color.md) | [jvm]<br>fun [setColor](set-color.md)(newColor: [Color](../-color/index.md)) |
| [setKey](../-binary-tree-node/set-key.md) | [jvm]<br>fun [setKey](../-binary-tree-node/set-key.md)(key: [K](index.md)) |
| [setParent](set-parent.md) | [jvm]<br>fun [setParent](set-parent.md)(newParent: [RBTreeNode](index.md)&lt;[K](index.md), [V](index.md)&gt;?) |
| [setValue](../-binary-tree-node/set-value.md) | [jvm]<br>fun [setValue](../-binary-tree-node/set-value.md)(value: [V](index.md)) |
