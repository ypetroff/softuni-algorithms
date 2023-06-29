package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o4_SumWithUnlimitedCoins {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] coins =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    int targetSum = Integer.parseInt(reader.readLine());

    int[] dp = new int[targetSum + 1];

    dp[0] = 1;

    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= targetSum; j++) {
        dp[j] += dp[j - coins[i]];
      }
    }
    System.out.println(dp[targetSum]);
  }
}
