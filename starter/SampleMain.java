import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

/**
 * SampleMain illustrates how one might use the provided GraphvizView class 
 *     to generate the Graphviz commands
 */
class SampleMain {

	/**
	 * Process one abstract data type
	 * @param t root of the tree data structure
	 * @param theList the elements to be added to the tree
	 */
	private static void processADT(ITree<Integer> t, ArrayList<Integer> theList) {
		System.out.print("List: ");
		for (int num: theList) {
			System.out.printf("%d ", num);
			t.insert(num);
		}
		System.out.println();

		System.out.print("Sorted: ");
		t.printTree();

		System.out.println("GraphViz: ");
		GraphvizView<Integer> gv = new GraphvizView<>(false); // use true for slightly better tree drawing
		gv.printGV(t.getRoot());

		/* not implemented in BinarySearchTree starter code - add your own
		System.out.println("Level order: ");
		t.printLevelOrder();
		*/
	}

	/**
	 * Insert the same list to multiple BST abstract data types
	 * @param theList the elements to be added to the tree
	 */
	private static void processList(ArrayList<Integer> theList) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		System.out.println("Binary Search Tree:");
		processADT(bst, theList);

		// add AVL and SplayTree  here
	}

	public static void main(String args[]) {
		int numNodes=0;
		if (args.length > 0) {
			try {
				numNodes = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(1);
			}
		} else {
			System.err.println("Usage: SampleMain <N>");
			System.err.println("    Where N is the number of elements in the tree");
			System.exit(1);
		}

		// Instead of generating a list with random numbers, just generate 
		// a list of consecutive numbers and permute them.
		// In this way, we guarantee we will have N unique numbers.
		ArrayList<Integer> myList = new ArrayList<Integer>();
		for (int i=0; i<numNodes; i++) {
			myList.add(i);
		}

		for (int i=0; i<3; i++) {
			Collections.shuffle(myList);
			System.out.printf("Permutation %d:\n", i);
			processList(myList);
		}

	}
}



