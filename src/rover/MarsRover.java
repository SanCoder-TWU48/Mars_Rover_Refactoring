package rover;

import java.util.*;
import java.util.List;

public class MarsRover {
    private static final Map<String, Runnable> SYMBOL_TO_COMMAND = new HashMap<>();
    private static final Map<Direction, Position> DIRECTION_TO_POINT = new HashMap<>();
    private Direction direction;
    private Position position;

    public MarsRover(int startingX, int startingY, String direction) {
        init();

        this.position = new Position(startingX, startingY);
        this.direction = Direction.fromString(direction);
    }

    private void init() {
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
        DIRECTION_TO_POINT.put(Direction.North, new Position(0, 1));
        DIRECTION_TO_POINT.put(Direction.East, new Position(1, 0));
        DIRECTION_TO_POINT.put(Direction.South, new Position(0, -1));
        DIRECTION_TO_POINT.put(Direction.West, new Position(-1, 0));
    }

    public String run(String input) {
        for (Runnable command : convertInputIntoCommands(input)) {
            command.run();
        }
        return asString();
    }

    private void move() {
        position.moveForward(DIRECTION_TO_POINT.get(direction));
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

    private static List<Runnable> convertInputIntoCommands(String input) {
        List<Runnable> commands = new ArrayList<>();
        for (String symbol : input.split("(?!^)")) {
            if (!SYMBOL_TO_COMMAND.containsKey(symbol)) {
                throw new IllegalArgumentException("Invalid command sequence: " + input);
            }
            commands.add(SYMBOL_TO_COMMAND.get(symbol));
        }
        return commands;
    }
}
