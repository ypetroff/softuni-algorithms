package o4_SearchingSortingAndGreedyAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o5_SetCovers {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] elements = reader.readLine().substring(10).split(", ");
    int[] universe = new int[elements.length];
    for (int i = 0; i < elements.length; i++) {
      universe[i] = Integer.parseInt(elements[i]);
    }
    int numberOfSets = Integer.parseInt(reader.readLine().substring(16));
    List<int[]> sets = new ArrayList<>();
    for (int i = 0; i < numberOfSets; i++) {
      String[] setElements = reader.readLine().split(", ");
      int[] set = new int[setElements.length];
      for (int j = 0; j < setElements.length; j++) {
        set[j] = Integer.parseInt(setElements[j]);
      }
      sets.add(set);
    }
    List<int[]> chosenSets = chooseSets(sets, universe);

    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Sets to take (%d):%n", chosenSets.size()));
    for (int[] set : chosenSets) {
      sb.append("{ ");
      sb.append(Arrays.toString(set).replaceAll("[\\[\\]]", ""));
      sb.append(" }").append(System.lineSeparator());
    }
    System.out.println(sb);
  }

  public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
    List<int[]> chosenSets = new ArrayList<>();
    Set<Integer> remainingElements = new HashSet<>();
    for (int element : universe) {
      remainingElements.add(element);
    }

    sets.sort((a, b) -> Integer.compare(b.length, a.length));

    for (int[] set : sets) {
      int covered = 0;
      for (int element : set) {
        if (remainingElements.contains(element)) {
          covered++;
        }
      }

      if (covered > 0) {
        chosenSets.add(set);
        for (int element : set) {
          remainingElements.remove(element);
        }
      }

      if (remainingElements.isEmpty()) {
        break;
      }
    }

    return chosenSets;
  }
}
