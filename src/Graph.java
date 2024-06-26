import java.util.*;

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

    public void removeEdge(Vertex a, Vertex b) {
        for (Edge edge : a.getEdges()) {
            if (edge.getA() == a && edge.getB() == b || edge.getB() == a && edge.getA() == b) {
                removeEdge(edge);
            }
        }
    }

    public void removeEdge(Edge edge) {
        edge.getA().getEdges().remove(edge);
        edge.getB().getEdges().remove(edge);
    }

    public int dijkstra(Vertex start, Vertex end) {
        HashSet<Vertex> settledVertices = new HashSet<>();
        HashSet<Edge> settledEdges = new HashSet<>();

        HashMap<Integer, Integer> distance = new HashMap<>();

        for (Map.Entry<Integer, Vertex> entry : this.vertices.entrySet()) {
            Vertex vertex = entry.getValue();
            distance.put(vertex.getKey(), Integer.MAX_VALUE);
        }
        distance.put(start.getKey(), 0);

        while (settledVertices.size() < vertices.size()) {
            Vertex lowestVertex = null;
            int lowestDistance = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Vertex> entry : this.vertices.entrySet()) {
                Vertex vertex = entry.getValue();
                if (distance.get(vertex.getKey()) <= lowestDistance && !settledVertices.contains(vertex)) {
                    lowestVertex = vertex;
                    lowestDistance = distance.get(vertex.getKey());
                }
            }

            if (lowestVertex != null) {
                Edge lowestEdge;
                do {
                    lowestEdge = null;
                    int lowestWeight = Integer.MAX_VALUE;
                    for (Edge edge : lowestVertex.getEdges()) {
                        if (edge.getWeight() < lowestWeight && !settledEdges.contains(edge)) {
                            lowestEdge = edge;
                            lowestWeight = edge.getWeight();
                        }
                    }

                    if (lowestEdge != null && distance.get(lowestVertex.getKey()) != Integer.MAX_VALUE) {
                        Vertex vertex = lowestEdge.getA() == lowestVertex ? lowestEdge.getB() : lowestEdge.getA();
                        int newDistance = distance.get(lowestVertex.getKey()) + lowestEdge.getWeight();
                        if (distance.get(vertex.getKey()) > newDistance) {
                            distance.put(vertex.getKey(), newDistance);
                        }
                    }

                    settledEdges.add(lowestEdge);
                } while (lowestEdge != null);

                settledVertices.add(lowestVertex);
            }
        }

        return distance.get(end.getKey());
    }

    public Graph kruskal() {
        Graph tree = new Graph();

        ArrayList<Edge> edges = new ArrayList<>();

        for (Map.Entry<Integer, Vertex> entry : this.vertices.entrySet()) {
            Vertex vertex = entry.getValue();
            edges.addAll(vertex.getEdges());

            Vertex newVertex = new Vertex(vertex.getKey());
            tree.addVertex(newVertex);
        }

        while (!edges.isEmpty()) {
            Edge edge = null;
            int minWeight = Integer.MAX_VALUE;
            for (Edge e : edges) {
                if (e.getWeight() < minWeight) {
                    edge = e;
                    minWeight = e.getWeight();
                }
            }
            if (edge == null) throw new RuntimeException();
            edges.remove(edge);

            int distance = tree.dijkstra(tree.getVertex(edge.getA().getKey()), tree.getVertex(edge.getB().getKey()));
            if (distance == Integer.MAX_VALUE) {
                tree.addEdge(tree.getVertex(edge.getA().getKey()), tree.getVertex(edge.getB().getKey()), edge.getWeight());
            }
        }

        return tree;
    }



    public Graph prim() {
        Graph tree = new Graph();

        ArrayList<Edge> edges = new ArrayList<>();

        tree.addVertex(new Vertex(this.vertices.entrySet().iterator().next().getValue().getKey()));

        while (tree.vertices.size() < this.vertices.size()) {
            Edge edge = null;
            int minWeight = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Vertex> entry : this.vertices.entrySet()) {
                Vertex vertex = entry.getValue();
                if (tree.getVertex(vertex.getKey()) != null) {
                    for (Edge e : vertex.getEdges()) {
                        if (e.getWeight() < minWeight && !edges.contains(e)) {
                            if (tree.vertices.get(e.getA().getKey()) == null) {
                                tree.addVertex(new Vertex(e.getA().getKey()));
                            }

                            if (tree.vertices.get(e.getB().getKey()) == null) {
                                tree.addVertex(new Vertex(e.getB().getKey()));
                            }

                            int distance = tree.dijkstra(tree.getVertex(e.getA().getKey()), tree.getVertex(e.getB().getKey()));
                            if (distance == Integer.MAX_VALUE) {
                                edge = e;
                                minWeight = e.getWeight();
                            }
                        }
                    }
                }
            }

            if (edge == null) throw new RuntimeException();
            tree.addEdge(tree.getVertex(edge.getA().getKey()), tree.getVertex(edge.getB().getKey()), edge.getWeight());
            edges.add(edge);
        }

        return tree;
    }

    public int chromaticNumber() {
        int chromaticNumber = 0;

        HashMap<Vertex, Integer> colors = new HashMap<>();

        for (Map.Entry<Integer, Vertex> entry : this.vertices.entrySet()) {
            Vertex vertex = entry.getValue();

            HashSet<Integer> usedColors = new HashSet<>();

            int color = 0;

            for (Edge edge : vertex.getEdges()) {
                if (edge.getA() == vertex) {
                    if (colors.get(edge.getB()) != null) {
                        usedColors.add(colors.get(edge.getB()));
                    }
                } else {
                    if (colors.get(edge.getA()) != null) {
                        usedColors.add(colors.get(edge.getA()));
                    }
                }
            }

            while (usedColors.contains(color)) {
                color++;
            }

            if (chromaticNumber < color) chromaticNumber = color;

            colors.put(vertex, color);

        }

        return chromaticNumber + 1;
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
