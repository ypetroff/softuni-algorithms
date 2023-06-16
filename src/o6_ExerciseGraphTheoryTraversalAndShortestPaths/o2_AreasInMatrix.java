package o6_ExerciseGraphTheoryTraversalAndShortestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o2_AreasInMatrix {

  public static class Edge {
    int[] source;
    int[] destination;

    public Edge(int row, int col) {
      this.source = new int[] {row, col};
    }

    private void setDestination(int row, int col) {
      this.destination = new int[] {row, col};
    }
  }

  private static final List<Edge> GRAPH = new ArrayList<>();
  private static char[][] matrix;
  private static boolean[][] visited;
  private static boolean[] visitedNode;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int rows = Integer.parseInt(reader.readLine());
    matrix = new char[rows][];
    visited = new boolean[rows][];

    for (int row = 0; row < rows; row++) {
      matrix[row] = reader.readLine().toCharArray();
      visited[row] = new boolean[matrix[row].length];
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (!visited[i][j]) {
          dfs(i, j, matrix[i][j]);
        }
      }
    }

    visitedNode = new boolean[GRAPH.size()];
    Map<Character, Integer> areas = new TreeMap<>();

    for (int i = 0; i < GRAPH.size(); i++) {
      if (!visitedNode[i]) {
        Edge edge = GRAPH.get(i);
        char key = matrix[edge.source[0]][edge.source[1]];
        areas.putIfAbsent(key, 0);
        areas.put(key, areas.get(key) + 1);
        bfs(i);
      }
    }

    int sum = areas.values().stream().mapToInt(e -> e).sum();
    System.out.printf("Areas: %d%n", sum);
    for (var entry : areas.entrySet()) {
      System.out.printf("Letter '%c' -> %d%n", entry.getKey(), entry.getValue());
    }
  }

  private static void bfs(int source) {
    Deque<Integer> queue = new ArrayDeque<>();

    queue.offer(source);
    visitedNode[source] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      Edge edge = GRAPH.get(node);
      if (edge.destination != null) {
        visitedNode[node + 1] = true;
        queue.offer(node + 1);
      }
    }
  }

  private static void dfs(int row, int col, char symbol) {
    visited[row][col] = true;

    Edge edge = new Edge(row, col);
    GRAPH.add(edge);

    if (isInBounds(row, col + 1) && !visited[row][col + 1] && matrix[row][col + 1] == symbol) {
      GRAPH.get(GRAPH.size() - 1).setDestination(row, col + 1);
      dfs(row, col + 1, symbol);
    }
    if (isInBounds(row, col - 1) && !visited[row][col - 1] && matrix[row][col - 1] == symbol) {
      GRAPH.get(GRAPH.size() - 1).setDestination(row, col - 1);
      dfs(row, col - 1, symbol);
    }
    if (isInBounds(row + 1, col) && !visited[row + 1][col] && matrix[row + 1][col] == symbol) {
      GRAPH.get(GRAPH.size() - 1).setDestination(row + 1, col);
      dfs(row + 1, col, symbol);
    }
    if (isInBounds(row - 1, col) && !visited[row - 1][col] && matrix[row - 1][col] == symbol) {
      GRAPH.get(GRAPH.size() - 1).setDestination(row - 1, col);
      dfs(row - 1, col, symbol);
    }
  }

  private static boolean isOutOfBounds(int row, int col) {
    return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
  }

  private static boolean isInBounds(int row, int col) {
    return !isOutOfBounds(row, col);
  }
}
