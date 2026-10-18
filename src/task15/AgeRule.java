package task15;

public record AgeRule(int minAge) implements Rule {

    @Override
    public Decision evaluate(EnrollmentRequest request) {
        int age = request.student().age();
        return age >= minAge ? new Accepted() : new Rejected("возраст " + age + " < " + minAge);
    }
}
