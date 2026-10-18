package task15;

import model.Course;
import model.Student;

public class EnrollmentService {

    private final Rule rule;

    public EnrollmentService(Rule rule) {
        this.rule = rule;
    }

    public Decision enroll(Student student, Course course) {
        return rule.evaluate(new EnrollmentRequest(student, course));
    }
}
