package task13;

public class EmailChannel implements NotificationChannel {

    private final String subject;

    public EmailChannel(String subject) {
        this.subject = subject;
    }

    @Override
    public String send(String address, String text) {
        if (!address.contains("@")) {
            throw new IllegalArgumentException("bad email");
        }
        return "EMAIL " + address + " | Subject: " + subject + " | " + text;
    }
}
