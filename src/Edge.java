public class Edge {
        private Vertex a;
        private Vertex b;

        private int weight;

        public Edge(Vertex a, Vertex b, int weight) {
            this.a = a;
            this.b = b;

            this.weight = weight;
        }

    public Vertex getA() {
        return a;
    }

    public Vertex getB() {
        return b;
    }

    public int getWeight() {
        return weight;
    }
}
