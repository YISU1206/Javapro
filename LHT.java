

public class LHT<AnyType>
{
	/**
	 * Probing table implementation of hash tables.
	 * Helper class used to store a hash table entry (a key/value pair).
	 * @author Yisu Tian
	 */
    public LHT( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public LHT( int size )
    {
    	
    	
        allocateArray( size );
        makeEmpty( );
    }
   
    /**
     *  Put a key/value pair into the table by hashing the key to get the array
     *  index at which the search for an empty slot (probing) should start.
     *  
     *  If an existing element with the same key is present 
     *  then move to next key(key++) until an empty position been found. 
     *
     *  If the table is half full, rehash the table (tableSize*2).
     *  
     *@param  key:  key object reference
     *@param  val:  data object reference
     *@param  mark: mark the value is word (1) or just part of word (0)
     */
    public void insert(int key, Object element, int mark)
    {
      if (key>array.length)  
      	key%=array.length;
                        //ensure the key is less than the size of table
  	
      if ((array[key] == null))
      {
        array[key] = new HashEntry<AnyType>( key, element, mark,   true );
        currentSize++;
      }
      else
      { key++;          //if the position is not empty, remove to next key
        insert(key, element,mark);
      }
      
      if( currentSize > array.length / 2 ) 
          rehash( );    //check the number of items in the table, to make sure
                        //the table does not exceed half full.
      
    }
    
    /**
     * Expand the hash table.
     */
    public void rehash( )
    {
        HashEntry<AnyType> [ ] oldArray = array;

            // Create a new double-sized, empty table
        allocateArray( nextPrime( 2 * oldArray.length ) );
        currentSize = 0;

            // Copy table over
        for( int i = 0; i < oldArray.length; i++ )
            if( oldArray[ i ] != null && oldArray[ i ].isActive )
                insert( i, oldArray[ i ].element,oldArray[i].mark );
    }

    /**
     * Method that performs quadratic probing resolution.
     * Assumes table is at least half empty and table length is prime.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    public int findPos( Object x )
    {
    	for(int i=0;i<array.length;i++) {
			 if( array[ i ]!=null && array[ i ].element.equals(x) ){
				 return i;
		
			 }
			 
				 
			}	 
			return -1; 
	   
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     */
    public void remove( Object x )
    {
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
            array[ currentPos ].isActive = false;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains1( AnyType x )
    {
        int currentPos = findPos( x );
        return isActive( currentPos );
    }
    
    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return item's mark if exists , otherwise return -1.
     */
    
    public int contains( AnyType x )
    {
        int i = findPos( x );
        if (i==-1) 
            
		    return 2;
        else
        	return array[i].mark;
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    public boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }

    public int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }
    
    public static class HashEntry<AnyType>
    {
    	public int key;
        public int mark;
		public Object  element;   // the element
        public boolean isActive;  // false if marked deleted

        public HashEntry( Object e )
        {
            this( 0, e, 0, true );
        }

        public HashEntry( int k,  Object e,int m, boolean i )
        {   key=k;
            mark=m;
            element  = e;
            isActive = i;
        }
    }

    public static final int DEFAULT_TABLE_SIZE = 11;

    public HashEntry<AnyType> [ ] array; // The array of elements
    public int currentSize;              // The number of occupied cells

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
     @SuppressWarnings("unchecked")
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
     public static int nextPrime( int n )
    {
        if( n <= 0 )
            n = 3;

        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
     public static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }

    public int size() {
        return currentSize;
    }
    
    
    public void print( )
    {
        
        for( int i = 0; i < array.length; i++ ) {
      	  
        	System.out.print(i+" ");
        	if(array[i]==null)
            System.out.println("null");
        	else 
        		System.out.println(array[i].element+ " "  );
        	    
       	    
    }
        
    }
   
///////////////////////////////Test linearHashTable///////////////////////////////  


public static void main( String [ ] args )
{
LHT<String> H = new LHT<String>();
//Put items:
H.insert(0, "a",1);
H.insert(1, "b",1);
H.insert(5, "c",0);
//if "d" put in index 1, it should be stored in position 3
H.insert(1, "d",0);
//Print items:
System.out.println("The hash table is as follows:");
H.print();
System.out.println("There are "+ H.size()+" items in the hash table.");
H.print();
//Check items:
System.out.println("Does the table contains b? " + H.contains1("b"));
System.out.println("Does the table contains c? " + H.contains1("c"));
System.out.println("c is in the position " + H.findPos("c"));


}
}
















