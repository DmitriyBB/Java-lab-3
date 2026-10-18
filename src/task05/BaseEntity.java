package task05;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

public abstract class BaseEntity implements Identifiable {

    private final String id;
    private final Instant createdAt;

    protected BaseEntity(String id, Clock clock) {
        this.id = Objects.requireNonNull(id);
        this.createdAt = clock.instant();
    }

    @Override
    public final String id() {
        return id;
    }

    public final Instant createdAt() {
        return createdAt;
    }

    @Override
    public final boolean equals(Object o) {
        return o instanceof BaseEntity other && getClass() == other.getClass() && id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getClass(), id);
    }
}
