package o2_CombinatorialProblems;

import java.util.HashSet;
import java.util.Scanner;

public class o2_PermutationsWithRepetitions {
  private static String[] elements;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    elements = scanner.nextLine().split("\\s+");

    permute(0);
  }

  private static void permute(int index) {
    if (index == elements.length) {
      print();
      return;
    }

    permute(index + 1);
    HashSet<String> swapped = new HashSet<>();
    swapped.add(elements[index]);

    for (int i = index + 1; i < elements.length; i++) {
      if (!swapped.contains(elements[i])) {
        swap(index, i);
        permute(index + 1);
        swap(index, i);
        swapped.add(elements[i]);
      }
    }
  }

  private static void swap(int first, int second) {
    String temp = elements[first];
    elements[first] = elements[second];
    elements[second] = temp;
  }

  private static void print() {
    System.out.println(String.join(" ", elements));
  }
}
