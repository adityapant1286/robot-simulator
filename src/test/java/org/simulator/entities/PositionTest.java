package org.simulator.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    Position position = null;
    @Before
    public void setup() {
        position = new Position(0, 0, Direction.EAST);
    }

    @Test
    public void testGetNextPosition() {


        Position newPosition = position.next();
        assertEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 0);
        assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition = newPosition.next();
        assertNotEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 0);
        assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition.setDirection(Direction.NORTH);
        newPosition = newPosition.next();
        assertNotEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 1);
        assertNotEquals(newPosition.getDirection(), Direction.EAST);

    }

}