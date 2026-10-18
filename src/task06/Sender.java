package task06;

public class Sender {

    public String send(String to, String text) {
        return "Sender -> " + to + ": " + text;
    }

    protected int priority() {
        return 0;
    }

    public Object copy() {
        return new Sender();
    }
}
