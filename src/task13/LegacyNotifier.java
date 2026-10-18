package task13;

public class LegacyNotifier {

    private final String notificationType;

    public LegacyNotifier(String notificationType) {
        this.notificationType = notificationType;
    }

    public String send(String address, String text) {
        switch (notificationType) {
            case "email":
                if (!address.contains("@")) {
                    throw new IllegalArgumentException("bad email");
                }
                return "EMAIL " + address + " | Subject: CourseHub | " + text;
            case "sms":
                if (!address.startsWith("+")) {
                    throw new IllegalArgumentException("bad phone");
                }
                return "SMS " + address + " | " + (text.length() > 20 ? text.substring(0, 20) : text);
            case "sms-long":
                if (!address.startsWith("+")) {
                    throw new IllegalArgumentException("bad phone");
                }
                return "SMS " + address + " | " + (text.length() > 70 ? text.substring(0, 70) : text);
            case "console":
                return "CONSOLE " + address + " | " + text;
            default:
                throw new IllegalStateException("unknown type " + notificationType);
        }
    }
}
