package task03;

import model.Course;
import task01.CourseFormatter;

public class CoursePrinter {

    private final CourseFormatter[] formatters;

    public CoursePrinter(CourseFormatter[] formatters) {
        this.formatters = formatters.clone();
    }

    public void printAll(Course course) {
        for (CourseFormatter formatter : formatters) {
            System.out.println(formatter.format(course));
            System.out.println("---");
        }
    }
}
