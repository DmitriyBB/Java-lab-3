package task14;

public class Installments implements PaymentPolicy {

    private final PaymentPolicy base;
    private final int parts;

    public Installments(PaymentPolicy base, int parts) {
        this.base = base;
        this.parts = parts;
    }

    @Override
    public String name() {
        return base.name() + " x" + parts + " платежа";
    }

    @Override
    public int total(int weeks, int extraPerWeek) {
        return Math.max(0, base.total(weeks, extraPerWeek)) * 105 / 100;
    }
}
