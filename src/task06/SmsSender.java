package task06;

public class SmsSender extends Sender {

    @Override
    public String send(String to, String text) {
        return "SMS -> " + to + ": " + text;
    }

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public SmsSender copy() {
        return new SmsSender();
    }
}
