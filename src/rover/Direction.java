package rover;

public enum Direction {
    North("N") {
        @Override
        public Direction left() {
            return West;
        }

        @Override
        public Direction right() {
            return East;
        }
    }, East("E") {
        @Override
        public Direction left() {
            return North;
        }

        @Override
        public Direction right() {
            return South;
        }
    }, South("S") {
        @Override
        public Direction left() {
            return East;
        }

        @Override
        public Direction right() {
            return West;
        }
    }, West("W") {
        @Override
        public Direction left() {
            return South;
        }

        @Override
        public Direction right() {
            return North;
        }
    };

    private final String value;

    public static Direction fromString(String value) {
        for (Direction direction : Direction.values()) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return value;
    }

    public abstract Direction left();

    public abstract Direction right();

    Direction(String value) {
        this.value = value;
    }
}
