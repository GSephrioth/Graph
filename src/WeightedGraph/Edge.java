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
        this.weight = Integer.MAX_VALUE;
        this.startVertex = new Vertex("");
        this.endVertex = new Vertex("");
    }
    Edge(String startVertex, String endVertex, int weight) {
        this.weight = weight;
        this.startVertex = new Vertex(startVertex);
        this.endVertex = new Vertex(endVertex);
    }

    int getWeight() {
        return weight;
    }

    Vertex getStartVertex() {
        return startVertex;
    }

    Vertex getEndVertex() {
        return endVertex;
    }
    /**
     * return true if the Current Edge is smaller than Edge 'e'
     * */
    boolean smallerThan(Edge e) {
        return this.weight < e.getWeight();
    }

    boolean largerThan(Edge e) {
        return this.weight > e.getWeight();
    }

    @Override
    public String toString() {
        String str;
        str = " (" + this.startVertex + "," + this.endVertex + "," + this.weight + ") ";
        return str;
    }


}
