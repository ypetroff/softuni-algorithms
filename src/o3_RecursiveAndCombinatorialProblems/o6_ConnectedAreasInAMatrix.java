package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class o6_ConnectedAreasInAMatrix {
  private static final List<int[]> AREAS = new ArrayList<>();
  private static final char FREE = '-';
  private static final char WALL = '*';
  private static final char VISITED = 'v';
  private static char[][] matrix;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int rows = Integer.parseInt(reader.readLine());
    int cols = Integer.parseInt(reader.readLine());

    matrix = new char[rows][cols];

    initialiseMatrix(reader, rows);

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (matrix[row][col] == FREE) {
          AREAS.add(new int[] {row, col, 0});
          traverseMatrix(row, col);
        }
      }
    }

    printResults();
  }

  private static void printResults() {
    StringBuilder sb =
        new StringBuilder("Total areas found: ")
            .append(AREAS.size())
            .append(System.lineSeparator());

    AtomicInteger counter = new AtomicInteger(1);

    AREAS.stream()
        .sorted((a, b) -> Integer.compare(b[2], a[2]))
        .forEach(
            x ->
                sb.append(
                        String.format(
                            "Area #%d at (%d, %d), size: %d",
                            counter.getAndIncrement(), x[0], x[1], x[2]))
                    .append(System.lineSeparator()));

    System.out.println(sb);
  }

  private static void traverseMatrix(int row, int col) {
    if (isOutOfBound(row, col) || isNotTraversable(row, col)) {
      return;
    }

    matrix[row][col] = VISITED;

    AREAS.get(AREAS.size() - 1)[2]++;

    traverseMatrix(row + 1, col);
    traverseMatrix(row, col + 1);
    traverseMatrix(row - 1, col);
    traverseMatrix(row, col - 1);
  }

  private static boolean isNotTraversable(int row, int col) {
    return matrix[row][col] == WALL || matrix[row][col] == VISITED;
  }

  private static boolean isOutOfBound(int row, int col) {
    return row < 0 || row == matrix.length || col < 0 || col == matrix[row].length;
  }

  private static void initialiseMatrix(BufferedReader reader, int rows) throws IOException {
    for (int row = 0; row < rows; row++) {
      matrix[row] = reader.readLine().toCharArray();
    }
  }
}
