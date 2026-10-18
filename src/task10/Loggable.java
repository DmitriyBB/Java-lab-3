package task10;

public interface Loggable {
    default String describe() {
        return "Loggable";
    }
}
