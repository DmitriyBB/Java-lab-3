package task15;

import java.util.List;

public sealed interface Rule permits AgeRule, PrerequisiteRule, CapacityRule, AllOf, AnyOf, CustomRule {

    Decision evaluate(EnrollmentRequest request);

    static Rule allOf(Rule... rules) {
        return new AllOf(List.of(rules));
    }

    static Rule anyOf(Rule... rules) {
        return new AnyOf(List.of(rules));
    }
}
