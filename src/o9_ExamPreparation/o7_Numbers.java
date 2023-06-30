package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o7_Numbers {

  private static final StringBuilder SB = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());

    combs(n, n, "");
    System.out.println(SB);
  }

  private static void combs(int target, int max, String combination) {
    if (target == 0) {
      SB.append(combination, 0, combination.length() -  3).append(System.lineSeparator());
      return;
    }
    if (target < 0) {
      return;
    }

    for(int i = max; i > 0 ; i--) {
      if (i <= target) {
        combs(target - i, i, combination + i + " + ");
      }
    }
  }
}
