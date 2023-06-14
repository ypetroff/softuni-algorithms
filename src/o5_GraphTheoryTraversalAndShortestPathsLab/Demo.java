package o5_GraphTheoryTraversalAndShortestPathsLab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
  private static final int N = 9;
  private static final String[] INPUT = {
    "3 6", "3 4 5 6", "8", "0 1 5", "1 6", "1 3", "0 1 4", " ", "2"
  };
  private static final List<List<Integer>> GRAPH_AS_LIST = new ArrayList<>();
  private static int[][] graphAsArray;

  public static void main(String[] args) {

    graphAsArray = new int[N][N];

    for (int i = 0; i < N; i++) {
      String current = INPUT[i];

      if (current.trim().length() == 0) {
        fillGraphAsList(new ArrayList<>());
        continue;
      }

      int[] ints = Arrays.stream(current.split("\\s+")).mapToInt(Integer::parseInt).toArray();

      fillGraphAsArray(i, ints);
      List<Integer> tempList = arrToList(ints);
      fillGraphAsList(tempList);
    }
    System.out.println("Array representation:");
    for (int[] ints : graphAsArray) {
      Arrays.stream(ints)
          .mapToObj(String::valueOf)
          .map(x -> String.format("%s ", x))
          .forEach(System.out::print);
      System.out.println();
    }
    System.out.println();
    System.out.println("List representation:");
    for (int i = 0; i < GRAPH_AS_LIST.size(); i++) {
      System.out.printf("node: %d connections: %s%n", i, GRAPH_AS_LIST.get(i));
    }
  }

  private static void fillGraphAsArray(int index, int[] ints) {
    for (int i : ints) {
      graphAsArray[index][i] = 1;
    }
  }

  private static void fillGraphAsList(List<Integer> list) {
    GRAPH_AS_LIST.add(list);
  }

  private static List<Integer> arrToList(int[] arr) {
    List<Integer> list = new ArrayList<>();
    for (int element : arr) {
      list.add(element);
    }
    return list;
  }
}
