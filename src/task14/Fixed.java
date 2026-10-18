package task14;

public class Fixed implements PaymentPolicy {

    private final int price;

    public Fixed(int price) {
        this.price = price;
    }

    @Override
    public String name() {
        return "Fixed " + price;
    }

    @Override
    public int total(int weeks, int extraPerWeek) {
        return price + weeks * extraPerWeek;
    }
}
