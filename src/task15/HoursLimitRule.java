package task15;

public class HoursLimitRule implements CustomRule {

    private final int maxHours;

    public HoursLimitRule(int maxHours) {
        this.maxHours = maxHours;
    }

    @Override
    public String name() {
        return "hours<=" + maxHours;
    }

    @Override
    public Decision evaluate(EnrollmentRequest request) {
        int hours = request.course().hours();
        return hours <= maxHours ? new Accepted() : new Rejected("курс " + hours + " ч > " + maxHours);
    }
}
