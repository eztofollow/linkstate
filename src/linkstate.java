package src;

import java.io.*;
import java.util.*;


public class linkstate 
{
	public class Node
	{
		private int id;
		
		public Node(int id){ this.id = id; }
		public int getId(){ return id; }
	}
	
	public class Edge
	{
		private Node[] ends;
		private int distance;
		
		public Edge(Node[] ends, int distance)
		{
			this.ends = ends;
			this.distance = distance;
		}
		
		public Node getDestination(Node source)
		{
			return ends[0]; 
		}
		public int getDistance(){ return distance; }
		
		public boolean isEqual(Edge imposter)
		{
			if( (this.ends[0] == imposter.ends[0] && this.ends[1] == imposter.ends[1])
					|| (this.ends[0] == imposter.ends[1] && this.ends[1] == imposter.ends[0]))
				return true;
			return false;
		}
	}
	
	public class Graph
	{
		private ArrayList<Node> vertices;
		private ArrayList<Edge> paths;
		
		public Graph(int[][] map)
		{
			//declare and store n nodes, n = map rows
			for(int i = 1; i <= map.length; i++)
				vertices.add(new Node(i));
				
			for(int i = 0; i < map.length; i++)
			{
				for(int j = 0; j < map.length; j++)
				{
					Node[] nodes = {vertices.get(i), vertices.get(j)};
					Edge temp = new Edge(nodes, map[i][j]);
					addPath(temp);
				}
			}
		}
		
		//Performs the add-to-EdgeList operation 
		public void addPath(Edge path)
		{
			for(Edge e : paths)
			{
				if(e.isEqual(path))
					paths.add(path);
			}
		}
		
		public ArrayList<Node> getVertices()
		{
			return vertices;
		}
		
		public ArrayList<Edge> getPaths()
		{
			return paths;
		}
	}
	
	public static void dijkstra(Graph map)
	{
		ArrayList<Node> vertices = map.getVertices();
		ArrayList<Edge> paths = map.getPaths();
		int[] distances = new int[vertices.size()];
		ArrayList<Node> visited = new ArrayList<Node>();
		
		//initialize distances and visitation
		for(int i = 0; i < vertices.size(); i++)
		{
			distances[i] = 4007;
			visited.add(vertices.get(i));
		}
		
		for()
		
	}
	
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
		
		int[][] map = fileRead("network.txt"); // TODO: make sure it's args[0]
		linkstate program = new linkstate();
		
		linkstate.Graph network = program.new Graph(map);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.print("Step\tN\'");
		
		for(int i = 1; i < map.length; i++)
			System.out.print("\tD(" + i + "),p(" + i + ")");
		System.out.println("\n---------------------------------------------------------------------------------------------");
		
		dijkstra(network);
	}
}