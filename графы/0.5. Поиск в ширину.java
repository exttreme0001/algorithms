import java.util.*;
import java.io.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
            StringTokenizer st;
            st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());


            int[][] matrix = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(reader.readLine());
                for (int j = 1; j <= n; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] bfs = bfsOnMatrix(matrix, n);

            for (int i = 1; i <= n; i++) {
                pw.print(bfs[i] + " ");
            }
            reader.close();
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] bfsOnMatrix(int[][] matrix, int n) {
        int[] order = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        int step = 1;

        for (int start = 1; start <= n; start++) {
            if (!visited[start]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(start);
                visited[start] = true;
                order[start] = step++;

                while (!queue.isEmpty()) {
                    int node = queue.poll();

                    for (int neighbor = 1; neighbor <= n; neighbor++) {
                        if (matrix[node][neighbor] == 1 && !visited[neighbor]) {
                            visited[neighbor] = true;
                            order[neighbor] = step++;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }

        return order;
    }
}