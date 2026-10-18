package task08;

import model.Course;

public interface CourseRepository extends CourseReader {
    void save(Course course);
}
