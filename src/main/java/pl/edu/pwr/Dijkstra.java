package pl.edu.pwr;

import java.util.*;

public class Dijkstra {
    public static List<Integer> shortestPath(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.node;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int d : dist) {
            result.add(d);
        }
        return result;
    }

    public static List<Integer> shortestPath(List<List<Edge>> graph, int src) {
        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.node;

            for (Edge e : graph.get(u)) {
                int v = e.node;
                int weight = e.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int d : dist) {
            result.add(d);
        }
        return result;
    }
}