package o8_ExerciseIntroductionToDynamicProgramming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class o2_LongestZigzagSubsequence {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int[] numbers =
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    int[][] dp = new int[numbers.length + 1][2];
    int[][] prev = new int[numbers.length + 1][2];

    // greater
    dp[0][0] = 1;
    // lesser
    dp[0][1] = 1;

    prev[0][0] = -1;
    prev[0][1] = -1;

    int maxLength = 0;
    int[] bestIndex = new int[2];

    for (int index = 0; index < numbers.length; index++) {
      int currentNum = numbers[index];
      for (int prevIndex = index - 1; prevIndex >= 0; prevIndex--) {
        int prevNum = numbers[prevIndex];
        if (currentNum > prevNum && dp[index][0] <= dp[prevIndex][1] + 1) {
          dp[index][0] = dp[prevIndex][1] + 1;
          prev[index][0] = prevIndex;
        }
        if (currentNum < prevNum && dp[index][1] <= dp[prevIndex][0] + 1) {
          dp[index][1] = dp[prevIndex][0] + 1;
          prev[index][1] = prevIndex;
        }
      }

      if (maxLength < dp[index][0]) {
        maxLength = dp[index][0];
        bestIndex[0] = index;
        bestIndex[1] = 0;
      }

      if (maxLength < dp[index][1]) {
        maxLength = dp[index][1];
        bestIndex[0] = index;
        bestIndex[1] = 1;
      }
    }
    Deque<Integer> longestZigZag = new ArrayDeque<>();

    int beginRow = bestIndex[0];

    while (beginRow >= 0) {
      longestZigZag.push(numbers[beginRow]);
      beginRow = prev[beginRow][bestIndex[1]];
      bestIndex[1] = bestIndex[1] == 0 ? 1 : 0;
    }

    StringBuilder sb = new StringBuilder();

    while (!longestZigZag.isEmpty()) {
      sb.append(longestZigZag.pop()).append(" ");
    }

    System.out.println(sb);
  }
}
