import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));

        Long n = sc.nextLong();//1
        if(n<=0){
            pw.println(-1);
        }

        // 10001110 ...
        //sout where 1
        rank(n,pw);

        //sout
        //pw.println(Arrays.toString(result));
        pw.close();
    }


    public static void rank(Long n,PrintWriter pw) {
        String bin = Long.toBinaryString(n);
        int l = bin.length();
        for (int i = 0;i<l; i++) {
            // end->start
            if (bin.charAt(l-1-i) =='1') {
                pw.println(i);
            }
    }
    }
}