package org.simulator.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    private Direction direction;

    @Before
    public void setup() {
        direction = Direction.NORTH;
    }

    @Test
    public void testDirectionChange() {

        direction = direction.left();
        assertEquals(direction, Direction.WEST);

        direction = direction.left();
        assertEquals(direction, Direction.SOUTH);

        direction = direction.left();
        assertEquals(direction, Direction.EAST);

        direction = direction.left();
        assertEquals(direction, Direction.NORTH);

        direction = direction.right();
        assertEquals(direction, Direction.EAST);

        direction = direction.right();
        assertEquals(direction, Direction.SOUTH);

        direction = direction.right();
        assertEquals(direction, Direction.WEST);

        direction = direction.right();
        assertEquals(direction, Direction.NORTH);

        direction = direction.right();
        assertEquals(direction, Direction.EAST);
    }

}