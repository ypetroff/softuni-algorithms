package o2_CombinatorialProblems;

import java.util.Arrays;
import java.util.Scanner;

/*
* swap() allows to reduce the use of two array, present in the first solution.
* The method does what the name suggests, it swaps two variables within the user provided set.
* */
public class o1_PermutationsWithoutRepetitionsWithSwap {
    private static String[] elements;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");

        permute(0);
    System.out.println("*************");
    Arrays.stream(elements).forEach(x -> System.out.print(x + " "));
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print();
            return;
        }

        permute(index + 1);

        for (int i = index + 1; i < elements.length; i++) {
                swap(index, i);
                permute(index + 1);
                swap(index, i);
            }
        }

    /*
     * swap() allows to reduce the use of two array, present in the first solution.
     * The method does what the name suggests, it swaps two variables within the user provided set.
     * */
    private static void swap(int first, int second) {
        String temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }

    private static void print() {
        System.out.println(String.join(" ", elements));
    }
}