package task15;

import model.Course;

public record CapacityRule(boolean allowWaitList) implements Rule {

    @Override
    public Decision evaluate(EnrollmentRequest request) {
        Course course = request.course();
        if (!course.isFull()) {
            return new Accepted();
        }
        if (allowWaitList) {
            return new WaitListed(course.enrolled() - course.capacity() + 1);
        }
        return new Rejected("нет мест");
    }
}
