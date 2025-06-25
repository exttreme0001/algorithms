import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("in.txt"));
        PrintWriter pw = new PrintWriter(new File("out.txt"));

        int del = sc.nextInt();//1
        int ins = sc.nextInt();//2
        int ch = sc.nextInt();//3

        String s1 = sc.next();//4
        String s2 = sc.next();//5

        // DP solve
        int result = minFine(del,ins,ch,s1,s2);

        //sout
        pw.println(result);
        pw.close();
    }


    public static int minFine(int del,int ins,int ch,String s1,String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] matrix = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            matrix[i][0] = i * del;//del all
        }
        for (int j = 0; j <= m; j++) {
            matrix[0][j] = j * ins;//ins all
        }

        // filling matrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];//skip , diag fine
                } else {
                    matrix[i][j] = Math.min(
                                            matrix[i - 1][j - 1] + ch,  // change
                                  Math.min( matrix[i - 1][j] + del,  // del
                                            matrix[i][j - 1] + ins)   // ins
                    );
                }
            }
            }
        return matrix[n][m];
    }
}