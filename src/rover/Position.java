package rover;

import java.awt.*;

public class Position extends Point {
    public Position(int x, int y) {
        super(x, y);
    }

    public void moveForward(Point point) {
        super.translate(point.x, point.y);
    }
}
