package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o9_o3_Gateways {
  private static boolean[] visited;
  private static int[] prevNodes;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int nodes = Integer.parseInt(reader.readLine());

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < nodes + 1; i++) {
      graph.add(new ArrayList<>());
    }

    int edges = Integer.parseInt(reader.readLine());

    for (int index = 0; index < edges; index++) {
      int[] destinationAndSource =
          Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
      int source = destinationAndSource[0];
      int destination = destinationAndSource[1];
      graph.get(source).add(destination);
    }

    visited = new boolean[graph.size()];
    prevNodes = new int[graph.size()];
    Arrays.fill(prevNodes, -1);

    int start = Integer.parseInt(reader.readLine());
    int end = Integer.parseInt(reader.readLine());

    bfs(start, end, graph);

    if (end < prevNodes.length && prevNodes[end] != -1) {
      printShortestPath(start, end);
    }
  }

  private static void printShortestPath(int start, int end) {
    List<Integer> path = new ArrayList<>();
    path.add(end);
    int current = end;

    while (current != start) {
      current = prevNodes[current];
      path.add(current);
    }
    StringBuilder sb = new StringBuilder();
    Collections.reverse(path);
    path.forEach(x -> sb.append(x).append(" "));
    System.out.println(sb.toString().trim());
  }

  private static void bfs(int start, int end, List<List<Integer>> graph) {
    Deque<Integer> queue = new ArrayDeque<>();

    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      if (node == end) {
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
}
