import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generate a Maze using Disjoint set. It will be used in Mazedisplay.java.
 * @author yisu
 */
public class Mazegenerate{
	
	
	/**
     * randomly pick one cell1 and its wall(wall below cell1:wall0 
     * or wall in the right of cell1: wall1) in the maze, 
     * get the cell2 next to the wall. Check cell1 and cell2,
     * if they do not have same root, break the wall (change sign 1-->0).
     * @param dimX the row number in Maze.
     * @param dimY the column number in Maze.
     * @param ds the disjoint sets
     * @param wall1 wall between each element in the maze (direction: North-South "|")
     * @param wall0 wall between each element in the maze (direction: West-East "！！")
     */
    public void checkremove(int dimX, int dimY,DisjSets ds
    		      ,List<Integer>wall1,List<Integer>wall0) {
	 
	    Random generator = new Random();
	    int num=generator.nextInt(dimX*dimY);  // pick one cell randomly
	    int k=generator.nextInt(2);      //randomly choose one wall which belongs to the cell
	                                     //       (below the cell or on the right of the cell)
	    //            wall1
	    //              |
	    //        ！！！！！ v ！！！！！！
	    //          num |num+1
	    // wall0->！！！！！！|！！！！！！！
	    //      num+dimY|
	    //        ！！！！！！！！！！！！！！          
	    
	    if (k==1 && (num+1)%dimY!=0){   // to make sure there exists one cell in the right
		    if(ds.find(num)!=ds.find(num+1)) { // check do they have same root
		    	ds.union(ds.find(num),ds.find(num+1));//union the elements
		        wall1.remove(num); 
		        wall1.add(num, 0);
		                   // change the wall sign from 1 (wall exists) to 0 (wall not exists)
		
		  }    
		    else 
			    checkremove(dimX, dimY,ds,wall1,wall0);
		                   // if there is no cell in the right, do checkremove again
	    }
	    
	    if (k==0 && num<(dimX-1)*dimY) {
	    	               // to make sure there exists one cell under the cell we chosed
		    if(ds.find(num)!=ds.find(num+dimY)) { 
			    ds.union(ds.find(num),ds.find(num+dimY));
			    wall0.remove(num);
		        wall0.add(num, 0);
		
	      }
		    else 
			    checkremove(dimX, dimY,ds,wall1,wall0);
	    }
	  
	  
   }
  
    
    
    
    
    /**
     * To check if all elements in the disjoint sets have same root,
     * otherwise continue to do checkremove.
     * @param dimX the row number in Maze.
     * @param dimY the column number in Maze.
     * @param ds the disjoint sets
     * @param wall1 wall between each element in the maze (direction: North-South)
     * @param wall0 wall between each element in the maze (direction: West-East)
     */
    
    public void createMaze(int dimX, int dimY,DisjSets ds
    		        ,List<Integer>wall1,List<Integer>wall0) {
    	
	    if (ds.check(dimX*dimY)==false) {
		    checkremove(dimX, dimY,ds,wall1,wall0);
		    createMaze(dimX, dimY,ds,wall1,wall0);
		  
	    }
	  
	    else {
		    wall1.remove(dimX*dimY-1);
		    wall1.add(dimX*dimY-1,0);
		    wall0.remove(dimX*dimY-1);
		    wall0.add(dimX*dimY-1,0);
		    // Open the botten-right cell
	    }
	  
    }
    
    
    
  
    /**
     * change the wall sign to specific coordinate which will be used in Maze display.
     * @param dimX the row number in Maze.
     * @param dimY the column number in Maze.
     * @param num one cell in the maze.
     * @param wall1 wall between each element in the maze (direction: North-South).
     * @param wall0 wall between each element in the maze (direction: West-East).
     * @return 2-D array which contains the coordinates of two walls which belongs to one cell
     */
  
   public int[][] changecoord(int dimX, int dimY,int num
		                , List<Integer>wall1,List<Integer>wall0) {
       	                  
	   int ele1=wall1.get(num);   //get the wall sign of one cell
	   int ele0=wall0.get(num);
       int k=Math.max(dimX, dimY);
	   int lar=460/k;        // seprate Stage(used to display maze) into k parts
	   int x=num/dimY +1;    // transform the cell num to specific row x, column y. (x,y)
	   int y=num%dimY +1;
	   int [][] coord = {{y,x-1,y,x},{y-1,x,y,x}};
	                        
	   for (int i=0;i<4;i++) {
		   coord[0][i]*=lar*ele1;
	   }
	   for (int i=0;i<4;i++) {
	 	  coord[1][i]*=lar*ele0;
	   }
	                  // create the wall coordinates according to wall sign and maze size
	                       
	   return coord;
    }
  
 
/////////////////////////////////////////Test///////////////////////////////////////// 
  
    public static void main(String[] args) {
        
	    int dimX=25, dimY=25;  
	           // create 25x25 maze
	    List<Integer> wall1 = new ArrayList<Integer>();
	    List<Integer> wall0 = new ArrayList<Integer>();
	    for (int i=0;i<dimX*dimY;i++) 
	        wall1.add(i,1);
	    for (int j=0; j<dimX*dimY;j++) 
		    wall0.add(j,1);
	           //create initial walls for all cells 
	    
	    DisjSets ds = new DisjSets( dimX*dimY );
	           // create a disjoint set represent cells
	    
	    Mazegenerate kk=new Mazegenerate();
	    kk.createMaze(dimX, dimY,ds,wall1,wall0);
	           //create Maze by destory the walls
	  
  }

}