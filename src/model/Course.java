package model;

import java.util.Set;

public record Course(String code, String title, int hours, int capacity, int enrolled, Set<String> prerequisites) {

    public Course {
        prerequisites = Set.copyOf(prerequisites);
    }

    public Course(String code, String title, int hours) {
        this(code, title, hours, 30, 0, Set.of());
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }
}
