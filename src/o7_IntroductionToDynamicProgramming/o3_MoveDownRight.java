package o7_IntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class o3_MoveDownRight {
  private static int[][] matrix;
  private static int[][] values;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int rows = Integer.parseInt(reader.readLine());
    int cols = Integer.parseInt(reader.readLine());

    matrix = new int[rows][cols];
    values = new int[rows][cols];

    fillMatrix(reader, rows);

    fillValues(rows, cols);

    int row = rows - 1;
    int col = cols - 1;

    List<String> path = new ArrayList<>();
    path.add(pointCoordinates(row, col));

    findBestPath(row, col, path);

    Collections.reverse(path);

    System.out.println(String.join(" ", path));
  }

  private static void findBestPath(int row, int col, List<String> path) {
    while (row > 0 || col > 0) {
      int top = -1;

      if (row > 0) {
        top = values[row - 1][col];
      }

      int left = -1;

      if (col > 0) {
        left = values[row][col - 1];
      }

      if (top > left) {
        row--;
      } else {
        col--;
      }

      path.add(pointCoordinates(row, col));
    }
  }

  private static String pointCoordinates(int row, int col) {
    return String.format("[%d, %d]", row, col);
  }

  private static void fillValues(int rows, int cols) {
    values[0][0] = matrix[0][0];
    for (int col = 1; col < cols; col++) {
      values[0][col] = values[0][col - 1] + matrix[0][col];
    }
    for (int row = 1; row < rows; row++) {
      values[row][0] = values[row - 1][0] + matrix[row][0];
    }
    for (int row = 1; row < rows; row++) {
      for (int col = 1; col < cols; col++) {
        values[row][col] = Math.max(values[row - 1][col], values[row][col - 1]) + matrix[row][col];
      }
    }
  }

  private static void fillMatrix(BufferedReader reader, int n) throws IOException {
    for (int i = 0; i < n; i++) {
      matrix[i] =
          Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
  }
}
