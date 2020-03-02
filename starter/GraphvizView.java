// GraphvizView class
// //
// // CONSTRUCTION: boolean flag turns attempt to pretty print output by adding invisible null nodes
// //
// // ******************PUBLIC OPERATIONS*********************
// // void printGV( x )        --> print the Graphviz commands for tree at root x
// // void printGVEdge( x )    --> print an edge and recursively print edges of subtrees
// // ******************ERRORS********************************

/**
 * Outputs Graphviz commands to display a binary tree
 * @author Harvey Siy
 */

import java.util.*;

public class GraphvizView<AnyType extends Comparable<AnyType>> {
	private long nodeCount;
	private long nullCount;
	private HashMap<AnyType, Long> mapping;
	private boolean prettyprint;
	private String nullColor;

	/**
	 * Create new GraphvizView instance; by default, choose raw output (no pretty print)
	 */
	public GraphvizView() {
		this(false);
	}

	/**
	 * Create new GraphvizView instance
	 * @param pretty if true, attempt to pretty print by adding invisible null nodes
	 */
	public GraphvizView(boolean pretty) {
		nodeCount = 0;
		nullCount = 0;
		mapping = new HashMap<>();
		prettyprint = pretty;
		nullColor = "invis";
	}

	private long getNodeID(INode<AnyType> t) {

		if (mapping.containsKey(t.getElement())) {
			return mapping.get(t.getElement());
		} 
		mapping.put(t.getElement(), ++nodeCount);
		System.out.printf("    n%d [label=\"", nodeCount);
		System.out.print(t.getElement());
		System.out.printf("\", shape=\"circle\"]\n");
		return nodeCount;
	}

	private long getNullID() {
		++nullCount;
		System.out.printf("    null%d [shape=\"point\", label=\"\", color=\"%s\"]\n", nullCount, nullColor);
		return nullCount;
	}

	private void printEdge(INode<AnyType> from, INode<AnyType> to) {
		Long fromID, toID; 
		if (! prettyprint) {
			System.out.printf("    %d -> %d\n", from.getElement(), to.getElement());
		} else {
			fromID = getNodeID(from);
			toID = getNodeID(to);
			System.out.printf("    n%d -> n%d\n", fromID, toID);
		}
	}

	private void printNullEdge(INode<AnyType> from) {
		Long fromID, toID; 
		if (prettyprint) {
			fromID = getNodeID(from);
			toID = getNullID();
			System.out.printf("    n%d -> null%d [color=\"%s\"]\n", fromID, toID, nullColor);
		}
	}

	/**
	 * Print a pair of Graphviz edges from a node to its children
	 * @param t the node whose children edges are to be printed
	 */
	public void printGVEdge(INode<AnyType> t) {
		if (t == null || t.getLeft() == t || t.getElement() == null) {
			return;
		}

		if (t.getLeft() != null) {
			printGVEdge(t.getLeft());
			printEdge(t, t.getLeft());
		} else {
			if (t.getRight() != null)
				printNullEdge(t);
		}


		if (t.getRight() != null) {
			printEdge(t, t.getRight());
			printGVEdge(t.getRight());
		} else {
			if (t.getLeft() != null)
				printNullEdge(t);
		}
	}

	/**
	 * Print the Graphviz commands for a binary tree
	 * @param root the root of the binary tree to be printed
	 */
	public void printGV(INode<AnyType> root) {
		System.out.println("digraph G {");
		printGVEdge(root);
		System.out.println("}");
	}

}
