package day5;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day5Test {

    @Test
    public void testPart1() {
        assertEquals(11108, Day5.part1(InputUtil.read(Day5.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(5094, Day5.part2(InputUtil.read(Day5.class)));
    }
}
