package task04;

public class EmailSender extends AbstractNotificationSender {

    @Override
    protected boolean supports(String recipient) {
        int at = recipient.indexOf('@');
        return at > 0 && at < recipient.length() - 1;
    }

    @Override
    protected String name() {
        return "email";
    }

    @Override
    protected void doSend(String recipient, String message) {
        System.out.println("[EMAIL] to=" + recipient + " subject=CourseHub body=" + message);
    }
}
