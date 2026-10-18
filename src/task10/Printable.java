package task10;

public interface Printable {
    default String describe() {
        return "Printable";
    }
}
