package org.simulator.entities;

/**
 * This type should use to set initial Robot position
 * and calculate next position based on the current direction
 *
 * @see Direction
 */
public class Position {

    private int x;
    private int y;
    private Direction direction;

    /**
     * Create Position with dimension and direction
     * @param x - Horizontal position
     * @param y - Vertical position
     * @param direction - {@link Direction}
     */
    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public Direction getDirection() { return direction; }

    public void setDirection(Direction direction) { this.direction = direction; }

    /**
     * Set position to new dimension
     * @param x - Horizontal
     * @param y - Vertical
     */
    public void to(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    /**
     * Get next position based on current {@link Direction}
     * @return New {@link Position}
     */
    public Position next() {

        // next position
        final Position nextPosition = new Position(x, y, direction);

        switch (direction) {
            case NORTH:
                nextPosition.to(0, 1);
                break;
            case EAST:
                nextPosition.to(1, 0);
                break;
            case SOUTH:
                nextPosition.to(0, -1);
                break;
            case WEST:
                nextPosition.to(-1, 0);
                break;
        }
        return nextPosition;
    }
}
