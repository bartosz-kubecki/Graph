import org.junit.Test;

public class GraphTests {

    @Test
    public void dijkstraTest() {
        Graph graph = new Graph();

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);

        graph.addEdge(v0, v1, 4);
        graph.addEdge(v0, v7, 8);
        graph.addEdge(v1, v7, 11);
        graph.addEdge(v1, v2, 8);
        graph.addEdge(v2, v8, 2);
        graph.addEdge(v2, v5, 4);
        graph.addEdge(v2, v3, 7);
        graph.addEdge(v3, v4, 9);
        graph.addEdge(v3, v5, 14);
        graph.addEdge(v4, v5, 10);
        graph.addEdge(v5, v6, 2);
        graph.addEdge(v6, v8, 6);
        graph.addEdge(v6, v7, 1);
        graph.addEdge(v7, v8, 7);

        assert graph.dijkstra(v0, v0) == 0;
        assert graph.dijkstra(v0, v1) == 4;
        assert graph.dijkstra(v0, v2) == 12;
        assert graph.dijkstra(v0, v3) == 19;
        assert graph.dijkstra(v0, v4) == 21;
        assert graph.dijkstra(v0, v5) == 11;
        assert graph.dijkstra(v0, v6) == 9;
        assert graph.dijkstra(v0, v7) == 8;
        assert graph.dijkstra(v0, v8) == 14;
    }

    @Test
    public void MSTTest() {
        Graph graph = new Graph();

        Vertex vA = new Vertex(0);
        Vertex vB = new Vertex(1);
        Vertex vC = new Vertex(2);
        Vertex vD = new Vertex(3);
        Vertex vE = new Vertex(4);
        Vertex vF = new Vertex(5);
        Vertex vG = new Vertex(6);

        graph.addVertex(vA);
        graph.addVertex(vB);
        graph.addVertex(vC);
        graph.addVertex(vD);
        graph.addVertex(vE);
        graph.addVertex(vF);
        graph.addVertex(vG);

        graph.addEdge(vA, vB, 7);
        graph.addEdge(vA, vD, 5);
        graph.addEdge(vB, vC, 8);
        graph.addEdge(vB, vD, 9);
        graph.addEdge(vB, vE, 7);
        graph.addEdge(vC, vE, 5);
        graph.addEdge(vD, vE, 15);
        graph.addEdge(vD, vF, 6);
        graph.addEdge(vE, vF, 8);
        graph.addEdge(vE, vG, 9);
        graph.addEdge(vF, vG, 11);

        Graph tree1 = graph.prim();
        assert tree1.dijkstra(tree1.getVertex(5), tree1.getVertex(6)) == 34;

        Graph tree2 = graph.kruskal();
        assert tree2.dijkstra(tree2.getVertex(5), tree2.getVertex(6)) == 34;
    }

    @Test
    public void MSTTest2() {
        Graph graph = new Graph();

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);

        graph.addEdge(v0, v1, 4);
        graph.addEdge(v0, v7, 8);
        graph.addEdge(v1, v7, 11);
        graph.addEdge(v1, v2, 8);
        graph.addEdge(v7, v8, 7);
        graph.addEdge(v7, v6, 1);
        graph.addEdge(v2, v8, 2);
        graph.addEdge(v8, v6, 6);
        graph.addEdge(v2, v3, 7);
        graph.addEdge(v6, v5, 2);
        graph.addEdge(v2, v5, 4);
        graph.addEdge(v3, v5, 14);
        graph.addEdge(v3, v4, 9);
        graph.addEdge(v5, v4, 10);

        Graph tree2 = graph.prim();
        assert tree2.dijkstra(tree2.getVertex(0), tree2.getVertex(4)) == 31;
        assert tree2.dijkstra(tree2.getVertex(7), tree2.getVertex(8)) == 9;

        Graph tree1 = graph.kruskal();
        assert tree1.dijkstra(tree1.getVertex(0), tree1.getVertex(4)) == 31;
        assert tree1.dijkstra(tree1.getVertex(7), tree1.getVertex(8)) == 9;
    }
}
