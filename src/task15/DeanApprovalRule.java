package task15;

import java.util.Set;

public class DeanApprovalRule implements CustomRule {

    private final Set<String> approved;

    public DeanApprovalRule(Set<String> approved) {
        this.approved = Set.copyOf(approved);
    }

    @Override
    public String name() {
        return "dean" + approved;
    }

    @Override
    public Decision evaluate(EnrollmentRequest request) {
        return approved.contains(request.student().name()) ? new Accepted() : new Rejected("нет одобрения декана");
    }
}
