package day6;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day6Test {

    @Test
    public void testPart1() {
        assertEquals(5429, Day6.part1(InputUtil.readLines(Day6.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(32614, Day6.part2(InputUtil.readLines(Day6.class)));
    }


}
