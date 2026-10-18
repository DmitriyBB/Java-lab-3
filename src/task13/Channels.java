package task13;

public final class Channels {

    private Channels() {
    }

    public static NotificationChannel fromConfig(String line) {
        String[] parts = line.split(":");
        return switch (parts[0]) {
            case "email" -> new EmailChannel(parts.length > 1 ? parts[1] : "CourseHub");
            case "sms" -> new SmsChannel(parts.length > 1 ? Integer.parseInt(parts[1]) : 20);
            case "console" -> new ConsoleChannel();
            default -> throw new IllegalArgumentException("unknown channel " + parts[0]);
        };
    }
}
