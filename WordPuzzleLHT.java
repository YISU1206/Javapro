
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class WordPuzzleLHT {
	
	/**
     * Create a random character. 
     * @return the random character
     */
     public static char Char(){
	     final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	     final int N = alphabet.length();

	     Random r = new Random(); 
	     return alphabet.charAt(r.nextInt(N));
	    
 }
 
     /**
      * Construct WordPuzzle with given the value of row and column. 
      * @param ROW:  value of row in the Puzzle
      * @param COL:  value of column in the Puzzle
      * @return a ROW*COL WordPuzzle Matrix contains random characters
      */
     
    public static char[][] MakePuzzle(int ROW, int COL) {
	
		 char[][] matrix= new char[ROW][COL];
		 
		 
		 
		 for (int r=0;r<ROW;r++) {
			 for (int c=0;c<COL;c++) {
				 char val=Char();
				 matrix[r][c]=val;
			 }
		 }
		 
		 for (int r=0;r<ROW;r++) {
			 for (int c=0;c<COL;c++) {
		        System.out.print(matrix[r][c]+" ");
		 
			 }
			 
			 
			 System.out.println(" ");
			 }
		 
		 return matrix;
	 }
 
//////////////////////////////////////////////////////////////////////////////
//
//                           Original Way
//
//////////////////////////////////////////////////////////////////////////////
 
    /**
     * Find all words in the WordPuzzle without prefix. 
     * print all words and number of words in the puzzle.
     * @param ROW:  value of row in the Puzzle
     * @param COL:  value of column in the Puzzle
     * @param tar:  the WordPuzzle matrix contains random characters
     * @param LHT:  the linear probing hash table contains dictionary words
     */

    
    public static int wordPuzzle1(char[][] tar,LHT<String> LHT,int ROW, int COL){
    	   
    		    int num = 0;

    	        for (int i=0; i<ROW;i++) {
    			
    			    for (int q=0; q<COL;q++) {
    				    StringBuilder word = new StringBuilder();
    				    for (int j=q; j<COL;j++) {
    				    	word.append(tar[i][j]);
    					    if (LHT.contains(word.toString())==1) {
    					    	System.out.println(word.toString());
    					        num++;}
    				}
    			}
    			
    			
    		}
    		
    	    	// combination order from North TO South
    	    	
    	    	
    		    for (int i=0; i<COL;i++) {
    			
    			    for (int q=0; q<ROW;q++) {
    				    StringBuilder word = new StringBuilder();
    				    for (int j=q;j<ROW;j++ ) {
    				    	word.append(tar[j][i]);
    				    	if (LHT.contains(word.toString())==1) {
    					    	System.out.println(word.toString());
    				    	    num++;}
    				}
    				
    				
    			}
    			
    		}
    		
    		 // combination order from East TO West
    			
    	    	for (int i=0; i<ROW;i++) {
    					
    				for (int q=COL-1; q>=0;q--) {
    					StringBuilder word = new StringBuilder();
    					for (int j=q;j>=0;j--) {
    					    word.append(tar[i][j]);
    					    if (LHT.contains(word.toString())==1) {
    					    	System.out.println(word.toString());
    						    num++;}
    						
    					}
    					
    				}
    			
    			
    			}
    				// combination order from South TO North
    				
    		    for (int i=0; i<COL;i++) {
    					
    					
    				for (int q=ROW-1; q>=0;q--) {
    					StringBuilder word = new StringBuilder();
    					for (int j=q;j>=0;j--) {
    						word.append(tar[j][i]);
    						if (LHT.contains(word.toString())==1) {
    					    	System.out.println(word.toString());
    						    num++;}
    					}
    					
    				}
    			
    			}
    	 
    				//Diagonal words
    				
    		    if (COL==ROW) {
    				
    				//  combination order from North-west to South-east  
    			    for (int i=0; i<COL;i++) {
    			  	    StringBuilder word1 = new StringBuilder();
    			    	    for (int j=i;j<COL;j++) {
    				            word1.append(tar[j][j]);
    				            if (LHT.contains(word1.toString())==1) {
    						    	System.out.println(word1.toString());
    				                num++;}
    			  }}
    			    //  combination order from South-east to North-west
    			    for (int i=COL-1;i>=0; i--) {
    				    StringBuilder word2 = new StringBuilder();
    			            for (int j=i; j>=0;j--) {
    				            word2.append(tar[j][j]);
    				            if (LHT.contains(word2.toString())==1) {
    						    	System.out.println(word2.toString());
    				                num++;}
    				
    		    }}
    			
    			    //  combination order from North-east to South-west 
    			    
    			    for (int i=0; i<COL;i++) {
    			        StringBuilder word3 = new StringBuilder();
    			    	    for (int j=i;j<COL;j++) {
    				            word3.append(tar[j][COL-1-j]);
    				            if (LHT.contains(word3.toString())==1) {
    						    	System.out.println(word3.toString());
    				                num++;}
    			}}
    			    //  combination order from North-west to South-east 
    			        
    			    for (int i=COL-1;i>=0;i--) {
    			        StringBuilder word4 = new StringBuilder();
    			            for (int j=i; j>=0;j--) {
    				            word4.append(tar[j][COL-1-j]);
    				            if (LHT.contains(word4.toString())==1) {
    						    	System.out.println(word4.toString());
    				                num++;}
    				
    		    }}
    		}	    	
    				
    						
    				
    		return num;
    		
    	} 
    	    
    	    

//////////////////////////////////////////////////////////////////////////////
//    	    
//                                Prefix
//   
//////////////////////////////////////////////////////////////////////////////
    
    /**
     * Find all words in the WordPuzzle using prefix. 
     * print all words and number of words in the puzzle.
     * @param ROW:  value of row in the Puzzle
     * @param COL:  value of column in the Puzzle
     * @param tar:  the WordPuzzle matrix contains random characters
     * @param LHT:  the linear probing hash table contains dictionary words
     */
    
    public static int wordPuzzle2(char[][] tar,LHT<String> LHT,int ROW, int COL){


	    int num = 0;

	
    // combination order from West TO East
	
        for (int i=0; i<ROW;i++) {
		
		    for (int q=0; q<COL;q++) {
		    	
			    StringBuilder word = new StringBuilder();
			    
			    innerloop:
			    for (int j=q; j<COL;j++) {
			    	word.append(tar[i][j]);
			    	
			    	if (LHT.contains(word.toString())==0)
			    		continue; 
			    
			    	else if (LHT.contains(word.toString())==1) {
				    	System.out.println(word.toString());
				        num++;}
				    else
				    	break innerloop;
				    
			}
		}
		
		
	}
	
	// combination order from North TO South
    	
    	
	    for (int i=0; i<COL;i++) {
		
		    for (int q=0; q<ROW;q++) {
		    	
			    StringBuilder word = new StringBuilder(); 
			    
			    innerloop:
			    for (int j=q;j<ROW;j++ ) {
			    	word.append(tar[j][i]);
			    	
			    	if (LHT.contains(word.toString())==0)
			    		continue; 

			    	else if (LHT.contains(word.toString())==1) {
				    	System.out.println(word.toString());
				        num++;}
				    else
				    	break innerloop;
			}
			
			
		}
		
	}
	 // combination order from East TO West
		
			for (int i=0; i<ROW;i++) {
				
				for (int q=COL-1; q>=0;q--) {
					
					StringBuilder word = new StringBuilder();
					
					innerloop:
					for (int j=q;j>=0;j--) {
					word.append(tar[i][j]);
					
					if (LHT.contains(word.toString())==0)
			    		continue; 
			    
			    	else if (LHT.contains(word.toString())==1) {
				    	System.out.println(word.toString());
				        num++;}
				    else
				    	break innerloop;
					
				}
				
			}
		
		
		}

			// combination order from South TO North
			
			for (int i=0; i<COL;i++) {
				
				for (int q=ROW-1; q>=0;q--) {
					
					StringBuilder word = new StringBuilder();
					
					innerloop:
					for (int j=q;j>=0;j--) {
					word.append(tar[j][i]);
					
					if (LHT.contains(word.toString())==0)
			    		continue; 
			    
			    	else if (LHT.contains(word.toString())==1) {
				    	System.out.println(word.toString());
				        num++;}
				    else
				    	break innerloop;
				}
				
			}
		
		}

			//Diagonal words
			
		    if (COL==ROW){
			
			//  combination order from North-west to South-east  
		        for (int i=0; i<COL;i++) {
		    	    StringBuilder word1 = new StringBuilder();
		    	    innerloop:
		    	    for (int j=i;j<COL;j++) {
			            word1.append(tar[j][j]);
			            if (LHT.contains(word1.toString())==0)
				    		continue; 
				    	else if (LHT.contains(word1.toString())==1) {
					    	System.out.println(word1.toString());
					        num++;}
					    else
					    	break innerloop;
		  }}
		    //  combination order from South-east to North-west
		        for (int i=COL-1;i>=0; i--) {
			         StringBuilder word2 = new StringBuilder();
			         innerloop:
		             for (int j=i; j>=0;j--) {
			            word2.append(tar[j][j]);
			            if (LHT.contains(word2.toString())==0)
				    		continue;
				    	else if (LHT.contains(word2.toString())==1) {
					    	System.out.println(word2.toString());
					        num++;}
					    else
					    	break innerloop;
			
	    }}
		
		    //  combination order from North-east to South-west 
		    
		        for (int i=0; i<COL;i++) {
		    	    StringBuilder word3 = new StringBuilder();
		    	    innerloop:
		    	    for (int j=i;j<COL;j++) {
			            word3.append(tar[j][COL-1-j]);
			            if (LHT.contains(word3.toString())==0)
				    		continue; 
				    	else if (LHT.contains(word3.toString())==1) {
					    	System.out.println(word3.toString());
					        num++;}
					    else
					    	break innerloop;
		}}
		        
		    //  combination order from North-west to South-east 
		        
		        for (int i=COL-1;i>=0;i--) {
		            StringBuilder word4 = new StringBuilder();
		            innerloop:
		            for (int j=i; j>=0;j--) {
			            word4.append(tar[j][COL-1-j]);
			            if (LHT.contains(word4.toString())==0)
				    		continue; 
				    	else if (LHT.contains(word4.toString())==1) {
					    	System.out.println(word4.toString());
					        num++;}
					    else
					    	break innerloop;
			
	    }}
	}
		    
	return num;

} 


    
    
//////////////////////////////////////////////////////////////////////////////
//
//                            Test WordPuzzle
//
//////////////////////////////////////////////////////////////////////////////
    private static final String FILENAME = "D:dictionary.txt";
    
    public static void main(String[] args) {
	 
     
     
     Scanner scanner = new Scanner(System.in);
     
     System.out.print("Enter the row number: ");
     
     int row = scanner.nextInt();
     
     System.out.print("Enter the col number: ");
     
     int col = scanner.nextInt();
     
     scanner.close();
     

	 //Read the dictionary into linear probing hash table
     
	 try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

		 String words;
         int j =0;
         LHT<String> LHT=new LHT<String>();
         
			while ((words = br.readLine()) != null) {
				
		        // put dictionary words into hash table LHT
				StringBuilder word = new StringBuilder();
				
   				for (int i=0;i<words.length();i++) {
   					word.append(words.toCharArray()[i]);
   					if (word.length()<words.length()) 
   					    LHT.insert(j, word.toString(), 0);   
   					else 
   						LHT.insert(j,  word.toString(), 1);
   				    j++;
   				
   				}
   				
				
			}
			
		
		System.out.println("The wordPuzzle contains characters as follows:");
// create random WordPuzzle
		char[][] tar=MakePuzzle(row,col );
		
//////////////////////////////////////////////////////////////////////////////
// No Prefix		
        System.out.println("Way One: without prefix: ");		
// set timer
        
		long tStart = System.currentTimeMillis();

// 	WordPuzzle solution without prefix	
		int num1=wordPuzzle1(tar,LHT, row,col);  //number 1

		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		
		
///////////////////
// Prefix
		System.out.println("Way Two: using prefix: ");
// set timer		
		
		long tStart1 = System.currentTimeMillis();

// WordPuzzle solution using prefix	
		int num2=wordPuzzle2(tar,LHT, row,col);  //number 2

		long tEnd1 = System.currentTimeMillis();
		long tDelta1 = tEnd1 - tStart1;
		double elapsedSeconds1 = tDelta1 / 1000.0;
		
//////////////////////////////Out Put////////////////////////////////////////		
		System.out.println("(No Prefix)The number of words is "+ num1);
		
		System.out.println("(No Prefix)The running time is "+ elapsedSeconds + " seconds");

		System.out.println("(Prefix)The number of words is "+ num2);
		
		System.out.println("(Prefix)The running time is "+ elapsedSeconds1 + " seconds");
        
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	 
	 
	 
	 
}