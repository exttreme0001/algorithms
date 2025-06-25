import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int testCases = Integer.parseInt(br.readLine());
        while (testCases-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] partial = new int[n - 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n - 1; i++) {
                partial[i] = Integer.parseInt(st.nextToken());
            }

            // 1
            int[] zInput = new int[n];
            zInput[0] = 0;
            System.arraycopy(partial, 0, zInput, 1, n - 1);

            if (isValidZFunction(zInput)) {
                int[] sZ = buildIntArrayFromZ(zInput);
                if (sZ == null) {
                    pw.println(-1);
                } else {
                    int[] piFromZ = computePrefixFunction(sZ);
                    printArray(piFromZ, 1, pw);
                }
            } else {
                pw.println(-1);
            }

            // 2
            int[] piInput = new int[n];
            piInput[0] = 0;
            System.arraycopy(partial, 0, piInput, 1, n - 1);

            if (isValidPrefixFunction(piInput)) {
                int[] sPi = buildIntArrayFromPrefix(piInput);
                int[] zFromPi = computeZFunction(sPi);
                printArray(zFromPi, 1, pw);
            } else {
                pw.println(-1);
            }

        }

        pw.close();
    }

    static void printArray(int[] arr, int startIndex, PrintWriter pw) {
        for (int i = startIndex; i < arr.length; i++) {
            pw.print(arr[i] + " ");
        }
        pw.println();
    }



    private static boolean isValidZFunction(int[] z) {
        int n = z.length;
        if (n == 0 || z[0] != 0) return false;

        for (int i = 1; i < n; i++) {
            if (z[i] < 0 || z[i] > n - i) return false;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < z[i] && i + j < n; j++) {
                if (z[i + j] < Math.min(z[j], z[i] - j)) return false;
            }
        }


        int[] s = buildIntArrayFromZ(z);
        if (s == null) return false;

        int[] computedZ = computeZFunction(s);
        return Arrays.equals(z, computedZ);
    }
    private static boolean isValidPrefixFunction(int[] pi) {
        int n = pi.length;
        if (n == 0 || pi[0] != 0) return false;

        for (int i = 1; i < n; i++) {
            if (pi[i] < 0 || pi[i] > i) return false;
            if (pi[i] > pi[i - 1] + 1) return false;
        }

        int[] s = buildIntArrayFromPrefix(pi);

        for (int i = 1; i < n; i++) {
            if (pi[i] > 0 && s[i] != s[pi[i] - 1]) {
                return false;
            }
        }

        return Arrays.equals(computePrefixFunction(s), pi);

    }




    public static int[] buildIntArrayFromPrefix(int[] pi) {
        int n = pi.length;
        int[] s = new int[n];
        s[0] = 0;
        int next = 1;

        for (int i = 1; i < n; i++) {
            if (pi[i] == 0) {
                s[i] = next++;
            } else {
                s[i] = s[pi[i] - 1];
            }
        }

        return s;
    }
    static int[] buildIntArrayFromZ(int[] z) {
        int n = z.length;
        int[] s = new int[n];
        Arrays.fill(s, -1);
        s[0] = 0;
        int next = 1;

        for (int i = 1; i < n; i++) {
            if (z[i] > 0) {
                if (i + z[i] > n) return null;
                for (int j = 0; j < z[i]; j++) {
                    if (s[i + j] == -1) {
                        s[i + j] = s[j];
                    } else if (s[i + j] != s[j]) {
                        return null;
                    }
                }
            } else if (s[i] == -1) {
                s[i] = next++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (s[i] == -1) {
                s[i] = next++;
            }
        }

        return s;
    }
    public static int[] computePrefixFunction(int[] s) {
        int n = s.length;
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s[i] != s[j]) {
                j = pi[j - 1];
            }
            if (s[i] == s[j]) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }
    public static int[] computeZFunction(int[] s) {
        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }


}