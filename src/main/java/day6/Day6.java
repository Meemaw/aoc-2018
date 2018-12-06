package day6;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day6 {


    public static int part1(Stream<String> lines) {
        List<Point> points = new LinkedList<>();
        int[] envelope = getEnvelope(lines, points);
        Set<Point> bounding = getBoundingPoints(envelope, points);
        Map<Point, Integer> map = new HashMap<>();

        for (Point current : getGridPoints(envelope).toArray(Point[]::new)) {
            Point nearest = findNearest(current, points);
            if (!bounding.contains(nearest)) {
                map.put(nearest, map.getOrDefault(nearest, 0) + 1);
            }
        }
        return map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getValue();
    }


    public static long part2(Stream<String> lines) {
        List<Point> points = new LinkedList<>();
        int[] envelope = getEnvelope(lines, points);

        return getGridPoints(envelope).filter(current -> points.stream().map(p -> manhattan(p, current))
                .mapToInt(Integer::valueOf).sum() < 10000).count();
    }

    private static int manhattan(Point a, Point b) {
        return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
    }

    private static int[] getEnvelope(Stream<String> lines, List<Point> points) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (String line : lines.toArray(String[]::new)) {
            String[] s = line.split(", ");
            Point p = new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            points.add(p);
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }
        return new int[]{minX, maxX, minY, maxY};
    }

    private static Set<Point> getBoundingPoints(int[] envelope, List<Point> points) {
        Set<Point> bounding = new HashSet<>();
        for (int x = envelope[0]; x < envelope[1]; x++) {
            addNearestBorderingPoints(bounding, points, new Point(x, envelope[2]), new Point(x, envelope[3]));
        }
        for (int y = envelope[2]; y < envelope[3]; y++) {
            addNearestBorderingPoints(bounding, points, new Point(envelope[0], y), new Point(envelope[1], y));
        }
        return bounding;
    }

    private static void addNearestBorderingPoints(Set<Point> bounding, List<Point> points, Point... toAdd) {
        for (Point current : toAdd) {
            bounding.add(points.stream().min(Comparator.comparing(p -> manhattan(p, current))).get());
        }
    }


    private static Stream<Point> getGridPoints(int[] envelope) {
        return IntStream.rangeClosed(envelope[2], envelope[3]).mapToObj(y -> IntStream.rangeClosed(envelope[0],
                envelope[1])
                .mapToObj(x -> new Point(x, y))).flatMap(Function.identity());
    }

    private static Point findNearest(Point current, List<Point> points) {
        return points.stream().min(Comparator.comparing(p -> manhattan(p, current))).get();
    }

}
