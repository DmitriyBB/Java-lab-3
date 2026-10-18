package task01;

import model.Course;

public class Main {

    public static void main(String[] args) {
        Course course = new Course("JAVA-101", "Основы Java", 48);

        CourseFormatter formatter = new CompactCourseFormatter();
        System.out.println("compile-time: CourseFormatter, runtime: " + formatter.getClass().getSimpleName());
        System.out.println(formatter.format(course));

        formatter = new DetailedCourseFormatter();
        System.out.println("compile-time: CourseFormatter, runtime: " + formatter.getClass().getSimpleName());
        System.out.println(formatter.format(course));

        System.out.println();
        System.out.println("Компилятор знает только тип переменной CourseFormatter: метод format(Course) существует и вернёт String.");
        System.out.println("Какая реализация format выполнится, JVM решает во время выполнения по классу объекта.");
    }
}
