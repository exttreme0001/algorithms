import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final int UNREACHABLE = -1;

        Scanner sc =new Scanner(System.in);

        int n = sc.nextInt();//1
        int baseArr[] =new int[n];

        for (int i = 0; i< n; i++){//2
            baseArr[i] = sc.nextInt();
        }
        if (n == 1){
            System.out.println(baseArr[0]);
            System.out.println(1);
            return;
        }

        int[] StepSums = new int[n];//init SumMass
        Arrays.fill(StepSums, UNREACHABLE);
        StepSums[0] = baseArr[0];

        int[] stepIndeces = new int[n];
        Arrays.fill(stepIndeces, -1);

        for (int i = 2; i< n; i++){
            if (StepSums[i-2] != UNREACHABLE && StepSums[i] < StepSums[i-2] + baseArr[i]) {
                StepSums[i] = StepSums[i-2] + baseArr[i];
                stepIndeces[i] = i-2;
            }
            if (i >= 3 && StepSums[i-3] != UNREACHABLE && StepSums[i] < StepSums[i-3] + baseArr[i]) {
                StepSums[i] = StepSums[i-3] + baseArr[i];
                stepIndeces[i] = i-3;
            }
        }

        if (StepSums[n-1] == UNREACHABLE) {
            System.out.println("-1");
            return;
        }

        // Восстановление пути
        Deque<Integer> path =new ArrayDeque<>();
        for (int i = n-1; i >=0; ) {
            path.addFirst(i+1);
            i =stepIndeces[i];
            if (i< 0) break;
        }

        PrintWriter pw = new PrintWriter(System.out);

        pw.println(StepSums[n - 1]);//res

        for (Integer step : path) {//path
            pw.print(step + " ");
        }

        pw.flush();
        pw.close();
    }}