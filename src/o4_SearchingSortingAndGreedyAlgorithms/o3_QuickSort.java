package o4_SearchingSortingAndGreedyAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class o3_QuickSort {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int[] arr = Arrays.stream(reader.readLine().split(" "))
                      .mapToInt(Integer::parseInt)
                      .toArray();

    quickSort(arr, 0, arr.length - 1);

    System.out.println(arrayToString(arr));
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      int pi = partition(arr, low, high);

      quickSort(arr, low, pi - 1);
      quickSort(arr, pi + 1, high);
    }
  }

  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (arr[j] <= pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
  }

  private static String arrayToString(int[] array) {
    StringJoiner joiner = new StringJoiner(" ");
    Arrays.stream(array).mapToObj(String::valueOf).forEach(joiner::add);
    return joiner.toString();
  }
}
