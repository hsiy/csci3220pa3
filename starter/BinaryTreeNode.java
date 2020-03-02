
/*
 * A generic binary tree node
 */
public abstract class BinaryTreeNode<AnyType, NodeType extends BinaryTreeNode<AnyType,NodeType>> implements INode<AnyType> {

        // Constructors
	BinaryTreeNode( AnyType theElement )
	{
		this( theElement, null, null );
	}

	BinaryTreeNode( AnyType theElement, NodeType lt, NodeType rt )
	{
		element  = theElement;
		left     = lt;
		right    = rt;
	}

	AnyType element;	// The data in the node
	NodeType left;   	// Left child
	NodeType right;		// Right child

	public INode<AnyType> getLeft() {
		return left;
	}

	public INode<AnyType> getRight() {
		return right;
	}

	public AnyType getElement() {
		return element;
	}
}


