package day9;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day9Test {

    @Test
    public void testPart1() {
        assertEquals(385820, Day9.part1(InputUtil.read(Day9.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(3156297594L, Day9.part2(InputUtil.read(Day9.class)));
    }
}
