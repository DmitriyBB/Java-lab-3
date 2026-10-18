package task02;

public class EmailSender extends NotificationSender {

    @Override
    public String send(Object message) {
        return "EmailSender.send(Object)";
    }

    public String send(Integer message) {
        return "EmailSender.send(Integer)";
    }
}
