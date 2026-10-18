package task05;

@FunctionalInterface
public interface Formatter<T> {
    String format(T value);
}
