package org.simulator.entities;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This type should be used to set the Direction of Robot position.
 * The direction can be change to Left or Right and get the changed direction.
 * <ul>
 *     <li>NORTH</li>
 *     <li>EAST</li>
 *     <li>SOUTH</li>
 *     <li>WEST</li>
 * </ul>
 */
public enum Direction {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private int index;

    private Direction(int index) { this.index = index; }

    /**
     * Change current direction to left
     * @return - changed {@link Direction}
     */
    public Direction left() { return change(-1); }

    /**
     * Change current direction to right
     * @return - changed {@link Direction}
     */
    public Direction right() { return change(1); }

    /**
     * Get direction by index
     * @param index - [0-3]
     * @return - {@link Direction} at this index
     */
    public static Direction of(int index) { return map.get(index); }

    /**
     * Change direction by one unit
     * @param turnTo - one unit to change
     * @return - Changed {@link Direction}
     */
    private Direction change(int turnTo) {

        int i = ((this.index + turnTo) < 0)
                ? (Direction.values().length - 1)
                : (this.index + turnTo) % Direction.values().length;

        return Direction.of(i);
    }

    // collecting all directions into map to get the current direction after
    // we called the to(turnTo)
    private static final Map<Integer, Direction> map = new LinkedHashMap<>();
    static {
        Arrays.stream(Direction.values()).forEach(d -> map.put(d.index, d));
    }
}
