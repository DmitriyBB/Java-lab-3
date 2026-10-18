package task09;

import java.util.Optional;
import model.Course;
import model.Student;

public class CapacityPolicy implements EnrollmentPolicy {

    @Override
    public Optional<String> violation(Student student, Course course) {
        if (course.isFull()) {
            return Optional.of("нет мест " + course.enrolled() + "/" + course.capacity());
        }
        return Optional.empty();
    }
}
