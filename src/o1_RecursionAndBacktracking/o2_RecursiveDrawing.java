package o1_RecursionAndBacktracking;

import java.util.Scanner;
import java.util.stream.IntStream;

public class o2_RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int n = Integer.parseInt(scanner.nextLine());

        printFigure(n);
    }

    private static void printFigure(int times) {
        if (times == 0) {
            return;
        }

        printRow(times, '*');

        printFigure(times - 1);

        printRow(times, '#');
    }

    private static void printRow(int times, char symbol) {
        IntStream.range(0, times)
                 .mapToObj(i -> Character.toString(symbol))
                 .forEach(System.out::print);
        System.out.println();
    }
}
