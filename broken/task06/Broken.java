class Sender {

    public String send(String to, String text) {
        return "Sender";
    }

    protected int priority() {
        return 0;
    }

    public Object copy() {
        return new Sender();
    }
}

class SmsSender extends Sender {

    @Override
    public String send(String to, Object text) {
        return "SMS";
    }

    @Override
    int priority() {
        return 5;
    }

    @Override
    public long copy() {
        return 1L;
    }
}

class BadCourse {

    @Override
    public boolean equals(BadCourse other) {
        return true;
    }

    @Override
    public int hashcode() {
        return 1;
    }
}
