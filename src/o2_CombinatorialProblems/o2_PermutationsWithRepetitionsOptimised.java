package o2_CombinatorialProblems;

import java.util.Arrays;
import java.util.Scanner;

public class o2_PermutationsWithRepetitionsOptimised {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String[] elements = scanner.nextLine().split("\\s+");

    Arrays.sort(elements);

    permute(elements, 0, elements.length - 1);
  }

  private static void permute(String[] arr, int start, int end) {
    print(arr);
    for (int left = end - 1; left >= start; left--) {
      for (int right = left + 1; right <= end; right++) {
        if (!arr[left].equals(arr[right])) {
          swap(arr, left, right);
          permute(arr, left + 1, end);
        }
      }
      String firstElement = arr[left];
      for (int i = left; i <= end - 1; i++) {
        arr[i] = arr[i + 1];
        arr[end] = firstElement;
      }
    }
  }

  private static void swap(String[] arr, int first, int second) {
    String temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }

  private static void print(String[] arr) {
    System.out.println(String.join(" ", arr));
  }
}
