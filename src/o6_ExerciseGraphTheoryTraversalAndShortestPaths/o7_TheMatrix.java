package o6_ExerciseGraphTheoryTraversalAndShortestPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o7_TheMatrix {
  private static char[][] matrix;
  private static boolean[][] visited;
  private static char requiredChar;
  private static char fillChar;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] coordinates =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    int row = coordinates[0];
    int col = coordinates[1];
    matrix = new char[row][col];

    visited = new boolean[matrix.length][matrix[0].length];

    populateMatrix(reader);

    fillChar = reader.readLine().charAt(0);

    int[] startPoint =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    int startRow = startPoint[0];
    int startCol = startPoint[1];

    requiredChar = matrix[startRow][startCol];

    dfs(startRow, startCol);

    printMatrix();
  }

  private static void printMatrix() {
    StringBuilder sb = new StringBuilder();
    for (char[] chars : matrix) {
      System.out.println(sb.append(chars));
      sb.setLength(0);
    }
  }

  private static void dfs(int row, int col) {
    if (isNotInBounds(row, col)
        || visited[row][col]
        || matrix[row][col] == fillChar
        || matrix[row][col] != requiredChar) {
      return;
    }

    visited[row][col] = true;
    matrix[row][col] = fillChar;

    dfs(row + 1, col);
    dfs(row, col + 1);
    dfs(row - 1, col);
    dfs(row, col - 1);
  }

  private static boolean isNotInBounds(int row, int col) {
    return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
  }

  private static void populateMatrix(BufferedReader reader) throws IOException {
    for (int i = 0; i < matrix.length; i++) {
      String[] split = reader.readLine().split("\\s+");
      for (int j = 0; j < split.length; j++) {
        matrix[i][j] = split[j].charAt(0);
      }
    }
  }
}
