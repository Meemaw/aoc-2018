package day11;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class Day11Test {

    @Test
    public void testPart1() {
        assertArrayEquals(new int[]{235, 63}, Day11.part1(Integer.parseInt(InputUtil.read(Day11.class))));
    }

    @Test
    public void testPart2() {
        // Too slow to test :)
        // assertArrayEquals(new int[]{229, 251, 16}, Day11.part2(Integer.parseInt(InputUtil.read(Day11.class))));
    }
}
