package day7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {

    private static final Pattern LINE_PATTERN = Pattern.compile("^Step (.) must be finished before step (.) can begin\\.$");

    static class Node implements Comparable<Node> {
        Character name;
        List<Node> out;
        List<Node> in;

        Node(Character name) {
            this.name = name;
            this.out = new LinkedList<>();
            this.in = new LinkedList<>();
        }

        void addOut(Node to) {
            out.add(to);
        }

        void addIn(Node from) {
            in.add(from);
        }

        int inCount() {
            return in.size();
        }

        @Override
        public int compareTo(Node o) {
            return this.name - o.name;
        }
    }


    private static Map<Character, Node> buildGraph(Stream<String> lines) {
        Map<Character, Node> map = new HashMap<>();
        for (String line : lines.toArray(String[]::new)) {
            Matcher m = LINE_PATTERN.matcher(line);
            if (m.find()) {
                Character c1 = m.group(1).charAt(0);
                Character c2 = m.group(2).charAt(0);

                Node start = map.getOrDefault(c1, new Node(c1));
                Node end = map.getOrDefault(c2, new Node(c2));
                start.addOut(end);
                end.addIn(start);
                map.put(c1, start);
                map.put(c2, end);
            }
        }
        return map;
    }

    public static String part1(Stream<String> lines) {
        Map<Character, Node> graph = buildGraph(lines);
        Node startingNode = graph.values().stream().min(Comparator.comparing(Node::inCount)).get();

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(startingNode);
        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (!result.toString().contains(current.name.toString())) {
                result.append(current.name);
            }

            queue.addAll(current.out.stream().filter(next -> next.in.stream().allMatch(n -> result.toString().contains(n.name
                    .toString())))
                    .collect(Collectors
                            .toList()));
        }
        return result.toString();
    }

    private static int getTimeForCharacter(Character c) {
        return Character.getNumericValue(c) - 9;
    }


    static class Worker {
        private Character job;
        private int secondsLeft;

        boolean isOccupied() {
            return this.job != null;
        }

        void giveTask(Character c) {
            this.job = c;
            this.secondsLeft = getTimeForCharacter(c) + 60;
        }

        Character makeTurn() {
            if (this.job == null) {
                return null;
            }
            this.secondsLeft = this.secondsLeft - 1;

            if (this.secondsLeft == 0) {
                Character c = this.job;
                this.job = null;
                return c;
            }
            return null;
        }
    }

    public static int part2(Stream<String> lines) {
        Map<Character, Node> graph = buildGraph(lines);
        Node startingNode = graph.values().stream().min(Comparator.comparing(Node::inCount)).get();

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(startingNode);
        StringBuilder result = new StringBuilder();

        int numWorkers = 5;
        Worker[] workers = new Worker[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            workers[i] = new Worker();
        }

        int seconds = 0;
        while (!queue.isEmpty() || Arrays.stream(workers).anyMatch(Worker::isOccupied)) {
            for (int i = 0; i < numWorkers; i++) {
                Character completed = workers[i].makeTurn();
                if (completed != null) {
                    result.append(completed);
                    queue.addAll(graph.get(completed).out.stream().filter(next -> next.in.stream().allMatch(n -> result.toString()
                            .contains(n.name
                                    .toString()
                            ))).collect(Collectors
                            .toList()));
                }
            }

            for (int i = 0; i < numWorkers; i++) {
                Worker worker = workers[i];
                if (!queue.isEmpty() && !worker.isOccupied()) {
                    worker.giveTask(queue.poll().name);
                }
            }
            seconds++;
        }

        return seconds - 1;
    }

}
