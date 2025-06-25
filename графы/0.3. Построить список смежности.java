import java.util.*;
import java.io.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
        List<List<Integer>> adjList = new ArrayList<>();

        StringTokenizer st;
        st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }


            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                addEdge(adjList, u-1, w-1);
            }

            for (int i = 0; i < n; i++) {
                pw.print(adjList.get(i).size());
                for (int neighbor : adjList.get(i)) {
                    pw.print(" " + (neighbor + 1));
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
    static void addEdge(List<List<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
}