package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _12_PathFinder {
  private static final Map<Integer, List<Integer>> GRAPH = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());

    for (int i = 0; i < n; i++) {
      GRAPH.put(i, new ArrayList<>());
      String input = reader.readLine().trim();
      if (input.length() == 0) {
        continue;
      }
      int[] edge = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();
      GRAPH.put(i, new ArrayList<>());

      Arrays.stream(edge).forEach(GRAPH.get(i)::add);
    }

    int p = Integer.parseInt(reader.readLine());

    List<int[]> paths = new ArrayList<>();

    for (int i = 0; i < p; i++) {
      int[] path =
          Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
      paths.add(path);
    }

    StringBuilder sb = new StringBuilder();

    for (int[] path : paths) {
      sb.append(bfs(path) ? "yes" : "no").append(System.lineSeparator());
    }

    System.out.println(sb);
  }

  private static boolean bfs(int[] path) {
    if (path.length < 2) {
      return false;
    }
    Deque<Integer> queue = new ArrayDeque<>();
    Arrays.stream(path).forEach(queue::offer);

    if (queue.isEmpty()) {
      return false;
    }

    int node = queue.poll();

    while (!queue.isEmpty()) {
      if (!GRAPH.get(node).contains(queue.peek())) {
        return false;
      }

      if (queue.isEmpty()) {
        return false;
      }

      node = queue.poll();

      if (node == path[path.length - 1] && queue.isEmpty()) {
        return true;
      }
    }

    return true;
  }
}
