package day11;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day11 {


    static class FuelCell {
        int x;
        int y;
        int value;
        int size;

        public FuelCell(int x, int y, int value, int size) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.size = size;
        }
    }

    private static int calculateFuelLevel(int serialNumber, int x, int y) {
        int rackId = x + 10;
        int res = rackId * y;
        res += serialNumber;
        res *= rackId;
        String s = Integer.toString(res);
        res = s.length() < 3 ? 0 : Integer.parseInt(Character.toString(s.charAt(s.length() - 3)));
        return res - 5;
    }

    private static int getGridSum(int[][] grid, int topLeftX, int topLeftY, int size) {
        return IntStream.rangeClosed(topLeftX, topLeftX + size - 1).mapToObj(offsetX -> IntStream.rangeClosed(topLeftY,
                topLeftY + size - 1).map(offsetY -> grid[offsetX][offsetY]
        )).flatMapToInt(Function.identity()).sum();
    }

    private static int[][] createCellGrid(int gridSize, int serialNumber) {
        final int[][] cellGrid = new int[gridSize][gridSize];
        IntStream.range(0, 300).forEach(x -> IntStream.range(0, gridSize).forEach(y -> cellGrid[x][y] = calculateFuelLevel(serialNumber,
                x, y)));
        return cellGrid;
    }

    private static FuelCell solve(int[][] cellGrid, int size) {
        return
                IntStream.rangeClosed(0, cellGrid.length - size).mapToObj(x -> IntStream.rangeClosed(0, cellGrid[0].length - size).mapToObj(y -> new FuelCell(x
                        , y,
                        getGridSum(cellGrid, x, y, size), size))).flatMap(Function.identity()).max(Comparator.comparing(f -> f.value)).get();
    }


    public static int[] part1(int serialNumber) {
        FuelCell cell = solve(createCellGrid(300, serialNumber), 3);
        return new int[]{cell.x, cell.y};
    }

    public static int[] part2(int serialNumber) {
        final int[][] cellGrid = createCellGrid(300, serialNumber);
        FuelCell cell = IntStream.range(1, 300).mapToObj(size -> solve(cellGrid, size)).max(Comparator.comparing(f -> f.value)).get();
        return new int[]{cell.x, cell.y, cell.size};
    }

}
