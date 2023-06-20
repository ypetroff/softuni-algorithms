package o6_ExerciseGraphTheoryTraversalAndShortestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class o5_BreakCycles {
  private static final Map<String, List<String>> GRAPH = new TreeMap<>();
  private static final Set<String> VISITED = new HashSet<>();
  private static final Set<String> CYCLES = new HashSet<>();
  private static final Deque<String[]> EDGES_TO_REMOVE = new ArrayDeque<>();
  private static boolean hasCycles = false;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String input = reader.readLine();

    while (input != null && !input.equals("")) {
      String[] split = input.split(" -> ");
      String node = split[0];
      List<String> edges = Arrays.stream(split[1].split("\\s+")).collect(Collectors.toList());

      GRAPH.putIfAbsent(node, new ArrayList<>());
      GRAPH.get(node).addAll(edges);

      input = reader.readLine();
    }

    addIndirectlyProvidedNodes();
    List<List<String>> connectedComponents = getConnectedComponents();

    for (List<String> component : connectedComponents) {
      makeAcyclic(convertToMap(component));
    }

    System.out.println("Edges to remove: " + EDGES_TO_REMOVE.size());
    for (String[] edge : EDGES_TO_REMOVE) {
      System.out.printf("%s - %s%n", edge[0], edge[1]);
    }
  }

  private static void addIndirectlyProvidedNodes() {
    Set<String> nodes = GRAPH.keySet();
    Set<String> missingNodes =
        GRAPH.values().stream()
            .flatMap(List::stream)
            .filter(x -> !nodes.contains(x))
            .collect(Collectors.toSet());

    if (missingNodes.size() > 0) {
      for (String node : missingNodes) {
        GRAPH.putIfAbsent(node, new ArrayList<>());
        List<String> nodesToAdd = new ArrayList<>();

        for (var key : GRAPH.keySet()) {
          GRAPH.get(key).stream().filter(x -> x.contains(node)).forEach(x -> nodesToAdd.add(key));
        }

        for (String n : nodesToAdd) {
          GRAPH.get(node).add(n);
        }
      }
    }
  }

  private static Map<String, List<String>> convertToMap(List<String> component) {
    Map<String, List<String>> result = new TreeMap<>();
    for (String node : component) {
      result.put(node, GRAPH.get(node));
    }
    return result;
  }

  private static void makeAcyclic(Map<String, List<String>> component) {
    for (String node : component.keySet()) {
      dfs(node, "", component);
      if (!hasCycles) {
        return;
      }

      List<String> children = component.get(node);
      Collections.sort(children);

      int index = 0;

      while (index < children.size() && children.size() > 1) {
        String child = children.get(index);
        Map<String, List<String>> tempGraph = copyMap(component);
        VISITED.clear();

        String[] edge = {node, child};
        removeEdge(tempGraph, node, child);

        boolean connected = areConnected(node, child, tempGraph);
        if (!connected) {

          index++;
          continue;
        }

        EDGES_TO_REMOVE.add(edge);
        removeEdge(component, node, child);
      }
    }
  }

  private static Map<String, List<String>> copyMap(Map<String, List<String>> component) {
    Map<String, List<String>> copy = new TreeMap<>();
    for (var entry : component.entrySet()) {
      copy.put(entry.getKey(), new ArrayList<>());
      copy.get(entry.getKey()).addAll(entry.getValue());
    }
    return copy;
  }

  private static void removeEdge(Map<String, List<String>> component, String node, String child) {

    component.get(node).remove(child);
    component.get(child).remove(node);
  }

  private static void dfs(String node, String prevNode, Map<String, List<String>> component) {
    if (hasCycles) {
      return;
    }

    if (CYCLES.contains(node)) {
      hasCycles = true;
      return;
    }

    if (VISITED.contains(node)) {
      return;
    }

    CYCLES.add(node);
    VISITED.add(node);

    List<String> children = component.get(node);

    if (children == null) {
      return;
    }

    for (String child : children) {
      if (child.equals(prevNode)) {
        long occurrences = children.stream().filter(x -> x.equals(child)).count();
        if (occurrences > 1) {
          hasCycles = true;
          return;
        }
        continue;
      }
      dfs(child, node, component);
    }

    CYCLES.remove(node);
  }

  private static List<List<String>> getConnectedComponents() {
    List<List<String>> result = new ArrayList<>();

    for (String node : GRAPH.keySet()) {
      if (!VISITED.contains(node)) {
        result.add(new ArrayList<>());
        bfs(node, result);
      }
    }
    VISITED.clear();
    return result;
  }

  private static boolean areConnected(
      String start, String end, Map<String, List<String>> component) {
    Deque<String> queue = new ArrayDeque<>();

    queue.offer(start);
    VISITED.add(start);

    while (!queue.isEmpty()) {
      String node = queue.poll();
      if (node.equals(end)) {
        return true;
      }
      for (String child : component.get(node)) {
        if (!VISITED.contains(child)) {
          VISITED.add(child);
          queue.offer(child);
        }
      }
    }
    return false;
  }

  private static void bfs(String source, List<List<String>> result) {
    Deque<String> queue = new ArrayDeque<>();
    VISITED.add(source);
    queue.offer(source);

    while (!queue.isEmpty()) {
      String node = queue.poll();

      result.get(result.size() - 1).add(node);

      for (String child : GRAPH.get(node)) {
        if (!VISITED.contains(child)) {
          VISITED.add(child);
          queue.offer(child);
        }
      }
    }
  }
}
