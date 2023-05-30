package o1_RecursionAndBacktracking;

import java.util.Arrays;
import java.util.Scanner;

public class o3_Generating01Vectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Integer[] arr = new Integer[n];

        int index = 0;

        createVectors(arr, index);
    }

    private static void createVectors(Integer[] arr, int index) {
        if (index >= arr.length) {
            printVector(arr);
            System.out.println();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            arr[index] = i;
            createVectors(arr, index + 1);
        }

    }

    private static void printVector(Integer[] arr) {
        Arrays.stream(arr).forEach(System.out::print);
    }
}
