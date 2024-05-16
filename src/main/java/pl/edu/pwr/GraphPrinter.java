package pl.edu.pwr;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.layout.mxCircleLayout;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class GraphPrinter {

    public void print(Graph<Integer, DefaultWeightedEdge> graph, int source) {
        // Obliczanie najkrótszych ścieżek
        DijkstraShortestPath<Integer, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        ShortestPathAlgorithm.SingleSourcePaths<Integer, DefaultWeightedEdge> paths = dijkstraAlg.getPaths(source);

        // Wizualizacja grafu
        JGraphXAdapter<Integer, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<>(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // Kolorowanie najkrótszych ścieżek na czerwono
        for (Integer target : graph.vertexSet()) {
            if (target != source) {
                List<DefaultWeightedEdge> path = paths.getPath(target).getEdgeList();
                for (DefaultWeightedEdge edge : path) {
                    graphAdapter.setCellStyle("strokeColor=red", new Object[]{graphAdapter.getEdgeToCellMap().get(edge)});
                }
            }
        }

        // Tworzenie ramki do wyświetlenia grafu
        JFrame frame = new JFrame();
        frame.getContentPane().add(graphComponent);
        frame.setTitle("Graf z najkrótszymi ścieżkami od wierzchołka " + source);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Graph<Integer, DefaultWeightedEdge> createGraph(int numVertices, double density) {
        Graph<Integer, DefaultWeightedEdge> graph = new DirectedWeightedPseudograph<>(DefaultWeightedEdge.class);
        Random rand = new Random();

        // Dodawanie wierzchołków
        for (int i = 0; i < numVertices; i++) {
            graph.addVertex(i);
        }

        // Dodawanie krawędzi
        int maxEdges = (int) (numVertices * (numVertices - 1) * density);
        for (int i = 0; i < maxEdges; i++) {
            int u = rand.nextInt(numVertices);
            int v = rand.nextInt(numVertices);
            if (u != v && !graph.containsEdge(u, v)) {
                DefaultWeightedEdge e = graph.addEdge(u, v);
                graph.setEdgeWeight(e, rand.nextInt(10) + 1);
            }
        }

        return graph;
    }
}
