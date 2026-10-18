package task02;

public class ConsoleSender extends NotificationSender {

    @Override
    public String send(String message) {
        return "ConsoleSender.send(String)";
    }
}
