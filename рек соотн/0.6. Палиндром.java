import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));

        String S = sc.next();

        // DP solve
        pal(S,S.length(),pw);

        pw.close();
    }

    public static void pal(String s,int n, PrintWriter pw) {
        int[][] matrix = new int[n][n];

        //base
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1;// 1 symb
        }

        // filling matrix
        for (int len = 2; len <= n; len++) {//len 2+
            for (int i = 0; i <= n-len ; i++) {
                int j = i+len-1; // right bodrer

                if (s.charAt(i) == s.charAt(j)) {
                    matrix[i][j] = matrix[i+1][j-1]+2; //add symbols
                } else {
                    matrix[i][j] = Math.max(matrix[i+1][j], matrix[i][j-1]); // skip symbol(max palin)
                }
            }
        }
        // restore
        String palindrome = restorePalin(matrix, s, n);

        // sout
        pw.println(matrix[0][n-1]);
        pw.println(palindrome);
    }
    static String restorePalin(int[][] matrix, String s, int n) {
        int i = 0, j = n - 1;
        StringBuilder left = new StringBuilder(), right = new StringBuilder();

        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                left.append(s.charAt(i));
                if (i != j) right.insert(0, s.charAt(j)); //second half
                i++;
                j--;
            } else if (matrix[i+1][j] >= matrix[i][j-1]) {
                i++;
            } else {
                j--;
            }
        }
        return left.toString() + right.toString();
    }

}