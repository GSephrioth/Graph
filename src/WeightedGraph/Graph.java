package WeightedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Undirected and Weighted Graph
 * Vertex contains: VertexName
 * Edges contains: Weight, Vertex
 * Created by cxz on 2016/10/2.
 */
public class Graph {
    protected HashMap<Vertex, List<Edge>> graph; // a graph using Vertex as key, and Edge as value
    /**
     * initialize an empty graph
     * Complexity: constant
     */
    public Graph() {
        graph = new HashMap<>();
    }
    /**
     * readMap Read the map from a file, which must follow the listed pattern:
     * 1. Each line in the file represents a node and the edges linked to it.
     * 2. Words are separated by 'space'.
     * 3. First word of each line represents the name of the Vertex.
     * 4. Following words represents the edges.
     * eg. a b,1 c,2 d,3 e,4
     * It means 'a' is the current node and it is linked to four other nodes:'b' 'c' 'd' 'e'.
     * And weight of each Edges are:1 2 3 4.
     *
     * Complexity: number of Edges (E)
     */
    public Graph(File f) {
        graph = new HashMap<>();
        List<String> temp = new LinkedList<>();   //Storing one line in File f, which represents a Vertex and Edges linked to it
        String str;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            while ((str = reader.readLine()) != null) {
                //separate the line with 'space'
                for (String part : str.split("\\s+")) {
                    temp.add(part);
                }

                if (!temp.isEmpty()) {
                    List<Edge> edgeList = new ArrayList<>();    //Storing Edges linked to the Vertex
                    Iterator i = temp.iterator();

                    if (i.hasNext()) {
                        Vertex v = new Vertex(i.next().toString());     //Storing the Vertex
                        while (i.hasNext()) {
                            String[] edge = i.next().toString().split("[,]");   //separate the line with 'comma'
                            int weight = Integer.valueOf(edge[1]);
                            Edge e = new Edge(v.getVertexName(), edge[0], weight); //Storing an Edge
                            edgeList.add(e);
                        }
                        graph.put(v, edgeList);      //add the Vertex and Edges to the Graph
                    }   // Do not clear edgeList!
                }
                temp.clear();   //clear the temp List contains one line in File f
            }
            reader.close(); //close the Buffered Reader

        } catch (Exception e) {
            e.printStackTrace();
        }
        temp.clear();
    }

    protected boolean addEdge(String startVertexName, String endVertexName, int weight) {
        Edge e = new Edge(startVertexName, endVertexName, weight);
        return addEdge(e);
    }

    /**
     * add an Edge to the Graph
     * Complexity: constant
     */
    protected boolean addEdge(Edge edge) {
        List<Edge> temp;
        Edge eReverse = edge.reverse();
        // if graph is empty, then add 2 Vertices, and add Edge e to each Vertex
        if (graph.isEmpty()) {
            temp = new LinkedList<>();
            temp.add(edge);
            graph.put(edge.getStartVertex(), temp);
            temp = new LinkedList<>();
            temp.add(eReverse);
            graph.put(edge.getEndVertex(), temp);
            return true;
        }
        if (graph.containsKey(edge.getStartVertex())) {
            if (graph.containsKey(edge.getEndVertex())) {
                // if graph have both Start and End Vertices, then add Edge e to each Vertex
                temp = graph.get(edge.getStartVertex());
                if (!temp.contains(edge))
                    temp.add(edge);
                temp = graph.get(edge.getEndVertex());
                if (!temp.contains(eReverse))
                    temp.add(eReverse);
            } else {
                // if graph have only Start Vertex, then add End Vertex, and add Edge e to each Vertex
                temp = graph.get(edge.getStartVertex());
                if (!temp.contains(edge)) temp.add(edge);
                temp = new LinkedList<>();
                temp.add(eReverse);
                graph.put(edge.getEndVertex(), temp);
            }
        } else if (graph.containsKey(edge.getEndVertex())) {
            // if graph have only End Vertex, then add Start Vertex, and add Edge e to each Vertex
            temp = graph.get(edge.getEndVertex());
            if (!temp.contains(eReverse)) temp.add(eReverse);
            temp = new LinkedList<>();
            temp.add(edge);
            graph.put(edge.getStartVertex(), temp);
        } else {
            // if graph don`t have Start or End Vertices, then add 2 Vertices, and add Edge e to each Vertex
            temp = new LinkedList<>();
            temp.add(edge);
            graph.put(edge.getStartVertex(), temp);
            temp = new LinkedList<>();
            temp.add(eReverse);
            graph.put(edge.getEndVertex(), temp);
        }
        return true;
    }

    /**
     * remove an Edge from the Graph
     * Complexity: V
     */
    private boolean removeEdge(Edge edge) {
        List<Edge> eL;
        if (!graph.containsKey(edge.getStartVertex()) && !graph.containsKey(edge.getEndVertex())) {
            return false;
        }
        eL = graph.get(edge.getStartVertex());
        for (Edge tempEdge : eL) {
            if (tempEdge.equals(edge))
                eL.remove(tempEdge);
        }
        eL = graph.get(edge.getEndVertex());
        for (Edge tempEdge : eL) {
            if (tempEdge.equals(edge.reverse()))
                eL.remove(tempEdge);
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        if (graph.isEmpty())
            str = "Empty graph!";
        else {
            for (Map.Entry<Vertex, List<Edge>> entry : graph.entrySet()) {
                str += entry.getKey().getVertexName() + entry.getValue().toString() + "\n";
            }
        }
        return str;
    }

    public Boolean isEmpty() {
        return graph.isEmpty();
    }

    /**
     * Judging whether the Graph is Connected by using DFS
     * return false when graph is empty
     * Complexity: V+E
     */
    public Boolean isConnected() {
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> unvisitedVertices;
        Vertex currentVertex;
        if (this.isEmpty()) return false;
        unvisitedVertices = new HashSet<>(graph.keySet());
        currentVertex = unvisitedVertices.iterator().next();

        stack.push(currentVertex);
        while (!stack.isEmpty()) {
            currentVertex = stack.pop();
            if (unvisitedVertices.contains(currentVertex)) {
                unvisitedVertices.remove(currentVertex);
                for (Edge e : graph.get(currentVertex))
                    if (unvisitedVertices.contains(e.getEndVertex()))
                        stack.push(e.getEndVertex());
            }
        }
        return unvisitedVertices.isEmpty();

    }

    /**
     * Judging whether the Graph has Circle by using DFS
     * return false when graph is empty
     * Complexity: V+E
     */
    public Boolean hasCircle() {
        Stack<Vertex> stack = new Stack<>();
        Set<Vertex> unvisitedVertices;
        Vertex currentVertex;

        if (this.isEmpty()) return false;
        unvisitedVertices = new HashSet<>(graph.keySet());

        // go through all the Vertices whether they are connected or not
        while (!unvisitedVertices.isEmpty()) {

            // start with a Vertex, put it in to the stack and set it`s PreVertex as null
            currentVertex = unvisitedVertices.iterator().next();
            currentVertex.setPreVertex(null);
            stack.push(currentVertex);

            // DFS Algorithm
            while (!stack.isEmpty()) {
                currentVertex = stack.pop();
                if (unvisitedVertices.contains(currentVertex)) {
                    unvisitedVertices.remove(currentVertex);
                    for (Edge e : graph.get(currentVertex)) {
                        Vertex nextVertex = e.getEndVertex();
                        /**
                         * Go through the Edges,
                         *  whose EndVertex is not PreVertex of the Current Vertex.
                         * If Current Vertex has an edge directed to a visited Vertex
                         *  than there is a circle, and return true.
                         * */
                        if (!nextVertex.equals(currentVertex.getPreVertex())) {
                            if (!unvisitedVertices.contains(nextVertex)) return true;
                            else {
                                nextVertex.setPreVertex(currentVertex);
                                stack.push(nextVertex);
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

    /**
     * Prim`s Algorithm for MST
     * Complexity: V * V * log(V)
     * */
    public List<Edge> PrimMST(String vertexName) throws Exception {
        Vertex v = new Vertex(vertexName);
        return PrimMST(v);
    }
    private List<Edge> PrimMST(Vertex startVertex) throws Exception {
        List<Edge> result = new LinkedList<>();     // store the MST
        MinHeap minHeap = new MinHeap();
        Edge currentEdge;
        Vertex currentVertex = startVertex;
        List<Edge> temp;   // temp store the Edge List of the Current Vertex
        HashSet<Vertex> unvisitedVertices = new HashSet<>(graph.keySet());     // use a set to traversal all Vertices
        if (unvisitedVertices.isEmpty() || !unvisitedVertices.contains(startVertex))
            throw new Exception("Can`t find the Start Vertex in Graph");
        // find and mark the start Vertex
        unvisitedVertices.remove(startVertex);
        /**
         * Complexity: V * V * log(V)
         * */
        while (!unvisitedVertices.isEmpty()) {
            // put the Edges linked to current Vertex to MinHeap
            temp = graph.get(currentVertex);

            if (temp.isEmpty()) break;
            /**
             * Complexity: V * log(V)
             * */
            for (Edge e : temp) {
                if (unvisitedVertices.contains(e.getEndVertex()))
                /**
                 * Replace the element having the same endVertex or add the element
                 * Complexity: log(number of Vertex: V )
                 */
                    minHeap.update(e);
            }

            // select the edge with minimum weight
            currentEdge = minHeap.popMin();
            currentVertex = currentEdge.getEndVertex();

            // add the selected edge to the MST
            result.add(currentEdge);
            // find and mark the current Vertex
            unvisitedVertices.remove(currentVertex);
        }
        return result;
    }

    /**
     * Dijkstra`s Algorithm for Problem:
     *      Shortest path from single Vertex to all Vertices
     * Complexity: E * log(V)
     */
    public Map<Vertex, Integer> Dijkstra(String vertexName) throws Exception {
        Vertex v = new Vertex(vertexName);
        return Dijkstra(v);
    }
    private Map<Vertex, Integer> Dijkstra(Vertex startVertex) throws Exception {
        Map<Vertex, Integer> result = new HashMap<>();
        MinHeap minHeap = new MinHeap();
        List<Edge> temp;   // temp store the Edge List of the Current Vertex
        if (graph.isEmpty() || !graph.containsKey(startVertex))
            throw new Exception("Can`t find the Start Vertex in Graph");
        Vertex currentVertex = startVertex;
        Edge currentEdge = new Edge(startVertex, startVertex, 0);

        result.put(startVertex, currentEdge.getWeight());
        /**
         * go through all the Edges to find the minimum Path
         * Complexity: E * log(V)
         * */
        while (result.size() < graph.size()) {

            temp = graph.get(currentVertex);
            if (temp == null || temp.isEmpty()) break;
            /**
             * Complexity: log(V) * number of Edges Linked to Current Vertex
             * */
            for (Edge e : temp) {
                if (!result.keySet().contains(e.getEndVertex())) {
                    // Replace the element having the same endVertex or add the element
                    Edge t = new Edge(currentEdge.getStartVertex(), e.getEndVertex(), currentEdge.getWeight() + e.getWeight());
                    /**
                     * Replace the element having the same endVertex or add the element
                     * Complexity: log(V-1)
                     */
                    minHeap.update(t);
                }
            }

            // select the edge with minimum weight
            currentEdge = minHeap.popMin();
            currentVertex = currentEdge.getEndVertex();

            // add the selected edge to the MST
            result.put(currentVertex, currentEdge.getWeight());
        }
        return result;
    }

    /**
     * Kruskal Algorithm for MST
     * Complexity: E * (V+E)
     */
    public Graph Kruskal() throws Exception {
        Graph result = new Graph();
        MinHeap minHeap = new MinHeap();
        Edge currentEdge;
        Set<Vertex> unvisitedVertices = new HashSet<>(graph.keySet());
        if (unvisitedVertices.isEmpty())
            throw new Exception("Can`t find the Start Vertex in Graph");
        /**
         * inserted all edges to the Minimum Heap
         * ignore the "coming back" edge, as for Undirected Graph
         * Complexity: E
         * */
        for (Map.Entry<Vertex, List<Edge>> graphEntry : graph.entrySet()) {
            for (Edge e : graphEntry.getValue()) {
                if (minHeap.find(e.getEndVertex(), e.getStartVertex()) == null)
                    minHeap.insert(e);
            }
        }
        // pop the minimum Edge from the heap
        currentEdge = minHeap.popMin();

        /**
         * add the current Vertex to the Graph "result"
         * make sure "result" has no circle
         * Complexity: V*(V+E)
         * */
        while (currentEdge != null) {
            result.addEdge(currentEdge);
            if (result.hasCircle()) {
                result.removeEdge(currentEdge);
            }
            currentEdge = minHeap.popMin();
        }
        return result;
    }
}
