package day1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

public final class Day1 {

    private static LongStream parseInput(String input) {
        return Arrays.stream(input.split("\n")).mapToLong(Integer::parseInt);
    }

    public static long part1(String input) {
        return parseInput(input).sum();
    }

    public static long part2(String input) {
        Set<Long> set = new HashSet<>();
        long[] freqList = parseInput(input).toArray();
        int ix = 0;
        long frequency = 0l;
        while (true) {
            frequency += freqList[ix++ % freqList.length];
            if (set.contains(frequency)) return frequency;
            else set.add(frequency);
        }
    }
}
