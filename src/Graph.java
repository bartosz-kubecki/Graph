import java.util.HashMap;

public class Graph {
    private HashMap<Integer, Vertex> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public Vertex getVertex(int key) {
        return vertices.get(key);
    }

    public void addVertex(Vertex node) {
        vertices.put(node.getKey(), node);
    }

    public void addEdge(Vertex a, Vertex b, int weight)
    {
        Edge edge = new Edge(a, b, weight);
        a.getEdges().add(edge);
        b.getEdges().add(edge);
    }

    public void removeVertex(Vertex vertex) {
        for (Edge edge : vertex.getEdges()) {
            if (edge.getA() == vertex) {
                edge.getB().getEdges().remove(edge);
            } else {
                edge.getA().getEdges().remove(edge);
            }
        }
        vertices.remove(vertex.getKey(), vertex);
    }

    public void removeEdge(Edge edge) {
        edge.getA().getEdges().remove(edge);
        edge.getB().getEdges().remove(edge);
    }

    public void printGraph(){
        for(Vertex vertex : vertices.values()){
            System.out.print("Value: "+ vertex.getKey() + ": ");
            for(Edge edge : vertex.getEdges()){
                System.out.print("vertexA: " + edge.getA().getKey() + " vertexB: " + edge.getB().getKey() + " Weight: " + edge.getWeight() +  " | ");
            }
            System.out.print("\n");
        }
    }
}
