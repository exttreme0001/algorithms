import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws FileNotFoundException   {
        Scanner sc=new Scanner(new File("input.txt"));
        PrintWriter pw=new PrintWriter(new File("output.txt"));

        int n=sc.nextInt();//1

        int[]sequence = new int[n];
        int size = 0; //len

        for (int i = 0;i<n;i++) {

            int num=sc.nextInt();
            //searching pos:
            int position = Arrays.binarySearch(sequence,0, size, num);
            //ne
            if (position < 0) {position = -(position + 1);//last
            };
            //exist
            if (position == size) {

                sequence[size++] = num; //inc size
            } else {

                sequence[position] = num;// change num
        }
    }
        sc.close();
        pw.println(size);
        pw.close();

    }


}