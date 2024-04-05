import java.util.LinkedList;

public class Vertex {
    private LinkedList<Edge> edges;
    private Integer key;

    public Vertex(Integer value) {
        this.key = value;
        edges = new LinkedList<>();
    }

    public Integer getKey() {
        return key;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }
}
