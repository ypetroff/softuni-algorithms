package o8_ExerciseIntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class o5_SumWithLimitedAmountOfCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] coins =
                Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int targetSum = Integer.parseInt(reader.readLine());

    System.out.println(calcSum(coins, targetSum));
    }

    private static int calcSum(int[] coins, int targetSum) {
        Map<Integer, Integer> result = new HashMap<>();

        int answer = 0;

        result.put(0, 0);

        for (int currentCoin : coins) {
            for (Integer number : new ArrayList<>(result.keySet())) {
                int newSum = currentCoin + number;

                if (newSum == targetSum) {
                    answer++;
                }

                if (!result.containsKey(newSum)) {
                    result.put(newSum, number);
                }
            }
        }

        return answer;
    }
}
