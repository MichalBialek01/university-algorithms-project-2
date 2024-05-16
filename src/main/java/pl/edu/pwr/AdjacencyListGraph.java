package pl.edu.pwr;

import java.util.*;

public class AdjacencyListGraph implements Graph {
    private List<List<Edge>> adjList;
    private int V;

    public AdjacencyListGraph(int V) {
        this.V = V;
        adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Edge(v, weight));
        adjList.get(v).add(new Edge(u, weight)); // undirected graph
    }

    @Override
    public List<Integer> dijkstra(int src) {
        return Dijkstra.shortestPath(adjList, src);
    }
}