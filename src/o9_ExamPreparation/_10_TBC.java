package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class _10_TBC {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static final char TUNNEL = 't';
    private static final char DIRT = 'd';
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int row = Integer.parseInt(reader.readLine());
        int col = Integer.parseInt(reader.readLine());

        matrix = new char[row][col];
        visited = new boolean[row][col];

        for(int i = 0; i < row; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }

        int connectedAreas = 0;

        for(int r = 0; r < row; r++) {
            for(int c = 0; c < col; c++) {
                if (!visited[r][c] && matrix[r][c] == TUNNEL) {
                    if(traverseMatrix(r, c, 0) > 1) {
                        connectedAreas++;
                    }
                }
            }
        }
    System.out.println(connectedAreas);
  }

    private static int traverseMatrix(int row, int col, int area) {
        if (isOutOfBounds(row, col) || visited[row][col] || matrix[row][col] == DIRT) {
            return area;
        }

        visited[row][col] = true;

        area++;

        area = traverseMatrix(row + 1, col, area);
        area = traverseMatrix(row - 1, col, area);
        area = traverseMatrix(row, col + 1, area);
        area = traverseMatrix(row, col - 1, area);
        area = traverseMatrix(row + 1, col + 1, area);
        area = traverseMatrix(row - 1, col - 1, area);
        area = traverseMatrix(row + 1, col - 1, area);
        area = traverseMatrix(row - 1, col + 1, area);

        return area;
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
