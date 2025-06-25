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
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[]rank = new int[n + 1];
        int components = n;

        int[] DSU = new int[n+1];
        for (int i = 1; i <= n; i++) {
            DSU[i]=i;
            rank[i] = 1;
        }

        StringBuilder result = new StringBuilder();
        String[] s = new String[m];

            for (int i = 0; i < m; i++) {
                s[i]=reader.readLine();
            }

            boolean[] removed = new boolean[m];
            int[] roads = new int[q];
            for (int i = q - 1; i >= 0; i--) {
                roads[i] = Integer.parseInt(reader.readLine())-1;
                removed[roads[i]] = true;
            }

            List<Integer> missingRoads = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (!removed[i])  {
                    missingRoads.add(i);
                }
            }
            //building
            for (int i = 0; i < m-q; i++) {
                st = new StringTokenizer(s[missingRoads.get(i)]);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                components = rankedUnion(DSU,rank,a, b,components);
            }
        boolean wasConnected = (components == 1);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(s[roads[i]]);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            components = rankedUnion(DSU,rank,a, b,components);
            if (components == 1 && !wasConnected) {
                result.append(0);
                wasConnected = true;
            } else if (components == 1){
                result.append(1);
            } else result.append(0);

        }
        pw.println(result.reverse().toString());
        reader.close();
        pw.close();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
} private static int rankedUnion(int[] DSU,int[]rank,int a, int b,int components) {

        a = findLeader(DSU,a);
        b = findLeader(DSU,b);

        if (a == b) return components;
        if (rank[a] > rank[b]) { DSU[b] = a;
        } else
        if (rank[a] < rank[b]) { DSU[a] = b;
        }
        else {
            DSU[b] = a;
            rank[a]++;
        }
        return components - 1;
    }
    private static int findLeader(int[] DSU, int x) {
        if (DSU[x] != x) {
            DSU[x] = findLeader(DSU, DSU[x]);
        }
        return DSU[x];
    }
}