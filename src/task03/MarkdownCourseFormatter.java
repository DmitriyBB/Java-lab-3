package task03;

import model.Course;
import task01.CourseFormatter;

public class MarkdownCourseFormatter implements CourseFormatter {

    @Override
    public String format(Course course) {
        return "| " + course.code() + " | " + course.title() + " | " + course.hours() + " |";
    }
}
