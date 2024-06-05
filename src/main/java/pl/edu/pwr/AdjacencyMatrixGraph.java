package pl.edu.pwr;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    @Override
    public List<Node> getNodes() {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            nodes.add(new Node(i));
        }
        return nodes;
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edges.add(new EdgeWithSource(i, j, adjacencyMatrix[i][j]));
                }
            }
        }
        return edges;
    }
    @Override
    public void addEdge(int u, int v, int weight) {
        adjacencyMatrix[u][v] = weight;
        adjacencyMatrix[v][u] = weight; // undirected graph
    }

    @Override
    public List<Integer> dijkstra(int src) {
        return Dijkstra.shortestPath(adjacencyMatrix, src);
    }
}
