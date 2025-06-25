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
        int q = Integer.parseInt(st.nextToken());
        int[]rank = new int[n + 1];
        int components = n;

        int[] DSU = new int[n+1];
        for (int i = 0; i <= n; i++) {
            DSU[i]=i;
            rank[i] = 1;
        }



        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            components = rankedUnion(DSU,rank,a, b,components);
            pw.println(components);
        }
        reader.close();
        pw.close();
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
} private static int rankedUnion(int[] DSU,int[]rank,int a, int b,int components) {
        if (components == 1) return 1;

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