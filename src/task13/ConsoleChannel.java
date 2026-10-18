package task13;

public class ConsoleChannel implements NotificationChannel {

    @Override
    public String send(String address, String text) {
        return "CONSOLE " + address + " | " + text;
    }
}
