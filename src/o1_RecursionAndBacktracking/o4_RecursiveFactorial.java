package o1_RecursionAndBacktracking;

import java.util.Scanner;

public class o4_RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(factorial(n));
    }

    private static long factorial(int number) {
        if (number == 1) {
            return 1;
        }

        return number * factorial(number - 1);
    }
}
