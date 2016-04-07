package rover;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MarsRover {
    private static final List<String> VALID_COMMANDS = Arrays.asList("L", "R", "M");
    private static final List<String> DIRECTIONS = Arrays.asList("N", "E", "S", "W");

    private String direction;
    private Point position;

    public MarsRover(int startingX, int startingY, String direction) {
        this.position = new Point(startingX, startingY);
        this.direction = direction;
    }

    public String run(String input) {
        String[] commands = convertInputIntoCommands(input);

        for (String command : commands) {
            if (command.equals("M")) {
                move();
            } else if (command.equals("R")) {
                turnRight();
            } else if (command.equals("L")) {
                turnLeft();
            }
        }

        return asString();
    }

    private void move() {
        if (direction.equals("N")) {
            position.translate(0, 1);
        } else if (direction.equals("S")) {
            position.translate(0, -1);
        } else if (direction.equals("E")) {
            position.translate(1, 0);
        } else if (direction.equals("W")) {
            position.translate(-1, 0);
        }
    }

    private String asString() {
        return String.format("%.0f %.0f %s", position.getX(), position.getY(), direction);
    }

    private void turnLeft() {
        direction = DIRECTIONS.get((DIRECTIONS.indexOf(direction) + 3) % DIRECTIONS.size());
    }

    private void turnRight() {
        direction = DIRECTIONS.get((DIRECTIONS.indexOf(direction) + 1) % DIRECTIONS.size());
    }

    private static String[] convertInputIntoCommands(String input) {
        String[] commandArray = input.split("(?!^)");

        validateCommands(input, commandArray);

        return commandArray;
    }

    private static void validateCommands(String input, String[] commandArray) {
        for (String command : commandArray) {
            if (!VALID_COMMANDS.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + input);
            }
        }
    }


}
