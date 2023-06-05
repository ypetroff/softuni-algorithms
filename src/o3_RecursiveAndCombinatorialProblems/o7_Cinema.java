package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class o7_Cinema {
  private static final HashMap<String, Integer> FRIENDS_AND_SEATS = new HashMap<>();
  private static final StringBuilder SB = new StringBuilder();
  private static final List<String[]> permutations = new ArrayList<>();
  private static final List<Integer> fixedPositions = new ArrayList<>();
  private static String[] friends;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    Arrays.stream(reader.readLine().split(", ")).forEach(x -> FRIENDS_AND_SEATS.put(x, null));

    String criterion = reader.readLine();
    while (!criterion.equals("generate")) {
      String[] friendAndSeat = criterion.split(" - ");
      int position = Integer.parseInt(friendAndSeat[1]) - 1;

      FRIENDS_AND_SEATS.put(friendAndSeat[0], position);
      fixedPositions.add(position);

      criterion = reader.readLine();
    }

    friends = new String[FRIENDS_AND_SEATS.size()];
    populateFriends();

    permute(0);

    selectPermutations();

    System.out.println(SB);
  }

  private static void populateFriends() {
    for (var entity : FRIENDS_AND_SEATS.entrySet()) {
      String name = entity.getKey();
      Integer fixedIndex = entity.getValue();

      if (fixedIndex != null) {
        friends[fixedIndex] = name;
      }
    }

    int index = 0;
    for (var entity : FRIENDS_AND_SEATS.entrySet()) {
      String name = entity.getKey();
      Integer fixedIndex = entity.getValue();
      if (fixedIndex == null) {
        for (int i = index; i < friends.length; i++) {
          if (friends[i] == null) {
            friends[i] = name;
            index = i + 1;
            break;
          }
        }
      }
    }
  }

  private static void selectPermutations() {
    boolean isValid = false;

    for (String[] permutation : permutations) {
      for (Integer position : fixedPositions) {
        if (!permutation[position].equals(friends[position])) {
          isValid = false;
          break;
        }
        isValid = true;
      }
      if (isValid) {
        SB.append(String.join(" ", permutation)).append(System.lineSeparator());
      }
    }
  }

  private static void permute(int index) {
    if (index == friends.length) {
      permutations.add(Arrays.copyOf(friends, friends.length));
      return;
    }

    permute(index + 1);

    for (int i = index + 1; i < friends.length; i++) {
      swap(index, i);
      permute(index + 1);
      swap(index, i);
    }
  }

  private static void swap(int first, int second) {
    String temp = friends[first];
    friends[first] = friends[second];
    friends[second] = temp;
  }
}
