package pl.edu.pwr;

import java.util.*;

public class AdjacencyListGraph implements Graph {
    private List<List<Edge>> adjacencyList;
    private int numVertices;

    public AdjacencyListGraph(int V) {
        this.numVertices = V;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int u, int v, int weight) {
        adjacencyList.get(u).add(new Edge(v, weight));
        adjacencyList.get(v).add(new Edge(u, weight)); // undirected graph
    }

    @Override
    public List<Integer> dijkstra(int src) {
        return Dijkstra.shortestPath(adjacencyList, src);
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
        for (List<Edge> edgeList : adjacencyList) {
            edges.addAll(edgeList);
        }
        return edges;
    }
}
