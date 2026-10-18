package task05;

import java.time.Clock;

public class Enrollment extends BaseEntity {

    private final String studentName;

    public Enrollment(String id, String studentName, Clock clock) {
        super(id, clock);
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Enrollment[" + id() + ", " + studentName + ", " + createdAt() + "]";
    }
}
