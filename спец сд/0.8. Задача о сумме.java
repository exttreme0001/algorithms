import java.util.*;
import java.io.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(System.out);
            int n = sc.nextInt();//1
            long[] mas = new long[n];
            for (int i = 0; i < n; i++) {//2
                mas[i] = sc.nextLong();
            }
            long[] tree = new long[4 * n];
            tree = fillAllMaxInt(tree, 4 * n);

            // input data, result tree mas, index to write, left border,right border
            buildTree(mas, tree, 1, 0, n);
            int q = sc.nextInt();//3 queries
            sc.nextLine();
            for (int i = 0; i < q; i++) {
                String[] parts = sc.nextLine().trim().split(" ");
                String method = parts[0];
                int index = Integer.parseInt(parts[1]);
                int x = Integer.parseInt(parts[2]);

                if (method.equals("FindSum")) {
                    pw.println(Find(tree, 1, 0, n, index, x));
                } else {
                    Add(tree, 1, 0, n, index, x);
                }
            }

            pw.close();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
    private static void Add ( long[] tree, int v, int tl, int tr, int i, int x ){

        if ((tr - tl) == 1) {
            tree[v] += x;
            return;
        } else {
            int median = (tr + tl) / 2;
            int left = 2 * v;
            int right = 2 * v +1;
            if (i < median) {
                Add(tree, left, tl, median, i, x);
            } else {
                Add(tree, right, median, tr, i, x);
            }

            tree[v] = tree[left] + tree[right];
        }
    }

    private static long Find ( long[] tree, int v, int tl, int tr, int l, int r){
        if (l > r) return 0;
        if (l == tl && r == tr) {
            return tree[v];
        }
        int median = (tr + tl) / 2;
        int left = 2 * v;
        int right = 2 * v + 1;
        if (r <= median) {
            return Find(tree, left, tl, median, l, r);
        }
        if (median <= l) {
            return Find(tree, right, median, tr, l, r);
        }
        return (Find(tree, left, tl, median, l, median) + Find(tree, right, median, tr, median, r));

    }


    public static void buildTree ( long[] mas, long[] tree, int v, int tl, int tr){

        if ((tr - tl) == 1) {
            tree[v] = mas[tl];
            return;
        } else {
            int median = (tr + tl) / 2;
            int left = 2 * v ;
            int right = 2 * v + 1;

            buildTree(mas, tree, left, tl, median);
            buildTree(mas, tree, right, median, tr);
            tree[v] = tree[left] + tree[right];
        }
    }
    private static long[] fillAllMaxInt ( long[] matrix, int n){
        for (int i = 0; i < n; i++) {
            matrix[i] = 0;
        }
        return matrix;
    }

}