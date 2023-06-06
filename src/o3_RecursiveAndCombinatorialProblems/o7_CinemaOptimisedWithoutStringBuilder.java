package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class o7_CinemaOptimisedWithoutStringBuilder {
  private static List<String> friends = new ArrayList<>();
  private static String[] seats;
  private static String[] combinations;

  private static boolean[] used;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    friends = Arrays.stream(reader.readLine().split(", ")).collect(Collectors.toList());

    seats = new String[friends.size()];

    String criterion = reader.readLine();
    while (!criterion.equals("generate")) {
      String[] friendAndSeat = criterion.split(" - ");

      String name = friendAndSeat[0];
      int seat = Integer.parseInt(friendAndSeat[1]) - 1;

      seats[seat] = name;
      friends.remove(name);

      criterion = reader.readLine();
    }

    combinations = new String[friends.size()];
    used = new boolean[friends.size()];

    findSeats(0);
  }

  private static void findSeats(int index) {
    if (index == combinations.length) {
      getCombination();
      return;
    }

    for (int i = 0; i < friends.size(); i++) {
      if (!used[i]) {
        used[i] = true;
        combinations[index] = friends.get(i);
        findSeats(index + 1);
        used[i] = false;
      }

    }
  }

  private static void getCombination() {
    StringBuilder sb = new StringBuilder();

    int index = 0;

    for (String seat : seats) {
      if (seat != null) {
        sb.append(seat).append(" ");
        continue;
      }
      sb.append(combinations[index++]).append(" ");
    }
    sb.setLength(sb.length() - 1);
    System.out.println(sb);
    sb.setLength(0);
  }
}
