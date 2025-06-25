import java.util.*;
import java.io.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
        StringTokenizer st;
        st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
            int[][] rank = new int[n][n];
            for (int i = 0; i <n ; i++) {
                Arrays.fill(rank[i],0);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                rank[u-1][w-1] = 1;
                rank[w-1][u-1] = 1;

            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pw.print(rank[i][j] + " ");
                }
                pw.println();
            }

            reader.close();
        pw.close();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}