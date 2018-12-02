package day2;

import java.awt.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Day2 {

    public static int part1(Stream<String> lines) {
        Point p = lines.map(Day2::scanId).reduce((p1, p2) -> new Point(p1.x + p2.x, p1.y + p2.y)).get();
        return p.x * p.y;
    }

    private static Point scanId(String id) {
        Map<String, Long> frequencies = id.codePoints().mapToObj(String::valueOf).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return new Point(frequencies.values().contains(Long.valueOf(2)) ? 1 : 0, frequencies.values().contains(Long.valueOf(3)) ? 1 : 0);
    }

    public static String part2(Stream<String> lines) {
        String[] arr = lines.toArray(String[]::new);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int diff = stringDifference(arr[i], arr[j]);
                if (diff == 1) {
                    return getCommon(arr[i], arr[j]);
                }
            }
        }
        throw new IllegalStateException("Could not find ids with difference of 1");
    }

    private static String getCommon(String s1, String s2) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                buffer.append(s1.charAt(i));
            }
        }
        return buffer.toString();
    }

    private static int stringDifference(String s1, String s2) {
        return s1.length() - getCommon(s1, s2).length();
    }
}
