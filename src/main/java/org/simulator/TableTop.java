package org.simulator;

import org.simulator.entities.Position;
import org.simulator.entities.Robot;

/**
 * A 5 x 5 fixed dimensions table.
 * On this platform we can place Robot at any position and direction.
 * We can move and turn Robot in any direction within
 * the boundaries on the table.
 */
public class TableTop {

    // fixed dimension table top 5 x 5
    // These values can be set dynamically by declaring instance variables
    // and overloading constructor to accept these values
    private static final int ROWS = 5;
    private static final int COLS = 5;

    private Robot robot;

    public TableTop(Robot robot) {
        this.robot = robot;
    }

    public static boolean isRobotFalling(Position position) {
        return position.getX() > COLS // falling at right side of the table
                || position.getX() < 0 // falling at the left side of the table
                || position.getY() > ROWS // falling at the bottom side of the table
                || position.getY() < 0; // falling at the top side of the table
    }


    /**
     * Place Robot at position on the table
     * @param position - dimension and direction
     * @return {@code true} if Robot is successfully placed at the position, otherwise {@code false}
     */
    public boolean placeRobot(Position position) {

        if (!isRobotFalling(position)) { // place the robot if not falling off the table
            robot.setPosition(position);
            return true;
        }
        return false;
    }

    /**
     * Execute command
     * @param command - MOVE, LEFT, RIGHT
     */
    public void execute(Commands command) {

        switch (command) {

            case MOVE: Position nextPosition = robot.getPosition().next();
                        if (!isRobotFalling(nextPosition))
                            robot.move(nextPosition);

                        break;

            case LEFT: robot.turnToLeft();
                        break;

            case RIGHT: robot.turnToRight();
                        break;

        }
    }

    /**
     * Robot current position stats
     * @return - Robot position dimension and direction
     */
    public String stats() {
        final Position robotPosition = robot.getPosition();
        return String.format("%d, %d, %s", robotPosition.getX(), robotPosition.getY(), robotPosition.getDirection().name());
    }
}
