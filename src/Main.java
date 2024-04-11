public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Vertex vA = new Vertex(0);
        Vertex vB = new Vertex(1);
        Vertex vC = new Vertex(2);
        Vertex vD = new Vertex(3);
        Vertex vE = new Vertex(4);

        graph.addVertex(vA);
        graph.addVertex(vB);
        graph.addVertex(vC);
        graph.addVertex(vD);
        graph.addVertex(vE);

        graph.addEdge(vA, vE, 1);
        graph.addEdge(vA, vD, 4);
        graph.addEdge(vA, vC, 3);
        graph.addEdge(vA, vB, 6);
        graph.addEdge(vB, vC, 1);
        graph.addEdge(vC, vE, 1);
        graph.addEdge(vC, vD, 2);
        graph.addEdge(vD, vE, 3);

        System.out.println(graph.dijkstra(vA, vD));

        graph.printGraph();
    }
}