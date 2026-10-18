package task14;

public class Subscription implements PaymentPolicy {

    private final int perWeek;

    public Subscription(int perWeek) {
        this.perWeek = perWeek;
    }

    @Override
    public String name() {
        return "Subscription " + perWeek + "/нед";
    }

    @Override
    public int total(int weeks, int extraPerWeek) {
        return weeks * (perWeek + extraPerWeek);
    }
}
