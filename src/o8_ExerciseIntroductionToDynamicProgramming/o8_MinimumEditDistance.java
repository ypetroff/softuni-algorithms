package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o8_MinimumEditDistance {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int replaceCost = Integer.parseInt(br.readLine());
    int insertCost = Integer.parseInt(br.readLine());
    int deleteCost = Integer.parseInt(br.readLine());

    char[] first = br.readLine().toCharArray();
    char[] second = br.readLine().toCharArray();

    int[][] dp = new int[first.length + 1][second.length + 1];

    for(int i = 1; i <= second.length; i++) {
      dp[0][i] = dp[0][ i - 1] + insertCost;
    }

    for (int i = 1; i <= first.length; i++) {
      dp[i][0] = dp[i - 1][0] + deleteCost;
    }

    for (int i = 1; i <= first.length; i++) {
      for (int j = 1; j <= second.length; j++) {
       if (first[i - 1] == second[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
         int delete = dp[i - 1][j] + deleteCost;
         int insert = dp[i][j - 1] + insertCost;
         int replace = dp[i - 1][j - 1] + replaceCost;

          dp[i][j] = Math.min(replace, Math.min(insert, delete));
        }
      }
    }
    System.out.println("Minimum edit distance: " + dp[first.length][second.length]);
  }
}
