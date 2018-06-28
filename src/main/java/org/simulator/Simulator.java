package org.simulator;

import org.simulator.entities.Direction;
import org.simulator.entities.Position;
import org.simulator.entities.Robot;

import java.util.Scanner;

public class Simulator {


    public static void main(String[] args) {
        final Simulator simulator = new Simulator();
        simulator.startSimulation();
    }

    private void startSimulation() {
        final Scanner sc = new Scanner(System.in);

        try {


            boolean placed = false;

            final Robot robot = new Robot();
            final TableTop tableTop = new TableTop(robot);

            System.out.println("Robot Simulator");
            System.out.println("Enter a command, Valid commands are:");
            System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT\n");

            while (true) {

                String input = sc.nextLine().trim().toUpperCase();

                // Exit the program
                if (Commands.EXIT.name().equals(input))
                    break;

                String[] arg = input.split(" ");

                Commands command;
                try {

                    command = Commands.valueOf(arg[0]);

                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid command. Try again.");
                    continue;
                }

                if (Commands.PLACE.equals(command) && arg.length == 2) {

                    placed = placeOnTable(arg[1], tableTop);

                } else if (placed) {

                    if (Commands.REPORT.equals(command))
                        System.out.println(String.format("Output: %s", tableTop.stats()));
                    else
                        tableTop.execute(command);
                }
            } // while loop

        } finally {
            sc.close();
        }
    }

    private static boolean placeOnTable(final String input, final TableTop tableTop) {

        final String[] params = input.split(",");
        int x = 0;
        int y = 0;
        Direction direction = null;

        try {
            x = Integer.valueOf(params[0].trim());
            y = Integer.valueOf(params[1].trim());
            direction = Direction.valueOf(params[2].trim());
        } catch (IllegalArgumentException e) {
            return false; // skip for invalid input
        }

        return tableTop.placeRobot(new Position(x, y, direction));
    }
}
