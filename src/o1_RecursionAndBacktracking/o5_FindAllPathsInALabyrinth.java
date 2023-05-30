package o1_RecursionAndBacktracking;

import java.util.Scanner;
import java.util.stream.IntStream;

public class o5_FindAllPathsInALabyrinth {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final StringBuilder PATH = new StringBuilder();
    private static final char EMPTY_CELL = '-';
    private static final char WALL = '*';
    private static final char VISITED = 'v';
    private static final char EXIT = 'e';
    private static char[][] labyrinth;

    public static void main(String[] args) {

        int rows = Integer.parseInt(SCANNER.nextLine());
        int cols = Integer.parseInt(SCANNER.nextLine());
        labyrinth = new char[rows][cols];

        populateLabyrinth(rows);

        int[] startingPoint = {0, 0};

        traverseLabyrinth(startingPoint[0], startingPoint[1], ' ');
    }

    private static void traverseLabyrinth(int row, int col, char direction) {
        if (pointOutsideTheLabyrinth(row, col)
                || labyrinth[row][col] == WALL
                || labyrinth[row][col] == VISITED) {
            return;
        }

        PATH.append(direction);

        if (labyrinth[row][col] == EXIT) {
            printPath();
            PATH.setLength(PATH.length() - 1);
            return;
        }

        labyrinth[row][col] = VISITED;

        traverseLabyrinth(row + 1, col, 'D');
        traverseLabyrinth(row - 1, col, 'U');
        traverseLabyrinth(row, col + 1, 'R');
        traverseLabyrinth(row, col - 1, 'L');

        labyrinth[row][col] = EMPTY_CELL;
        PATH.setLength(PATH.length() - 1);
    }

    private static void printPath() {
        System.out.println(PATH.substring(1));
    }

    private static boolean pointOutsideTheLabyrinth(int row, int col) {
        return row >= labyrinth.length || row < 0 || col >= labyrinth[row].length || col < 0;
    }

    private static void populateLabyrinth(int rows) {
        IntStream.range(0, rows)
                 .forEach(row -> labyrinth[row] = SCANNER.nextLine().toCharArray());
    }
}
