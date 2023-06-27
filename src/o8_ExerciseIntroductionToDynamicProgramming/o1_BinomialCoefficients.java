package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o1_BinomialCoefficients {
  private static long[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    int k = Integer.parseInt(reader.readLine());
    dp = new long[n + 1][k + 1];

    System.out.println(calcBinom(n, k));
  }

  private static long calcBinom(int n, int k) {
    if (k == 0 || k == n) {
      return 1;
    }
    if (dp[n][k] != 0) {
      return dp[n][k];
    }
    return dp[n][k] = calcBinom(n - 1, k - 1) + calcBinom(n - 1, k);
  }
}
