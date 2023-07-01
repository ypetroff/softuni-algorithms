package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _11_Socks {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] left =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    int[] right =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    int[][] dp = new int[left.length + 1][right.length + 1];

    for (int i = 1; i <= left.length; i++) {
      for (int j = 1; j <= right.length; j++) {
        if (left[i - 1] == right[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max( dp[i - 1][j],  dp[i][j - 1]);
        }
      }
    }

    System.out.println(dp[left.length][right.length]);
  }
}
