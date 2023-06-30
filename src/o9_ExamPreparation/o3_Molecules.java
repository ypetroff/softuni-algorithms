package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class o3_Molecules {
  private static int[][] graph;
  private static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int nodes = Integer.parseInt(reader.readLine());

    graph = new int[nodes + 1][nodes + 1];
    visited = new boolean[nodes + 1];

    int edges = Integer.parseInt(reader.readLine());

    for (int i = 0; i < edges; i++) {
      int[] edge =
          Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

      graph[edge[0]][edge[1]] = 1;
    }

    int start = Integer.parseInt(reader.readLine());

    bfs(start);

    StringBuilder sb = new StringBuilder();

    for (int i = 1; i < visited.length; i++) {
        if (!visited[i]) {
            sb.append(i).append(" ");
        }
    }

    System.out.println(sb.toString().trim());
  }

  private static void bfs(int node) {
    Deque<Integer> queue = new ArrayDeque<>();
    queue.offer(node);


    while (!queue.isEmpty()) {
      int currentNode = queue.poll();

      if (!visited[currentNode]) {
        visited[currentNode] = true;

        for (int i = 1; i < graph[currentNode].length; i++) {
          if (graph[currentNode][i] == 1) {
            queue.offer(i);
          }
        }
      }
    }
  }
}
