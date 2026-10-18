package task11;

public class ColoredPoint extends Point {

    private final String color;

    public ColoredPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ColoredPoint cp && x == cp.x && y == cp.y && color.equals(cp.color);
    }

    @Override
    public String toString() {
        return "ColoredPoint(" + x + "," + y + "," + color + ")";
    }
}
