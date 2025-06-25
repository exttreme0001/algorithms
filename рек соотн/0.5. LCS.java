import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();//1

        int[] A = new int[n];//2
        int[] B = new int[n];//3

        for (int i = 0;i<n;i++) A[i]=sc.nextInt();
        for (int i = 0;i<n;i++) B[i]=sc.nextInt();
        // DP solve
        LCS(A,B,n);
    }

    public static void LCS(int[]A, int[]B, int n) {
        int[][] Matrix = new int[n + 1][n + 1];

        // filling matrix
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                if (A[i - 1] == B[j - 1]) { // if els same
                    Matrix[i][j] = Matrix[i - 1][j - 1] + 1;
                } else { // max from top and left
                    Matrix[i][j] = Math.max(Matrix[i - 1][j], Matrix[i][j - 1]);
                }
            }
        }

        Deque<Integer> indicesA=new ArrayDeque<>();
        Deque<Integer> indicesB=new ArrayDeque<>();
        // ret indices
        restoreIndices(Matrix,A,B,n,indicesA,indicesB);

        //sout
        System.out.println(Matrix[n][n]);// final LCS length
        printIndices(indicesA);
        printIndices(indicesB);
    }
 private static void restoreIndices(int[][]dp, int[]A, int[]B, int n,
                                    Deque<Integer> indicesA, Deque<Integer> indicesB) {
     int i=n,j=n;
     while (i >0 && j >0){
         if (A[i-1] == B[j-1]) {
             indicesA.addFirst(i - 1); // add to start
             indicesB.addFirst(j - 1);
             i--;
             j--;
         } else if (dp[i-1][j] > dp[i][j-1]) {
             i--;
         } else {
             j--;
         }
     }
 }

 private static void printIndices(Deque<Integer> indices) {
     while (!indices.isEmpty()) {
         System.out.print(indices.poll() + (indices.isEmpty() ? "\n" : " "));
     }
 }

}