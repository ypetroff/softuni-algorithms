package o2_CombinatorialProblems;

import java.util.Scanner;

public class o5_CombinationsWithoutRepetition {
    private static String[] elements;
    private static String[] combinations;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        elements = scanner.nextLine().split("\\s+");

        int k = Integer.parseInt(scanner.nextLine());

        combinations = new String[k];

        findCombinations(0, 0);
    }

    private static void findCombinations(int index, int start) {
        if (index == combinations.length) {
            print();
            return;
        }

        for(int i = start; i < elements.length; i++) {
            combinations[index] = elements[i];
            findCombinations(index + 1, i + 1);
        }
    }

    private static void print() {
    System.out.println(String.join(" ", combinations));
    }
}