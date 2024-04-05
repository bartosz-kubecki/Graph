//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(v0, v1, 2);
        graph.addEdge(v1, v2, 3);
        graph.addEdge(v2, v0, 1);
        graph.addEdge(v2, v3, 1);
        graph.addEdge(v3, v2, 4);

        graph.removeVertex(v2);

        graph.printGraph();
    }
}