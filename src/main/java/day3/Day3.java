package day3;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 {

    private static final Pattern LINE_PATTERN = Pattern.compile("^#([0-9]+)\\s@\\s([0-9]+),([0-9]+):\\s([0-9]+)x([0-9]+)$");


    public static class Claim {
        public final int id;
        public final Point location;
        public final Point size;

        public Claim(String line) {
            Matcher matcher = LINE_PATTERN.matcher(line);
            matcher.find();
            this.id = Integer.parseInt(matcher.group(1));
            this.location = new Point(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            this.size = new Point(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)));
        }
    }

    private static List<Claim> populateSantaSuit(int[][] santaGrid, Stream<String> lines) {
        return lines.map(Claim::new).map(claim -> {
            for (int i = claim.location.x; i < claim.location.x + claim.size.x; i++) {
                for (int j = claim.location.y; j < claim.location.y + claim.size.y; j++) {
                    santaGrid[i][j]++;
                }
            }
            return claim;
        }).collect(Collectors.toList());
    }


    public static long part1(Stream<String> lines) {
        int[][] santaSuit = new int[1000][1000];
        populateSantaSuit(santaSuit, lines);
        return Arrays.stream(santaSuit).flatMapToInt(IntStream::of).filter(v -> v > 1).count();
    }

    public static int part2(Stream<String> lines) {
        int[][] santaSuit = new int[1000][1000];
        return populateSantaSuit(santaSuit, lines).stream().filter(claim -> {
            for (int i = claim.location.x; i < claim.location.x + claim.size.x; i++) {
                for (int j = claim.location.y; j < claim.location.y + claim.size.y; j++) {
                    if (santaSuit[i][j] != 1) {
                        return false;
                    }
                }
            }
            return true;
        }).findFirst().get().id;
    }

}
