package org.simulator;

import org.simulator.entities.Direction;
import org.simulator.entities.Position;
import org.simulator.entities.Robot;

import java.util.Scanner;

import static java.util.Objects.isNull;

public class Simulator {


    public static void main(String[] args) {
        final Simulator simulator = new Simulator();
        simulator.startSimulation();
    }

    private void startSimulation() {

        try (Scanner sc = new Scanner(System.in)) { // auto closable

            boolean placed = false;

            final Robot robot = new Robot();
            final TableTop tableTop = new TableTop(robot);

            System.out.println("Robot Simulator");
            System.out.println("Enter a command, Valid commands are:");
            System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

            while (true) {

                final String input = sc.nextLine().trim().toUpperCase(); // read user input

                // Exit the program
                if (Commands.EXIT.name().equals(input)) // exit the loop and program
                    break;

                final String[] arg = input.split(" ");

                Commands command = identifyCommand(arg[0]);
                if (isNull(command)) // continue on invalid command
                    continue;

                // place the Robot on the table
                if (Commands.PLACE.equals(command) && arg.length == 2) {

                    placed = placeOnTable(arg[1], tableTop);

                } else if (placed) { // execute commands only if Robot is placed on the table

                    if (Commands.REPORT.equals(command)) // report
                        System.out.println(String.format("Output: %s", tableTop.stats()));
                    else
                        tableTop.execute(command);
                }
            } // while loop

        } // try
    }

    private Commands identifyCommand(String cmd) {

        Commands command = null;
        try {

            command = Commands.valueOf(cmd); // get command from input

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid command. Try again.");
        }

        return command;
    }

    private static boolean placeOnTable(final String input, final TableTop tableTop) {

        final String[] params = input.split(",");
        int x;
        int y;
        Direction direction;

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
