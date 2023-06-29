package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o7_ConnectingCables {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[] cables =
        Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    int[] sockets = Arrays.stream(cables.clone()).sorted().toArray();

    int[][] dp = new int[cables.length + 1][cables.length + 1];

    for (int i = 1; i <= cables.length; i++) {
      for (int j = 1; j <= cables.length; j++) {
          if (cables[i - 1] == sockets[j - 1]) {
              dp[i][j] = dp[i - 1][j - 1] + 1;
          } else {
              dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          }
      }
    }

    System.out.println("Maximum pairs connected: " + dp[cables.length][cables.length]);
  }
}
