package WeightedGraph;

/**
 * Edge of Undirected and Weighted Graph
 * Contains: Weight, Vertex
 * Created by cxz on 2016/10/2.
 */
class Edge {
    private int weight;
    private Vertex endVertex;
    private Vertex startVertex;

    Edge() {
        this.weight = 0;
        this.startVertex = new Vertex("0");
        this.endVertex = new Vertex("1");
    }

    Edge(String startVertex, String endVertex, int weight) {
        this.weight = weight;
        this.startVertex = new Vertex(startVertex);
        this.endVertex = new Vertex(endVertex);
    }
    public int getWeight() {
        return weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }
    public Vertex getEndVertex() {
        return endVertex;
    }

    /**
     * toString
     * Override the 'toString' in class 'Object' return a String
     * containing the detail of the Edge
     */
    public String toString() {
        String str;
        str = " (" + this.endVertex + "," + this.weight + ") ";
        return str;
    }

    /**
     *
     * */
    public boolean smallerThan(Edge e) {
        return this.weight < e.weight;
    }
}
