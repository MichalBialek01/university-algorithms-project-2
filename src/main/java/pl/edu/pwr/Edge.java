package pl.edu.pwr;


public class Edge {
    int node;
    int weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public int getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}