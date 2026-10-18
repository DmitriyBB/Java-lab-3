package task15;

import java.util.Set;
import java.util.TreeSet;

public record PrerequisiteRule() implements Rule {

    @Override
    public Decision evaluate(EnrollmentRequest request) {
        Set<String> missing = new TreeSet<>(request.course().prerequisites());
        missing.removeAll(request.student().completed());
        return missing.isEmpty() ? new Accepted() : new Rejected("не пройдены " + missing);
    }
}
