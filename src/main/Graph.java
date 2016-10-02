package main;
/* Nondirected Graph
 * Includes BFS, DFS, shortest router based on BFS
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Graph {
	private Map<String, GraphNode> graph;// a graph using name of Nodes as key, and edges as value

	/*
	 * Construction Method 
	 * Create an empty Map without Nodes or edges
	 * 
	 */
	public Graph() {
		graph = new HashMap<String, GraphNode>();
	}

	/*
	 * toString 
	 * Override the 'toString' in class 'Object' return a String
	 * containing the detail of the Graph
	 */
	public String toString() {
		String str = "";
		if (graph.isEmpty())
			str = "Empty graph!";
		else {
			for (Entry<String, GraphNode> entry : graph.entrySet()) {
				str += entry.getValue().toString() + "\n";
			}
		}
		return str;
	}

	/*
	 * readMap Read the map from a file, which must follow the listed pattern:
	 * 1. Each line in the file represents a node and the edges linked to it. 
	 * 2. Words are separated by 'space'. 
	 * 3. First word of each line represents the name of the Node. 
	 * 4. Following words represents the edges. 
	 * eg. a b c d e
	 * It means 'a' is the current node and it is linked to four other nodes:'b' 'c' 'd' 'e'.
	 */
	public void readMap(String filePath) {
		File f = new File(filePath);
		String str;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

			while ((str = reader.readLine()) != null) {
				GraphNode node = new GraphNode();
				node.String2Node(str);
				graph.put(node.getName(), node);
			}
			reader.close();

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * removeAllMarks remove all the marks after searching the graph
	 */
	public void removeAllMarks() {
		if (!graph.isEmpty()) {
			for (Entry<String, GraphNode> entry : graph.entrySet()) {
				entry.getValue().unMark();
			}
		}
		return;
	}

	/*
	 * DFS(Depth First Search) Using the Stack to store the
	 **/
	public String DFS(String startNodeName) {
		String result = "";
		GraphNode current;
		Stack<GraphNode> tStack = new Stack<GraphNode>();

		if (graph.isEmpty())
			result = "Empty graph!";

		else if ((current = graph.get(startNodeName)) != null) {
			tStack.push(current);
			while (!tStack.isEmpty()) {
				current = tStack.pop();
				if (!current.isMarked()) {
					current.mark();
					result += current.getName() + "  ";
					Iterator i = current.getEdges().iterator();
					while (i.hasNext()) {
						tStack.push(graph.get(i.next().toString()));
					}
				}
			}
			tStack = null;
			removeAllMarks();
		}

		else
			result = "Can`t find the start node in graph!";
		return result;
	}

	/*
	 * BFS(Breadth First Search)
	 * 
	 **/
	public String BFS(String startNodeName) {
		String result = "";
		GraphNode current, next;
		Queue<GraphNode> tQueue = new LinkedList<GraphNode>();

		if (graph.isEmpty())
			result = "Empty graph!";

		else if ((current = graph.get(startNodeName)) != null) {
			current.mark();
			result += current.getName() + "  ";
			tQueue.add(current);

			while (!tQueue.isEmpty()) {
				current = tQueue.poll();
				Iterator i = current.getEdges().iterator();
				while (i.hasNext()) {
					next = graph.get(i.next().toString());
					if (!next.isMarked()) {
						next.mark();
						tQueue.add(next);
						result += next.getName() + "  ";
					}
				}
			}
			tQueue = null;
			removeAllMarks();
		}

		else
			result = "Can`t find the start node in graph!";
		return result;
	}

	public String ShortestPath_BFS(String startNodeName, String endNodeName) {

		String result = "";
		GraphNode current, next;
		Queue<GraphNode> tQueue = new LinkedList<GraphNode>();

		if (graph.isEmpty())
			result = "Empty graph!";

		else if ((current = graph.get(startNodeName)) != null) {
			current.mark();
			result += current.getName() + "  ";
			tQueue.add(current);

			while (!tQueue.isEmpty()) {
				current = tQueue.poll();
				Iterator i = current.getEdges().iterator();
				while (i.hasNext()) {
					next = graph.get(i.next().toString());
					if (next.getName().equals(endNodeName)) {
						next.setParent(current.getName()); // Node parent recording
						return path(next, startNodeName);
					}
					if (!next.isMarked()) {
						next.mark();
						next.setParent(current.getName()); // Node parent recording
						tQueue.add(next);
						result += next.getName() + "  ";
					}
				}
			}
			tQueue = null;
			removeAllMarks();
		}

		else
			result = "Can`t find the start node in graph!";
		return result;
	}

	private String path(GraphNode current, String startNodeName) {
		String str = "";
		Stack<String> route = new Stack<String>();
		route.push(current.getName());
		
		while (current.getParent() != null) {
			if (current.getParent() == startNodeName) {
				route.push(startNodeName);
				break;
			} else {
				current = graph.get(current.getParent());
				route.push(current.getName());
			}
		}
		
		while(!route.isEmpty()){
			str += route.pop();
			if(!route.isEmpty())str += "->";
		}

		return str;
	}
}
