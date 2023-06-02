package o2_CombinatorialProblems;

import java.util.HashMap;
import java.util.Scanner;

/**
 * This class calculates the binomial coefficient using memoization.
 */
public class o7_N_Choose_K_Count {

    /**
     * A memoization table to store computed binomial coefficients.
     */
    private final static HashMap<String, Integer> memo = new HashMap<>();
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        System.out.println(binomialCoefficient(n, k));
    }

    /**
     * Calculates the binomial coefficient of n choose k using memoization.
     *
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @return The binomial coefficient of n choose k.
     */
    private static int binomialCoefficient(int n, int k) {
        if(n < k) {
            return 0;
        }

        if (k == n || k == 0) {
            return 1;
        }

        String key = n + "_" + k;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int result = binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);

        memo.put(key, result);

        return result;
    }
}