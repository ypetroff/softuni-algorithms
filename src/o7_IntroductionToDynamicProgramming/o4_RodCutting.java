package o7_IntroductionToDynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o4_RodCutting {
  private static int[] prices;
  private static int[] bestPrices;
  private static int[] prevIndex;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    prices =
        Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

    int length = Integer.parseInt(reader.readLine());

    bestPrices = new int[length + 1];
    prevIndex = new int[length + 1];

    int maxProfit = cutRod(length);

    System.out.println(maxProfit);
    printPieces(length);
  }

  private static void printPieces(int n) {
    while (n - prevIndex[n] != 0) {
      System.out.print(prevIndex[n] + " ");
      n = n - prevIndex[n];
    }
    System.out.println(prevIndex[n]);
  }

  private static int cutRod(int n) {
    if (n == 0) {
      return 0;
    }

    if (bestPrices[n] != 0) {
      return bestPrices[n];
    }

    int currentBest = bestPrices[n];

    for(int i = 1; i <= n; i++) {
      currentBest = Math.max(currentBest, prices[i] + cutRod(n - i));

      if (currentBest > bestPrices[n]) {
        bestPrices[n] = currentBest;
        prevIndex[n] = i;
      }
    }


    return bestPrices[n];
  }
}
