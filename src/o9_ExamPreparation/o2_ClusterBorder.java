package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class o2_ClusterBorder {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] singleTime =
            Arrays.stream(reader.readLine().split("\\s+"))
                  .mapToInt(Integer::parseInt)
                  .toArray();

    int[] pairsTime =
                  Arrays.stream(reader.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

    int[] dp = new int[singleTime.length + 1];
    dp[1] = singleTime[0];

    for (int i = 2; i <= singleTime.length; i++) {
      int singleShip = singleTime[i - 1] + dp[i - 1];
      int pairShips = pairsTime[i - 2] + dp[i - 2];
      dp[i] = Math.min(singleShip, pairShips);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Optimal Time: ").append(dp[dp.length - 1]).append(System.lineSeparator());

    List<String> ordering = new ArrayList<>();

    for (int i = dp.length - 1; i > 0; i--) {
      int singleShip = singleTime[i - 1] + dp[i - 1];

      String output =
          dp[i] - singleShip == 0
              ? String.format("Single %d", i)
              : String.format("Pair of %d and %d", i - 1, i--);

      ordering.add(output);
    }

    Collections.reverse(ordering);

    ordering.forEach(x -> sb.append(x).append(System.lineSeparator()));

    System.out.println(sb);
  }
}
