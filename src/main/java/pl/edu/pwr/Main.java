package pl.edu.pwr;+

import java.util.List;

public class Main {
    private static final int NUM_INSTANCES = 100;

    public static void main(String[] args) {
        int[] vertexCounts = {100};
        double[] densities = {0.25, 0.5, 0.75, 1.0};

        for (int numVertices : vertexCounts) {
            System.out.println("Number of vertices: " + numVertices);

            for (double density : densities) {
                System.out.println("Density: " + (density * 100) + "%");

                for (boolean useMatrix : new boolean[]{true, false}) {
                    String representation = useMatrix ? "Matrix" : "List";
                    System.out.println("Representation: " + representation);

                    long totalTime = 0;

                    for (int i = 0; i < NUM_INSTANCES; i++) {
                        Graph graph;
                        if (useMatrix) {
                            graph = new AdjacencyMatrixGraph(numVertices);
                            int[][] edges = GraphGenerator.generateMatrix(numVertices, density);
                            for (int u = 0; u < numVertices; u++) {
                                for (int v = 0; v < numVertices; v++) {
                                    if (edges[u][v] != Integer.MAX_VALUE) {
                                        graph.addEdge(u, v, edges[u][v]);
                                    }
                                }
                            }
                        } else {
                            graph = new AdjacencyListGraph(numVertices);
                            List<List<Edge>> edges = GraphGenerator.generateList(numVertices, density);
                            for (int u = 0; u < numVertices; u++) {
                                for (Edge edge : edges.get(u)) {
                                    graph.addEdge(u, edge.node, edge.weight);
                                }
                            }
                        }

                        long startTime = System.nanoTime();
                        graph.dijkstra(0);
                        long endTime = System.nanoTime();
                        totalTime += (endTime - startTime);
                    }

                    double averageTime = totalTime / (double) NUM_INSTANCES;
                    System.out.println("Average time: " + averageTime + " ns\n");

                    // Drukowanie grafu
                    GraphPrinter printer = new GraphPrinter();
                    org.jgrapht.Graph<Integer, org.jgrapht.graph.DefaultWeightedEdge> jGraphTGraph = printer.createGraph(numVertices, density);
                    printer.print(jGraphTGraph, 0, numVertices, density);
                }
            }
        }
    }

}
