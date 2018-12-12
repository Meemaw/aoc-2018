package day8;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day8Test {

    @Test
    public void testPart1() {
        assertEquals(42798, Day8.part1(InputUtil.read(Day8.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(23798, Day8.part2(InputUtil.read(Day8.class)));
    }
}
