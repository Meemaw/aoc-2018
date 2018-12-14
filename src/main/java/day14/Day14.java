package day14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14 {

    private static List<Integer> getInitialBoard() {
        List<Integer> board = new ArrayList<>();
        board.add(3);
        board.add(7);
        return board;
    }

    private static <T> String join(List<T> values) {
        return values.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static List<Integer> addRecipes(Function<List<Integer>, Boolean> isNotFinished) {
        List<Integer> board = getInitialBoard();
        int e1 = 0;
        int e2 = 1;
        while (isNotFinished.apply(board)) {
            int ve1 = board.get(e1);
            int ve2 = board.get(e2);
            for (Integer newRecipeScore : Integer.toString(ve1 + ve2).chars().mapToObj(c -> c - '0').collect(Collectors.toList())) {
                board.add(newRecipeScore);
                if (!isNotFinished.apply(board)) {
                    return board;
                }
            }
            e1 = (1 + ve1 + e1) % board.size();
            e2 = (1 + ve2 + e2) % board.size();
        }
        return board;
    }

    private static boolean endEquals(List<Integer> values, String input) {
        if (values.size() < input.length()) return false;
        int inputIx = 0;
        for (int i = values.size() - input.length(); i < values.size(); i++, inputIx++) {
            if (Character.forDigit(values.get(i), 10) != input.charAt(inputIx)) {
                return false;
            }
        }
        return true;
    }

    public static int part1(String input) {
        int maxNumRecipes = Integer.parseInt(input);
        List<Integer> recipesBoard = addRecipes(b -> b.size() < maxNumRecipes + 10);
        return Integer.parseInt(join(recipesBoard.subList(maxNumRecipes, recipesBoard.size())));
    }


    public static int part2(String input) {
        List<Integer> recipesBoard = addRecipes(b -> !endEquals(b, input));
        return recipesBoard.size() - input.length();
    }


}
