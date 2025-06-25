import java.io.*;
import java.util.*;

public class Main implements Runnable {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

            String[] header = input.readLine().split(" ");
            int n = Integer.parseInt(header[0]);
            int m = Integer.parseInt(header[1]);

            int[][] capacity = new int[n + 1][n + 1]; // capacities of edges
            List<Integer>[] graph = new ArrayList[n + 1]; // adjacency list

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                String[] parts = input.readLine().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts[2]);

                capacity[u][v] += w; // handle multiple edges
                graph[u].add(v);
                graph[v].add(u); // add reverse edge for residual graph
            }

            int maxFlow = edmondsKarp(n, graph, capacity, 1, n);
            output.write(Integer.toString(maxFlow));
            output.newLine();
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int edmondsKarp(int n, List<Integer>[] graph, int[][] capacity, int source, int sink) {
        int flow = 0;
        int[] parent = new int[n + 1];

        while (true) {
            Arrays.fill(parent, -1);
            parent[source] = source;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);

            while (!queue.isEmpty() && parent[sink] == -1) {
                int current = queue.poll();
                for (int neighbor : graph[current]) {
                    if (parent[neighbor] == -1 && capacity[current][neighbor] > 0) {
                        parent[neighbor] = current;
                        queue.add(neighbor);
                    }
                }
            }

            if (parent[sink] == -1) break; // no more augmenting paths

            // Find min on new road
            int pathFlow = INF;
            int v = sink;
            while (v != source) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
                v = u;
            }

            // Update capacities
            v = sink;
            while (v != source) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
                v = u;
            }

            flow += pathFlow;
        }

        return flow;
    }
}