package task11;

public class StrictColoredPoint extends StrictPoint {

    private final String color;

    public StrictColoredPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && color.equals(((StrictColoredPoint) o).color);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + color.hashCode();
    }
}
