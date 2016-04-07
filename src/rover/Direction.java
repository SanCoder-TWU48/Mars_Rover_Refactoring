package rover;

import java.util.List;

import static java.util.Arrays.asList;

public enum Direction {
    North("N"), East("E"), South("S"), West("W");

    private static final List<Direction> directions = asList(North, East, South, West);

    private final String value;

    public static Direction fromString(String value) {
        for (Direction direction : Direction.values()) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

    public Direction left() {
        return directions.get((directions.indexOf(this) + 3) % directions.size());
    }

    public Direction right() {
        return directions.get((directions.indexOf(this) + 1) % directions.size());
    }

    @Override
    public String toString() {
        return value;
    }

    Direction(String value) {
        this.value = value;
    }
}
