package day14;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Test {

    @Test
    public void testPart1() {
        assertEquals(2103141159, Day14.part1(InputUtil.read(Day14.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(20165733, Day14.part2(InputUtil.read(Day14.class)));
    }
}
