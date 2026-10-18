package task05;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import model.Course;

public class Main {

    record Tag(String id) implements Identifiable {}

    public static void main(String[] args) {
        Clock clock = Clock.fixed(Instant.parse("2026-10-18T10:00:00Z"), ZoneOffset.UTC);
        Course course = new Course("JAVA-101", "Основы Java", 48);

        Identifiable[] things = {new Tag("java"), new Enrollment("E-1", "Анна", clock)};
        for (Identifiable thing : things) {
            System.out.println("Identifiable: " + thing.id() + " -> " + thing);
        }

        CoursePolicy shortOnly = c -> c.hours() <= 40;
        CoursePolicy hasSeats = c -> !c.isFull();
        System.out.println("CoursePolicy shortOnly: " + shortOnly.allows(course) + ", hasSeats: " + hasSeats.allows(course));

        Formatter<Course> formatter = c -> c.code() + " / " + c.title();
        System.out.println("Formatter: " + formatter.format(course));

        Enrollment a = new Enrollment("E-2", "Борис", clock);
        Enrollment b = new Enrollment("E-2", "Борис", Clock.systemUTC());
        System.out.println("BaseEntity equals по id: " + a.equals(b));
        System.out.println("ClockProvider не нужен: java.time.Clock, в тесте Clock.fixed -> " + a.createdAt());
    }
}
