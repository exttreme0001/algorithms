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

            int[] p = new int[n+1];
            Arrays.fill(p,0);

            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                p[w] = u;

            }

            for (int i = 1; i <= n; i++) {
                pw.print(p[i] + " ");
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