package o2_CombinatorialProblems;

import java.util.Scanner;

public class o3_VariationsWithoutRepetitions {
  private static String[] elements;
  private static String[] variations;
  private static boolean[] used;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    elements = scanner.nextLine().split("\\s+");

    used = new boolean[elements.length];

    int k = Integer.parseInt(scanner.nextLine());

    variations = new String[k];

    findVariations(0);
  }

  private static void findVariations(int index) {
    if (index == variations.length) {
      System.out.println(String.join(" ", variations));
      return;
    }

    for (int i = 0; i < elements.length; i++) {
      if (!used[i]) {
        used[i] = true;
        variations[index] = elements[i];
        findVariations(index + 1);
        used[i] = false;
      }
    }
  }
}