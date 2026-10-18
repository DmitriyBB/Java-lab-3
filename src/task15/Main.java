package task15;

import java.util.Set;
import model.Course;
import model.Student;

public class Main {

    static String show(Decision decision) {
        return switch (decision) {
            case Accepted a -> "ACCEPTED";
            case Rejected r -> "REJECTED " + r.reasons();
            case WaitListed w -> "WAITLISTED #" + w.position();
        };
    }

    public static void main(String[] args) {
        Course oop = new Course("OOP-201", "ООП", 36, 2, 1, Set.of("JAVA-101"));
        Course alg = new Course("ALG-301", "Алгоритмы", 40, 2, 2, Set.of("JAVA-101"));
        Course ml = new Course("ML-401", "Машинное обучение", 90, 10, 0, Set.of());
        Student anna = new Student("Анна", 19, Set.of("JAVA-101"));
        Student boris = new Student("Борис", 16, Set.of());
        Student vera = new Student("Вера", 20, Set.of());

        System.out.println("1) dynamic dispatch: один вызов rule.evaluate(request), разный код");
        EnrollmentRequest request = new EnrollmentRequest(boris, alg);
        Rule[] rules = {
                new AgeRule(18),
                new PrerequisiteRule(),
                new CapacityRule(true),
                new CapacityRule(false),
                new DeanApprovalRule(Set.of("Борис")),
                new HoursLimitRule(60),
                Rule.allOf(new AgeRule(18), new CapacityRule(true)),
                Rule.anyOf(new AgeRule(18), new DeanApprovalRule(Set.of("Борис")))
        };
        for (Rule rule : rules) {
            System.out.printf("   %-18s %-36s %s%n", rule.getClass().getSimpleName(), RuleExplainer.explain(rule), show(rule.evaluate(request)));
        }

        System.out.println();
        Rule policy = Rule.allOf(
                Rule.anyOf(new DeanApprovalRule(Set.of("Вера")), Rule.allOf(new AgeRule(18), new PrerequisiteRule())),
                new CapacityRule(true),
                new HoursLimitRule(60));
        System.out.println("2) правило CourseHub: " + RuleExplainer.explain(policy));
        EnrollmentService service = new EnrollmentService(policy);
        for (Student student : new Student[]{anna, boris, vera}) {
            for (Course course : new Course[]{oop, alg, ml}) {
                System.out.printf("   %-6s %-8s %s%n", student.name(), course.code(), show(service.enroll(student, course)));
            }
        }
    }
}
