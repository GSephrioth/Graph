package WeightedGraph;

/**
 * Edge of Undirected and Weighted Graph
 * Contains: Weight, Vertex
 * Created by cxz on 2016/10/2.
 */
class Edge {
    private int weight;
    private Vertex endVertex;

    Edge() {
        this.weight = 0;
        this.endVertex = new Vertex();
    }

    Edge(String endVertex, int weight) {
        this.weight = weight;
        this.endVertex = new Vertex(endVertex);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    /**
     * toString
     * Override the 'toString' in class 'Object' return a String
     * containing the detail of the Ndoe
     */
    public String toString() {
        String str;
        str = " (" + this.endVertex + "," + this.weight + ") ";
        return str;
    }
}
