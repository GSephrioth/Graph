package WeightedGraph;

/**
 * Vertex of Undirected and Weighted Graph
 * Only contains VertexName
 * Created by cxz on 2016/10/2.
 */
class Vertex {
    private String vertexName;

    Vertex() {
        this.vertexName = "";
    }

    Vertex(String nodeName) {
        this.vertexName = nodeName;
    }
    public String getVertexName() {
        return vertexName;
    }
    /**
     * toString
     * Override the 'toString' in class 'Object' return a String
     * containing the detail of the Vertex
     */
    public String toString() {
        String str;
        str = "Vertex: " + this.vertexName + " ";
        return str;
    }

}
