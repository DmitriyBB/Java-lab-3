package task01;

import model.Course;

public class CompactCourseFormatter implements CourseFormatter {

    @Override
    public String format(Course course) {
        return course.code() + " " + course.title() + " (" + course.hours() + " ч)";
    }
}
