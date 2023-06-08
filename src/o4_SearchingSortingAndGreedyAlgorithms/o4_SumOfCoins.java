package o4_SearchingSortingAndGreedyAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o4_SumOfCoins {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] elements = reader.readLine().substring(7).split(", ");
    int[] coins = new int[elements.length];
    for (int i = 0; i < coins.length; i++) {
      coins[i] = Integer.parseInt(elements[i]);
    }

    int targetSum = Integer.parseInt(reader.readLine().substring(5));

    Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

    System.out.println(
        "Number of coins to take:" + usedCoins.values().stream().mapToInt(Integer::intValue).sum());

    for (var usedCoin : usedCoins.entrySet()) {
      System.out.println(usedCoin.getKey() + " -> " + usedCoin.getValue());
    }
  }

  private static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
    Arrays.sort(coins);
    Map<Integer, Integer> chosenCoins = new LinkedHashMap<>();
    int index = coins.length - 1;
    while (index >= 0 && targetSum > 0) {
      int numberOfCoins = targetSum / coins[index];
      if (numberOfCoins != 0) {
        chosenCoins.put(coins[index], numberOfCoins);
        targetSum -= coins[index] * numberOfCoins;
      }
      index--;
    }
    if (targetSum > 0) {
      return new HashMap<>();
    }
    return chosenCoins;
  }
}
