package o9_ExamPreparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class o4_Trains {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    double[] arrivals =
        Arrays.stream(reader.readLine().split("\\s+"))
            .mapToDouble(Double::parseDouble)
            .sorted()
            .toArray();

    double[] departures =
        Arrays.stream(reader.readLine().split("\\s+"))
            .mapToDouble(Double::parseDouble)
            .sorted()
            .toArray();

    int platforms = 0;
    int maxPlatforms = 0;

    for (int i = 0, j = 0; i < arrivals.length; ) {
      if (arrivals[i] < departures[j]) {
        platforms++;
        maxPlatforms = Math.max(maxPlatforms, platforms);
        i++;
      } else {
        platforms--;
        j++;
      }
    }
    System.out.println(maxPlatforms);
  }
}
