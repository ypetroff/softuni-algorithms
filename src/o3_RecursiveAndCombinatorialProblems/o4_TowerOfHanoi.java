package o3_RecursiveAndCombinatorialProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * Represents a solution to the Tower of Hanoi problem using a recursive approach.
 * The Tower of Hanoi is a mathematical puzzle that consists of three rods and a number of disks of different sizes.
 * The objective is to move the entire stack of disks from the source rod to the destination rod, following these rules:
 * 1. Only one disk can be moved at a time.
 * 2. Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack
 *    or an empty rod.
 * 3. No disk may be placed on top of a smaller disk.
 */
public class o4_TowerOfHanoi {
  private static final Deque<Integer> source = new ArrayDeque<>();
  private static final Deque<Integer> destination = new ArrayDeque<>();
  private static final Deque<Integer> spare = new ArrayDeque<>();
  private static final StringBuilder sb = new StringBuilder();
  private static int stepCounter;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int disksCount = Integer.parseInt(reader.readLine());

    fillSource(disksCount);

    rodsStatus();

    moveToDestinationRode(disksCount, source, destination, spare);

    System.out.println(sb);
  }

  private static void rodsStatus() {
    sb.append(
        String.format(
            "Source: %s%nDestination: %s%nSpare: %s%n",
            getRode(source), getRode(destination), getRode(spare)))
            .append(System.lineSeparator());
  }

  private static String getRode(Deque<Integer> rode) {
    return rode.stream()
        .sorted(Comparator.reverseOrder())
        .map(String::valueOf)
        .collect(Collectors.joining(", "));
  }

  private static void fillSource(int disksCount) {
    for(int i = disksCount; i > 0; i--) {
      source.push(i);
    }
  }

  private static void moveToDestinationRode(
      int disk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
    if (disk == 1) {
      destination.push(source.pop());
      sb.append(String.format("Step #%d: Moved disk", ++stepCounter))
          .append(System.lineSeparator());
      rodsStatus();

      return;
    }

    moveToDestinationRode(disk - 1, source, spare, destination);
    moveToDestinationRode(1, source, destination, spare);
    moveToDestinationRode(disk - 1, spare, destination, source);
  }
}
