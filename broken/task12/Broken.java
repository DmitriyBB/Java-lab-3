sealed interface EnrollmentResult permits Accepted, Rejected, WaitListed, PendingApproval {}

record Accepted(String studentName) implements EnrollmentResult {}

record Rejected(String reason) implements EnrollmentResult {}

record WaitListed(int position) implements EnrollmentResult {}

record PendingApproval(String approver) implements EnrollmentResult {}

class Broken {

    static String describe(EnrollmentResult result) {
        return switch (result) {
            case Accepted a -> "записан";
            case Rejected r -> "отказ";
            case WaitListed w -> "ожидание";
        };
    }

    static boolean isFinal(EnrollmentResult result) {
        return switch (result) {
            case Accepted a -> true;
            case Rejected r -> true;
            case WaitListed w -> false;
        };
    }
}

record Deferred() implements EnrollmentResult {}
