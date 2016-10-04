package WeightedGraph;

import java.io.*;
import java.util.*;

/**
 * Undirected and Weighted Graph
 * Vertex contains: VertexName
 * Edges contains: Weight, Vertex
 * Created by cxz on 2016/10/2.
 */
public class Graph {
    private HashMap<Vertex, List<Edge>> graph; // a graph using Vertex as key, and Edge as value
    /**
     * initialize an empty graph
     */
    public Graph() {
        graph = new HashMap<Vertex, List<Edge>>();
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
     */
    public Graph(File f) {
        graph = new HashMap<Vertex, List<Edge>>();
        List<String> temp = new LinkedList<String>();   //Storing one line in File f, which represents a Vertex and Edges linked to it
        String str;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            while ((str = reader.readLine()) != null) {
                //separate the line with 'space'
                for (String part : str.split("\\s+")) {
                    temp.add(part);
                }

                if (!temp.isEmpty()) {
                    List<Edge> edgeList = new ArrayList<Edge>();    //Storing Edges linked to the Vertex
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        temp.clear();
    }

    public int getVertexNumber() {
        return graph.size();
    }

    public void add(Vertex v, List<Edge> e) {
        graph.put(v, e);
    }

    @Override
    public String toString() {
        String str = "";
        if (graph.isEmpty())
            str = "Empty graph!";
        else {
            for (Map.Entry<Vertex, List<Edge>> entry : graph.entrySet()) {
                str += entry.getKey() + entry.getValue().toString() + "\n";
            }
        }
        return str;
    }

    /**
     *
     * */
    public List<Edge> PrimMST(Vertex startVertex) {
        List<Edge> result = new LinkedList<>();     // store the MST
        MinHeap minHeap = new MinHeap();
        Edge currentEdge;
        Vertex currentVertex = startVertex;
        List<Edge> temp;   // temp store the Edge List of the Current Vertex
        HashSet<Vertex> vertexSet = new HashSet<>(graph.keySet());     // use a set to traversal all Vertices

        // find and mark the start Vertex
        vertexSet.remove(startVertex);
        while (!vertexSet.isEmpty()) {
            // put the Edges linked to current Vertex to MinHeap
            temp = graph.get(currentVertex);
            if (temp.isEmpty()) break;
            for (Edge e : temp) {
                if (vertexSet.contains(e.getEndVertex()))
                    // Replace the element having the same endVertex or add the element
                    minHeap.update(e);
            }
            temp.clear();

            // select the edge with minimum weight
            currentEdge = minHeap.popMin();
            currentVertex = currentEdge.getEndVertex();

            // add the selected edge to the MST
            result.add(currentEdge);
            // find and mark the current Vertex
            vertexSet.remove(currentVertex);
        }
        return result;
    }
}
