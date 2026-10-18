package task09;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import model.Course;
import model.Student;

public class PrerequisitesPolicy implements EnrollmentPolicy {

    @Override
    public Optional<String> violation(Student student, Course course) {
        Set<String> missing = new TreeSet<>(course.prerequisites());
        missing.removeAll(student.completed());
        if (missing.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of("не пройдены " + missing);
    }
}
