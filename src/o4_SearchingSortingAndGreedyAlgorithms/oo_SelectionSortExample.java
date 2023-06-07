package o4_SearchingSortingAndGreedyAlgorithms;

import java.util.StringJoiner;
/*
* Example of Selection sort algorithm with hardcoded values.
* */
public class oo_SelectionSortExample {
  public static void main(String[] args) {
    int[] arr = {11, 22, 1, 2, 55, 666, 999, 0};

    selectionSort(arr);

    String output = arrayToString(arr);

    System.out.println(output);
  }

  private static String arrayToString(int[] arr) {
    StringJoiner joiner = new StringJoiner(", ");
    for (int e : arr) {
      joiner.add(String.valueOf(e));
    }
    return joiner.toString();
  }

  private static void selectionSort(int[] arr) {
    for (int index = 0; index < arr.length; index++) {
      int min = index;
      for (int current = index + 1; current < arr.length; current++) {
        if (arr[min] > arr[current]) {
          min = current;
        }
      }
      /*
      * This if statement is placed as an attempt to optimise the algorithm.
      * Basically we skip the swap method,
      * if the element at index position remains the min after the inner loop is completed.
      * */
      if (min != index) {
        swap(arr, index, min);
      }
    }
  }

  private static void swap(int[] arr, int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }
}
