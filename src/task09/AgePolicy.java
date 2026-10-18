package task09;

import java.util.Optional;
import model.Course;
import model.Student;

public class AgePolicy implements EnrollmentPolicy {

    private final int minAge;

    public AgePolicy(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public Optional<String> violation(Student student, Course course) {
        if (student.age() < minAge) {
            return Optional.of("возраст " + student.age() + " < " + minAge);
        }
        return Optional.empty();
    }
}
