
# Another Library in the Wall...

Well tested and documented custom library with binary trees implementation.
It contains:
- Binary search tree (BST)
- AVL-tree
- Red-Black tree (RBTree)



## Features

- Supports values of arbitrary type
- Search, insert and remove methods
- Iterators by keys and values 
- Does not support duplicate keys


## Usage/Examples

You can create any of the three types of trees with any type for values and any iterated type for keys:
```kotlin
val bsTree = BSTree<Char, Unit>()
val avlTree = AVLTree<Long, Int>()
val rbTree = RBTree<Int, String>()
```
You can use:
- insertation:
```kotlin
rbTree.insert(1, "Hell Word")
rbTree.insert(1, "Hello World")
rbTree.insert(2, "dlroW olleH")
```
- search:
```kotlin
val element = rbTree.search(1) // element = "Hello world"
```
- iterate:
```kotlin
rbTree.iterateKeys {
        print("${it + 10} ")
    }
// output: 11 12

rbTree.iterateValues {
        print("${it.first()} ")
    }
// output: H d
```
- and remove:
```kotlin
rbTree.remove(2)
```


## Running Tests

To run tests, run the following command

```bash
  ./gradlew test
```

## Documentation

[Documentation .md](https://github.com/spbu-coding-2023/trees-7/blob/main/docs/gfm/Documentation.md)

[Documentation .html](https://github.com/spbu-coding-2023/trees-7/blob/main/docs/html/Documentation.html)

## License

[MIT](https://choosealicense.com/licenses/mit/)


## Authors

- [@peso69](https://github.com/Sem4kok)
- [@suvorovrain](https://github.com/suvorovrain)
- [@dronshock](https://github.com/DronShock)


## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
![Static Badge](https://img.shields.io/badge/Kotlin-purple?style=flat&logo=kotlin)
![Static Badge](https://img.shields.io/badge/Gradle-8.6-green?style=flat&logo=gradle&logoColor=%2302303A)


