package org.simulator.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTest {

    private Robot robot;

    @Before
    public void setup() {
        robot = new Robot(new Position(0, 0, Direction.NORTH));
    }

    @Test
    public void testNavigation() {

        robot.move();
        assertEquals(0, robot.getPosition().getX());
        assertEquals(1, robot.getPosition().getY());
        assertEquals(Direction.NORTH, robot.getPosition().getDirection());

        robot.setPosition(new Position(1, 2, Direction.EAST));
        robot.move();
        robot.turnToRight();
        robot.move();
        robot.move();

        assertEquals(2, robot.getPosition().getX());
        assertEquals(0, robot.getPosition().getY());
        assertEquals(Direction.SOUTH, robot.getPosition().getDirection());

        robot.setPosition(new Position(0, 0, Direction.NORTH));
        robot.turnToRight();
        assertEquals(Direction.EAST, robot.getPosition().getDirection());
        robot.turnToRight();
        assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
        robot.turnToRight();
        assertEquals(Direction.WEST, robot.getPosition().getDirection());
        robot.turnToRight();
        assertEquals(Direction.NORTH, robot.getPosition().getDirection());
        robot.turnToRight();
        assertEquals(Direction.EAST, robot.getPosition().getDirection());

        robot.turnToLeft();
        assertEquals(Direction.NORTH, robot.getPosition().getDirection());
        robot.turnToLeft();
        assertEquals(Direction.WEST, robot.getPosition().getDirection());
        robot.turnToLeft();
        assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
        robot.turnToLeft();
        assertEquals(Direction.EAST, robot.getPosition().getDirection());

    }

}