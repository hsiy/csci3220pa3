# CSCI 3320 Programming Assignment 3

Invitation link: https://classroom.github.com/a/f4jD3Kna

Assignment description: https://unomaha.instructure.com/courses/30257/assignments/381550

---
## Starter code

In addition to the starter code for AVL and Splay Trees, a set of classes are provided showing how one might organize the common parts of the code. 
Since the Graphviz generation code is common to all variations of the binary tree, the code has been separated out (GraphvizView.java). 
SampleMain.java illustrates how one might use GraphvizView along with the BinarySearchTree, with placeholders for other trees.

To enable these, I created a series of interfaces and abstract classes to encapsulate the BST implementations:

![Starter code structure](https://yuml.me/diagram/plain;dir:TD/class/[BinaryTreeNode]-.-implements%5E[INode],%20[BinarySearchTree]-.-implements%5E[ITree],%20[BinarySearchTree]%3C%3E-%3E[BinarySearchTree.BinaryNode],%20[BinarySearchTree.BinaryNode]-%5E[BinaryTreeNode],%20[GraphvizView]-uses-%3E[INode],%20[GraphvizView]-uses-%3E[BinarySearchTree],%20[SampleMain]-uses-%3E[ITree],%20[SampleMain]-uses-%3E[GraphvizView])

<!---
Original code passed to yuml.me:
https://yuml.me/diagram/plain;dir:TD/class/[BinaryTreeNode]-.-implements^[INode], [BinarySearchTree]-.-implements^[ITree], [BinarySearchTree]<>->[BinarySearchTree.BinaryNode], [BinarySearchTree.BinaryNode]-^[BinaryTreeNode], [GraphvizView]-uses->[INode], [GraphvizView]-uses->[BinarySearchTree], [SampleMain]-uses->[ITree], [SampleMain]-uses->[GraphvizView]
--->
