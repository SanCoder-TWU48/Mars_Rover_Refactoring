package rover;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarsRover {

    private static final List<String> VALID_COMMANDS = Arrays.asList("L", "R", "M");

    private static final Map<String, Runnable> SYMBOL_TO_COMMAND;

    static {
        SYMBOL_TO_COMMAND = new HashMap<>();
    }

    private Direction direction;
    private Point position;

    public MarsRover(int startingX, int startingY, String direction) {
        SYMBOL_TO_COMMAND.put("M", new Runnable() {
            @Override
            public void run() {
                move();
            }
        });
        SYMBOL_TO_COMMAND.put("L", new Runnable() {
            @Override
            public void run() {
                turnLeft();
            }
        });
        SYMBOL_TO_COMMAND.put("R", new Runnable() {
            @Override
            public void run() {
                turnRight();
            }
        });

        this.position = new Point(startingX, startingY);
        this.direction = Direction.fromString(direction);
    }

    public String run(String input) {
        String[] commands = convertInputIntoCommands(input);

        for (String command : commands) {
            SYMBOL_TO_COMMAND.get(command).run();
        }

        return asString();
    }

    private void move() {
        switch (direction) {
            case North:
                position.translate(0, 1);
                break;
            case East:
                position.translate(1, 0);
                break;
            case South:
                position.translate(0, -1);
                break;
            case West:
                position.translate(-1, 0);
                break;
        }
    }

    private String asString() {
        return String.format("%.0f %.0f %s", position.getX(), position.getY(), direction);
    }

    private void turnLeft() {
        direction = direction.left();
    }

    private void turnRight() {
        direction = direction.right();
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
