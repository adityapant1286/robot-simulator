package org.simulator;

import org.junit.Before;
import org.junit.Test;
import org.simulator.entities.Direction;
import org.simulator.entities.Position;
import org.simulator.entities.Robot;

import static org.junit.Assert.*;

public class TableTopTest {

    private TableTop tableTop;
    private Robot robot;

    @Before
    public void setup() {
        robot = new Robot();
        tableTop = new TableTop(robot);
    }

    @Test
    public void isRobotFalling() {

        Position fallingPosition1 = new Position(5, 5, Direction.NORTH);
        assertTrue(tableTop.isRobotFalling(fallingPosition1));

        Position fallingPosition2 = new Position(-1, 0, Direction.NORTH);
        assertTrue(tableTop.isRobotFalling(fallingPosition2));

        Position position1 = new Position(4, 4, Direction.NORTH);
        assertFalse(tableTop.isRobotFalling(position1));

        Position position2 = new Position(0, 0, Direction.WEST);
        assertFalse(tableTop.isRobotFalling(position2));

    }

    @Test
    public void testPlaceRobot() {
        assertTrue(tableTop.placeRobot(new Position(0, 1, Direction.EAST)));
        assertTrue(tableTop.placeRobot(new Position(0, 0, Direction.NORTH)));
        assertTrue(tableTop.placeRobot(new Position(4, 3, Direction.WEST)));

        assertFalse(tableTop.placeRobot(new Position(4, 5, Direction.NORTH)));
        assertFalse(tableTop.placeRobot(new Position(5, 5, Direction.NORTH)));
        assertFalse(tableTop.placeRobot(new Position(2, -2, Direction.NORTH)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void executeException() {
        tableTop.execute(Commands.MOVE);
    }

    @Test
    public void execute() {

        tableTop.placeRobot(new Position(0, 0, Direction.WEST));

        tableTop.execute(Commands.MOVE); // unchanged due to falling case
        assertEquals("0,0,WEST", tableTop.stats());

        tableTop.execute(Commands.LEFT);
        tableTop.execute(Commands.MOVE); // unchanged due to falling case
        assertEquals("0,0,SOUTH", tableTop.stats());

        tableTop.execute(Commands.LEFT);
        tableTop.execute(Commands.MOVE);
        assertEquals("1,0,EAST", tableTop.stats());

        tableTop.execute(Commands.MOVE);
        tableTop.execute(Commands.MOVE);
        assertEquals("3,0,EAST", tableTop.stats());

        tableTop.execute(Commands.MOVE);
        tableTop.execute(Commands.MOVE); // unchanged due to falling case
        tableTop.execute(Commands.MOVE); // unchanged due to falling case
        tableTop.execute(Commands.MOVE); // unchanged due to falling case
        assertEquals("4,0,EAST", tableTop.stats());

        tableTop.execute(Commands.LEFT);
        tableTop.execute(Commands.MOVE);
        tableTop.execute(Commands.MOVE);
        assertEquals("4,2,NORTH", tableTop.stats());
    }

}