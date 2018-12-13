package day10;

import common.InputUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 {

    private static final Pattern POINT_PATTERN = Pattern.compile("^position=< ?(-?\\d+),  ?(-?\\d+)> velocity=< ?(-?\\d+),  ?(-?\\d+)>$");

    static class Point {
        int posX;
        int posY;
        int velX;
        int velY;

        Point(int posX, int posY, int velX, int velY) {
            this.posX = posX;
            this.posY = posY;
            this.velX = velX;
            this.velY = velY;
        }

        Point move() {
            return new Point(posX + velX, posY + velY, velX, velY);
        }

    }

    private static int[] getEnvelope(List<Point> points) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Point p : points) {
            minX = Math.min(minX, p.posX);
            minY = Math.min(minY, p.posY);
            maxX = Math.max(maxX, p.posX);
            maxY = Math.max(maxY, p.posY);
        }
        return new int[]{minX, maxX, minY, maxY};
    }


    private static List<Point> parseInput(Stream<String> lines) {
        return lines.map(line -> {
            Matcher matcher = POINT_PATTERN.matcher(line);
            if (matcher.find()) {
                return new Point(Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)));
            }
            throw new IllegalArgumentException("Invalid line: " + line);
        }).collect(Collectors.toList());
    }

    private static void print(int[] envelope, List<Point> points) {
        StringBuilder builder = new StringBuilder();
        for (int y = envelope[2] - 2; y < envelope[3] + 2; y++) {
            for (int x = envelope[0] - 2; x < envelope[1] + 2; x++) {
                final int posX = x;
                final int posY = y;
                builder.append(points.stream().anyMatch(p -> p.posX == posX && p.posY == posY) ? "#" : ".");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }


    public static void part1(Stream<String> lines) {
        List<Point> points = parseInput(lines);
        int[] envelope = getEnvelope(points);


        int c = 0;
        while (envelope[3] - envelope[2] > 11) {
            c++;
            points = points.stream().map(Point::move).collect(Collectors.toList());
            envelope = getEnvelope(points);
        }
        System.out.println(c);
        print(envelope, points);
    }


    public static void main(String[] args) {
        part1(InputUtil.readLines(Day10.class));
    }
}
