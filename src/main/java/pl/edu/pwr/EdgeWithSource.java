package pl.edu.pwr;

public class EdgeWithSource extends Edge {
    private int source;

    public EdgeWithSource(int source, int node, int weight) {
        super(node, weight);
        this.source = source;
    }

    public int getSource() {
        return source;
    }
}