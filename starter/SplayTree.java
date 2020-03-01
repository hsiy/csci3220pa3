// SplayTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is found
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a splay tree.
 * Note that all "matching" is based on the compareTo method.
 * adapted from Weiss's top-down splay tree implementation and Sedgewick's implementation: 
 *     http://users.cis.fiu.edu/~weiss/dsaajava3/code/SplayTree.java
 *     https://algs4.cs.princeton.edu/33balanced/SplayBST.java.html
 * @author Harvey Siy
 */
public class SplayTree<AnyType extends Comparable<? super AnyType>> 
{
    /**
     * Construct the tree.
     */
    public SplayTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        BinaryNode<AnyType> newNode = new BinaryNode<AnyType>( x );

        if( root == null )
        {
            root = newNode;
        }
        else
        {
            root = splay( x, root );

            int compareResult = x.compareTo( root.element );
			
            if( compareResult < 0 )
            {
                newNode.left = root.left;
                newNode.right = root;
                root.left = null;
                root = newNode;
            }
            else
            if( compareResult > 0 )
            {
                newNode.right = root.right;
                newNode.left = root;
                root.right = null;
                root = newNode;
            }
            else
                return;   // No duplicates
        }
    }

    /**
     * Remove from the tree.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        if( !contains( x ) )
            return;

        BinaryNode<AnyType> newTree;

            // If x is found, it will be splayed to the root by contains
        if( root.left == null)
            newTree = root.right;
        else
        {
            // Find the maximum in the left subtree
            // Splay it to the root; and then attach right child
            newTree = root.left;
            newTree = splay( x, newTree );
            newTree.right = root.right;
        }
        root = newTree;
    }

    /**
     * Find the smallest item in the tree.
     * Not the most efficient implementation (uses two passes), but has correct
     *     amortized behavior.
     * A good alternative is to first call find with parameter
     *     smaller than any item in the tree, then call findMin.
     * @return the smallest item or throw UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        BinaryNode<AnyType> ptr = root;

        while( ptr.left != null )
            ptr = ptr.left;

        root = splay( ptr.element, root );
        return ptr.element;
    }

    /**
     * Find the largest item in the tree.
     * Not the most efficient implementation (uses two passes), but has correct
     *     amortized behavior.
     * A good alternative is to first call find with parameter
     *     larger than any item in the tree, then call findMax.
     * @return the largest item or throw UnderflowException if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        BinaryNode<AnyType> ptr = root;

        while( ptr.right != null )
            ptr = ptr.right;

        root = splay( ptr.element, root );
        return ptr.element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found; otherwise false.
     */
    public boolean contains( AnyType x )
    {
        if( isEmpty( ) )
            return false;
			
        root = splay( x, root );

        return root.element.compareTo( x ) == 0;
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Internal method to perform a top-down splay.
     * The last accessed node becomes the new root.
     * @param x the target item to splay around.
     * @param t the root of the subtree to splay.
     * @return the subtree after the splay.
     */
    private BinaryNode<AnyType> splay( AnyType x, BinaryNode<AnyType> t )
    {

        if ( t == null ) 
            return null;

        int compareResult = x.compareTo( t.element );

        if ( compareResult < 0 ) 
        {
            if ( t.left == null )
                return t;

            int compareResult2 = x.compareTo( t.left.element ); 
            if ( compareResult2 < 0 ) 
            {
                t.left.left = splay( x, t.left.left );
                t = rotateWithLeftChild ( t );
            } 
            else if ( compareResult2 > 0 ) 
            {
                t.left.right = splay( x, t.left.right );
                if ( t.left.right != null )
                    t.left = rotateWithRightChild( t.left );
            }

            if ( t.left == null )
                return t;
            else
                return rotateWithLeftChild( t );

        }
        else if( compareResult > 0 )
        {
            if ( t.right == null )
                return t;

            int compareResult2 = x.compareTo( t.right.element ); 
            if ( compareResult2 < 0 ) 
            {
                t.right.left = splay( x, t.right.left );
                if ( t.right.left != null )
                    t.right = rotateWithLeftChild( t.right );
            } 
            else if ( compareResult2 > 0 ) 
            {
                t.right.right = splay( x, t.right.right );
                t = rotateWithRightChild ( t );
            }

            if ( t.right == null )
                return t;
            else
                return rotateWithRightChild( t );
        }
        
        return t;
    }


    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     */
    private static <AnyType> BinaryNode<AnyType> rotateWithLeftChild( BinaryNode<AnyType> k2 )
    {
        BinaryNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     */
    private static <AnyType> BinaryNode<AnyType> rotateWithRightChild( BinaryNode<AnyType> k1 )
    {
        BinaryNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    // Basic node stored in binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


    private BinaryNode<AnyType> root;


        // Test program; should print min and max and nothing else
    public static void main( String [ ] args )
    {
        SplayTree<Integer> t = new SplayTree<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no bad output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );
        System.out.println( "Inserts complete" );

        for( int i = 1; i < NUMS; i += 2 )
            t.remove( i );
        System.out.println( "Removes complete" );

        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i += 2 )
            if( !t.contains( i ) )
                System.out.println( "Error: find fails for " + i );

        for( int i = 1; i < NUMS; i += 2 ) {
            if( t.contains( i ) )
                System.out.println( "Error: Found deleted item " + i );
        }
    }
}

