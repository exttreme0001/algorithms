import java.io.*;
import java.util.*;

public class Main {
    static int[] leader;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        List<String> inputTokens = new ArrayList<>();
        int index = 0;
        String currentLine;

        // Чтение количества предприятий
        while (inputTokens.isEmpty()) {
            currentLine = reader.readLine();
            if (currentLine == null) return;
            inputTokens.addAll(Arrays.asList(currentLine.trim().split("\\s+")));
        }

        int companies = Integer.parseInt(inputTokens.get(index++));
        leader = new int[companies + 1];
        distance = new int[companies + 1];

        for (int i = 1; i <= companies; i++) {
            leader[i] = i;
        }

        while (true) {
            while (index >= inputTokens.size()) {
                currentLine = reader.readLine();
                if (currentLine == null) break;
                inputTokens.addAll(Arrays.asList(currentLine.trim().split("\\s+")));
            }

            if (index >= inputTokens.size()) break;
            String command = inputTokens.get(index++);

            if (command.equals("O")) {
                break;
            } else if (command.equals("E")) {
                int node = Integer.parseInt(inputTokens.get(index++));
                findRoot(node);
                writer.write(distance[node] + "\n");
            } else if (command.equals("I")) {
                int from = Integer.parseInt(inputTokens.get(index++));
                int to = Integer.parseInt(inputTokens.get(index++));
                leader[from] = to;
                distance[from] = Math.abs(from - to) % 1000;
            }
        }

        writer.flush();
    }

    static int findRoot(int x) {
        if (leader[x] == x) return x;

        int current = x;
        List<Integer> trace = new ArrayList<>();

        while (leader[current] != current) {
            trace.add(current);
            current = leader[current];
        }

        int totalDistanceToRoot = 0;
        for (int i = trace.size() - 1; i >= 0; i--) {
            int v = trace.get(i);
            totalDistanceToRoot += distance[v];
            distance[v] = totalDistanceToRoot;
            leader[v] = current;
        }


        return current;
    }
}