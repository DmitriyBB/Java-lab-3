package task07;

public class RetryingSender implements NotificationSender {

    private final NotificationSender inner;
    private final int maxAttempts;

    public RetryingSender(NotificationSender inner, int maxAttempts) {
        this.inner = inner;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void send(String to, String message) {
        RuntimeException last = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                inner.send(to, message);
                return;
            } catch (RuntimeException e) {
                last = e;
            }
        }
        throw last;
    }
}
