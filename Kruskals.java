import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;


/**
 * Kruskal's algorithm is a minimum-spanning-tree algorithm 
 * which finds an edge of the least possible weight that connects 
 * any two trees in the forest using priority queue, Disjoint Set.
 */
public class Kruskals{
	
	/**
     * choose two vertices with least edge and check if they are not in same set then 
     * connect them by union the roots of these two vertices.
     * @param pq PriorityQueue which is used to pick the minimum value of edges
     * @param weight Arraylist which is used to find the weight of corresponding edges
     * @param vertexpairs ArrayList which contains vertexpairs
     * @param VERTEX Arraylist which is used to find the corresponding vertices of edges
     */
	
		public static void kruskal(PriorityQueue<Integer> pq,ArrayList<Integer> weight,
				  ArrayList<ArrayList<String>> vertexpairs,ArrayList<String> VERTEX) 
		{
			int edgeAccepted=0;  
			                    // count the edges we linked
			int Distance=0;     
			                    // count the overall distance we linked
			
			DisjSets ds = new DisjSets( VERTEX.size());
			
			while (edgeAccepted<VERTEX.size()-1) {
				
				int edge=pq.remove(); 
				                      // find the minimum edges
				int ind=weight.indexOf(edge); 
				                      //find the corresponding index of edges
				weight.set(ind, null); 
				                      // reset the initial value not to be the weight
				String V0=vertexpairs.get(ind).get(0); 
				                      // using the index to find the vertices
				String V1=vertexpairs.get(ind).get(1);
				
				int u=VERTEX.indexOf(V0); 
				                      // get the corresponding index of vertices stored in the 
				                                                      //disjoint set
				int v=VERTEX.indexOf(V1);
				
				if (ds.find(u)!=ds.find(v)) {
					                  // if the vertices do not have same root which means 
					                  // a circle will not be created after connecting
					edgeAccepted++;
					ds.union(ds.find(u), ds.find(v)); // union the root of vertices
					Distance+=edge;
					System.out.println(V0 + " ！！！！> "+V1+ " ("+edge+")");
					
				}
			}
			System.out.println();
			System.out.println("The overall distance is " + Distance);
			
		}
		
		
		public static void main(String[] args) {
			
			        String csvFile = "D:assn9_data.csv"; //the route of file
			        String line = "";
			        String cvsSplitBy = ",";

			        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			        	
	                ArrayList<String> CITY=new ArrayList<String> ();
	                             // change the city name to integer which will used in disjoint set
	                ArrayList<ArrayList<String>> citypairs = new ArrayList<ArrayList<String>>();
	                             // arraylist of (city1,city2)
	                ArrayList<Integer> weight=new ArrayList<Integer> ();
	                             // arraylist contains the distances between cities
	        		PriorityQueue<Integer> pq =  new PriorityQueue<Integer>();
	        		             // priority queue of distances between cities which used to find the
	        		             // minimum distance and corresponding city pairs
	                    while ((line = br.readLine()) != null) {
			  
			                String[] citys = line.split(cvsSplitBy);
	                       
			                CITY.add(citys[0]);
			                     // add all cities in CITY
			                
			        		
			                for (int i=1;i<citys.length-1;i=i+2) {
			                	
			                	ArrayList<String> in=new ArrayList<String>();
			                	 // create city pairs and shore them into arraylist:citypairs
			                	in.add(citys[0]);
			        		    in.add(citys[i]);
			        		    citypairs.add(in);
			        		    
			        		    weight.add(Integer.parseInt(citys[i+1]));
			        		     // store the distances between cities into arraylist:weight
			        		    pq.add(Integer.parseInt(citys[i+1]));
			        		     // also store them into priority queue
			                }
			               
			                
			            }
			            
			      
			         kruskal( pq, weight,citypairs,CITY);       
			            
			
			        } catch (IOException e) {
			            e.printStackTrace();
			        }

			    }

			
		
	}


