package o2_CombinatorialProblems;

import java.util.Scanner;
/*
* Permutations without repetition are equal to n!, where n is the number of elements in the initial
* set which we use.
* For example, if we have set of 3 elements, the number of permutations is equal to 3! which is 6.
* */
public class o1_PermutationsWithoutRepetitions {
    private static String[] elements;
    private static String[] permutes;
    private static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");
        permutes = new String[elements.length];
        used = new boolean[elements.length];

        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print();
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                permutes[index] = elements[i];
                permute(index + 1);
                used[i] = false;
            }
        }
        
    }

    private static void print() {
        System.out.println(String.join(" ", permutes));
    }
}