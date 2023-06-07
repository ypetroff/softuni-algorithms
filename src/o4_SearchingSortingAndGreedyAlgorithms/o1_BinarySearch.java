package o4_SearchingSortingAndGreedyAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o1_BinarySearch {
  private static int[] arr;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    arr = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    int n = Integer.parseInt(reader.readLine());

    System.out.println(findIndexOfANumberWithinArray(n));
  }

    private static int findIndexOfANumberWithinArray(int n) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (n == arr[mid]) {
                return mid;
            } else if(n > arr[mid]){
                start= mid + 1;
            }else if(n < arr[mid]){
                end= mid - 1;
            }
        }
        return -1;
    }
}
