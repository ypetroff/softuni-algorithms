package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class o9_SchoolTeams {
  private static final int GIRLS = 3;
  private static final int BOYS = 2;
  private static final String[] combination = new String[GIRLS];
  private static final List<String[]> allGirlsCombinations = new ArrayList<>();
  private static final List<String[]> allBoysCombinations = new ArrayList<>();

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

    printCombinations();
  }

    private static void printCombinations() {
    for (String[] girls : allGirlsCombinations) {
        for (String[] boys : allBoysCombinations) {
        System.out.println(String.join(", ", girls) + ", " + String.join(", ", boys));
        }
    }
    }

    private static void combinations(String[] group, int index, int start, int k) {
    if (index == k) {
        if (k == 3) {
            allGirlsCombinations.add(Arrays.copyOf(combination, combination.length));
        } else if(k == 2){
            allBoysCombinations.add(Arrays.copyOfRange(combination, 0, 2));
        }
        return;
    }
    for (int i = start; i < group.length; i++) {
        combination[index] = group[i];
        combinations(group, index + 1, i + 1, k);
    }
  }
}
