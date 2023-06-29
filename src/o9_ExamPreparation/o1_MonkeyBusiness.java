package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o1_MonkeyBusiness {

  private static final StringBuilder sb = new StringBuilder();
  private static int[] numbers;
  private static int[] expression;
  private static int solutions = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    numbers = new int[n];
    expression = new int[n];

    for (int i = 1; i <= n; i++) {
      numbers[i - 1] = i;
    }

    findSolutions(0);

    sb.append("Total Solutions: ").append(solutions);
    System.out.println(sb);
  }

  private static void findSolutions(int index) {
    if (index == numbers.length) {
      verifySolution();
      return;
    }

    expression[index] = numbers[index];
    findSolutions(index + 1);

    expression[index] = -numbers[index];
    findSolutions(index + 1);
  }

  private static void verifySolution() {
    int sum = Arrays.stream(expression).sum();
    if (sum == 0) {
      for (int number : expression) {
        if (number < 0) {
          sb.append(number).append(" ");
        } else {
          sb.append("+").append(number).append(" ");
        }
      }
      sb.append(System.lineSeparator());
      solutions++;
    }
  }
}
