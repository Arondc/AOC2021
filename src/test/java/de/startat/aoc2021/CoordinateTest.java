package de.startat.aoc2021;

import de.startat.aoc2021.solutions.fifthDay.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTest {

    @Test
    public void testComparable(){
        Coordinate c1 = new Coordinate(0,0);
        Coordinate c2 = new Coordinate(0,2);
        Coordinate c3 = new Coordinate(2,0);
        Coordinate c4 = new Coordinate(2,2);

        assertEquals(-1,c1.compareTo(c2));
        assertEquals(-1,c1.compareTo(c3));
        assertEquals(-1,c1.compareTo(c4));

        assertEquals(1,c2.compareTo(c1));
        assertEquals(1,c3.compareTo(c1));
        assertEquals(1,c4.compareTo(c1));

        assertEquals(-1, c2.compareTo(c3));
        assertEquals(-1, c2.compareTo(c4));

        assertEquals(1, c3.compareTo(c2));
        assertEquals(1, c4.compareTo(c2));

        assertEquals(-1, c3.compareTo(c4));
        assertEquals(1, c4.compareTo(c3));

    }

}
