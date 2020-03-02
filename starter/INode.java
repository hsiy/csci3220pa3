/**
 * An interface defining access points for binary tree nodes
 */
public interface INode<AnyType> {
	public INode<AnyType> getLeft();
	public INode<AnyType> getRight();
	public AnyType getElement();
}

