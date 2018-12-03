package day3;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day3Test {

    @Test
    public void testPart1() {
        assertEquals(103482, Day3.part1(InputUtil.readLines(Day3.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(686, Day3.part2(InputUtil.readLines(Day3.class)));
    }
}
