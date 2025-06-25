import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));

        int n = sc.nextInt();//1

        int[] dimensions = new int[n+1];//2

        for (int i = 0; i < n; i++) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            dimensions[i] = row;
            if (i == n-1) {
                dimensions[i+1] =col;//last val
            }
        }


        // DP solve
        int result = minOperations(dimensions,n);

        //sout
        pw.println(result);
        pw.close();
    }


    public static int minOperations(int[] dimensions,int n) {
         int[][] matrix = new int[n][n];

         
        // filling matrix
        for (int len = 2; len <= n; len++) {//len 2+
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1; // right bodrer

                matrix[start][end] = Integer.MAX_VALUE;//maxv

                for (int split = start; split < end; split++) {
                    int leftCost = matrix[start][split];
                    int rightCost = matrix[split + 1][end];
                    int mergeCost = dimensions[start] * dimensions[split + 1] * dimensions[end + 1];

                    // resC
                    int totalCost = leftCost + rightCost + mergeCost;

                    //update cost
                    matrix[start][end] = Math.min(matrix[start][end], totalCost);
                }
            }
        }
        return matrix[0][n-1];
}
}