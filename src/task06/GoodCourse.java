package task06;

public class GoodCourse {

    private final String code;

    public GoodCourse(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GoodCourse other && code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return "GoodCourse(" + code + ")";
    }
}
