package o1_RecursionAndBacktracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o7_RecursiveFibonacci {
    private static final int MAX_CAPACITY = 50;
    private static final long[] MEMO = new long[MAX_CAPACITY];
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        while (n <= 0 || n >= 50) {
            System.out.println("Wrong input! Enter number between 1 and 49 inclusive!");
            n = Integer.parseInt(reader.readLine());
        }

        System.out.println(fibonacciRecursive(n));
    }

    private static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return 1;
        }

        if (MEMO[n] != 0) {
            return MEMO[n];
        }


        long fib = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);

        MEMO[n] = fib;
        return fib;
    }
}
