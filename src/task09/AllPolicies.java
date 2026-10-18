package task09;

import java.util.List;
import java.util.Optional;
import model.Course;
import model.Student;

public class AllPolicies implements EnrollmentPolicy {

    private final List<EnrollmentPolicy> policies;

    public AllPolicies(EnrollmentPolicy... policies) {
        this.policies = List.of(policies);
    }

    @Override
    public Optional<String> violation(Student student, Course course) {
        for (EnrollmentPolicy policy : policies) {
            Optional<String> v = policy.violation(student, course);
            if (v.isPresent()) {
                return v;
            }
        }
        return Optional.empty();
    }
}
