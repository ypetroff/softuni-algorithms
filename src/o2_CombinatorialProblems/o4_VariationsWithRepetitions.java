package o2_CombinatorialProblems;

import java.util.Scanner;

public class o4_VariationsWithRepetitions {
  private static String[] elements;
  private static String[] variations;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    elements = scanner.nextLine().split("\\s+");

    int k = Integer.parseInt(scanner.nextLine());

    variations = new String[k];

    findVariations(0);
  }

  private static void findVariations(int index) {
    if (index == variations.length) {
      System.out.println(String.join(" ", variations));
      return;
    }

    for (String element : elements) {
      variations[index] = element;
      findVariations(index + 1);
    }
  }
}