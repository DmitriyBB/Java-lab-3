package task15;

import java.util.stream.Collectors;

public final class RuleExplainer {

    private RuleExplainer() {
    }

    public static String explain(Rule rule) {
        return switch (rule) {
            case AgeRule age -> "age>=" + age.minAge();
            case PrerequisiteRule prerequisites -> "prerequisites";
            case CapacityRule capacity -> capacity.allowWaitList() ? "capacity+waitlist" : "capacity";
            case AllOf all -> all.rules().stream().map(RuleExplainer::explain).collect(Collectors.joining(" AND ", "(", ")"));
            case AnyOf any -> any.rules().stream().map(RuleExplainer::explain).collect(Collectors.joining(" OR ", "(", ")"));
            case CustomRule custom -> custom.name();
        };
    }
}
