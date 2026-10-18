package task12;

public class Main {

    static String describe(EnrollmentResult result) {
        return switch (result) {
            case Accepted a -> "записан: " + a.studentName() + " на " + a.courseCode();
            case Rejected r -> "отказ: " + r.reason();
            case WaitListed w -> "лист ожидания, позиция " + w.position();
            case PendingApproval p -> "ждёт одобрения: " + p.approver();
        };
    }

    static boolean isFinal(EnrollmentResult result) {
        return switch (result) {
            case Accepted a -> true;
            case Rejected r -> true;
            case WaitListed w -> false;
            case PendingApproval p -> false;
        };
    }

    public static void main(String[] args) {
        EnrollmentResult[] results = {
                new Accepted("Анна", "JAVA-101"),
                new Rejected("нет prerequisites"),
                new WaitListed(3),
                new PendingApproval("декан")
        };
        for (EnrollmentResult result : results) {
            System.out.println(describe(result) + "  final=" + isFinal(result));
        }
    }
}
