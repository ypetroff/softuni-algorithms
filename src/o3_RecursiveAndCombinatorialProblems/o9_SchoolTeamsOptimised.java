package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class o9_SchoolTeamsOptimised {
  private static final int GIRLS = 3;
  private static final int BOYS = 2;
  private static final String[] combination = new String[GIRLS];
  private static final List<String[]> allCombinations = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] girls = reader.readLine().split(", ");
    String[] boys = reader.readLine().split(", ");

    if (girls.length + boys.length == GIRLS + BOYS) {
      sb.append(String.join(", ", girls)).append(", ").append(String.join(", ", boys));
      System.out.println(sb);
      return;
    }

    combinations(girls, 0, 0, GIRLS);
    combinations(boys, 0, 0, BOYS);

    printCombinations(girls.length);
  }

  private static void printCombinations(int girlsCount) {
    int totalGirlsCombinations = getCombinationsCount(girlsCount, GIRLS);


    for (int i = 0; i < totalGirlsCombinations; i++) {
      String[] girls = allCombinations.get(i);
      for (int j = totalGirlsCombinations;
          j < allCombinations.size();
          j++) {
        String[] boys = Arrays.copyOfRange(allCombinations.get(j), 0, BOYS);
        System.out.println(String.join(", ", girls) + ", " + String.join(", ", boys));
      }
    }
  }

  private static int getCombinationsCount(int n, int k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
  }

  private static int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  private static void combinations(String[] group, int index, int start, int k) {
    if (index == k) {
      allCombinations.add(combination.clone());
      return;
    }
    for (int i = start; i < group.length; i++) {
      combination[index] = group[i];
      combinations(group, index + 1, i + 1, k);
    }
  }
}
