package o5_GraphTheoryTraversalAndShortestPathsLab;

import java.util.*;

public class o2_TopologicalSortingDFS {
  public static void main(String[] args) {

    Map<String, List<String>> graph = new LinkedHashMap<>();
    graph.put("A", new ArrayList<>(Arrays.asList("B", "C")));
    graph.put("B", new ArrayList<>(Arrays.asList("D", "E")));
    graph.put("C", new ArrayList<>(List.of("F")));
    graph.put("D", new ArrayList<>(Arrays.asList("C", "F")));
    graph.put("E", new ArrayList<>(List.of("D")));
    graph.put("F", new ArrayList<>());

    Collection<String> output = topSort(graph);

    System.out.println("Topological sorting:");
    System.out.println(String.join(", ", output));
  }

  public static Collection<String> topSort(Map<String, List<String>> graph) {
    List<String> sorted = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    Set<String> detectCycles = new HashSet<>();

    for (var node : graph.entrySet()) {
      String key = node.getKey();
      if (!visited.contains(key)) {
        dfs(key, visited, graph, sorted, detectCycles);
      }
    }

    Collections.reverse(sorted);

    return sorted;
  }

  private static void dfs(
      String key,
      Set<String> visited,
      Map<String, List<String>> graph,
      List<String> sorted,
      Set<String> detectCycles) {
    if (detectCycles.contains(key)) {
      throw new IllegalArgumentException();
    }

    if (!visited.contains(key)) {
      visited.add(key);
      detectCycles.add(key);

      for (String child : graph.get(key)) {
        dfs(child, visited, graph, sorted, detectCycles);
      }
      detectCycles.remove(key);
      sorted.add(key);
    }
  }
}
