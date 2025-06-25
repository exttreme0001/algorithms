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

            int[] labels = new int[n + 1]; // метки вершин
            boolean[] visited = new boolean[n + 1];
            int[] counter = new int[1]; // по ссылке массивов проще(якобы глобальная переменная)
            counter[0] = 1;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i, matrix, visited, labels, counter, n);
                }
            }

            for (int i = 1; i <= n; i++) {
                pw.print(labels[i] + " ");
            }
            reader.close();
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void dfs(int node, int[][] matrix, boolean[] visited, int[] labels, int[] counter, int n) {
        visited[node] = true;
        labels[node] = counter[0]++;

        for (int neighbor = 1; neighbor <= n; neighbor++) {
            if (matrix[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, matrix, visited, labels, counter, n);
            }
        }
    }
}