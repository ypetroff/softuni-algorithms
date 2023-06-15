package o6_ExerciseGraphTheoryTraversalAndShortestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o1_DistanceBetweenVertices {
  private static final List<List<Integer>> graph = new ArrayList<>();
  private static final List<int[]> pathsToFollow = new ArrayList<>();
  private static final Set<Integer> detectCycles = new HashSet<>();
  private static int[] prevNodes;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int numberOfVertices = Integer.parseInt(reader.readLine());
    int numberOfPairs = Integer.parseInt(reader.readLine());

    addVertices(0, numberOfVertices);

    addEdgesToVertices(reader, numberOfVertices);

    prevNodes = new int[graph.size()];
    Arrays.fill(prevNodes, -1);

    addPathsToFollow(reader, numberOfPairs);

    for (int[] path : pathsToFollow) {
      int source = path[0];
      int destination = path[1];
      bsf(source, destination);
      printShortestPath(source, destination);
      detectCycles.clear();
    }
  }

  private static void printShortestPath(int start, int end) {
    List<Integer> path = new ArrayList<>();
    path.add(end);
    int current = end;

    while (current != start && current != -1) {
      if (!detectCycles.contains(current)) {
        detectCycles.add(current);
        current = prevNodes[current];
        path.add(current);
        continue;
      }
      current = -1;
    }

    int distance = current != -1 ? path.size() - 1 : -1;
    System.out.printf("{%d, %d} -> %d%n", start, end, distance);
  }

  private static void bsf(int source, int destination) {
    Deque<Integer> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[graph.size()];
    queue.offer(source);
    visited[source] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      if (node == destination || graph.get(node).size() == 0) {
        return;
      }
      for (Integer child : graph.get(node)) {

        if (!visited[child]) {
          visited[child] = true;
          prevNodes[child] = node;
          queue.offer(child);
        }
      }
    }
  }

  private static void addPathsToFollow(BufferedReader reader, int numberOfPairs)
      throws IOException {
    for (int i = 0; i < numberOfPairs; i++) {
      int[] path =
          Arrays.stream(reader.readLine().split("-")).mapToInt(Integer::parseInt).toArray();
      pathsToFollow.add(path);
    }
  }

  private static void addEdgesToVertices(BufferedReader reader, int numberOfVertices)
      throws IOException {
    for (int i = 0; i < numberOfVertices; i++) {
      String[] vertexAndItsEdges = reader.readLine().split(":");

      int vertex = Integer.parseInt(vertexAndItsEdges[0]);

      if (vertex >= graph.size()) {
        addVertices(graph.size(), vertex);
      }

      if (vertexAndItsEdges.length == 1) {
        continue;
      }

      List<Integer> edges =
          Arrays.stream(vertexAndItsEdges[1].split("\\s+")).map(Integer::parseInt).toList();

      graph.get(vertex).addAll(edges);
    }
  }

  private static void addVertices(int start, int end) {
    for (int i = start; i < end + 1; i++) {
      graph.add(new ArrayList<>());
    }
  }
}
