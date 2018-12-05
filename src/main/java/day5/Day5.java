package day5;

import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day5 {

    public static int part1(String input) {
        Stack<Character> chars = input.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(Stack::new));
        Stack<Character> result = new Stack<>();

        for (char c : chars) {
            if (result.isEmpty()) {
                result.push(c);
            } else {
                char lastInResult = result.peek();
                if (Math.abs(lastInResult - c) == 32) {
                    result.pop();
                } else {
                    result.push(c);
                }
            }
        }
        return result.size();
    }

    public static int part2(String input) {
        return "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(c -> (char) c).map(letter -> part1(input.replaceAll(String.format("[%c|%c]",
                letter, Character.toUpperCase(letter)), ""))).min
                (Comparator
                        .comparing
                                (Integer::valueOf)).get();
    }


}
