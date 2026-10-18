package task07;

public class EmailSender implements NotificationSender {

    private int failuresLeft;

    public EmailSender(int failuresBeforeSuccess) {
        this.failuresLeft = failuresBeforeSuccess;
    }

    @Override
    public void send(String to, String message) {
        if (failuresLeft > 0) {
            failuresLeft--;
            throw new IllegalStateException("SMTP timeout");
        }
        System.out.println("    [smtp] delivered to " + to + ": " + message);
    }
}
