package day8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day8 {

    static class Node {
        Node[] children;
        int[] metadata;

        public Node(Node[] children, int[] metadata) {
            this.children = children;
            this.metadata = metadata;
        }
    }

    private static Queue<Integer> parseInput(String input) {
        return Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
    }

    private static Node createTree(Queue<Integer> input) {
        int numChildren = input.poll();
        int numMetadataEntries = input.poll();


        Node[] children = new Node[numChildren];
        for (int i = 0; i < numChildren; i++) {
            children[i] = createTree(input);
        }

        int[] metadata = new int[numMetadataEntries];
        for (int i = 0; i < numMetadataEntries; i++) {
            metadata[i] = input.poll();
        }

        return new Node(children, metadata);
    }

    private static int sum(Node n) {
        int metadataSum = Arrays.stream(n.metadata).sum();
        return n.children.length == 0 ? metadataSum : metadataSum + Arrays.stream(n.children).mapToInt(Day8::sum).sum();
    }

    private static int metadataIndexedSum(Node n) {
        return n.children.length == 0 ? Arrays.stream(n.metadata).sum() : Arrays.stream(n.metadata).filter(index -> index <= n.children
                .length).map(index -> metadataIndexedSum(n
                .children[index - 1])).sum();
    }


    public static int part1(String input) {
        return sum(createTree(parseInput(input)));
    }

    public static int part2(String input) {
        return metadataIndexedSum(createTree(parseInput(input)));
    }


}
