package task15;

import java.util.ArrayList;
import java.util.List;

public record AnyOf(List<Rule> rules) implements Rule {

    public AnyOf {
        rules = List.copyOf(rules);
    }

    @Override
    public Decision evaluate(EnrollmentRequest request) {
        List<String> reasons = new ArrayList<>();
        WaitListed waitListed = null;
        for (Rule rule : rules) {
            switch (rule.evaluate(request)) {
                case Accepted accepted -> {
                    return accepted;
                }
                case Rejected rejected -> reasons.addAll(rejected.reasons());
                case WaitListed w -> waitListed = waitListed == null ? w : waitListed;
            }
        }
        return waitListed != null ? waitListed : new Rejected(reasons);
    }
}
