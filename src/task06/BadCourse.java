package task06;

public class BadCourse {

    private final String code;

    public BadCourse(String code) {
        this.code = code;
    }

    public boolean equals(BadCourse other) {
        return other != null && code.equals(other.code);
    }

    public int hashcode() {
        return code.hashCode();
    }

    public String tostring() {
        return "BadCourse(" + code + ")";
    }
}
