package task14;

public class Free implements PaymentPolicy {

    @Override
    public String name() {
        return "Free";
    }

    @Override
    public int total(int weeks, int extraPerWeek) {
        return 0;
    }
}
