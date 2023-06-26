package o7_IntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o2_LongestIncreasingSubsequence {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int[] arr =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    int[] length = new int[arr.length];
    Arrays.fill(length, 1);
    int[] prev = new int[arr.length];
    Arrays.fill(prev, -1);

    int maxLength = 0, indexWithMaxLength = 0;

    for (int i = 0; i < arr.length; i++) {
      int num = arr[i];
      int bestLength = length[i];
      int bestIndex = prev[i];
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j] < num && bestLength <= length[j] + 1) {
          bestLength = length[j] + 1;
          prev[i] = j;
          bestIndex = j;
        }
      }
      length[i] = bestLength;
      prev[i] = bestIndex;

      if (bestLength > maxLength) {
        maxLength = bestLength;
        indexWithMaxLength = i;
      }
    }
    List<Integer> LIS = new ArrayList<>();
    int index = indexWithMaxLength;
    while (index != -1) {
      LIS.add(arr[index]);
      index = prev[index];
    }
    Collections.reverse(LIS);
    StringJoiner joiner = new StringJoiner(" ");
    LIS.stream().map(String::valueOf).forEach(joiner::add);
    System.out.println(joiner);
  }
}
