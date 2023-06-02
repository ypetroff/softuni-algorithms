package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class o2_NestedLoopsToRecursion {
  private static String[] arr;
  private static int limit;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    limit = Integer.parseInt(reader.readLine());

    arr = new String[limit];

    printLoopsResultBasedOnLimit(0);
  }

  private static void printLoopsResultBasedOnLimit(int index) {
    if (index == arr.length) {
      System.out.println(String.join(" ", arr));
      return;
    }

    for(int i = 1; i <= limit; i++) {
      arr[index] = String.valueOf(i);
      printLoopsResultBasedOnLimit(index + 1);
    }
  }
}