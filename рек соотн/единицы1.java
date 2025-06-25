import java.util.Scanner;

public class Main {
    private static final int MOD = (int)1e9 + 7;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        int onesCount = scanner.nextInt();
        scanner.close();

        System.out.println(BinomialC(length, onesCount));
    }

    private static long BinomialC(int n, int k) {

        if (k > n) return 0;

        long[] fact = new long[n+1];
        long[] invFact = new long[n+1];

        //!
        fact[0] = 1;
        for (int i = 1; i <= n; i++){
            fact[i] = (fact[i-1]*i)% MOD;
        }
        // fast inv!
        invFact[n] = modInverse(fact[n]);
        for (int i = n-1; i >= 0; i--) {
            invFact[i] = (invFact[i+1] * (i+1))% MOD;
        }

        return (((fact[n] * invFact[k])% MOD) * invFact[n-k])% MOD;
    }


    // (a^b % MOD)
    private static long modPow(long base, long exp) {

        long result =1;
        while (exp > 0) {
            if ((exp &1) == 1) {
                result = (result *base)% MOD;
            }
            base = (base * base)% MOD;
            exp>>=1;
        }

        return result;
    }

    // countInv (ферма)
    private static long modInverse(long num) {

        return modPow(num, MOD-2);
    }
}