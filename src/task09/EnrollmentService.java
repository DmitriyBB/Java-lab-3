package task09;

import model.Course;
import model.Student;

public class EnrollmentService {

    private final EnrollmentPolicy policy;

    public EnrollmentService(EnrollmentPolicy policy) {
        this.policy = policy;
    }

    public String enroll(Student student, Course course) {
        return policy.violation(student, course)
                .map(reason -> student.name() + " -> " + course.code() + ": отказ (" + reason + ")")
                .orElse(student.name() + " -> " + course.code() + ": записан");
    }
}
