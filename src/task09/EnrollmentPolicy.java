package task09;

import java.util.Optional;
import model.Course;
import model.Student;

@FunctionalInterface
public interface EnrollmentPolicy {
    Optional<String> violation(Student student, Course course);
}
