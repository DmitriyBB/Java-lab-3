package task09;

import java.util.Optional;
import java.util.Set;
import model.Course;
import model.Student;

public class Main {

    public static void main(String[] args) {
        Course oop = new Course("OOP-201", "ООП", 36, 2, 1, Set.of("JAVA-101"));
        Course full = new Course("ALG-301", "Алгоритмы", 40, 2, 2, Set.of());
        Student anna = new Student("Анна", 19, Set.of("JAVA-101"));
        Student boris = new Student("Борис", 16, Set.of());
        Student vera = new Student("Вера", 20, Set.of());

        EnrollmentService[] services = {
                new EnrollmentService(new AgePolicy(18)),
                new EnrollmentService(new PrerequisitesPolicy()),
                new EnrollmentService(new CapacityPolicy()),
                new EnrollmentService(new AllPolicies(new AgePolicy(18), new PrerequisitesPolicy(), new CapacityPolicy()))
        };
        String[] names = {"age", "prerequisites", "capacity", "all"};
        for (int i = 0; i < services.length; i++) {
            System.out.println("[" + names[i] + "]");
            for (Student s : new Student[]{anna, boris, vera}) {
                System.out.println("  " + services[i].enroll(s, oop));
            }
            System.out.println("  " + services[i].enroll(anna, full));
        }

        System.out.println("[новые политики без изменения EnrollmentService]");
        EnrollmentService strict = new EnrollmentService(new AllPolicies(
                new BlockedStudentsPolicy(Set.of("Анна")),
                (s, c) -> c.hours() > 38 ? Optional.of("курс длиннее 38 ч") : Optional.empty()));
        System.out.println("  " + strict.enroll(anna, oop));
        System.out.println("  " + strict.enroll(vera, oop));
        System.out.println("  " + strict.enroll(vera, full));
    }
}
