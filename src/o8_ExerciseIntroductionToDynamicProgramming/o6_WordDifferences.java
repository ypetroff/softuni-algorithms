package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o6_WordDifferences {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[] first = br.readLine().toCharArray();
    char[] second = br.readLine().toCharArray();

    int[][] dp = new int[first.length + 1][second.length + 1];

    for (int i = 0; i <= first.length; i++) {
      for (int j = 0; j <= second.length; j++) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else if (first[i - 1] == second[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        }
      }
    }
    System.out.print("Deletions and Insertions: ");
    System.out.println(dp[first.length][second.length]);
  }
}
