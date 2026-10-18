package model;

import java.util.Set;

public record Student(String name, int age, Set<String> completed) {

    public Student {
        completed = Set.copyOf(completed);
    }
}
