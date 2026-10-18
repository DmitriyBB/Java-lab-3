package task03;

import model.Course;
import task01.CompactCourseFormatter;
import task01.CourseFormatter;
import task01.DetailedCourseFormatter;

public class Main {

    public static void main(String[] args) {
        Course course = new Course("OOP-201", "Интерфейсы и полиморфизм", 36);

        CourseFormatter[] formatters = {
                new CompactCourseFormatter(),
                new DetailedCourseFormatter(),
                new MarkdownCourseFormatter(),
                c -> c.title().toUpperCase()
        };

        new CoursePrinter(formatters).printAll(course);
        System.out.println("CoursePrinter импортирует только CourseFormatter и Course.");
        System.out.println("Markdown и лямбда добавлены без изменения цикла в printAll.");
    }
}
