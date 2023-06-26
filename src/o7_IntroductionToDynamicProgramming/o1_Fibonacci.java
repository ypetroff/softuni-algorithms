package o7_IntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class o1_Fibonacci {
    private static BigInteger[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        dp = new BigInteger[n + 1];
    System.out.println(fibonacci(n));
    }

    private static BigInteger fibonacci(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1 || n == 2) {
            return BigInteger.ONE;
        }
        if (dp[n] != null) {
            return dp[n];
        }
        return dp[n] = fibonacci(n - 1 ).add(fibonacci(n - 2));
    }
}
