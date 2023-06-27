package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o1_BinomialCoefficientsIterative {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    int k = Integer.parseInt(reader.readLine());
    long[][] dp = new long[n + 1][k + 1];

   for(int row = 0; row <= n; row++) {
     for(int col = 0; col <= Math.min(row, k); col++) {
       if (col == 0 || col == row) {
dp[row][col] = 1;
continue;
       }
       dp[row][col] = dp[row - 1][col] + dp[row - 1][col - 1];
     }
   }
   System.out.println(dp[n][k]);
  }
}
