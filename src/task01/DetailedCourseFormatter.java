package task01;

import model.Course;

public class DetailedCourseFormatter implements CourseFormatter {

    @Override
    public String format(Course course) {
        return "Курс: " + course.title()
                + "\n  код: " + course.code()
                + "\n  часов: " + course.hours()
                + "\n  мест: " + course.enrolled() + "/" + course.capacity();
    }
}
