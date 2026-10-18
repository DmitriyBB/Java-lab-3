package task07;

public class MetricsSender implements NotificationSender {

    private final NotificationSender inner;
    private int calls;
    private int failures;

    public MetricsSender(NotificationSender inner) {
        this.inner = inner;
    }

    @Override
    public void send(String to, String message) {
        calls++;
        try {
            inner.send(to, message);
        } catch (RuntimeException e) {
            failures++;
            throw e;
        }
    }

    public String report() {
        return "calls=" + calls + ", failures=" + failures;
    }
}
