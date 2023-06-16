package o6_ExerciseGraphTheoryTraversalAndShortestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class o4_Salaries {
  private static final List<List<Integer>> GRAPH = new ArrayList<>();
  private static long[] salaries;
  private static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int employees = Integer.parseInt(reader.readLine());

    salaries = new long[employees];
    visited = new boolean[employees];

    int[] managersCount = new int[employees];

    for (int i = 0; i < employees; i++) {
      GRAPH.add(new ArrayList<>());

      char[] chars = reader.readLine().toCharArray();
      for (int j = 0; j < chars.length; j++) {
        if (chars[j] == 'Y') {
          managersCount[j]++;
          GRAPH.get(i).add(j);
        }
      }
    }
    List<Integer> sources =
        IntStream.range(0, managersCount.length)
                 .filter(x -> managersCount[x] == 0)
                 .boxed()
                 .toList();

    for (Integer source : sources) {
      dfs(source);
    }

    long sum = Arrays.stream(salaries).sum();
    System.out.println(sum);
  }

  private static void dfs(Integer node) {
    if (visited[node]) {
      return;
    }

    visited[node] = true;

    for (Integer child : GRAPH.get(node)) {
      if (!visited[child]) {
        dfs(child);
        getSum(child);
      }
    }
    getSum(node);
  }

  private static void getSum(Integer node) {
    long sum = GRAPH.get(node).stream().mapToLong(c -> salaries[c]).sum();

    salaries[node] = sum == 0 ? 1 : sum;
  }
}
