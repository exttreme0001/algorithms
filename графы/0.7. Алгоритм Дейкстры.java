import java.io.*;
import java.util.*;

public class ShortestPath {
    static class Connection {
        int target;
        int cost;

        Connection(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    static class PathNode implements Comparable<PathNode> {
        int id;
        long totalCost;

        PathNode(int id, long totalCost) {
            this.id = id;
            this.totalCost = totalCost;
        }

        @Override
        public int compareTo(PathNode other) {
            return Long.compare(this.totalCost, other.totalCost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("output.txt"));

        String[] header = input.readLine().split(" ");
        int vertexCount = Integer.parseInt(header[0]);
        int edgeCount = Integer.parseInt(header[1]);

        List<List<Connection>> graph = new ArrayList<>(vertexCount + 1);
        for (int i = 0; i <= vertexCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            String[] data = input.readLine().split(" ");
            int from = Integer.parseInt(data[0]);
            int to = Integer.parseInt(data[1]);
            int length = Integer.parseInt(data[2]);

            graph.get(from).add(new Connection(to, length));
            graph.get(to).add(new Connection(from, length));
        }

        long[] minDistance = new long[vertexCount + 1];
        Arrays.fill(minDistance, Long.MAX_VALUE);
        minDistance[1] = 0;

        PriorityQueue<PathNode> queue = new PriorityQueue<>();
        queue.add(new PathNode(1, 0));

        boolean[] processed = new boolean[vertexCount + 1];

        while (!queue.isEmpty()) {
            PathNode current = queue.poll();
            int currentNode = current.id;

            if (processed[currentNode]) continue;
            processed[currentNode] = true;

            for (Connection edge : graph.get(currentNode)) {
                int neighbor = edge.target;
                int travelCost = edge.cost;

                if (minDistance[currentNode] + travelCost < minDistance[neighbor]) {
                    minDistance[neighbor] = minDistance[currentNode] + travelCost;
                    queue.add(new PathNode(neighbor, minDistance[neighbor]));
                }
            }
        }

        output.write(Long.toString(minDistance[vertexCount]));
        output.newLine();
        output.close();
        input.close();
    }
}