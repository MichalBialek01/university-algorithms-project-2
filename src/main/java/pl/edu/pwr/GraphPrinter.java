package pl.edu.pwr;

import java.io.FileWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GraphPrinter {

    public static void exportGraphData(Graph graph, List<Integer> shortestPath, String filename) {
        JSONObject graphJson = new JSONObject();

        JSONArray nodesJson = new JSONArray();
        for (Node node : graph.getNodes()) {
            JSONObject nodeJson = new JSONObject();
            nodeJson.put("id", node.getId());
            nodesJson.put(nodeJson);
        }
        graphJson.put("nodes", nodesJson);

        JSONArray edgesJson = new JSONArray();
        for (Edge edge : graph.getEdges()) {
            if (edge instanceof EdgeWithSource) {
                EdgeWithSource ews = (EdgeWithSource) edge;
                JSONObject edgeJson = new JSONObject();
                edgeJson.put("source", ews.getSource());
                edgeJson.put("target", ews.getNode());
                edgeJson.put("weight", ews.getWeight());
                edgesJson.put(edgeJson);
            }
        }
        graphJson.put("edges", edgesJson);

        JSONArray pathJson = new JSONArray(shortestPath);
        graphJson.put("shortestPath", pathJson);

        try (FileWriter file = new FileWriter(filename)) {
            file.write(graphJson.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}