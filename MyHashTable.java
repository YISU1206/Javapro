import java.util.NoSuchElementException;



public class MyHashTable {
	/**
	 * Probing table implementation of hash tables.
	 * Helper class used to store a hash table entry (a key/value pair).
	 * @author Yisu Tian
	 */
	
  private static class Entry
  {
   
    public int key;  //Key object
   
    public Object val;  //Data object
	
    public int mark;
   
    /**
     *  Constructor for an Entry object
     *@param  key   key object reference
     *@param  val   data object reference
     */
    public Entry(int key, Object val, int mark)
    {
      this.key = key;
      this.val = val;
      this.mark=mark;
      
    }
  }
  
  
  /**
   * Array of Entry references used as the hash table data structure.
   * By default all elements are initialized to null.
   */
  
  private Entry[] data;
 
  /**
   * tableSize: Number of entries allowed in table.
   */
  private int tableSize;
  
  /**
   * currentSize: Number of entries already in table.
   */
  
  private int currentSize; 
  
  /**
   * Default table size.
   */
   
  private static final int DEFAULT_TABLESIZE = 15;
  
  /**
   * Construct table with default array size.
   */
  
  public MyHashTable()
  {
    this(DEFAULT_TABLESIZE);
  }
  
  
  /**
   * Construct table with given array size. 
   * the number of items which can be entered in table has been fixed.
   * @param size:  size of table array
   */
  public MyHashTable(int size)
  {
    tableSize = size;
    data = new Entry[tableSize];
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
  
  public void put(int key, Object val, int mark)
  {
    if (key>data.length)  
    	key%=data.length;
                      //ensure the key is less than the size of table
	
    if ((data[key] == null))
    {
      data[key] = new Entry(key, val, mark);
      currentSize++;
    }
    else
    { key++;          //if the position is not empty, remove to next key
      put(key, val,mark);
    }
    
    if( currentSize > data.length / 2 ) 
        rehash( );    //check the number of items in the table, to make sure
                      //the table does not exceed half full.
    
  }
  
  /**
   *  Given a key object, return a data object from the table or
   *  null if it is not found.
   *@param  key:  key object reference
   *@return data object reference or null if no object found
   */
  
  public Object get(int key)
  {
   // int index = findEntry(key, false);
    if (data[key] != null)
    {
      return data[key].val;
    }
    else
    {
      return null;
    }
  }
  
  
  /**
   * Remove a key/value pair from table if present, otherwise make no change.
   *@param  key  key object reference
   */
  
  
  public void remove(int key)
  {
    
    if (data[key] != null)
    {
      data[key] = null;  
      currentSize--;
    }
  }
  

  
  /**
   *  Find an item in the hash table.
   *@param  item:  the item to search for 
   *@return 1 if item in the table.
   *@return 2 if item not in the table.
   *@return 0 if item in the table but it is not a word
   */
 
  public int contains( Object item ) {
	  for(int i=0;i<data.length;i++) {
		 if(data[ i] != null && data[ i ].val.equals( item ) && data[i].mark==1) {
			 return 1; //  words in the table
		 }
		 else if (data[ i] != null && data[ i ].val.equals( item ) && data[i].mark==0) {
			 return 0;  // not words but in the table
		 }
		}	 
		 
		return 2;  // not in the table
	  
  }
  
  /**
   *  Find the position of an item.  
   *@param  item:  the item to search for 
   *@return the index of the item if present, otherwise, return "NoSuchElementException"
   */
 
  
  private int findPos( Object item )
  {
	  for(int i=0;i<tableSize;i++) {
			 if(data[ i] != null && data[ i ].val==item) {
				 return i;
			 }
			}	 
			 
	  throw new NoSuchElementException("There is no" + item);
  }
  

  /**
   * Method to print the hash table.
   */
  

  public void print( )
  {
      
      for( int i = 0; i < data.length; i++ ) {
    	  
      	System.out.print(i+" ");
      	if(data[i]==null)
          System.out.println("null");
      	else 
      		System.out.println(data[i].val+ " "  );
     	    
  }
      
  }
  

  /**
   * Method to allocate array.
   * @param arraySize the size of the array.
   */
  
  private void allocateArray( int arraySize )
  {
	  
	    data = new Entry[arraySize];
  }

  /**
   * count the number of items in the hash table.
   * @return the number of items in the table
   */

   public int size() {
       return currentSize;
   }
  
  
   /**
    * Expand the hash table by tableSize*2.
    */
   
  private void rehash( )
  {
      Entry [] oldArray = data;
   
      allocateArray(2 * oldArray.length) ; 
                   // Create a new double-sized, empty table
      
      currentSize=0;
      
      for( int i = 0; i < oldArray.length; i++ )
          if( oldArray[ i ] != null )
              put(i, oldArray[ i ].val,oldArray[i].mark);  // copy the old table
  }
  
  
  
///////////////////////////////Test linearHashTable///////////////////////////////  
  
  
  public static void main( String [ ] args )
  {
	  MyHashTable H = new MyHashTable( );
//Put items:
  	  H.put(0, 'a',1);
      H.put(1, "b",1);
      H.put(5, "c",0);
      //if "d" put in index 1, it should be stored in position 3
      H.put(1, "d",0);
//Print items:
      System.out.println("The hash table is as follows:");
      H.print();
      System.out.println("There are "+ H.size()+" items in the hash table.");
//Delete items:
      H.remove(1);
      System.out.println("After removing one item, the table is as follows:");
      H.print();
//Check items:
      System.out.println("Does the table contains b? " + H.contains("b"));
      
      System.out.println("Does the table contains c? " + H.contains("c"));
      System.out.println("c is in the position " + H.findPos("c"));
   
      H.put(30, 'f',1);
      H.put(25, "g",0);
      H.put(5, "h",0);
      H.put(20, 'i',1);
      H.put(21, "o",0);
      H.put(27, "p",1);
      H.print();
 
    
}
}
 



