import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));

        int n = sc.nextInt();//1
        int[] mas = new int[n];

        for (int i=0; i<n; i++) {//2
            mas[i] = sc.nextInt();
        }

        // isHeap
        boolean result = isHeap(mas,n);

        //sout
        pw.println((result ? "Yes\n" : "No\n"));
        pw.close();
    }


    public static boolean isHeap(int[] mas,int n) {

        for (int i=0; i<n/2;++i) {
            int left = 2*i+1;
            int right = 2*i+2;

            if (left<n && mas[i]>mas[left])
                return false;
            if (right<n && mas[i]>mas[right])
                return false;
        }
        return true;
    }
}