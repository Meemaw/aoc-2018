package day9;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 {

    private static Pattern INPUT_PATTERN = Pattern.compile("^([0-9]+) players; last marble is worth ([0-9]+) points$");

    private static int[] parseInput(String input) {
        Matcher matcher = INPUT_PATTERN.matcher(input);
        if (matcher.find()) {
            return new int[]{Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
        }
        throw new IllegalArgumentException("Invalid input: " + input);
    }

    static class Ring {
        int value;
        Ring prev;
        Ring next;

        Ring(int value) {
            this.value = value;
            this.next = this;
            this.prev = this;
        }

        Ring traverse(int toTraverse) {
            Ring current = this;
            for (int i = 0; i < Math.abs(toTraverse); i++) {
                current = toTraverse < 0 ? current.prev : current.next;
            }
            return current;
        }

        Ring remove() {
            Ring previous = this.prev;
            Ring next = this.next;
            previous.next = next;
            next.prev = previous;
            return next;
        }

        Ring insert(int after, Ring n) {
            Ring current = this.traverse(after);
            Ring next = current.next;

            n.prev = current;
            n.next = next;

            current.next = n;
            next.prev = n;
            return n;
        }
    }

    private static long solve(int[] data) {
        long[] playerScores = new long[data[0]];
        Ring current = new Ring(0);

        int marbleWorth = 1;
        int playerIndex = 0;

        while (marbleWorth <= data[1]) {
            if (marbleWorth % 23 == 0) {
                playerScores[playerIndex] += marbleWorth;
                Ring sevenCounterClockWise = current.traverse(-7);
                playerScores[playerIndex] += sevenCounterClockWise.value;
                current = sevenCounterClockWise.remove();
            } else {
                current = current.insert(1, new Ring(marbleWorth));
            }
            marbleWorth++;
            playerIndex = (playerIndex + 1) % playerScores.length;
        }
        return Arrays.stream(playerScores).max().getAsLong();
    }


    public static long part1(String input) {
        return solve(parseInput(input));
    }

    public static long part2(String input) {
        int[] data = parseInput(input);
        data[1] *= 100;
        return solve(data);
    }

}
