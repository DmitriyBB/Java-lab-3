package task04;

public abstract class AbstractNotificationSender {

    public final void send(String recipient, String message) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("адресат не указан");
        }
        String normalized = recipient.trim();
        if (!supports(normalized)) {
            throw new IllegalArgumentException(name() + ": неверный адресат '" + normalized + "'");
        }
        doSend(normalized, message);
    }

    protected boolean supports(String recipient) {
        return true;
    }

    protected abstract String name();

    protected abstract void doSend(String recipient, String message);
}
