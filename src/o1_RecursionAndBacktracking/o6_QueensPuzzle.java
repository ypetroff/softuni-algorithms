package o1_RecursionAndBacktracking;

public class o6_QueensPuzzle {
    private final static char QUEEN = '*';
    private final static char EMPTY = '-';
    private final static int BOARD_SIZE = 8;
    private static final char[][] CHESS_BOARD = new char[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        makeEmptyChessBoard();
        int startRow = 0;
        place8QueensWithoutAttackingThemselves(startRow);
    }

    private static void place8QueensWithoutAttackingThemselves(int row) {
        if (row >= BOARD_SIZE) {
            printSolution();
            return;
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (colIsHitByAnotherQueen(row, col)
                    && diagonalIsNotHitByAnotherQueen(row - 1, col - 1, "left")
                    && diagonalIsNotHitByAnotherQueen(row - 1, col + 1, "right")
                    && noQueenOnRow(row)) {
                CHESS_BOARD[row][col] = QUEEN;
                place8QueensWithoutAttackingThemselves(row + 1);
                CHESS_BOARD[row][col] = EMPTY;
            }
        }
    }

    private static void printSolution() {
        for (char[] chars : CHESS_BOARD) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean noQueenOnRow(int row) {
        for (char c : CHESS_BOARD[row]) {
            if (c == QUEEN) {
                return false;
            }
        }
        return true;
    }

    private static boolean diagonalIsNotHitByAnotherQueen(int row, int col, String side) {
        if (pointIsOutsideTheBoard(row, col)) {
            return true;
        }
        for (int currentRow = row, currentCol = col;
             currentRow >= 0 && evaluateCol(currentCol, side);
             currentRow--, currentCol = side.equals("right")
                         ? currentCol + 1
                         : currentCol - 1) {
            if (CHESS_BOARD[currentRow][currentCol] == QUEEN) {
                return false;
            }
        }
        return true;
    }

    private static boolean evaluateCol(int currentCol, String side) {
        if (side.equals("right")) {
            return currentCol < CHESS_BOARD[0].length;
        } else {
            return currentCol >= 0;
        }
    }

    private static boolean colIsHitByAnotherQueen(int endRow, int col) {
        for (int currentRow = 0; currentRow < endRow; currentRow++) {
            if (CHESS_BOARD[currentRow][col] == QUEEN) {
                return false;
            }
        }
        return true;
    }

    private static boolean pointIsOutsideTheBoard(int row, int col) {
        return row < 0 || row >= CHESS_BOARD.length || col < 0 || col >= CHESS_BOARD[row].length;
    }

    private static void makeEmptyChessBoard() {
        for (int row = 0; row < CHESS_BOARD.length; row++) {
            CHESS_BOARD[row] = "--------".toCharArray();
        }
    }
}
