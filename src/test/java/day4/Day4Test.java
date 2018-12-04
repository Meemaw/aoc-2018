package day4;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day4Test {

    @Test
    public void testPart1() {
        assertEquals(76357, Day4.part1(InputUtil.readLines(Day4.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(41668, Day4.part2(InputUtil.readLines(Day4.class)));
    }
}
