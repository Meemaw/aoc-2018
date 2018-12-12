package day7;

import common.InputUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day7Test {

    @Test
    public void testPart1() {
        assertEquals("BGJCNLQUYIFMOEZTADKSPVXRHW", Day7.part1(InputUtil.readLines(Day7.class)));
    }

    @Test
    public void testPart2() {
        assertEquals(1017, Day7.part2(InputUtil.readLines(Day7.class)));
    }
}
