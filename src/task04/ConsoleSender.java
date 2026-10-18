package task04;

public class ConsoleSender extends AbstractNotificationSender {

    @Override
    protected String name() {
        return "console";
    }

    @Override
    protected void doSend(String recipient, String message) {
        System.out.println("[CONSOLE] " + recipient + ": " + message);
    }
}
