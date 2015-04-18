package src;

import java.io.*;
import java.util.*;

public class linkstate {
	public class Node {
		private int id;

		public Node(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	public class Edge {
		private Node[] ends;
		private int distance;

		public Edge(Node[] ends, int distance) {
			this.ends = ends;
			this.distance = distance;
		}

		public Node getDestination(Node source) {
			return ends[0];
		}

		public int getDistance() {
			return distance;
		}

		public boolean contains(Node n) {
			return (this.ends[0].getId() == n.getId())
					|| (this.ends[1].getId() == n.getId());
		}

		public boolean isEqual(Edge imposter) {
			if ((this.ends[0].getId() == imposter.ends[0].getId() && this.ends[1]
					.getId() == imposter.ends[1].getId())
					|| (this.ends[0].getId() == imposter.ends[1].getId() && this.ends[1]
							.getId() == imposter.ends[0].getId()))
				return true;
			return false;
		}
	}

	public class Graph {
		private ArrayList<Node> vertices;
		private ArrayList<Edge> paths;

		public Graph(int[][] map) {
			vertices = new ArrayList<Node>();
			// initialize and store n nodes, n = map rows
			for (int i = 1; i <= map.length; i++)
				vertices.add(new Node(i));

			paths = new ArrayList<Edge>();
			// initialize paths for all nodes
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					// only stores path if not just one node, when i=j
					if (i != j) {
						Node[] nodes = { vertices.get(i), vertices.get(j) };
						Edge temp = new Edge(nodes, map[i][j]);
						addPath(temp);
					}
				}
			}
		}

		// Performs the add-to-EdgeList operation
		public void addPath(Edge path) {
			//TODO: why aren't edges being added, ARGH
			/*if(paths.isEmpty())
				paths.add(path);		TODO:this is causing errors that I don't know about
			else{*/
				for (Edge e : paths) {
					if (!e.isEqual(path))
						paths.add(path);
				}
			//}
		}

		public ArrayList<Node> getVertices() {
			return vertices;
		}

		public ArrayList<Edge> getPaths() {
			return paths;
		}
		
		public int getDistance(Node n1, Node n2) {
			for(Edge e: paths)
			{
				if(e.contains(n1) && e.contains(n2))
				{
					return e.getDistance();
				}
			}
			
			return 4007;
		}
	}

	public void dijkstra(Graph map) {
		ArrayList<Node> vertices = map.getVertices();
		ArrayList<Edge> paths = map.getPaths();

		// contains last path detail from node 1 to target index
		int[] path = new int[vertices.size() - 1];
		// contains best distance for path i
		int[] distances = new int[vertices.size() - 1];
		// for designating paths that were already visited, #remember to +1
		// index to get node id
		int[] visited = new int[vertices.size() - 1];

		// initializes best distances for node 1
		int count = 0;
		for (Edge e : paths) {
			
			if (e.contains(vertices.get(0))) {
				distances[count] = e.getDistance();
				count++;
			}

			if (vertices.size() == count)
				break;
		}

		Node lastNode = null;
		// finds best total path for node 1 to current Node
		for(int step = 0; step < vertices.size(); step++){
			
			int best = 4007;
			
			//find best node to continue on
			for(int i = 0; i < distances.length; i++)
			{
				if(best > distances[i] && visited[i] != 1)
				{
					best = distances[i];
					lastNode = vertices.get(i + 1);
				}
			}
			
			System.out.print(step + "\t1");
			for(int i = 0; i < visited.length; i++)
			{
				if(visited[i] == 1)
					System.out.print("," + (i + 1) + " ");
			}
			
			visited[lastNode.getId() - 1] = 1;
			
			//finds cheapest path distances from current path to target i
			for(int i = 0; i < visited.length; i++)
			{
				if(best + map.getDistance(lastNode, vertices.get(i + 1)) < distances[i] && visited[i] != 1)
				{
					distances[i] = best + map.getDistance(lastNode, vertices.get(i + 1));
					path[i] = lastNode.getId();
				}
			}
			
			for(int i = 0; i < visited.length; i++)
				System.out.print("\t\t" + distances[i] + ", " + path[i]);
			System.out.println("\n---------------------------------------------------------------------------------------------");
		}
	}

	// working
	public static int[][] fileRead(String fileName) throws IOException {
		ArrayList<String> text = new ArrayList<String>();

		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String temp = in.readLine();

		while (temp.compareTo("EOF.") != 0) {
			text.add(temp);
			temp = in.readLine();
		}
		in.close();

		int max = text.size();
		int[][] map = new int[max][max];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (text.get(i).charAt(j * 2) == 'N')
					map[i][j] = 999;
				else
					map[i][j] = Integer.parseInt(text.get(i).charAt(j * 2) + "");
			}
		}

		return map;
	}

	public static void main(String[] args) throws IOException {

		int[][] map = fileRead(args[0]); // TODO: make sure it's args[0]
		linkstate program = new linkstate();

		Graph network = program.new Graph(map);
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.print("Step\tN\'");

		for (int i = 1; i < map.length; i++)
			System.out.print("\tD(" + (i + 1) + "),p(" + (i + 1) + ")");
		System.out.println("\n---------------------------------------------------------------------------------------------");

		program.dijkstra(network);
	}
}