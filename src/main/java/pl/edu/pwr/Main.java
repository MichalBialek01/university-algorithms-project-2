package pl.edu.pwr;

import java.util.List;

public class Main {
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
                                graph.addEdge(u, edge.getNode(), edge.getWeight());
                            }
                        }
                    }

                    long startTime = System.nanoTime();
                    List<Integer> shortestPath = graph.dijkstra(0);
                    long endTime = System.nanoTime();
                    long totalTime = endTime - startTime;

                    System.out.println("Time taken: " + totalTime + " ns");

                    // Eksportowanie danych grafu do pliku
                    String filename = String.format("graphData_%s_%d_%.2f.json", representation, numVertices, density);
                    GraphPrinter.exportGraphData(graph, shortestPath, filename);
                }
            }
        }
    }
}