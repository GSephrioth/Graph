package WeightedGraph;

/**
 * Vertex of Undirected and Weighted Graph
 * Only contains VertexName
 * Created by cxz on 2016/10/2.
 */
class Vertex {
    private String vertexName;
    private Vertex preVertex;

    public Vertex() {
        this.vertexName = "";
    }
    public Vertex(String nodeName) {
        this.vertexName = nodeName;
    }

    String getVertexName() {
        return vertexName;
    }

    Vertex getPreVertex() {
        return preVertex;
    }

    void setPreVertex(Vertex v) {
        preVertex = v;
    }
    @Override
    public String toString() {
        String str;
        str = "Vertex: " + this.vertexName + " ";
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex v = (Vertex) o;
        return vertexName != null ? vertexName.equals(v.getVertexName()) : v.getVertexName() == null;
    }

    @Override
    public int hashCode() {
        return vertexName != null ? vertexName.hashCode() : 0;
    }
}
