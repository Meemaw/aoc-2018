package day4;

import common.InputUtil;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 {

    private static final Pattern LINE_PATTERN = Pattern.compile("^\\[(.*)\\]\\s(.*)$");
    private static final Pattern GUARD_PATTERN = Pattern.compile("^.*#([0-9]+).*$");


    private static final class Record {
        public final String date;
        public final String message;
        public int guardId;
        public int minute;

        public Record(String line) {
            Matcher matcher = LINE_PATTERN.matcher(line);
            matcher.find();
            this.date = matcher.group(1);
            this.message = matcher.group(2);
            this.minute = Integer.parseInt(this.date.substring(this.date.length() - 2));
            matcher = GUARD_PATTERN.matcher(this.message);
            if (matcher.find()) {
                this.guardId = Integer.parseInt(matcher.group(1));
            }
        }
    }

    private static void buildDataMaps(Stream<String> lines, Map<Integer, Long> sleepingMap, Map<Integer, List<Integer>> guardSleepingMinutes, Map<String, Long> byMinuteMap) {
        int guardId = 0;
        int startMinute = 0;
        for (Record r : lines.map(Record::new).sorted(Comparator.comparing(r -> r.date)).toArray(Record[]::new)) {
            if (r.guardId != 0) {
                guardId = r.guardId;
            } else if (r.message.equals("falls asleep")) {
                startMinute = r.minute;
            } else if (r.message.equals("wakes up")) {
                List<Integer> list = guardSleepingMinutes.getOrDefault(guardId, new LinkedList<>());
                guardSleepingMinutes.put(guardId, Stream.concat(IntStream.range(startMinute, r.minute).boxed(), list.stream()).collect(Collectors.toList()));
                sleepingMap.put(guardId, sleepingMap.getOrDefault(guardId, 0l) + r.minute - startMinute);
                for (int minute = startMinute; minute < r.minute; minute++) {
                    String compoundKey = String.format("%d-%d", guardId, minute);
                    byMinuteMap.put(compoundKey, byMinuteMap.getOrDefault(compoundKey, 0l) + 1);
                }
            }
        }
    }

    public static long part1(Stream<String> lines) {
        Map<Integer, Long> sleepingMap = new HashMap<>();
        Map<Integer, List<Integer>> guardSleepingMinutes = new HashMap<>();
        buildDataMaps(lines, sleepingMap, guardSleepingMinutes, new HashMap<>());
        int sleeperId = Collections.max(sleepingMap.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();
        int minute = guardSleepingMinutes.get(sleeperId).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return minute * sleeperId;
    }

    public static long part2(Stream<String> lines) {
        Map<String, Long> byMinuteMap = new HashMap<>();
        buildDataMaps(lines, new HashMap<>(), new HashMap<>(), byMinuteMap);
        String[] compoundKeySplit = byMinuteMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey().split("-");
        return Integer.parseInt(compoundKeySplit[0]) * Integer.parseInt(compoundKeySplit[1]);
    }

}
