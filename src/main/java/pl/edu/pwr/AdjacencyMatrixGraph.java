package pl.edu.pwr;

import java.util.Arrays;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    private int[][] adjMatrix;
    private int V;

    public AdjacencyMatrixGraph(int V) {
        this.V = V;
        adjMatrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
        }
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
        adjMatrix[v][u] = weight; // undirected graph
    }

    @Override
    public List<Integer> dijkstra(int src) {
        return Dijkstra.shortestPath(adjMatrix, src);
    }
}