package o4_SearchingSortingAndGreedyAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 Merge sort example algorithm.
 Reads an array of integers from the standard input, performs merge sort on the array,
 and prints the sorted array to the standard output.
 */
public class o2_MergeSort {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] array = Arrays.stream(reader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

    mergeSort(array, 0, array.length - 1);

    System.out.println(arrayToString(array));
  }

  private static String arrayToString(int[] array) {
    StringJoiner joiner = new StringJoiner(" ");
    Arrays.stream(array)
          .mapToObj(String::valueOf)
          .forEach(joiner::add);
    return joiner.toString();
  }

  private static void mergeSort(int[] array, int start, int end) {
    if (start >= end) {
      return;
    }

    int mid = (start + end) / 2;

    mergeSort(array, start, mid);
    mergeSort(array, mid + 1, end);

    merge(array, start, mid, end);
  }

  private static void merge(int[] array, int start, int mid, int end) {
    if (mid < 0 || mid > array.length || array[mid] <= array[mid + 1]) {
      return;
    }

    int[] helper = array.clone();
    int left = start;
    int right = mid + 1;

    for (int i = start; i <= end; i++) {
      if (left > mid) {
        array[i] = helper[right++];
      } else if (right > end) {
        array[i] = helper[left++];
      } else if (helper[left] < helper[right]) {
        array[i] = helper[left++];
      } else {
        array[i] = helper[right++];
      }
    }
  }
}
