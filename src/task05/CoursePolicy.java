package task05;

import model.Course;

@FunctionalInterface
public interface CoursePolicy {
    boolean allows(Course course);
}
