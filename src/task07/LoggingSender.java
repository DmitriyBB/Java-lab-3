package task07;

public class LoggingSender implements NotificationSender {

    private final NotificationSender inner;

    public LoggingSender(NotificationSender inner) {
        this.inner = inner;
    }

    @Override
    public void send(String to, String message) {
        System.out.println("    [log] send to " + to);
        try {
            inner.send(to, message);
            System.out.println("    [log] ok");
        } catch (RuntimeException e) {
            System.out.println("    [log] failed: " + e.getMessage());
            throw e;
        }
    }
}
