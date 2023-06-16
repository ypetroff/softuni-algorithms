package o6_ExerciseGraphTheoryTraversalAndShortestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o3_CyclesInGraph {
  private static final Map<String, Set<String>> GRAPH = new HashMap<>();
  private static final Set<String> visited = new HashSet<>();
  private static final Set<String> cycles = new HashSet<>();
  private static boolean detectedCycle;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String source = null;

    String input = reader.readLine();

    while (!input.equalsIgnoreCase("end")) {
      String[] split = input.split("-");
      String node1 = split[0];
      String node2 = split[1];

      if (source == null) {
        source = node1;
      }

      GRAPH.putIfAbsent(node1, new HashSet<>());
      GRAPH.get(node1).add(node2);
      GRAPH.putIfAbsent(node2, new HashSet<>());
      GRAPH.get(node2).add(node1);

      input = reader.readLine();
    }

      dfs(source, "");

    String output = detectedCycle ? "No" : "Yes";
    System.out.printf("Acyclic: %s", output);
  }

  private static void dfs(String node, String prevNode) {
    if (cycles.contains(node)) {
      detectedCycle = true;
      return;
    }
    if (visited.contains(node)) {
      return;
    }
    cycles.add(node);
    visited.add(node);

    Set<String> children = GRAPH.get(node);

    if (children == null) {
      return;
    }

    for (String child : children) {
      if (child.equals(prevNode)) {
        continue;
      }
      dfs(child, node);
    }
    cycles.remove(node);
  }
}
