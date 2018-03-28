import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

/**
 * Using javaFX to display the maze.
 * @author yisu
 */
public class Mazedisplay extends Application {
    @Override // Override the start method in the Application class
    
    /**
     * Generate a primary stage and set a scene in the stage
     * @param primary Stage which used to place the scene in it.
     */
    public void start(Stage primaryStage) {   
              // Create a scene and place it in the stage
        Scene scene = new Scene(new LinePane(), 500, 500);
        primaryStage.setTitle("Maze"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
  }
}

/**
 * Display the maze by transforming the wall sign created from Mazegenerate.java to 
 * specific line and show it using javaFX.
 * @author yisu
 */
class LinePane extends Pane {
    public LinePane() {
	   	         
        
        Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Enter the number of rows : ");
	    
	    int dimX = scanner.nextInt();
	     
	    System.out.println("Enter the number of columns: ");
	    
	    int dimY = scanner.nextInt();
	     
	    scanner.close();
	     
	                // ask user to enter the maze size
	    
	    
	    
	    
	    List<Integer> wall1 = new ArrayList<Integer>();
	    
	    List<Integer> wall0 = new ArrayList<Integer>();
	    
	    for (int i=0;i<dimX*dimY;i++) 
		    wall1.add(i,1);
	    
	    for (int j=0; j<dimX*dimY;j++) 
		    wall0.add(j,1);
	    
	                //create initial walls for all cells 
	    
	    
	    
        Mazegenerate kk=new Mazegenerate();
	    
	    DisjSets ds = new DisjSets( dimX*dimY );
	                //create a disjoint set to represent cells
	    kk.createMaze(dimX, dimY,ds,wall1,wall0);
	                //generate the maze
	    
	    for(int i=0;i<dimX*dimY;i++) {
	  
	        int [][] coord =kk.changecoord(dimX,dimY,i,wall1,wall0);
	                //change the wall sign to line coordinates
	        
	        Line line0 = new Line(coord[0][0]+20, coord[0][1]+20, coord[0][2]+20, coord[0][3]+20);
	                //generate the (North-South)line according to the specific coordinates
	                //and move the line to right in 20 units 
	        line0.setStrokeWidth(4);
	        getChildren().add(line0);
	        
	        Line line1 = new Line(coord[1][0]+20, coord[1][1]+20, coord[1][2]+20, coord[1][3]+20);
	                //generate the (west-east)line according to the specific coordinates
                    //and move the line to right in 20 units 
	        line1.setStrokeWidth(4);
	        getChildren().add(line1);
	 
	    }
	    
	    
	    
	    int k=Math.max(dimX, dimY);
		int lar=460/k;
	    Line line2 = new Line(20, 20+lar, 20, 20+lar*dimX);
	    line2.setStrokeWidth(4);
	    getChildren().add(line2);
	     
	    Line line3 = new Line(20+lar, 20, 20+lar*dimY, 20);
	    line3.setStrokeWidth(4);
	    getChildren().add(line3);
	                // create the left and top bound lines
	    
  }
}

