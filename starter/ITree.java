/**
 * An interface defining access points for all binary trees
 */
public interface ITree<AnyType> {
	public void printTree();
	public void insert(AnyType x);
	public boolean contains(AnyType x);
	public int height();
	public INode<AnyType> getRoot();
}

