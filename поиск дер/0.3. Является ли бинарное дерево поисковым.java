import java.util.*;
import java.io.*;
class Range {
    long start;
    long end; // null -inf

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }
    public long getLeft(){
        return this.start;
    }
    public long getRight(){
        return this.end;
    }
}
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("bst.in"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("bst.out"));

            int n = Integer.parseInt(reader.readLine().trim());//1


            if (n == 0||n == 1) {
                writer.write("YES");
                writer.close();
                return;
            }

            boolean isValid = true;
            long[] val = new long[n];
            Range[] ranges = new Range[n];

            val[0]=Long.parseLong(reader.readLine().trim());//2
            ranges[0] = new Range(Long.MIN_VALUE, Long.MAX_VALUE);


            for (int i = 1; i < n; i++) {
                String[] parts = reader.readLine().trim().split(" ");
                val[i] = Long.parseLong(parts[0]);
                int parentIdx = Integer.parseInt(parts[1]);
                char rule = parts[2].charAt(0);

                if (rule == 'R' ){
                    ranges[i] = new Range(val[parentIdx-1],ranges[parentIdx-1].getRight());
                }else{
                    ranges[i] = new Range(ranges[parentIdx-1].getLeft(),val[parentIdx-1]);
                }

                long left = ranges[i].getLeft();
                long right = ranges[i].getRight();

                if (( val[i] < left) || ( val[i] >= right)) {
                    isValid = false;
                    break;
                }
            }

            //sout
            writer.write(isValid ? "YES\n" : "NO\n");
            writer.close();
            reader.close();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}