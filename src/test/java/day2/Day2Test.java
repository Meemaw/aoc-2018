package day2;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Day2Test {


    @Test
    public void testPart1() {
        assertEquals(5952, Day2.part1(InputUtil.readLines(Day2.class)));
    }

    @Test
    public void testPart2() {
        assertEquals("krdmtuqjgwfoevnaboxglzjph", Day2.part2(InputUtil.readLines(Day2.class)));
    }

}
