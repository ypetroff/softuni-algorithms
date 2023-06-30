package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o5_Stairs {
  private static long[] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());

    if (n < 4) {
      System.out.println(n);
      return;
    }

    dp = new long[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 3;

    System.out.println(permute(n));
  }

  private static long permute(int step) {
    if (step == 0) {
      return 0;
    }

    if (dp[step] != 0) {
      return dp[step];
    }

    return dp[step] = permute(step - 1) + permute(step - 2);
  }
}
