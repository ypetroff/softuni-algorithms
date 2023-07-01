package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o8_TheTyrant {

  private static final int REQUIRED_SIZE = 4;
  private static int[] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] sequence =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    if (sequence.length <= REQUIRED_SIZE) {
      System.out.println(minValue(sequence, sequence.length));
      return;
    }

    dp = new int[sequence.length];
    populateInitialValuesOfDP(sequence);

    System.out.println(minSum(sequence, sequence.length));
  }

  private static void populateInitialValuesOfDP(int[] sequence) {
    System.arraycopy(sequence, 0, dp, 0, 4);
  }

  private static int minValue(int[] sequence, int length) {
    if (length == 1) {
      return sequence[0];
    } else if (length == 2) {
      return Math.min(sequence[0], sequence[1]);
    } else if (length == 3) {
      return Math.min(sequence[0], Math.min(sequence[1], sequence[2]));
    } else {
      return Math.min(Math.min(sequence[0], sequence[1]), Math.min(sequence[2], sequence[3]));
    }
  }

  private static int minSum(int[] sequence, int end) {

      for(int i = REQUIRED_SIZE; i < end; i++) {
dp[i] = sequence[i] + Math.min(Math.min(dp[i - 1], dp[i - 2]), Math.min(dp[i - 3], dp[i - 4]));
      }
    return Math.min(Math.min(dp[end - 1], dp[end - 2]), Math.min(dp[end - 3], dp[end - 4]));
  }
}
