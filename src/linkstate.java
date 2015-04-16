package src;

import java.io.*;
import java.util.*;


public class linkstate 
{
	public class Graph
	{
		private List<Node> vertices;
		private List<Edge> paths;
		
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
		}
		
		public Graph(int[][] map)
		{
			//declare and store n nodes, n = map rows
			for(int i = 1; i <= map.length; i++)
				vertices.add(new Node(i));
				
			for(int i = 0; i < map.length; i++)
			{
				for(int j = 0; j < map.length; j++)
				{
					add();
					Node[] nodes = {vertices.get(i), vertices.get(j)};
					paths.add(new Edge(nodes, map[i][j]));
				}
			}
			
		}
		
		public void add()
		{
			
		}
		
		public List<Node> getVertices()
		{
			return vertices;
		}
		
		public List<Edge> getPaths()
		{
			return paths;
		}
	}
	
	public static void dijkstra(Graph map)
	{
		private final List<Vertex> nodes;
		private final List<Edge> edges;
		private Set<Vertex> settledNodes;
		private Set<Vertex> unSettledNodes;
		private Map<Vertex, Vertex> predecessors;
		private Map<Vertex, Integer> distance;
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
		Graph network = new Graph(map);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.print("Step\tN\'");
		
		for(int i = 1; i < map.length; i++)
			System.out.print("\tD(" + i + "),p(" + i + ")");
		System.out.println("\n---------------------------------------------------------------------------------------------");
		
		dijkstra(network);
		
	}

}