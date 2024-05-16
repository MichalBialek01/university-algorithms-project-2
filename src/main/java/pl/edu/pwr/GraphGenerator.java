package pl.edu.pwr;

import java.util.*;

public class GraphGenerator {
    public static int[][] generateMatrix(int V, double density) {
        int[][] matrix = new int[V][V];
        Random rand = new Random();

        for (int i = 0; i < V; i++) {
            Arrays.fill(matrix[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (rand.nextDouble() < density) {
                    int weight = rand.nextInt(10) + 1;
                    matrix[i][j] = weight;
                    matrix[j][i] = weight;
                }
            }
        }
        return matrix;
    }

    public static List<List<Edge>> generateList(int V, double density) {
        List<List<Edge>> list = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (rand.nextDouble() < density) {
                    int weight = rand.nextInt(10) + 1;
                    list.get(i).add(new Edge(j, weight));
                    list.get(j).add(new Edge(i, weight));
                }
            }
        }
        return list;
    }
}