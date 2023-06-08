package o4_SearchingSortingAndGreedyAlgorithms;

import java.util.Arrays;
/*
* This is inefficient algorithm like bubble and selection sort,
* but in addition it is not suitable if we have negative numbers.
* To overcome this obstacle we need to separate the positive from negative numbers.
* Then with separate counts for both of  them (using Math.abs() for the negative numbers)
* Basically we turn the negatives to positives do the sorting and print first the negatives
* with the necessary format, namely minus sign before each negative,
* and then print the positives. One final thing to consider is that the larger negative numbers are smaller.
* */
public class oo_CountSortExample {
  public static void main(String[] args) {
    int[] arr = {11, 22, 1, 2, 15, 6, 9, 0};

    int max = Arrays.stream(arr)
                    .max()
                    .orElse(0);

    int[] counts = new int[max + 1];

    countSort(arr, counts);

    for (int index = 0; index < counts.length; index++) {
      if (counts[index] != 0) {
        for (int i = 0; i < counts[index]; i++) {
          System.out.print(index + " ");
        }
      }
    }
  }

  private static void countSort(int[] arr, int[] counts) {
    for (int j : arr) {
      counts[j]++;
    }
  }
}
