package pl.edu.pwr;

import java.util.List;

public interface Graph {
    void addEdge(int u, int v, int weight);
    List<Integer> dijkstra(int src);
}