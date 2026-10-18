package task09;

import java.util.Optional;
import java.util.Set;
import model.Course;
import model.Student;

public class BlockedStudentsPolicy implements EnrollmentPolicy {

    private final Set<String> blocked;

    public BlockedStudentsPolicy(Set<String> blocked) {
        this.blocked = Set.copyOf(blocked);
    }

    @Override
    public Optional<String> violation(Student student, Course course) {
        return blocked.contains(student.name()) ? Optional.of("студент заблокирован") : Optional.empty();
    }
}
