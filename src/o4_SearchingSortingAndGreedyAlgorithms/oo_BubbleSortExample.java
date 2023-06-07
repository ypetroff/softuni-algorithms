package o4_SearchingSortingAndGreedyAlgorithms;

import java.util.StringJoiner;

/*
* Example of Bubble sort algorithm with hardcoded values.
* Two different implementations
* */
public class oo_BubbleSortExample {
  public static void main(String[] args) {
    int[] arr = {11, 22, 1, 2, 55, 666, 999, 0};

    System.out.println(bubbleSort(arr.clone()));

    System.out.println(bubbleSort2(arr.clone()));
  }

  private static String arrayToString(int[] arr) {
    StringJoiner joiner = new StringJoiner(", ");
    for (int e : arr) {
      joiner.add(String.valueOf(e));
    }
    return joiner.toString();
  }

  private static String bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if(arr[i] > arr[j]) {
          swap(arr, i, j);
        }
      }
    }
    return arrayToString(arr);
  }

  private static String bubbleSort2(int[] arr) {
    boolean swapped;
    for (int i = 0; i < arr.length - 1; i++) {
      swapped = false;
      for (int index = 0; index < arr.length - i - 1; index++) {
        if (arr[index] > arr[index + 1]) {
          swap(arr, index, index + 1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
    return arrayToString(arr);
  }

  private static void swap(int[] arr, int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }
}
