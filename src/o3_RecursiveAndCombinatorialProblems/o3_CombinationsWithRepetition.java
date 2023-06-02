package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class o3_CombinationsWithRepetition {
  private static int[] elements;
  private static int[] combinations;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    int k = Integer.parseInt(reader.readLine());

    elements = IntStream.range(1, n + 1).toArray();
    combinations = new int[k];

    findCombinationsAndPrintThem(0, 0);
  }

  private static void findCombinationsAndPrintThem(int index, int start) {
    if (index == combinations.length) {
      print();
      return;
    }

    for (int i = start; i < elements.length; i++) {
      combinations[index] = elements[i];
      findCombinationsAndPrintThem(index + 1, i);
    }
  }

  private static void print() {
    for (int combination : combinations) {
      System.out.print(combination + " ");
    }
    System.out.println();
  }
}
