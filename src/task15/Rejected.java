package task15;

import java.util.List;

public record Rejected(List<String> reasons) implements Decision {

    public Rejected {
        reasons = List.copyOf(reasons);
    }

    public Rejected(String reason) {
        this(List.of(reason));
    }
}
