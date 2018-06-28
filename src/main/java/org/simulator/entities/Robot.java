package org.simulator.entities;

public class Robot {

    private Position position;

    public Robot() {}

    /**
     * Create Robot with this initial position
     * @param position - Create to this position
     */
    public Robot(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Simply move to next position in the same direction
     */
    public void move() {
        move(this.position.next());
    }

    /**
     * Move to custom position and direction
     * @param to - move to this position
     */
    public void move(Position to) {
        this.position = to;
    }

    /**
     * Change Robot direction to left
     */
    public void turnToLeft() {
        position.setDirection(position.getDirection().left());
    }

    /**
     * Change Robot direction to right
     */
    public void turnToRight() {
        position.setDirection(position.getDirection().right());
    }
}
