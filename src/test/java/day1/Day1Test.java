package day1;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Day1Test {


    @Test
    public void testPart1() {
        assertEquals(416, Day1.part1(InputUtil.read(Day1.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(56752, Day1.part2(InputUtil.read(Day1.class)));
    }

}
