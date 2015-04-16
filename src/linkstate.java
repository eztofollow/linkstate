package src;

import java.io.*;
import java.util.ArrayList;


public class linkstate 
{
	//working
	public static int[][] fileRead(String fileName) throws IOException
	{
		ArrayList<String> text = new ArrayList<String>();
		
		BufferedReader in = new BufferedReader( new FileReader(fileName));
		String temp = in.readLine();
		
		while(temp.compareTo("EOF.") != 0)
		{
			text.add(temp);
			temp = in.readLine();
		}
		in.close();
		
		int max = text.size();
		int[][] map = new int[max][max];
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map.length; j++)
			{
				if(text.get(i).charAt(j*2) == 'N')
					map[i][j] = 999;
				else
					map[i][j] = Integer.parseInt(text.get(i).charAt(j*2) + "");
			}
		}
		
		return map;
	}
	
	public static void main(String[] args) throws IOException{
		
		int[][] matrix = fileRead("network.txt"); // TODO: make sure it's args[0]
		
		int[] distance = new int[matrix.length];
		int[] visited = new int[matrix.length];
		int[] preD = new int[matrix.length];
		int min;
		int nextNode = 0;
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.print("Step\tN\'");
		
		for(int i = 1; i < matrix.length; i++)
			System.out.print("\tD(" + i + "),p(" + i + ")");
		System.out.println("\n--------------------------------------------------------------------------");
		
		distance = matrix[0];	//set 
		distance[0] = 0;
		visited[0] = 1;
		
		//algorithm starts here
		
		for(int i = 0; i < 6; i++)
		{	
			min = 999;
			
			for(int j = 0; j < 6; j++){
				if(min > distance[j] && visited[j]!=1){
					
					min = distance[j];
					nextNode = j;
				}
			}
			
			visited[nextNode] = 1;
			
			// now we start the actual algorithm
			for(int c =0; c < 6; c++){
				if(visited[c]!=1){
					if(min+matrix[nextNode][c]<distance[c]){
						
						distance[c]=min+matrix[nextNode][c];
						preD[c] = nextNode;
					}	
				}	
			}
		}
		
		System.out.print("|");
		
		//for printing paths
		for(int i = 0; i < 6; i++){
			int j = i;
			System.out.println("\nPath = " + i);
			
			do{
				
				j = preD[j];
				System.out.print(" <- " + j); 
			}while(j != 0);
		}
		
	}

}