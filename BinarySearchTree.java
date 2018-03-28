import java.util.NoSuchElementException;


//import java.util.ArrayList;
//import java.util.List;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new NoSuchElementException("Symbol table underflow");
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new NoSuchElementException("Symbol table underflow");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
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
    public int height() {
    	return height(root);
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

  
    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
            
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    
    
    
    // Basic node stored in unbalanced binary search trees
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


      /** The tree root. */
    private BinaryNode<AnyType> root;
    
    
    

//////////////////////////////////////////////////////////////////////// 
//    
//                       new code
//   
//    
////////////////////////////////////////////////////////////////////////    
    
    

///////////////////////////////////////////////(a)    
     /**
     * Count the number of nodes in the tree.
     * @return the count number of nodes in the tree.
     */
     public int nodeCount() {
	
		return nodeCount(root);
		
}

     /**
      * Internal method to count the node number of a subtree.
      * @param t the node that roots the subtree.
      * @return the count number of nodes in the subtree.
      */
     private int nodeCount( BinaryNode<AnyType> t ){


        if( t == null )
           return 0;
        else
    	    return(nodeCount(t.left)+1+nodeCount(t.right));
   	        // if t not null, count+=1 and continue to count in next steps
    
}


///////////////////////////////////////////////(b) 
     
     /**
     * Check this is a full tree or not.
     * @return true if it is a full binary search tree.
     */     
     
    public boolean isFull() {
	    return  isFull(root);
    }
    
    /**
     * Internal method to check this subtree is full or not.
     * @param t the node that roots the subtree.
     * @return true if it is a full subtree.
     */
    
    private boolean isFull (BinaryNode<AnyType> t) {
	    if (t==null) 
	    	return true; 
	    if (t.left==null && t.right==null) 
	    	      //if t does have children, it is suitable for full tree.
	    	return true;
	    if (t.left!=null && t.right!=null)
	    	return (isFull(t.left) && isFull(t.right));
	              //if t has child, it must have two, then continue to check children in the next steps.                 
	    return false;
}



///////////////////////////////////////////////(c)

    /**
    * Get the root of a binary search tree.
    * @return the root of tree.
    */     
        
    public BinaryNode<AnyType> root (){
    	return root;    // it will be used in the function: compareStructure
    }    
    
    
    /**
    * Compare structures of two trees.
    * @return true if two trees have same structure (same element is unnecessary).
    */     
    
    public boolean compareStructure(BinarySearchTree<AnyType> T1) {
	    return compareStructure(root,T1.root());
}

    /**
     * Internal method to check two subtrees have same structures or not.
     * @param t1 the node that roots one subtree.
     * @param t2 the node that roots another subtree.
     * @return true if these two subtrees have same structure.
     */   
    
    private boolean compareStructure (BinaryNode<AnyType> t1, BinaryNode<AnyType> t2 ) {
	    if (t1==null && t2==null)//case one: all nodes are null in same location of subtrees
	    	return true;
	    if (t1!=null && t2!=null)//case two: all nodes are not null in same location of subtrees
	    	return (compareStructure (t1.left, t2.left) && compareStructure(t1.right, t2.right));
	            // if the structure of the tree in one location is the same, turn to their childrens.
	    else
		    return false;

}


///////////////////////////////////////////////(d) 
    
    
    /**
    * compare two trees are same or not.
    * @return true if two trees are same (same structure and same elements).
    */       
    
    public boolean equals(BinarySearchTree<AnyType> T1) {
	    return equals(root, T1.root());
}
    
    /**
     * Internal method to check two subtrees are same or not.
     * @param t1 the node that roots one subtree.
     * @param t2 the node that roots another subtree.
     * @return true if these two subtrees are same.
     */     
    private boolean equals (BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
        if (t1==null && t2==null)//case one: all nodes are null in same location of trees
            return true;
        if (t1!=null && t2!=null)//case two: all nodes are not null in same location of trees
            return ( t1.element==t2.element && equals (t1.left, t2.left) && equals(t1.right, t2.right));
        else   // check element of t1 and t2 are same or not, then turn to their children.
            return false;

}


///////////////////////////////////////////////(e)
    
    /**
    * Creates a new tree that is a copy of the original tree.
    * @return the new tree copied from the original tree.
    */       
       
    
     public BinarySearchTree<AnyType> copy() {
    	 
	     BinarySearchTree<AnyType> T2 = new BinarySearchTree<AnyType>();
         if (root==null)
         	return T2;
         else
         	return copy(root, T2);

}

     /**
      * Internal method to create a new subtree that is a copy of the original subtree.
      * @param t the node that roots the original subtree.
      * @param tree the new tree will be returned.
      * @return the new subtree copied from the original subtree.
      */       
     
     private BinarySearchTree<AnyType> copy (BinaryNode<AnyType> t, BinarySearchTree<AnyType> tree){
	     if (t!=null) {
		     tree.insert(t.element); // copy this node into the new tree then turn to it's children.
		     copy(t.left,tree);
		     copy(t.right,tree);
	}
	     return tree;
}


///////////////////////////////////////////////(f)
     /**
      * Insert into the tree; duplicates are ignored. Difference: nodes more close to left will be larger.
      * @param x the item to insert.
      */    
     
    public void insert1( AnyType x ){

        root = insert1( x, root );
}

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    
    private BinaryNode<AnyType> insert1( AnyType x, BinaryNode<AnyType> t ){

        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
    
        int compareResult = x.compareTo( t.element );
        
        if( compareResult > 0 )           // if x bigger than the node t, turn to left way of t.
            t.left = insert1( x, t.left );
        
        else if( compareResult < 0 )
            t.right = insert1( x, t.right );// if x smaller than the node t, turn to right way of t.
        else
            ;  // Duplicate; do nothing
        return t;
}

    /**
    * Creates a new tree that is a mirror image of the original tree.
    * @return the new tree which is a mirror image of the original tree.
    */       
       
    public BinarySearchTree<AnyType> mirror() {
    	
	    BinarySearchTree<AnyType> tree = new BinarySearchTree<AnyType>();

        if (root==null)
    	    return tree;
        else
    	    
    	    return mirror(root, tree);

}
    
    /**
     * Internal method to create a new subtree that is a mirror image of the original subtree.
     * @param t the node that roots the original subtree.
     * @param tree the new tree will be returned.
     * @return the new subtree which is a mirror image of the original subtree.
     */     
    
// Using insert1 to insert nodes from the original tree. The way of inserting is different,
// smaller value will put in the right, it is the mirror of original tree.
    
    private BinarySearchTree<AnyType> mirror(BinaryNode<AnyType> t, BinarySearchTree<AnyType> tree){
	    
    	if(t!=null) {
	    tree.insert1(t.element);  
	    mirror(t.left, tree);
	    mirror(t.right, tree);
	
	    }
	    return tree;
		
}


///////////////////////////////////////////////(g)
    
    /**
    * Compare two trees.
    * @return true if the tree is a mirror of the passed tree.
    */       
    
    public boolean isMirror(BinarySearchTree<AnyType> Tree) {
	    return isMirror(root, Tree.root());
    }
    
    /**
     * Internal method to check if one subtree is a mirror of another subtree.
     * @param t1 the node that roots one subtree.
     * @param t2 the node that roots another subtree.
     * @return true if one subtree is a mirror of another subtree.
     */     
    
    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2) {
	    if (t1==null && t2==null)
		    return true; 
	    if (t1!=null && t2!=null) 
	        return (t1.element==t2.element && isMirror(t1.left,t2.right) && isMirror(t1.right, t2.left));
	    //if we check two nodes in this function, the location of their children should be exchanged.
	    //left=>right, right=>left. But the value of these two nodes must be the same.
	    else
		    return false;
	
}


///////////////////////////////////////////////(h)
    /**
    * Performs a single right rotation on the node having the passed value.
    */        
    
    public void rotateRight() {
	    root=rotateRight(root);
}
    /**
     * Internal method to Performs a single right rotation on the node having the passed value.
     * @param t the node that roots one subtree.
     * @return the new root of subtree
     */     
       
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t) {
    	//single right rotation: 
    	//the new root will be the left child of the original root.
	    BinaryNode<AnyType> t1=t.left;
	    if(t1!=null) { 
	        t.left=t1.right;// left child of original root will be the right child of new root
	        t1.right=t;     // after changing, right child of new root will be original root
	        return t1;      // return the new root t1
	        
	    }
	    else                // if original root does not have left child, print: it can not make right rotation
		    
	    	throw new NoSuchElementException("This tree can not make right rotaton.");
}


//////////////////////////////////////////////(i)
    /**
     * Performs a single left rotation on the node having the passed value.
     */     
    public void rotateLeft() {
        root=rotateLeft(root);
    }
    
    
    /**
     * Internal method to Performs a single left rotation on the node having the passed value.
     * @param t the node that roots one subtree.
     * @return the new root of subtree
     */    
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t) {
    	// single left rotation:
    	// new root will be the right child of the original root
    	
        BinaryNode<AnyType> t1=t.right;
        if(t1!=null) {
            t.right=t1.left; // right child of original root will be the left child of new root
            t1.left=t;       // after changing, left child of new root will be original root
            return t1;       // return the new root t1
        }
        else                 // if original root does not have right child,print: it can not make left rotation
        	throw new NoSuchElementException("This tree can not make left rotaton.");
	
}

///////////////////////////////////////////////(j)
    /**
     * Performs a level-by-level printing of the tree.
     */ 
    

    public void printLevels (){
	    int h = height(root);
	    int i;
	    for (i=0; i<=h; i++) {
	    	printLevels(root, i);
	        System.out.println();
	}
}

    /**
     * Internal method to Performs a level-by-level printing of the subtree.
     * @param t: the node that roots one subtree.
     * @param level: the level which the nodes in.
     */        

    //check the height of this tree, to know the levels we need to go through.
    //from the level 0 to the level equals height, we print nodes in different levels into different lines.
    //in the iteration, if the level not equals to 0, it means we need change to the next level 
    //and change the node to the children of it.
    //return the value until we got a null node.    
    
    private void printLevels (BinaryNode<AnyType> t, int level) {
	 
	    if (t==null)
	    	return;
	    if (level==0)
	    	System.out.print(t.element+" ");
	    else if (level>0) {
		
		    printLevels(t.left, level-1);
		    printLevels(t.right, level-1);
	}
}



//////////////////////////////////////////////////////////////////////////
//
//                           Test program
//
//////////////////////////////////////////////////////////////////////////



    public static void main( String [ ] args )
    {
    	BinarySearchTree<Integer> T = new BinarySearchTree<Integer>( );
    	BinarySearchTree<Integer> T1 = new BinarySearchTree<Integer>( );
    	BinarySearchTree<Integer> T2 = new BinarySearchTree<Integer>( );
    	BinarySearchTree<Integer> T3 = new BinarySearchTree<Integer>( );

    	
    	
//T:           80
//           /   \
//         40     90
//         / \    / \      
//        20  60 85 100  
//        /\  / \       
//      10 30 50 70 
//           /    \
//          45    75    	
    	
    	T.insert(80);
    	T.insert(90);
    	T.insert(40);
    	T.insert(20);
    	T.insert(60);
    	T.insert(85);
    	T.insert(100);
    	T.insert(30);
    	T.insert(70);
    	T.insert(50);
    	T.insert(10);
    	T.insert(45);
    	T.insert(75);
    	

         //T.printTree();
         
    	
    	
    	
    	
    	
         
         //(a)
         System.out.println("(a) nodeCount: ");
         System.out.println("The number of node in the binary tree is: "+T.nodeCount());
         
         
         
         
         
         
         
         //(b)
         System.out.println("(b) full tree: ");
             // check T is full tree or not
         System.out.println("Is this a full binary tree? "+T.isFull());
         
             // change T to be a full tree then check again
         T.remove(45);
    	 T.remove(75);
    	 
    	 //T:       80
         //       /   \
         //     40     90
         //     / \    / \      
         //    20  60 85 100  
         //    /\  / \       
         //  10 30 50 70 
    	 System.out.println("Is this a full binary tree? "+T.isFull());
    	 
         
    	 
    	 
    	 
    	 

     	 //(c)
    	 System.out.println("(c) sameStructure: ");
    	 
    	 //T1:      80
         //        /   \
         //      40     90
         //      / \    / \    
         //     20  60 85  128  
         //     /\  / \       
         //   10 30 50 70 
  	
 	
 	        T1.insert(80);
 	 	 	T1.insert(90);
 	 	 	T1.insert(40);
 	 	 	T1.insert(20);
 	        T1.insert(60);
 	        T1.insert(85);
 	        T1.insert(128);
 	        T1.insert(50);
 	        T1.insert(10);
 	        T1.insert(30);
 	        T1.insert(70);
   	    
 	        //create a tree T1 with same structure of T but one node.element is not the same.
         System.out.println("Do they have same structure? "+T.compareStructure(T1));

         
         
         
         
         
         
         //(d)
         System.out.println("(d) identical: ");
            // check T and T1 are identical or not
         System.out.println("Are they identical? "+T.equals(T1));
         T1.remove(128);
         T1.insert(100);
        
            // change the different node.element in T1 to be the same in T and check again
         System.out.println("Are they identical? "+T.equals(T1));
        
         
         
         
         
         
         //(e)
         System.out.println("(e) copy tree: ");
           //copy tree T to new tree T2 and print the element out
         T2=T.copy();
         T2.printLevels();
         
         
         
         
         
         
         //(f)
         System.out.println("(f) mirror tree: ");
          // create the mirror tree of T and named it to be T3, then print element of T3 out
         T3=T.mirror();
         T3.printLevels();
    	 //T3:      80
         //       /   \
         //     90     40
         //     / \    / \      
         //    100 85 60  20  
         //          / \  / \       
         //         70 50 30 10        
         
         
         
         
         
         
         
         
         //(g)
         System.out.println("(g) ismirror : ");
           // check if T3 is the mirror tree of T
         System.out.println("Are they a pair of mirror tree?" +T.isMirror(T3));
         
           // check another tree T2 is the mirror tree of T or not
         System.out.println("Are they a pair of mirror tree?" +T.isMirror(T2));
         
         
         
         
         
         
         
         //(h)
         System.out.println("(h) right rotation: ");
         //tree T before the right rotation:
    	 //T:       80
         //       /   \
         //     40     90
         //     / \    / \      
         //    20  60 85 100  
         //    /\  / \       
         //  10 30 50 70 
         
         //print the root of T before the right rotation
         System.out.println("Root of T before right rotation: "+T.root().element);
         System.out.println("Level-by-level print of tree T before rotation: ");
         T.printLevels();
         
         T.rotateRight();
         
         //print the root of T after the right rotation, the result should not the same as before.
         
         System.out.println("Root of T after right rotation: "+T.root().element);
         //tree T after the right rotation: 
    	 //T:      40
         //       /  \
         //      20   80
         //     / \   / \         
         //   10  30 60  90   
         //         / \  / \    
         //        50 70 85 100                 
         
         
         System.out.println("Level-by-level print of tree T after rotation: ");
         T.printLevels();
         
         
         
         
        
         
         //(i)
         System.out.println("(i) left rotation: ");
         //tree T2 before the left rotation:
    	 //T2:      80
         //       /   \
         //     40     90
         //     / \    / \      
         //    20  60 85 100  
         //    /\  / \       
         //  10 30 50 70 
         
       
         //print the root of T before the left rotation
         System.out.println("Root of T2 before left rotation: "+T2.root().element);
         System.out.println("Level-by-level of tree T2 before rotation: ");
         T2.printLevels();
         
         T2.rotateLeft();
         //print the root of T after the right rotation, the result should not the same as before.
        
         System.out.println("Root of T2 after left rotation: "+T2.root().element);
       
         //tree T2 after the right rotation:
    	 //T2:     90
         //       /  \
         //      80  100
         //      / \          
         //     40  85      
         //    /  \         
         //   20   60      
         //  / \   / \
         //10  30 50 70
         
         System.out.println("Level-by-level of tree T2 after rotation: ");
         T2.printLevels();
         
         
         
         
         
         //(j)
         System.out.println("(j) Level-by-Level printing: ");
         //performs a level-by-level printing of T
    	 //T:      40
         //       /  \
         //      20   80
         //     / \   / \         
         //   10  30 60  90   
         //         / \  / \    
         //        50 70 85 100    
         System.out.println("Level-by-level print of T:");
         T.printLevels();
         
         
        //performs a level-by-level printing of T2
    	 //T2:     90
         //       /  \
         //      80  100
         //      / \          
         //     40  85      
         //    /  \         
         //   20   60      
         //  / \   / \
         //10  30 50 70            
         System.out.println("Level-by-level print of T2:");
         T2.printLevels();

         
         
        
        
         
         
         
         
         
    }
}