package task13;

public class SmsChannel implements NotificationChannel {

    private final int maxLength;

    public SmsChannel(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String send(String address, String text) {
        if (!address.startsWith("+")) {
            throw new IllegalArgumentException("bad phone");
        }
        return "SMS " + address + " | " + (text.length() > maxLength ? text.substring(0, maxLength) : text);
    }
}
